package memoire.com.memoirelisence.controller;

import memoire.com.memoirelisence.entite.Role;
import memoire.com.memoirelisence.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/create")
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }
    @GetMapping("/read")
    public List<Role> read() {
        return roleService.read();
    }
    @PostMapping("/update")
    public Role update(@RequestBody Role role) {
        return roleService.update(role);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Role role) {
        return roleService.delete(role);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {
        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
