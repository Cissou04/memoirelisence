package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Officier_etat;
import memoire.com.memoirelisence.repository.OfficierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OfficierService {
    @Autowired
    private OfficierRepository officierRepository;
    public Officier_etat create(Officier_etat officier_etat) {
        return officierRepository.save(officier_etat);
    }
    public List<Officier_etat> read() {
        return officierRepository.findAll();
    }
    public String delete(Officier_etat officier_etat) {
        String reponse="";
        boolean b=true;
        b= officierRepository.existsById(officier_etat.getId());
        if (b){
            System.out.println("L'element existe");
            officierRepository.deleteById(officier_etat.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Officier_etat update(Officier_etat officier_etat) {
        boolean b=true;
        b=officierRepository.existsById(officier_etat.getId());
        if (b){
            System.out.println("L'element existe");
            return officierRepository.save(officier_etat);
        }else {
            System.out.println("L'element n'existe pas");
            return new Officier_etat();
        }

    }
    public Optional<Officier_etat> getOfficier_etatById(int id) {
        return officierRepository.findById(id);
    }


}
