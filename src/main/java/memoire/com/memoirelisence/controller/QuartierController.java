package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Quartier;
import memoire.com.memoirelisence.service.QuartierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quartier")
public class QuartierController {
    @Autowired
    private QuartierService quartierService;
    @PostMapping("/create")
    public Quartier create(@RequestBody Quartier quartier) {
        return quartierService.create(quartier);
    }
    @GetMapping("/read")
    public List<Quartier> read() {
        return quartierService.read();
    }
    @PostMapping("/update")
    public Quartier update(@RequestBody Quartier quartier) {
        return quartierService.update(quartier);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Quartier quartier) {
        return quartierService.delete(quartier);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Quartier> getQuartierById(@PathVariable Integer id) {
        return quartierService.getQuartierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
