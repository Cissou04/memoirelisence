package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Registre_naissance;
import memoire.com.memoirelisence.repository.NaissanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NaissanceService {
    @Autowired
    private NaissanceRepository naissanceRepository;
    public Registre_naissance create(Registre_naissance registre_naissance) {
        return naissanceRepository.save(registre_naissance);
    }
    public List<Registre_naissance> read() {
        return naissanceRepository.findAll();
    }
    public String delete(Registre_naissance registre_naissance) {
        String reponse="";
        boolean b=true;
        b= naissanceRepository.existsById(registre_naissance.getId());
        if (b){
            System.out.println("L'element existe");
            naissanceRepository.deleteById(registre_naissance.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Registre_naissance update(Registre_naissance registre_naissance) {
        boolean b=true;
        b=naissanceRepository.existsById(registre_naissance.getId());
        if (b){
            System.out.println("L'element existe");
            return naissanceRepository.save(registre_naissance);
        }else {
            System.out.println("L'element n'existe pas");
            return new Registre_naissance();
        }

    }
    public Optional<Registre_naissance> getRegistre_naissanceById(int id) {
        return naissanceRepository.findById(id);
    }

}
