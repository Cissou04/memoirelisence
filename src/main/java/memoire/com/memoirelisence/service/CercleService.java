package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Cercle;
import memoire.com.memoirelisence.repository.CercleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class CercleService {
    @Autowired
    private CercleRepository cercleRepository;
    public Cercle create(Cercle cercle) {
        return cercleRepository.save(cercle);
    }
    public List<Cercle> read() {
        return cercleRepository.findAll();
    }
  public String delete(Cercle cercle) {
        String reponse="";
        boolean b=true;
        b= cercleRepository.existsById(cercle.getId());
        if (b){
            System.out.println("L'element existe");
            cercleRepository.deleteById(cercle.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
  public Cercle update(Cercle cercle) {
        boolean b=true;
        b=cercleRepository.existsById(cercle.getId());
        if (b){
            System.out.println("L'element existe");
            return cercleRepository.save(cercle);
        }else {
            System.out.println("L'element n'existe pas");
            return new Cercle();
        }

  }
  public Optional<Cercle> getCercleById(int id) {
        return cercleRepository.findById(id);
  }

}
