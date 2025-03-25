package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Tribunal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TribunalRepository extends JpaRepository<Tribunal, Integer> {
}
