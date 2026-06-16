package pl.polsl.league.participant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/participant")
public class ParticipantController {

	@Autowired
	ParticipantRepository participantRepository;

	@PostMapping
	public @ResponseBody ParticipantDTO addParticipant(@RequestBody Participant participant) {
		Participant savedParticipant = participantRepository.save(participant);
		return new ParticipantDTO(savedParticipant);
	}

	@GetMapping
	public @ResponseBody CollectionModel<ParticipantDTO> getAllParticipants() {
		List<ParticipantDTO> participantsDTO = StreamSupport.stream(participantRepository.findAll().spliterator(), false)
				.map(ParticipantDTO::new)
				.collect(Collectors.toList());
		return CollectionModel.of(participantsDTO);
	}

	@GetMapping("/{id}")
	public @ResponseBody ParticipantDTO getParticipant(@PathVariable Integer id) {
		Participant participant = participantRepository.findById(id).orElse(null);
		return participant != null ? new ParticipantDTO(participant) : null;
	}

	@PutMapping
	public @ResponseBody ParticipantDTO updateParticipant(@RequestBody Participant participant) {
		Participant updatedParticipant = participantRepository.save(participant);
		return new ParticipantDTO(updatedParticipant);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteParticipant(@PathVariable Integer id) {
		participantRepository.deleteById(id);
	}
}