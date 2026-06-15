package pl.polsl.league.goal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private Match match;
	@ManyToOne
	private Player player;
	@Column(name = "`minute`")
	private Integer minute; // Time in seconds = minute/60 : minute % 60
	
	@Enumerated(EnumType.STRING)
	private GoalType goalType;
}