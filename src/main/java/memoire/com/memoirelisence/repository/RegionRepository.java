package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
