package service;

import dto.ClientDTO;
import dto.CommandeDTO;
import dto.Ligne_CommandeDTO;
import bo.Commande;
import java.util.List;

/**
 * Interface pour le service Commande
 */
public interface ICommandeService {
    /**
     * Crée ou retourne la CommandeDTO courante
     * @return CommandeDTO instance courante
     */
    CommandeDTO createCommandDTO();
    
    /**
     * Retourne la CommandeDTO courante
     * @return CommandeDTO 
     */
    CommandeDTO getCommandeDTO();
    
    /**
     * Ajoute une ligne à la commande
     * @param ligne ligne de commande à ajouter
     */
    void addLigne(Ligne_CommandeDTO ligne);
    
    /**
     * Associe un client à la commande
     * @param dto client à associer
     */
    void addClient(ClientDTO dto);
    
    /**
     * Sauvegarde la commande en base de données
     * @param commandeDTO commande à sauvegarder
     */
    void save(CommandeDTO commandeDTO);
    
    /**
     * Initialise une nouvelle commande
     */
    void initialiserCommande();
    
    /**
     * Récupère toutes les commandes
     * @return Liste de toutes les commandes
     */
    List<Commande> getAllCommandes();
}
