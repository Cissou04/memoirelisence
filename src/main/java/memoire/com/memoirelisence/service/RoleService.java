package memoire.com.memoirelisence.service;

import memoire.com.memoirelisence.entite.Quartier;
import memoire.com.memoirelisence.entite.Role;
import memoire.com.memoirelisence.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role create(Role role) {
        return roleRepository.save(role);
    }
    public List<Role> read() {
        return roleRepository.findAll();
    }
    public String delete(Role role) {
        String reponse="";
        boolean b=true;
        b= roleRepository.existsById(role.getId());
        if (b){
            System.out.println("L'element existe");
            roleRepository.deleteById(role.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Role update(Role role) {
        boolean b=true;
        b=roleRepository.existsById(role.getId());
        if (b){
            System.out.println("L'element existe");
            return roleRepository.save(role);
        }else {
            System.out.println("L'element n'existe pas");
            return new Role();
        }

    }
    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById(id);
    }


}
