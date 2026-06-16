package pl.polsl.league.match;

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
@RequestMapping(path = "/match")
public class MatchController {

	@Autowired
	MatchRepository matchRepository;

	@PostMapping
	public @ResponseBody MatchDTO addMatch(@RequestBody Match match) {
		Match savedMatch = matchRepository.save(match);
		return new MatchDTO(savedMatch);
	}

	@GetMapping
	public @ResponseBody CollectionModel<MatchDTO> getAllMatchs() {
		List<MatchDTO> matchsDTO = StreamSupport.stream(matchRepository.findAll().spliterator(), false)
				.map(MatchDTO::new)
				.collect(Collectors.toList());
		return CollectionModel.of(matchsDTO);
	}

	@GetMapping("/{id}")
	public @ResponseBody MatchDTO getMatch(@PathVariable Integer id) {
		Match match = matchRepository.findById(id).orElse(null);
		return match != null ? new MatchDTO(match) : null;
	}

	@PutMapping
	public @ResponseBody MatchDTO updateMatch(@RequestBody Match match) {
		Match updatedMatch = matchRepository.save(match);
		return new MatchDTO(updatedMatch);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteMatch(@PathVariable Integer id) {
		matchRepository.deleteById(id);
	}
}