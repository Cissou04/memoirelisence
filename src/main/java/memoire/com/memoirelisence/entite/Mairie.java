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
@Table( name= "mairie")
public class Mairie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String email;
    private String nom_utilisateur;
    private String mot_de_passe;
    @ManyToOne
    @JoinColumn(name = "commune", nullable = false)
    private Commune commune;
    //@OneToMany(mappedBy = "mairie", cascade = CascadeType.ALL)
    //private List<Officier_etat> officier;


}
