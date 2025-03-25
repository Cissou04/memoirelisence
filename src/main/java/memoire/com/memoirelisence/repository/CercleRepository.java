package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Cercle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CercleRepository extends JpaRepository <Cercle, Integer>{
    Integer id(int id);
}
