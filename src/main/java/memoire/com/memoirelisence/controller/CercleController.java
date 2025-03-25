package memoire.com.memoirelisence.controller;

import memoire.com.memoirelisence.entite.Cercle;
import memoire.com.memoirelisence.service.CercleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cercle")
public class CercleController {
@Autowired
    private CercleService cercleService;
@PostMapping("/create")
    public Cercle create(@RequestBody Cercle cercle) {
    return cercleService.create(cercle);
}
@GetMapping("/read")
    public List<Cercle> read() {
    return cercleService.read();
}
@PostMapping("/update")
    public Cercle update(@RequestBody Cercle cercle) {
    return cercleService.update(cercle);
}
@DeleteMapping("/delete")
    public String delete(@RequestBody Cercle cercle) {
    return cercleService.delete(cercle);
}
    @GetMapping("/{id}")
    public ResponseEntity<Cercle> getCercleById(@PathVariable Integer id) {
        return cercleService.getCercleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
