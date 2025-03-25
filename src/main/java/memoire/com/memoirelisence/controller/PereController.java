package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Pere;
import memoire.com.memoirelisence.service.PereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peres")
public class PereController {
    @Autowired
    private PereService pereService;
    @PostMapping("/create")
    public Pere create(@RequestBody Pere pere) {
        return pereService.create(pere);
    }
    @GetMapping("/read")
    public List<Pere> read() {
        return pereService.read();
    }
    @PostMapping("/update")
    public Pere update(@RequestBody Pere pere) {
        return pereService.update(pere);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Pere pere) {
        return pereService.delete(pere);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pere> getPereById(@PathVariable Integer id) {
        return pereService.getPereById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
