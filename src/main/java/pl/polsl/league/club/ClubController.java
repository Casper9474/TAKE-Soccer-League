package pl.polsl.league.club;

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
@RequestMapping(path = "/club")
public class ClubController {

	@Autowired
	ClubRepository clubRepository;

	@PostMapping
	public @ResponseBody ClubDTO addClub(@RequestBody Club club) {
		Club savedClub = clubRepository.save(club);
		return new ClubDTO(savedClub);
	}

	@GetMapping
	public @ResponseBody CollectionModel<ClubDTO> getAllClubs() {
		List<ClubDTO> clubsDTO = StreamSupport.stream(clubRepository.findAll().spliterator(), false).map(ClubDTO::new)
				.collect(Collectors.toList());
		return CollectionModel.of(clubsDTO);
	}

	@GetMapping("/{id}")
	public @ResponseBody ClubDTO getClub(@PathVariable Integer id) {
		Club club = clubRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
		return new ClubDTO(club);
	}

	@PutMapping("/{id}")
	public @ResponseBody ClubDTO updateClub(@PathVariable Integer id, @RequestBody Club club) {
		if (!clubRepository.existsById(id)) {
			throw new ResourceNotFoundException("Record not found with id: " + id);
		}
		club.setId(id);
		Club updatedClub = clubRepository.save(club);
		return new ClubDTO(updatedClub);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteClub(@PathVariable Integer id) {
		if (!clubRepository.existsById(id)) {
			throw new ResourceNotFoundException("Record not found with id: " + id);
		}
		clubRepository.deleteById(id);
	}
}