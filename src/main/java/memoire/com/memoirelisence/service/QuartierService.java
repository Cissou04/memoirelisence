package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Quartier;
import memoire.com.memoirelisence.repository.QuartierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QuartierService {
    @Autowired
    private QuartierRepository quartierRepository;
    public Quartier create(Quartier quartier) {
        return quartierRepository.save(quartier);
    }
    public List<Quartier> read() {
        return quartierRepository.findAll();
    }
    public String delete(Quartier quartier) {
        String reponse="";
        boolean b=true;
        b= quartierRepository.existsById(quartier.getId());
        if (b){
            System.out.println("L'element existe");
            quartierRepository.deleteById(quartier.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Quartier update(Quartier quartier) {
        boolean b=true;
        b=quartierRepository.existsById(quartier.getId());
        if (b){
            System.out.println("L'element existe");
            return quartierRepository.save(quartier);
        }else {
            System.out.println("L'element n'existe pas");
            return new Quartier();
        }

    }
    public Optional<Quartier> getQuartierById(int id) {
        return quartierRepository.findById(id);
    }



}
