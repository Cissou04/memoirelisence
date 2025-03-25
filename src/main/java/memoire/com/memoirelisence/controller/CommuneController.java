package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Cercle;
import memoire.com.memoirelisence.entite.Commune;
import memoire.com.memoirelisence.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commune")
public class CommuneController {
    @Autowired
    private CommuneService communeService;
    @PostMapping("/create")
    public Commune create(@RequestBody Commune commune) {
        return communeService.create(commune);
    }
    @GetMapping("/read")
    public List<Commune> read() {
        return communeService.read();
    }
    @PostMapping("/update")
    public Commune update(@RequestBody Commune commune) {
        return communeService.update(commune);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Commune commune) {
        return communeService.delete(commune);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Commune> getCommuneById(@PathVariable Integer id) {
        return communeService.getCommuneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
