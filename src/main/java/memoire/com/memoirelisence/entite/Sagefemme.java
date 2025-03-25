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
@Table( name= "sagefemme")
public class Sagefemme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String domicile;
    private int age;
    private String profession;
   // @ManyToOne
    //@JoinColumn(name = "hopital", nullable = false)
    //private Hopital hopital;

    @OneToMany(mappedBy = "sagefemme")
    private List<Registre_declaration> declaration;

    @ManyToOne
    @JoinColumn(name = "utilisateur", nullable = false)
    private Utilisateur utilisateur;




}
