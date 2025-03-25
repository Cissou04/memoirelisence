package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Sagefemme;
import memoire.com.memoirelisence.repository.Sage_femmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Sage_femmeService {
    @Autowired
    private Sage_femmeRepository sage_femmeRepository;
    public Sagefemme create(Sagefemme sagefemme) {
        return sage_femmeRepository.save(sagefemme);
    }
    public List<Sagefemme> read() {
        return sage_femmeRepository.findAll();
    }
    public String delete(Sagefemme sagefemme) {
        String reponse="";
        boolean b=true;
        b= sage_femmeRepository.existsById(sagefemme.getId());
        if (b){
            System.out.println("L'element existe");
            sage_femmeRepository.deleteById(sagefemme.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Sagefemme update(Sagefemme sagefemme) {
        boolean b=true;
        b=sage_femmeRepository.existsById(sagefemme.getId());
        if (b){
            System.out.println("L'element existe");
            return sage_femmeRepository.save(sagefemme);
        }else {
            System.out.println("L'element n'existe pas");
            return new Sagefemme();
        }

    }
    public Optional<Sagefemme> getSagefemmeById(int id) {
        return sage_femmeRepository.findById(id);
    }


}
