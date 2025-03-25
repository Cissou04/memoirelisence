package memoire.com.memoirelisence.service;


import lombok.AllArgsConstructor;
import memoire.com.memoirelisence.entite.Utilisateur;
import memoire.com.memoirelisence.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UtilisateurService implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;
    private PasswordEncoder  passwordEncoder;

    public Utilisateur create(Utilisateur utilisateur) {

        if (!utilisateur.getEmail().contains("@")){
            throw new RuntimeException("Votre email est invalide");
        }

        if (!utilisateur.getEmail().contains(".")){
            throw new RuntimeException("Votre email est invalide");
        }

        Optional<Utilisateur> utilisateurOptional = this.utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (utilisateurOptional.isPresent()){
            throw new RuntimeException("Votre mail est déja utilisé");
        }

        String mdpCrypte = this.passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setMot_de_passe(mdpCrypte);


        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> read() {

        return utilisateurRepository.findAll();
    }
    public String delete(Utilisateur utilisateur) {
        String reponse="";
        boolean b=true;
        b= utilisateurRepository.existsById(utilisateur.getId());
        if (b){
            System.out.println("L'element existe");
            utilisateurRepository.deleteById(utilisateur.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Utilisateur update(Utilisateur utilisateur) {
        boolean b=true;
        b=utilisateurRepository.existsById(utilisateur.getId());
        if (b){
            System.out.println("L'element existe");
            return utilisateurRepository.save(utilisateur);
        }else {
            System.out.println("L'element n'existe pas");
            return new Utilisateur();
        }

    }
    public Optional<Utilisateur> getUtilisateurById(int id) {
        return utilisateurRepository.findById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateurRepository.findByEmail(username).orElseThrow();
    }

}
