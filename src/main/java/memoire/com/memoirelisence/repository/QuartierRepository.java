package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Quartier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartierRepository extends JpaRepository<Quartier, Integer> {
}
