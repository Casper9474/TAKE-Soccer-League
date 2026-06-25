package pl.polsl.league.player;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.league.club.Club;

@Entity
@Getter @Setter
public class Player {
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
	@NotNull(message = "Club cannot be null")
	private Club club;
	
	@NotBlank(message = "First name cannot be blank")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	private String firstName;
	
	@NotBlank(message = "Last name cannot be blank")
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	private String lastName;	
	
	@NotNull(message = "Shirt number cannot be null")
	@Min(value = 1, message = "Shirt number must be at least 1")
	private Integer number;
	
	@NotNull(message = "Birth date cannot be null")
	private LocalDate birthDate;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Position cannot be null")
	private Position position;
}