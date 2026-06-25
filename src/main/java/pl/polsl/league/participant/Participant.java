package pl.polsl.league.participant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.league.club.Club;
import pl.polsl.league.season.Season;

@Entity
@Getter @Setter
public class Participant {
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
	@NotNull(message = "Season cannot be null")
	private Season season;
	
	@ManyToOne
	@NotNull(message = "Club cannot be null")
	private Club club;
	
	@NotNull(message = "Points cannot be null")
	@Min(value = 0, message = "Points must be non-negative")
	private Integer points;
}