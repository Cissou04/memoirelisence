package memoire.com.memoirelisence.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name= "registre_naissance")
public class Registre_naissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date_creation;
    @OneToOne
    @JoinColumn(name = "declaration", nullable = false)
    private Registre_declaration declaration;
    @ManyToOne
    @JoinColumn(name = "officier", nullable = false)
    private Officier_etat officier;


}
