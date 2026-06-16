package pl.polsl.league.club;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClubDTO extends RepresentationModel<ClubDTO> {
	private Integer id;
	private String name;
	private String city;
	private Integer foundedYear;
	private String stadiumName;

	public ClubDTO(Club club) {
		super();
		this.id = club.getId();
		this.name = club.getName();
		this.city = club.getCity();
		this.foundedYear = club.getFoundedYear();
		this.stadiumName = club.getStadiumName();

		this.add(linkTo(methodOn(ClubController.class).getClub(club.getId())).withSelfRel());
	}
}