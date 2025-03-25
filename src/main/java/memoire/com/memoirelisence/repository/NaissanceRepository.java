package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Registre_naissance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaissanceRepository extends JpaRepository<Registre_naissance, Integer> {
}
