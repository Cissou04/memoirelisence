package memoire.com.memoirelisence.service;


import memoire.com.memoirelisence.entite.Hopital;
import memoire.com.memoirelisence.entite.Mairie;
import memoire.com.memoirelisence.entite.Registre_declaration;
import memoire.com.memoirelisence.repository.DeclarationRepository;
import memoire.com.memoirelisence.repository.MairieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class DeclarationService {
    @Autowired
    private DeclarationRepository declarationRepository;
    @Autowired
    private MairieRepository mairieRepository;
    @Autowired
    private JavaMailSender mailSender;


    public Registre_declaration create(Registre_declaration declaration) {
        return declarationRepository.save(declaration);
    }
    public List<Registre_declaration> read() {
        return declarationRepository.findAll();
    }
    public String delete(Registre_declaration declaration) {
        String reponse="";
        boolean b=true;
        b= declarationRepository.existsById(declaration.getId());
        if (b){
            System.out.println("L'element existe");
            declarationRepository.deleteById(declaration.getId());
            reponse="L'element existant est supprimer";
        }else {
            reponse="L'element n'existe pas";
        }
        return reponse;
    }
    public Registre_declaration update(Registre_declaration declaration) {
        boolean b=true;
        b=declarationRepository.existsById(declaration.getId());
        if (b){
            System.out.println("L'element existe");
            return declarationRepository.save(declaration);
        }else {
            System.out.println("L'element n'existe pas");
            return new Registre_declaration();
        }

    }
    public Optional<Registre_declaration> getRegistre_declarationById(int id) {
        return declarationRepository.findById(id);
    }

    public boolean peutModifier(Registre_declaration declaration) {
            if (declaration.getDateEnregistrement() == null) {
                throw new IllegalArgumentException("La date d'enregistrement est null ");
            }
        long millisInDay = 24 * 60 * 60 * 1000;
        long diff = new Date().getTime() - declaration.getDateEnregistrement().getTime();
        long joursEcoules = diff / millisInDay;

        return joursEcoules <= 15 || declaration.isAutorisationAdmin();
    }

    public Registre_declaration modifierRegistre_declaration(int id, String nom, String prenom) {
        Registre_declaration declaration = declarationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Déclaration introuvable"));

        if (!peutModifier(declaration)) {
            throw new RuntimeException("Modification interdite après 15 jours ");
        }

        declaration.setNom(nom);
        declaration.setPrenom(prenom);
        return declarationRepository.save(declaration);
    }

    public void donnerAutorisationAdmin(int id) {
        Registre_declaration declaration = declarationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Déclaration introuvable"));

        declaration.setAutorisationAdmin(true);
        declarationRepository.save(declaration);
    }


    @Scheduled(cron = "0 0 0 * * ?") // Exécute tous les jours à minuit
    public void verifierDeclarations() {
        List<Registre_declaration> declarations = declarationRepository.findAll();

        for (Registre_declaration declaration : declarations) {
            if (doitEtreVerrouille(declaration)) {
                declaration.setVerrouille(true); // Bloque les modifications
                declarationRepository.save(declaration); // Sauvegarde dans la base de données
            }
        }
    }

    // Vérifie si la déclaration doit être verrouillée après 15 jours
    private boolean doitEtreVerrouille(Registre_declaration declaration) {
        long millisInDay = 24 * 60 * 60 * 1000;
        long diff = new Date().getTime() - declaration.getDateEnregistrement().getTime();
        long joursEcoules = diff / millisInDay;
        return joursEcoules >= 15;
    }


    /**
     * Enregistre une déclaration et envoie un email à la mairie si toutes les informations sont complètes.
     */
    public Registre_declaration enregistrerDeclaration(Registre_declaration declaration) {
        Registre_declaration savedDeclaration = declarationRepository.save(declaration);

        // Vérifie si toutes les informations sont complètes
        if (estDeclarationComplete(savedDeclaration)) {
            envoyerNotificationMairie(savedDeclaration);
        }

        return savedDeclaration;
    }

    /**
     * Vérifie si toutes les informations obligatoires sont renseignées.
     */
    private boolean estDeclarationComplete(Registre_declaration declaration) {
        return declaration.getNom() != null && !declaration.getNom().isEmpty()
                && declaration.getPrenom() != null && !declaration.getPrenom().isEmpty();

    }

    // Envoie un email à la mairie de la commune de l’hôpital
    private void envoyerNotificationMairie(Registre_declaration declaration) {
        Hopital hopital = declaration.getHopital();
        if (hopital != null && hopital.getCommune() != null && hopital.getCommune().getMairie() != null) {
            Mairie mairie = (Mairie) hopital.getCommune().getMairie();
            String emailMairie = mairie.getEmail();

            if (emailMairie != null && !emailMairie.isEmpty()) {
                String sujet = "Nouvelle déclaration de naissance prête à être traitée";
                String message = "Bonjour " + mairie.getNom() + ",\n\n" +
                        "Une déclaration de naissance  est disponible.\n" +
                        "Numéro de déclaration : " + declaration.getId() + "\n\n" +
                        "Merci de traiter cette demande pour établir l’acte de naissance.\n\nCordialement.";

                try {
                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(emailMairie);
                mailMessage.setSubject(sujet);
                mailMessage.setText(message);

                mailSender.send(mailMessage);

                System.out.println("Email envoyé avec succès à " + emailMairie);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
            }

        }
        }
    }
}
