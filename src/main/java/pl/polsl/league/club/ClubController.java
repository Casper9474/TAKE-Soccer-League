package pl.polsl.league.club;

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
@RequestMapping(path = "/club")
public class ClubController {
	
	@Autowired
	ClubRepository clubRepository;
	
	@PostMapping
    public @ResponseBody Club addClub(@RequestBody Club Club) {
        return clubRepository.save(Club);
    }

    @GetMapping
    public @ResponseBody Iterable<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Club getClub(@PathVariable Integer id) {
        return clubRepository.findById(id).orElse(null);
    }

	@PutMapping
	public @ResponseBody Club updateClub(@RequestBody Club club) {
		return clubRepository.save(club);
	}

}
