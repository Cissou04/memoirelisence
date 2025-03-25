package memoire.com.memoirelisence.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name= "registre_declaration")
public class Registre_declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String pays_naissance;
    private String lieu_accouchement;
    private int nobre_enfant_accouchement;
    private LocalDate date_naissance;
    private LocalTime heure_naissans;
    private Date dateEnregistrement;
    private boolean verrouille = false;
    @ManyToOne
    @JoinColumn(name = "pere", nullable = false)
    private Pere pere;

    @ManyToOne
    @JoinColumn(name = "mere", nullable = false)
    private Mere mere;

    @ManyToOne
    @JoinColumn(name = "hopital", nullable = false)
    private Hopital hopital;
    @ManyToOne
    @JoinColumn(name = "sagefemme", nullable = false)
    private Sagefemme sagefemme;
    @Temporal(TemporalType.TIMESTAMP)
    private boolean autorisationAdmin = false; // L'admin peut donner une autorisation

    @PrePersist
    protected void onCreate() {
        this.dateEnregistrement = new Date(); // Met automatiquement la date d'enregistrement

}
}
