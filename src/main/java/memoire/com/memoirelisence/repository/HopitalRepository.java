package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopitalRepository extends JpaRepository<Hopital, Integer> {
}
