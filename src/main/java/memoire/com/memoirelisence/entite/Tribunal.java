package memoire.com.memoirelisence.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name= "tribunal")
public class Tribunal {
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


}
