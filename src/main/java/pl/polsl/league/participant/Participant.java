package pl.polsl.league.participant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private Season season;
	@ManyToOne
	private Club club;
	
	private Integer points;
}