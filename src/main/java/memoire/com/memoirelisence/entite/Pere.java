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
@Table( name= "pere")
public class Pere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String domicile;
    private String nationalite;
    private String profession;
    private String statu_matri;
    private String niveau_instruction;
    @OneToMany(mappedBy = "pere")
    private List<Registre_declaration> declaration;


}
