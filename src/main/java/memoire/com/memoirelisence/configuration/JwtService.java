package memoire.com.memoirelisence.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import memoire.com.memoirelisence.entite.Jwt;
import memoire.com.memoirelisence.entite.Utilisateur;
import memoire.com.memoirelisence.repository.JwtRepository;
import memoire.com.memoirelisence.service.UtilisateurService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class JwtService {
    public static final String BEARER = "Bearer ";
    private final String ENCRIPTION_KEY="7f70b73f1169a2b0fa78fada2953220b2386d6c42fe9a25631b64009422c9e83";
    private JwtRepository jwtRepository;
    private UtilisateurService utilisateurService;

    public Jwt tokenByValue(String value){
        return this.jwtRepository.findByValeurAndDesactiveAndExpire(
                value,
                false,
                false
        ).orElseThrow(()-> new RuntimeException("Token invalide ou inconnu"));
    }
    public Map<String,String> generate (String username){
        Utilisateur utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(username);
        final Map<String,String> jwtMap = this.generateJwt(utilisateur);

        final Jwt jwt = Jwt
                .builder()
                .valeur(jwtMap.get(BEARER))
                .desactive(false)
                .expire(false)
                .utilisateur(utilisateur)
                .build();
        this.jwtRepository.save(jwt);
        return jwtMap;
    }

    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate= getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return this.getClaim(token, Claims::getExpiration);
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

   private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
   }

    private Map<String,String> generateJwt(Utilisateur utilisateur){
        final long currentTime=System.currentTimeMillis();
        final long expirationTime=currentTime + 30 * 60 * 1000;

        final Map<String,Object> claims= Map.of(
                "id", utilisateur.getId(),
                "nom", utilisateur.getNom(),
                "role", utilisateur.getRole().getName(),
               Claims.EXPIRATION, new Date(expirationTime),
               Claims.SUBJECT, utilisateur.getEmail()
        );

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(utilisateur.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of(BEARER,bearer);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public  Map<String,String> generateResetPasswordToken(String email){
        final Map<String,Object> claims = new HashMap<>();
        claims.put("email", email);

        String token= Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 600000))//Expire dans 10 minutes
                .signWith(getKey(), SignatureAlgorithm.HS256)// Assurez-vous que la clé correspond à cet algorithme
                .compact();
        return Map.of("token",token);
    }

    public String getEmailFromToken(String token){
        //Mesure temporaire: log du token
        System.out.println("Vérification du token: "+token);
        if (token==null||token.isEmpty()){
            throw new RuntimeException("Le token recu est vide ou null");
        }

        Claims claims = this.getAllClaims(token);//decoder les claims
        return claims.getSubject();//extrait l'email ou l'identifiant de l'utilisateur
    }
}
