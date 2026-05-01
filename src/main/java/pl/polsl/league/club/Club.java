package pl.polsl.league.club;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Club {
	@Id @GeneratedValue
	private String id;
	
	private String name;
	private String city;
	private Integer foundedYear;
	private String stadiumName;
}
