package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Pere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PereRepository extends JpaRepository<Pere, Integer> {
}
