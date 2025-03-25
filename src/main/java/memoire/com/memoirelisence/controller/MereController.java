package memoire.com.memoirelisence.controller;



import memoire.com.memoirelisence.entite.Mere;
import memoire.com.memoirelisence.service.MereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mere")
public class MereController {
    @Autowired
    private MereService mereService;


    @PostMapping("/create")
    public Mere create(@RequestBody Mere mere) {
        return mereService.create(mere);
    }
    @GetMapping("/read")
    public List<Mere> read() {
        return mereService.read();
    }
    @PostMapping("/update")
    public Mere update(@RequestBody Mere mere) {
        return mereService.update(mere);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Mere mere) {
        return mereService.delete(mere);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Mere> getMereById(@PathVariable Integer id) {
        return mereService.getMereById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
