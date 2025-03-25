package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Registre_naissance;
import memoire.com.memoirelisence.service.NaissanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/naissance")
public class NaissanceController {
    @Autowired
    private NaissanceService naisssanceService;
    @PostMapping("/create")
    public Registre_naissance create(@RequestBody Registre_naissance registre_naissance) {
        return naisssanceService.create(registre_naissance);
    }
    @GetMapping("/read")
    public List<Registre_naissance> read() {
        return naisssanceService.read();
    }
    @PostMapping("/update")
    public Registre_naissance update(@RequestBody Registre_naissance registre_naissance) {
        return naisssanceService.update(registre_naissance);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Registre_naissance registre_naissance) {
        return naisssanceService.delete(registre_naissance);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Registre_naissance> getRegistre_naissanceById(@PathVariable Integer id) {
        return naisssanceService.getRegistre_naissanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
