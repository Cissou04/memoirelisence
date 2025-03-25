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
@Table( name= "hopital")
public class Hopital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String adresse;
    private String email;
    private String nom_utilisateur;
    private String mot_de_passe;
    @ManyToOne
    @JoinColumn(name = "commune", nullable = false)
    private Commune commune;
   // @OneToMany(mappedBy = "hopital", cascade = CascadeType.ALL)
    //private List<Sagefemme> sagefemme;


}
