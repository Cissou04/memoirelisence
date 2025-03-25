package memoire.com.memoirelisence.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name= "utilisateur")
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String adresse;
    private String email;
    private String nom_utilisateur;
    private String mot_de_passe;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "commune", nullable = false)
    private Commune commune;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Sagefemme> sagefemme;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Officier_etat> officier;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+this.role.getName()));
    }

    @Override
    public String getPassword() {
        return mot_de_passe;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
