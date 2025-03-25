package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Mairie;
import memoire.com.memoirelisence.repository.MairieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MairieService {
    @Autowired
    private MairieRepository mairieRepository;
    public Mairie create(Mairie mairie) {
        return mairieRepository.save(mairie);
    }
    public List<Mairie> read() {
        return mairieRepository.findAll();
    }
    public String delete(Mairie mairie) {
        String reponse="";
        boolean b=true;
        b= mairieRepository.existsById(mairie.getId());
        if (b){
            System.out.println("L'element existe");
            mairieRepository.deleteById(mairie.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Mairie update(Mairie mairie) {
        boolean b=true;
        b=mairieRepository.existsById(mairie.getId());
        if (b){
            System.out.println("L'element existe");
            return mairieRepository.save(mairie);
        }else {
            System.out.println("L'element n'existe pas");
            return new Mairie();
        }

    }
    public Optional<Mairie> getMairieById(int id) {
        return mairieRepository.findById(id);
    }



}
