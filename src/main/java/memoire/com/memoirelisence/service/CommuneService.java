package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Commune;
import memoire.com.memoirelisence.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommuneService {
    @Autowired
    private CommuneRepository communeRepository;
    public Commune create(Commune commune) {
        return communeRepository.save(commune);
    }
    public List<Commune> read() {
        return communeRepository.findAll();
    }
    public String delete(Commune commune) {
        String reponse="";
        boolean b=true;
        b= communeRepository.existsById(commune.getId());
        if (b){
            System.out.println("L'element existe");
            communeRepository.deleteById(commune.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Commune update(Commune commune) {
        boolean b=true;
        b=communeRepository.existsById(commune.getId());
        if (b){
            System.out.println("L'element existe");
            return communeRepository.save(commune);
        }else {
            System.out.println("L'element n'existe pas");
            return new Commune();
        }

    }
    public Optional<Commune> getCommuneById(int id) {

        return communeRepository.findById(id);
    }


}

