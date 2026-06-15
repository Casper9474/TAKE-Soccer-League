package pl.polsl.league.participant;

import org.springframework.beans.factory.annotation.Autowired;
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
	public @ResponseBody Participant addParticipant(@RequestBody Participant Participant) {
		return participantRepository.save(Participant);
	}

	@GetMapping
	public @ResponseBody Iterable<Participant> getAllParticipants() {
		return participantRepository.findAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody Participant getParticipant(@PathVariable Integer id) {
		return participantRepository.findById(id).orElse(null);
	}

	@PutMapping
	public @ResponseBody Participant updateParticipant(@RequestBody Participant participant) {
		return participantRepository.save(participant);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteParticipant(@PathVariable Integer id) {
		participantRepository.deleteById(id);
	}
}
