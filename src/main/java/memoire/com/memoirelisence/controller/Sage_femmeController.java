package memoire.com.memoirelisence.controller;

import memoire.com.memoirelisence.entite.Cercle;
import memoire.com.memoirelisence.entite.Sagefemme;
import memoire.com.memoirelisence.service.CercleService;
import memoire.com.memoirelisence.service.Sage_femmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sage_femme")
public class Sage_femmeController {
    @Autowired
    private Sage_femmeService sage_femmeService;
    @PostMapping("/create")
    public Sagefemme create(@RequestBody Sagefemme sagefemme) {
        return sage_femmeService.create(sagefemme);
    }
    @GetMapping("/read")
    public List<Sagefemme> read() {
        return sage_femmeService.read();
    }
    @PostMapping("/update")
    public Sagefemme update(@RequestBody Sagefemme sagefemme) {
        return sage_femmeService.update(sagefemme);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Sagefemme sagefemme) {
        return sage_femmeService.delete(sagefemme);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sagefemme> getSagefemmeById(@PathVariable Integer id) {
        return sage_femmeService.getSagefemmeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
