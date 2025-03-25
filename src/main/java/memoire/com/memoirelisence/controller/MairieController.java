package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Mairie;
import memoire.com.memoirelisence.service.MairieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mairie")
public class MairieController {
    @Autowired
    private MairieService mairieService;
    @PostMapping("/create")
    public Mairie create(@RequestBody Mairie mairie) {
        return mairieService.create(mairie);
    }
    @GetMapping("/read")
    public List<Mairie> read() {
        return mairieService.read();
    }
    @PostMapping("/update")
    public Mairie update(@RequestBody Mairie mairie) {
        return mairieService.update(mairie);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Mairie mairie) {
        return mairieService.delete(mairie);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Mairie> getMairieById(@PathVariable Integer id) {
        return mairieService.getMairieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
