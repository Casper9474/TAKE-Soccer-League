package pl.polsl.league.season;

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
@RequestMapping(path = "/season")
public class SeasonController {

	@Autowired
	SeasonRepository seasonRepository;

	@PostMapping
	public @ResponseBody SeasonDTO addSeason(@RequestBody Season season) {
		Season savedSeason = seasonRepository.save(season);
		return new SeasonDTO(savedSeason);
	}

	@GetMapping
	public @ResponseBody CollectionModel<SeasonDTO> getAllSeasons() {
		List<SeasonDTO> seasonsDTO = StreamSupport.stream(seasonRepository.findAll().spliterator(), false)
				.map(SeasonDTO::new).collect(Collectors.toList());
		return CollectionModel.of(seasonsDTO);
	}

	@GetMapping("/{id}")
	public @ResponseBody SeasonDTO getSeason(@PathVariable Integer id) {
		Season season = seasonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
		return new SeasonDTO(season);
	}

	@PutMapping
	public @ResponseBody SeasonDTO updateSeason(@RequestBody Season season) {
		Season updatedSeason = seasonRepository.save(season);
		return new SeasonDTO(updatedSeason);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteSeason(@PathVariable Integer id) {
		if (!seasonRepository.existsById(id)) {
			throw new ResourceNotFoundException("Record not found with id: " + id);
		}
		seasonRepository.deleteById(id);
	}
}