package pl.polsl.league.season;

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
@RequestMapping(path = "/season")
public class SeasonController {

	@Autowired
	SeasonRepository seasonRepository;

	@PostMapping
	public @ResponseBody Season addSeason(@RequestBody Season Season) {
		return seasonRepository.save(Season);
	}

	@GetMapping
	public @ResponseBody Iterable<Season> getAllSeasons() {
		return seasonRepository.findAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody Season getSeason(@PathVariable Integer id) {
		return seasonRepository.findById(id).orElse(null);
	}

	@PutMapping
	public @ResponseBody Season updateSeason(@RequestBody Season season) {
		return seasonRepository.save(season);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteSeason(@PathVariable Integer id) {
		seasonRepository.deleteById(id);
	}
}
