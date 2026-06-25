package pl.polsl.league.match;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import pl.polsl.league.error.ResourceNotFoundException;
import pl.polsl.league.goal.GoalRepository;
import pl.polsl.league.participant.Participant;
import pl.polsl.league.participant.ParticipantRepository;

@Controller
@RequestMapping(path = "/match")
public class MatchController {

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	ParticipantRepository participantRepository;

	@Autowired
	GoalRepository goalRepository;

	@PostMapping("/{id}/finish")
	@Transactional
	public @ResponseBody MatchDTO finishMatch(@PathVariable Integer id) {
		Match match = matchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));

		int homePoints = 0;
		int awayPoints = 0;

		if (match.getHomeScore() > match.getAwayScore()) {
			homePoints = 3;
			match.setStatus(Status.HOMEWIN);
		} else if (match.getHomeScore() < match.getAwayScore()) {
			awayPoints = 3;
			match.setStatus(Status.AWAYWIN);
		} else {
			homePoints = 1;
			awayPoints = 1;
			match.setStatus(Status.DRAW);
		}

		Participant homePart = participantRepository
				.findBySeasonIdAndClubId(match.getSeason().getId(), match.getHomeClub().getId()).orElse(null);
		if (homePart != null) {
			homePart.setPoints((homePart.getPoints() == null ? 0 : homePart.getPoints()) + homePoints);
			participantRepository.save(homePart);
		}

		Participant awayPart = participantRepository
				.findBySeasonIdAndClubId(match.getSeason().getId(), match.getAwayClub().getId()).orElse(null);
		if (awayPart != null) {
			awayPart.setPoints((awayPart.getPoints() == null ? 0 : awayPart.getPoints()) + awayPoints);
			participantRepository.save(awayPart);
		}

		Match updatedMatch = matchRepository.save(match);
		return new MatchDTO(updatedMatch);
	}

	@PostMapping("/{id}/walkover")
	@Transactional
	public @ResponseBody MatchDTO walkover(@PathVariable Integer id, @RequestParam Boolean homeWins) {
		Match match = matchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));

		goalRepository.deleteByMatchId(match.getId());

		if (homeWins) {
			match.setHomeScore(3);
			match.setAwayScore(0);
			match.setStatus(Status.HOMEWIN);
		} else {
			match.setHomeScore(0);
			match.setAwayScore(3);
			match.setStatus(Status.AWAYWIN);
		}

		Participant homePart = participantRepository
				.findBySeasonIdAndClubId(match.getSeason().getId(), match.getHomeClub().getId()).orElse(null);
		Participant awayPart = participantRepository
				.findBySeasonIdAndClubId(match.getSeason().getId(), match.getAwayClub().getId()).orElse(null);

		if (homeWins && homePart != null) {
			homePart.setPoints((homePart.getPoints() == null ? 0 : homePart.getPoints()) + 3);
			participantRepository.save(homePart);
		} else if (!homeWins && awayPart != null) {
			awayPart.setPoints((awayPart.getPoints() == null ? 0 : awayPart.getPoints()) + 3);
			participantRepository.save(awayPart);
		}

		Match updatedMatch = matchRepository.save(match);
		return new MatchDTO(updatedMatch);
	}

	@PostMapping
	public @ResponseBody MatchDTO addMatch(@Valid @RequestBody Match match) {
		Match savedMatch = matchRepository.save(match);
		return new MatchDTO(savedMatch);
	}

	@GetMapping
	public @ResponseBody CollectionModel<MatchDTO> getAllMatchs() {
		List<MatchDTO> matchsDTO = StreamSupport.stream(matchRepository.findAll().spliterator(), false)
				.map(MatchDTO::new).collect(Collectors.toList());
		return CollectionModel.of(matchsDTO);
	}

	@GetMapping("/{id}")
	public @ResponseBody MatchDTO getMatch(@PathVariable Integer id) {
		Match match = matchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
		return new MatchDTO(match);
	}

	@PutMapping("/{id}")
	public @ResponseBody MatchDTO updateMatch(@PathVariable Integer id, @Valid @RequestBody Match match) {
		if (!matchRepository.existsById(id)) {
			throw new ResourceNotFoundException("Record not found with id: " + id);
		}
		match.setId(id);
		Match updatedMatch = matchRepository.save(match);
		return new MatchDTO(updatedMatch);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteMatch(@PathVariable Integer id) {
		if (!matchRepository.existsById(id)) {
			throw new ResourceNotFoundException("Record not found with id: " + id);
		}
		matchRepository.deleteById(id);
	}
}