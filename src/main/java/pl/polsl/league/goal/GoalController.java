package pl.polsl.league.goal;

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
@RequestMapping(path = "/goal")
public class GoalController {

	@Autowired
	GoalRepository goalRepository;

	@PostMapping
	public @ResponseBody Goal addGoal(@RequestBody Goal Goal) {
		return goalRepository.save(Goal);
	}

	@GetMapping
	public @ResponseBody Iterable<Goal> getAllGoals() {
		return goalRepository.findAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody Goal getGoal(@PathVariable Integer id) {
		return goalRepository.findById(id).orElse(null);
	}

	@PutMapping
	public @ResponseBody Goal updateGoal(@RequestBody Goal goal) {
		return goalRepository.save(goal);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteGoal(@PathVariable Integer id) {
		goalRepository.deleteById(id);
	}
}
