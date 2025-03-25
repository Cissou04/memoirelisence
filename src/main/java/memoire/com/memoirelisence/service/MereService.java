package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Mere;
import memoire.com.memoirelisence.repository.MereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MereService {
    @Autowired
    private MereRepository mereRepository;

    public Mere create(Mere mere) {
        return mereRepository.save(mere);
    }

    public List<Mere> read() {

        return mereRepository.findAll();
    }
    public String delete(Mere mere) {
        String reponse="";
        boolean b=true;
        b= mereRepository.existsById(mere.getId());
        if (b){
            System.out.println("L'element existe");
            mereRepository.deleteById(mere.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Mere update(Mere mere) {
        boolean b=true;
        b=mereRepository.existsById(mere.getId());
        if (b){
            System.out.println("L'element existe");
            return mereRepository.save(mere);
        }else {
            System.out.println("L'element n'existe pas");
            return new Mere();
        }

    }
    public Optional<Mere> getMereById(int id) {

        return mereRepository.findById(id);
    }



}
