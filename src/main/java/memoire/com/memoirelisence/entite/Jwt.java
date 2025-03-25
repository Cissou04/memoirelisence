package memoire.com.memoirelisence.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="jwt")
@Entity
@Data
public class Jwt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String valeur;
    private boolean desactive;
    private boolean expire;
    //@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    //private RefreshToken refreshToken;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
