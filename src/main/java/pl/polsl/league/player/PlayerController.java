package pl.polsl.league.player;

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
@RequestMapping(path = "/player")
public class PlayerController {

	@Autowired
	PlayerRepository playerRepository;

	@PostMapping
	public @ResponseBody PlayerDTO addPlayer(@RequestBody Player player) {
		Player savedPlayer = playerRepository.save(player);
		return new PlayerDTO(savedPlayer);
	}

	@GetMapping
	public @ResponseBody CollectionModel<PlayerDTO> getAllPlayers() {
		List<PlayerDTO> playersDTO = StreamSupport.stream(playerRepository.findAll().spliterator(), false)
				.map(PlayerDTO::new)
				.collect(Collectors.toList());
		return CollectionModel.of(playersDTO);
	}

	@GetMapping("/{id}")
	public @ResponseBody PlayerDTO getPlayer(@PathVariable Integer id) {
		Player player = playerRepository.findById(id).orElse(null);
		return player != null ? new PlayerDTO(player) : null;
	}

	@PutMapping
	public @ResponseBody PlayerDTO updatePlayer(@RequestBody Player player) {
		Player updatedPlayer = playerRepository.save(player);
		return new PlayerDTO(updatedPlayer);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deletePlayer(@PathVariable Integer id) {
		playerRepository.deleteById(id);
	}
}