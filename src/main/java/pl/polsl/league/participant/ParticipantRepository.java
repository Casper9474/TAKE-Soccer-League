package pl.polsl.league.participant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParticipantRepository extends CrudRepository<Participant, Integer> {

	@Query("SELECT p FROM Participant p WHERE p.season.id = :seasonId ORDER BY p.points DESC")
	List<Participant> findBySeasonIdOrderByPointsDesc(@Param("seasonId") Integer seasonId);

	Optional<Participant> findBySeasonIdAndClubId(Integer seasonId, Integer clubId);
}