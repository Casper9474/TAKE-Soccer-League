package pl.polsl.league.player;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerDTO extends RepresentationModel<PlayerDTO> {
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer number;
	private LocalDate birthDate;
	private Position position;

	public PlayerDTO(Player player) {
		super();
		this.id = player.getId();
		this.firstName = player.getFirstName();
		this.lastName = player.getLastName();
		this.number = player.getNumber();
		this.birthDate = player.getBirthDate();
		this.position = player.getPosition();

		this.add(linkTo(methodOn(PlayerController.class).getPlayer(player.getId())).withSelfRel());

		if (player.getClub() != null) {
			this.add(linkTo(methodOn(pl.polsl.league.club.ClubController.class).getClub(player.getClub().getId()))
					.withRel("club"));
		}
	}
}