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

import pl.polsl.league.error.ResourceNotFoundException;

@Controller
@RequestMapping(path = "/participant")
public class ParticipantController {

	@Autowired
	ParticipantRepository participantRepository;
	
	@GetMapping("/league-table/{seasonId}")
	public @ResponseBody CollectionModel<ParticipantDTO> getLeagueTable(@PathVariable Integer seasonId) {
	    List<ParticipantDTO> participantsDTO = participantRepository.findBySeasonIdOrderByPointsDesc(seasonId).stream()
	            .map(ParticipantDTO::new)
	            .collect(Collectors.toList());
	    return CollectionModel.of(participantsDTO);
	}

	@PostMapping
	public @ResponseBody ParticipantDTO addParticipant(@RequestBody Participant participant) {
		Participant savedParticipant = participantRepository.save(participant);
		return new ParticipantDTO(savedParticipant);
	}

	@GetMapping
	public @ResponseBody CollectionModel<ParticipantDTO> getAllParticipants() {
		List<ParticipantDTO> participantsDTO = StreamSupport
				.stream(participantRepository.findAll().spliterator(), false).map(ParticipantDTO::new)
				.collect(Collectors.toList());
		return CollectionModel.of(participantsDTO);
	}

	@GetMapping("/{id}")
	public @ResponseBody ParticipantDTO getParticipant(@PathVariable Integer id) {
		Participant participant = participantRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
		return new ParticipantDTO(participant);
	}

	@PutMapping("/{id}")
	public @ResponseBody ParticipantDTO updateParticipant(@PathVariable Integer id, @RequestBody Participant participant) {
		if (!participantRepository.existsById(id)) {
			throw new ResourceNotFoundException("Record not found with id: " + id);
		}
		participant.setId(id);
		Participant updatedParticipant = participantRepository.save(participant);
		return new ParticipantDTO(updatedParticipant);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteParticipant(@PathVariable Integer id) {
		if (!participantRepository.existsById(id)) {
			throw new ResourceNotFoundException("Record not found with id: " + id);
		}
		participantRepository.deleteById(id);
	}
}