package pl.polsl.league.goal;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GoalRepository extends CrudRepository<Goal, Integer> {

	@Query("SELECT p.firstName, p.lastName, COUNT(g) as goals " + "FROM Goal g JOIN g.player p JOIN g.match m "
			+ "WHERE m.season.id = :seasonId " + "GROUP BY p.id, p.firstName, p.lastName " + "ORDER BY goals DESC")
	List<Object[]> getTopScorers(@Param("seasonId") Integer seasonId);

	@Query("SELECT " + "CASE " + "  WHEN g.minute BETWEEN 1 AND 15 THEN '1-15' "
			+ "  WHEN g.minute BETWEEN 16 AND 30 THEN '16-30' " + "  WHEN g.minute BETWEEN 31 AND 45 THEN '31-45' "
			+ "  WHEN g.minute BETWEEN 46 AND 60 THEN '46-60' " + "  WHEN g.minute BETWEEN 61 AND 75 THEN '61-75' "
			+ "  ELSE '76-90+' " + "END AS timeRange, COUNT(g) " + "FROM Goal g " + "GROUP BY timeRange")
	List<Object[]> getGoalTimeDistribution();

	@Modifying
	@Query("DELETE FROM Goal g WHERE g.match.id = :matchId")
	void deleteByMatchId(@Param("matchId") Integer matchId);
}