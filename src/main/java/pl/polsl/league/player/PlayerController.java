package pl.polsl.league.player;

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
@RequestMapping(path = "/player")
public class PlayerController {

	@Autowired
	PlayerRepository playerRepository;

	@PostMapping
	public @ResponseBody Player addPlayer(@RequestBody Player Player) {
		return playerRepository.save(Player);
	}

	@GetMapping
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody Player getPlayer(@PathVariable Integer id) {
		return playerRepository.findById(id).orElse(null);
	}

	@PutMapping
	public @ResponseBody Player updatePlayer(@RequestBody Player player) {
		return playerRepository.save(player);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deletePlayer(@PathVariable Integer id) {
		playerRepository.deleteById(id);
	}

}
