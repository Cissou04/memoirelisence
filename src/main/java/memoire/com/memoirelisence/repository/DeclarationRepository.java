package memoire.com.memoirelisence.repository;

import memoire.com.memoirelisence.entite.Registre_declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationRepository extends JpaRepository<Registre_declaration, Integer> {
    List<Registre_declaration> findAll();
}
