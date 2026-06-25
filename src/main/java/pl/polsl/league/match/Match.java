package pl.polsl.league.match;

import java.time.LocalDateTime;

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
import pl.polsl.league.club.Club;
import pl.polsl.league.season.Season;

@Entity
@Getter @Setter
public class Match {
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
	@NotNull(message = "Season cannot be null")
	private Season season;
	
	@ManyToOne
	@NotNull(message = "Home club cannot be null")
	private Club homeClub;
	
	@ManyToOne
	@NotNull(message = "Away club cannot be null")
	private Club awayClub;
	
	@NotNull(message = "Match date cannot be null")
	private LocalDateTime matchDate;
	
	@NotNull(message = "Match week cannot be null")
	@Min(value = 1, message = "Match week must be at least 1")
	private Integer matchWeek;
	
	@Min(value = 0, message = "Home score must be non-negative")
	private Integer homeScore;
	
	@Min(value = 0, message = "Away score must be non-negative")
	private Integer awayScore;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status cannot be null")
	private Status status;
}
