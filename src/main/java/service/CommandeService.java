package service;

import java.util.stream.Collectors;
import bo.Commande;
import dao.ICommandeDAO;
import dao.CommandeDAO;
import dto.ClientDTO;
import dto.CommandeDTO;
import dto.Ligne_CommandeDTO;


public class CommandeService implements ICommandeService {
    
    private CommandeDTO cmd;
    private final ICommandeDAO commandeDAO;
    
    public CommandeService(ICommandeDAO commandeDAO) {
        this.commandeDAO = commandeDAO;
        this.cmd = new CommandeDTO();
    }
    
    public CommandeService() {
        this(new CommandeDAO());
    }
    
    /**
     * Cr\u00e9e ou retourne la CommandeDTO courante
     * @return CommandeDTO instance courante
     */
    public CommandeDTO createCommandDTO() {
        if (cmd == null) {
            cmd = new CommandeDTO();
        }
        return cmd;
    }
    
    @Override
    public CommandeDTO getCommandeDTO() {
        return cmd;
    }
    
    @Override
    public void addLigne(Ligne_CommandeDTO ligne) {
        if (cmd == null) {
            cmd = new CommandeDTO();
        }
        cmd.getLignes().add(ligne);
        
        // Recalcule le total
        float total = 0;
        for (Ligne_CommandeDTO l : cmd.getLignes()) {
            total += l.getSous_total();
        }
        cmd.setTotal(total);
    }
    
    @Override
    public void addClient(ClientDTO dto) {
        if (cmd == null) {
            cmd = new CommandeDTO();
        }
        cmd.setClient(dto);
    }
    
    @Override
    public void save(CommandeDTO commandeDTO) {
        Commande commande = toCommande(commandeDTO);
        commandeDAO.create(commande);
    }
    
    @Override
    public void initialiserCommande() {
        cmd = new CommandeDTO();
    }
    

    public Commande toCommande(CommandeDTO commandeDTO) {
        if (commandeDTO == null) {
            throw new IllegalArgumentException("CommandeDTO ne peut pas être null");
        }
        if (commandeDTO.getClient() == null) {
            throw new IllegalArgumentException("Le client doit être sélectionné avant de sauvegarder la commande");
        }
        if (commandeDTO.getLignes() == null || commandeDTO.getLignes().isEmpty()) {
            throw new IllegalArgumentException("La commande doit contenir au moins un produit");
        }
        
        if (commandeDTO.getDatecmd() == null) {
            commandeDTO.setDatecmd(new java.util.Date());
        }
        
        Commande commande = new Commande();
        commande.setDatecmd(commandeDTO.getDatecmd());
        commande.setClient(new ClientService().toClient(commandeDTO.getClient()));
        commande.setLignes(commandeDTO.getLignes().stream()
            .map(ldto -> new Ligne_CommandeService().toLigne(ldto))
            .collect(Collectors.toList()));
        commande.setTotal(commandeDTO.getTotal());
        return commande;
    }
    
    @Override
    public java.util.List<Commande> getAllCommandes() {
        return commandeDAO.findAll();
    }
}
