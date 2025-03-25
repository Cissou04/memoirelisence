package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Tribunal;
import memoire.com.memoirelisence.service.TribunalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tribunal")
public class TribunalController {
    @Autowired
    private TribunalService tribunalService;
    @PostMapping("/create")
    public Tribunal create(@RequestBody Tribunal tribunal) {
        return tribunalService.create(tribunal);
    }
    @GetMapping("/read")
    public List<Tribunal> read() {
        return tribunalService.read();
    }
    @PostMapping("/update")
    public Tribunal update(@RequestBody Tribunal tribunal) {
        return tribunalService.update(tribunal);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Tribunal tribunal) {
        return tribunalService.delete(tribunal);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tribunal> getTribunalById(@PathVariable Integer id) {
        return tribunalService.getTribunalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
