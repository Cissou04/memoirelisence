package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Pere;
import memoire.com.memoirelisence.repository.PereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PereService {
    @Autowired
    private PereRepository pereRepository;
    public Pere create(Pere pere) {
        return pereRepository.save(pere);
    }
    public List<Pere> read() {
        return pereRepository.findAll();
    }
    public String delete(Pere pere) {
        String reponse="";
        boolean b=true;
        b= pereRepository.existsById(pere.getId());
        if (b){
            System.out.println("L'element existe");
            pereRepository.deleteById(pere.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Pere update(Pere pere) {
        boolean b=true;
        b=pereRepository.existsById(pere.getId());
        if (b){
            System.out.println("L'element existe");
            return pereRepository.save(pere);
        }else {
            System.out.println("L'element n'existe pas");
            return new Pere();
        }

    }
    public Optional<Pere> getPereById(int id) {
        return pereRepository.findById(id);
    }


}
