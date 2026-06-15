package pl.polsl.league.match;

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
@RequestMapping(path = "/match")
public class MatchController {

	@Autowired
	MatchRepository matchRepository;

	@PostMapping
	public @ResponseBody Match addMatch(@RequestBody Match Match) {
		return matchRepository.save(Match);
	}

	@GetMapping
	public @ResponseBody Iterable<Match> getAllMatchs() {
		return matchRepository.findAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody Match getMatch(@PathVariable Integer id) {
		return matchRepository.findById(id).orElse(null);
	}

	@PutMapping
	public @ResponseBody Match updateMatch(@RequestBody Match match) {
		return matchRepository.save(match);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteMatch(@PathVariable Integer id) {
		matchRepository.deleteById(id);
	}

}
