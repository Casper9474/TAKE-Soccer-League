package pl.polsl.league.club;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Club {
	@Id @GeneratedValue
	private Integer id;
	
	@NotBlank(message = "Club name cannot be blank")
	@Size(min = 2, max = 100, message = "Club name must be between 2 and 100 characters")
	private String name;
	
	@NotBlank(message = "City cannot be blank")
	@Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
	private String city;
	
	@NotNull(message = "Founded year cannot be null")
	@Min(value = 1800, message = "Founded year must be at least 1800")
	private Integer foundedYear;
	
	@NotBlank(message = "Stadium name cannot be blank")
	@Size(min = 2, max = 100, message = "Stadium name must be between 2 and 100 characters")
	private String stadiumName;
}
