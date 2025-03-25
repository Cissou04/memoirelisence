package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Registre_declaration;
import memoire.com.memoirelisence.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/declaration")
public class DeclarationController {
    @Autowired
    private DeclarationService declarationService;
    @PostMapping("/create")
    public Registre_declaration create(@RequestBody Registre_declaration registre_declaration) {
        return declarationService.create(registre_declaration);
    }
    @GetMapping("/read")
    public List<Registre_declaration> read() {
        return declarationService.read();
    }
    @PostMapping("/update")
    public Registre_declaration update(@RequestBody Registre_declaration registre_declaration) {
        return declarationService.update(registre_declaration);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Registre_declaration registre_declaration) {
        return declarationService.delete(registre_declaration);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Registre_declaration> getCercleById(@PathVariable Integer id) {
        return declarationService.getRegistre_declarationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<?> modifierRegistre_declaration(@PathVariable int id,
                                                 @RequestParam String nom,
                                                 @RequestParam String prenom) {
        try {
            Registre_declaration updated = declarationService.modifierRegistre_declaration(id, nom, prenom);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/autorisation-admin")
    public ResponseEntity<?> donnerAutorisationAdmin(@PathVariable int id) {
        declarationService.donnerAutorisationAdmin(id);
        return ResponseEntity.ok("Autorisation donnée par l'admin !");
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Registre_declaration> enregistrerDeclaration(@RequestBody Registre_declaration declaration) {
        Registre_declaration savedDeclaration = declarationService.enregistrerDeclaration(declaration);
        return ResponseEntity.ok(savedDeclaration);
    }

}
