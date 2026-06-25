package pl.polsl.league.season;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Season {
	@Id @GeneratedValue
	private Integer id;
	
	@NotBlank(message = "Season name cannot be blank")
	@Size(min = 2, max = 100, message = "Season name must be between 2 and 100 characters")
	private String name;
	
	@NotNull(message = "Start date cannot be null")
	private LocalDate startDate;
	
	@NotNull(message = "End date cannot be null")
	private LocalDate endDate;
	
	@NotNull(message = "IsActive status cannot be null")
	private Boolean isActive;
}
