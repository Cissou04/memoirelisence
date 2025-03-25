package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Hopital;
import memoire.com.memoirelisence.repository.HopitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HopitalService {
    @Autowired
    private HopitalRepository hopitalRepository;
    public Hopital create(Hopital hopital) {
        return hopitalRepository.save(hopital);
    }
    public List<Hopital> read() {
        return hopitalRepository.findAll();
    }
    public String delete(Hopital hopital) {
        String reponse="";
        boolean b=true;
        b= hopitalRepository.existsById(hopital.getId());
        if (b){
            System.out.println("L'element existe");
            hopitalRepository.deleteById(hopital.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Hopital update(Hopital hopital) {
        boolean b=true;
        b=hopitalRepository.existsById(hopital.getId());
        if (b){
            System.out.println("L'element existe");
            return hopitalRepository.save(hopital);
        }else {
            System.out.println("L'element n'existe pas");
            return new Hopital();
        }

    }
    public Optional<Hopital> getHopitalById(int id) {
        return hopitalRepository.findById(id);
    }


}
