package controller;

import dto.ClientDTO;
import dto.CommandeDTO;
import dto.Ligne_CommandeDTO;
import service.ICommandeService;
import service.CommandeService;
import bo.Commande;
import java.util.List;

/**
 * Controller Commande avec injection de dépendances
 */
public class CommandeController {
    
    private final ICommandeService commandeService;
    
    public CommandeController(ICommandeService commandeService) {
        this.commandeService = commandeService;
    }
    
    public CommandeController() {
        this(new CommandeService());
    }
    
    /**
     * Crée ou retourne la commande courante
     * @return CommandeDTO instance courante
     */
    public CommandeDTO createCommande() {
        return commandeService.createCommandDTO();
    }
    
    public CommandeDTO getCommande() {
        return commandeService.getCommandeDTO();
    }
    
    public void ajouterLigne(Ligne_CommandeDTO ligne) {
        commandeService.addLigne(ligne);
    }
    
    public void associerClient(ClientDTO dto) {
        commandeService.addClient(dto);
    }
    
    public void saveCommande(CommandeDTO commande) {
        commandeService.save(commande);
    }
    
    public void initialiserCommande() {
        commandeService.initialiserCommande();
    }
    
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }
}
