package memoire.com.memoirelisence.controller;


import memoire.com.memoirelisence.entite.Region;
import memoire.com.memoirelisence.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @PostMapping("/create")
    public Region create(@RequestBody Region region) {
        return regionService.create(region);
    }
    @GetMapping("/read")
    public List<Region> read() {
        return regionService.read();
    }
    @PostMapping("/update")
    public Region update(@RequestBody Region region) {
        return regionService.update(region);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Region region) {
        return regionService.delete(region);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Integer id) {
        return regionService.getRegionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
