package pl.polsl.league.goal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.league.match.Match;
import pl.polsl.league.player.Player;

@Entity
@Getter @Setter
public class Goal {
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
	@NotNull(message = "Match cannot be null")
	private Match match;
	
	@ManyToOne
	@NotNull(message = "Player cannot be null")
	private Player player;
	
	@Column(name = "`minute`")
	@NotNull(message = "Minute cannot be null")
	@Min(value = 0, message = "Minute must be non-negative")
	private Integer minute; // Time in seconds = minute/60 : minute % 60
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Goal type cannot be null")
	private GoalType goalType;
}