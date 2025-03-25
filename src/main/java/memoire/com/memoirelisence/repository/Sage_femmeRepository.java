package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Sagefemme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Sage_femmeRepository extends JpaRepository<Sagefemme, Integer> {
}
