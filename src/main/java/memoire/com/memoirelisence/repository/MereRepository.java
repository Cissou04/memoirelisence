package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Mere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MereRepository  extends JpaRepository<Mere, Integer> {
}
