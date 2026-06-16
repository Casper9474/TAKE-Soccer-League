package pl.polsl.league.match;

import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MatchDTO extends RepresentationModel<MatchDTO> {
    private Integer id;
    private LocalDateTime matchDate;
    private Integer matchWeek;
    private Integer homeScore;
    private Integer awayScore;
    private Status status;

    public MatchDTO(Match match) {
        super();
        this.id = match.getId();
        this.matchDate = match.getMatchDate();
        this.matchWeek = match.getMatchWeek();
        this.homeScore = match.getHomeScore();
        this.awayScore = match.getAwayScore();
        this.status = match.getStatus();

        this.add(linkTo(methodOn(MatchController.class).getMatch(match.getId())).withSelfRel());
        
        if (match.getSeason() != null) {
            this.add(linkTo(methodOn(pl.polsl.league.season.SeasonController.class).getSeason(match.getSeason().getId())).withRel("season"));
        }
        if (match.getHomeClub() != null) {
            this.add(linkTo(methodOn(pl.polsl.league.club.ClubController.class).getClub(match.getHomeClub().getId())).withRel("homeClub"));
        }
        if (match.getAwayClub() != null) {
            this.add(linkTo(methodOn(pl.polsl.league.club.ClubController.class).getClub(match.getAwayClub().getId())).withRel("awayClub"));
        }
    }
}