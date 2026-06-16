package pl.polsl.league.goal;

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
@RequestMapping(path = "/goal")
public class GoalController {

	@Autowired
	GoalRepository goalRepository;

	@PostMapping
	public @ResponseBody GoalDTO addGoal(@RequestBody Goal goal) {
		Goal savedGoal = goalRepository.save(goal);
		return new GoalDTO(savedGoal);
	}

	@GetMapping
	public @ResponseBody CollectionModel<GoalDTO> getAllGoals() {
		List<GoalDTO> goalsDTO = StreamSupport.stream(goalRepository.findAll().spliterator(), false).map(GoalDTO::new)
				.collect(Collectors.toList());
		return CollectionModel.of(goalsDTO);
	}

	@GetMapping("/{id}")
	public @ResponseBody GoalDTO getGoal(@PathVariable Integer id) {
		Goal goal = goalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
		return new GoalDTO(goal);
	}

	@PutMapping
	public @ResponseBody GoalDTO updateGoal(@RequestBody Goal goal) {
		Goal updatedGoal = goalRepository.save(goal);
		return new GoalDTO(updatedGoal);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteGoal(@PathVariable Integer id) {
		if (!goalRepository.existsById(id)) {
			throw new ResourceNotFoundException("Record not found with id: " + id);
		}
		goalRepository.deleteById(id);
	}
}