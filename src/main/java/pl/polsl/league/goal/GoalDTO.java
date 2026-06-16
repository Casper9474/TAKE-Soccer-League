package pl.polsl.league.goal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GoalDTO extends RepresentationModel<GoalDTO> {
	private Integer id;
	private Integer minute;
	private GoalType goalType;

	public GoalDTO(Goal goal) {
		super();
		this.id = goal.getId();
		this.minute = goal.getMinute();
		this.goalType = goal.getGoalType();

		this.add(linkTo(methodOn(GoalController.class).getGoal(goal.getId())).withSelfRel());

		if (goal.getMatch() != null) {
			this.add(linkTo(methodOn(pl.polsl.league.match.MatchController.class).getMatch(goal.getMatch().getId()))
					.withRel("match"));
		}
		if (goal.getPlayer() != null) {
			this.add(linkTo(methodOn(pl.polsl.league.player.PlayerController.class).getPlayer(goal.getPlayer().getId()))
					.withRel("player"));
		}
	}
}