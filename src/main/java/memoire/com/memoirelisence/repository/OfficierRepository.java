package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Officier_etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficierRepository extends JpaRepository<Officier_etat, Integer> {
}
