package pl.polsl.league.participant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ParticipantDTO extends RepresentationModel<ParticipantDTO> {
	private Integer id;
	private Integer points;

	public ParticipantDTO(Participant participant) {
		super();
		this.id = participant.getId();
		this.points = participant.getPoints();

		this.add(linkTo(methodOn(ParticipantController.class).getParticipant(participant.getId())).withSelfRel());

		if (participant.getSeason() != null) {
			this.add(linkTo(
					methodOn(pl.polsl.league.season.SeasonController.class).getSeason(participant.getSeason().getId()))
					.withRel("season"));
		}
		if (participant.getClub() != null) {
			this.add(linkTo(methodOn(pl.polsl.league.club.ClubController.class).getClub(participant.getClub().getId()))
					.withRel("club"));
		}
	}
}