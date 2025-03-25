package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Tribunal;
import memoire.com.memoirelisence.repository.TribunalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TribunalService {
    @Autowired
    private TribunalRepository tribunalRepository;
    public Tribunal create(Tribunal tribunal) {
        return tribunalRepository.save(tribunal);
    }
    public List<Tribunal> read() {
        return tribunalRepository.findAll();
    }
    public String delete(Tribunal tribunal) {
        String reponse="";
        boolean b=true;
        b= tribunalRepository.existsById(tribunal.getId());
        if (b){
            System.out.println("L'element existe");
            tribunalRepository.deleteById(tribunal.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Tribunal update(Tribunal tribunal) {
        boolean b=true;
        b=tribunalRepository.existsById(tribunal.getId());
        if (b){
            System.out.println("L'element existe");
            return tribunalRepository.save(tribunal);
        }else {
            System.out.println("L'element n'existe pas");
            return new Tribunal();
        }

    }
    public Optional<Tribunal> getTribunalById(int id) {
        return tribunalRepository.findById(id);
    }



}
