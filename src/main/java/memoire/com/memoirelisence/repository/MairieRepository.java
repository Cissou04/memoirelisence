package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Mairie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MairieRepository extends JpaRepository<Mairie, Integer> {
    Mairie findByCommune_Nom(String nomCommune);
}
