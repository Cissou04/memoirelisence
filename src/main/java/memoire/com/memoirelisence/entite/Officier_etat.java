package memoire.com.memoirelisence.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name= "officier_etat")
public class Officier_etat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String qualite;
    //@ManyToOne
    //@JoinColumn(name = "mairie", nullable = false)
    //private Mairie mairie;
    @OneToMany(mappedBy = "officier", cascade = CascadeType.ALL)
    private List<Registre_naissance> naissance;

    @ManyToOne
    @JoinColumn(name = "utilisateur", nullable = false)
    private Utilisateur utilisateur;



}
