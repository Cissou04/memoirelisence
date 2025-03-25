package memoire.com.memoirelisence.entite;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name= "cercle")
public class Cercle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @ManyToOne
    @JoinColumn(name = "region", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "cercle")
    private List<Commune> commune;



}
