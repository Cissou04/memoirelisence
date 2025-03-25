package memoire.com.memoirelisence.auth;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import memoire.com.memoirelisence.configuration.JwtService;
import memoire.com.memoirelisence.dto.AuthentificationDTO;
import memoire.com.memoirelisence.repository.UtilisateurRepository;
import memoire.com.memoirelisence.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Transactional
@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class LoginJava {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UtilisateurRepository utilisateurRepository;
    private UtilisateurService utilisateurService;
    private JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<?> connexion(@RequestBody AuthentificationDTO authentificationDTO){
        if (authentificationDTO.getUsername() == null || authentificationDTO.getMot_de_passe() == null){
            return ResponseEntity.badRequest().body("Nom d'utilisateur et mot de passe requis");
        }

            try {
                final Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authentificationDTO.getUsername(), authentificationDTO.getMot_de_passe())
                );
                if (authentication.isAuthenticated()){
                    Map<String, String> token = this.jwtService.generate(authentificationDTO.getUsername());
                    return ResponseEntity.ok(token);
                }else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("echec de l'authentification");
                }
            } catch (BadCredentialsException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur  de  connexion" + e.getMessage());
            }
    }
}
