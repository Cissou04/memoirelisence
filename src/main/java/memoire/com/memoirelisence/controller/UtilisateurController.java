package memoire.com.memoirelisence.controller;

import memoire.com.memoirelisence.entite.Utilisateur;
import memoire.com.memoirelisence.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;
    @PostMapping("/create")
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.create(utilisateur);
    }
    @GetMapping("/read")
    public List<Utilisateur> read() {
        return utilisateurService.read();
    }
    @PostMapping("/update")
    public Utilisateur update(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.update(utilisateur);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.delete(utilisateur);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Integer id) {
        return utilisateurService.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
