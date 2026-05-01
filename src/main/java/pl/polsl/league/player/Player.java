package pl.polsl.league.player;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.league.club.Club;

@Entity
@Getter @Setter
public class Player {
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Club club;
	
	private String firstName;
	private String lastName;	
	private Integer number;
	private LocalDate birthDate;
	
	@Enumerated(EnumType.STRING)
	private Position position;
}