package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtRepository extends JpaRepository<Jwt, Integer> {
    Optional<Jwt> findByValeurAndDesactiveAndExpire(String valeur, boolean desactive, boolean expire);

    boolean desactive(boolean desactive);
}
