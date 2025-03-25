package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Officier_etat;
import memoire.com.memoirelisence.service.OfficierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/officier")
public class OfficierController {
    @Autowired
    private OfficierService officierService;
    @PostMapping("/create")
    public Officier_etat create(@RequestBody Officier_etat officier_etat) {
        return officierService.create(officier_etat);
    }
    @GetMapping("/read")
    public List<Officier_etat> read() {
        return officierService.read();
    }
    @PostMapping("/update")
    public Officier_etat update(@RequestBody Officier_etat officier_etat) {
        return officierService.update(officier_etat);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Officier_etat officier_etat) {
        return officierService.delete(officier_etat);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Officier_etat> getOfficier_etatById(@PathVariable Integer id) {
        return officierService.getOfficier_etatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
