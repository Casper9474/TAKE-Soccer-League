package pl.polsl.league.match;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private Season season;
	@ManyToOne
	private Club homeClub;
	@ManyToOne
	private Club awayClub;
	
	private LocalDateTime matchDate;
	private Integer matchWeek;
	private Integer homeScore;
	private Integer awayScore;
	
	@Enumerated(EnumType.STRING)
	private Status status;
}
