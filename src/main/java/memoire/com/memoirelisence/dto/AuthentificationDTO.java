package memoire.com.memoirelisence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import memoire.com.memoirelisence.entite.Role;

@Data
@AllArgsConstructor
public class AuthentificationDTO {
    private String nom;
    private String email;
    private String mot_de_passe;
    //private String nom_utilisateur;
    private Role role;
    private String token;
    private String username;
}
