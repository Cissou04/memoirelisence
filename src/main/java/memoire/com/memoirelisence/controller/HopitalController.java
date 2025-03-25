package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Hopital;
import memoire.com.memoirelisence.service.HopitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hopital")
public class HopitalController {
    @Autowired
    private HopitalService hopitalService;
    @PostMapping("/create")
    public Hopital create(@RequestBody Hopital hopital) {
        return hopitalService.create(hopital);
    }
    @GetMapping("/read")
    public List<Hopital> read() {
        return hopitalService.read();
    }
    @PostMapping("/update")
    public Hopital update(@RequestBody Hopital hopital) {
        return hopitalService.update(hopital);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Hopital hopital) {
        return hopitalService.delete(hopital);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hopital> getHopitalById(@PathVariable Integer id) {
        return hopitalService.getHopitalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
