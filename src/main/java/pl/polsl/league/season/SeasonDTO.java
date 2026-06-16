package pl.polsl.league.season;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SeasonDTO extends RepresentationModel<SeasonDTO> {
	private Integer id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean isActive;

	public SeasonDTO(Season season) {
		super();
		this.id = season.getId();
		this.name = season.getName();
		this.startDate = season.getStartDate();
		this.endDate = season.getEndDate();
		this.isActive = season.getIsActive();

		this.add(linkTo(methodOn(SeasonController.class).getSeason(season.getId())).withSelfRel());
	}
}