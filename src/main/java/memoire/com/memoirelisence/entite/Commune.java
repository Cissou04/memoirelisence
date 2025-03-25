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
@Table( name= "commune")
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @ManyToOne
    @JoinColumn(name = "cercle", nullable = false)
    private Cercle cercle;

    @OneToMany(mappedBy = "commune")
    private List<Hopital> hopital;

    @OneToMany(mappedBy = "commune")
    private List<Mairie> mairie;

    @OneToMany(mappedBy = "commune")
    private List<Quartier> quartier;
    @OneToMany(mappedBy = "commune", cascade = CascadeType.ALL)
    private List<Tribunal> tribunal;
    @OneToMany(mappedBy = "commune", cascade = CascadeType.ALL)
    private List<Utilisateur> utilisateur;



}
