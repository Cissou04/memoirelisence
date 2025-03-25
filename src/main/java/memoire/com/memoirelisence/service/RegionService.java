package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Region;
import memoire.com.memoirelisence.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    public Region create(Region region) {
        return regionRepository.save(region);
    }
    public List<Region> read() {
        return regionRepository.findAll();
    }
    public String delete(Region region) {
        String reponse="";
        boolean b=true;
        b= regionRepository.existsById(region.getId());
        if (b){
            System.out.println("L'element existe");
            regionRepository.deleteById(region.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Region update(Region region) {
        boolean b=true;
        b=regionRepository.existsById(region.getId());
        if (b){
            System.out.println("L'element existe");
            return regionRepository.save(region);
        }else {
            System.out.println("L'element n'existe pas");
            return new Region();
        }

    }
    public Optional<Region> getRegionById(int id) {
        return regionRepository.findById(id);
    }


}
