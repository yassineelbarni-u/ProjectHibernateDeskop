package controller;

import java.util.List;
import dto.ClientDTO;
import service.IClientService;
import service.ClientService;

/**
 * Controller Client avec injection de dépendances
 */
public class ClientController {
    
    private final IClientService clientService;
    
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }
    
    public ClientController() {
        this(new ClientService());
    }
    
    public void ajouterClient(ClientDTO clientDTO) {
        clientService.create(clientDTO);
    }
    
    public void modifierClient(ClientDTO clientDTO, int id) {
        clientService.update(clientDTO, id);
    }
    
    public boolean supprimerClient(int id) {
        return clientService.delete(id);
    }
    
    public ClientDTO getClientDTO(int id) {
        return clientService.getClientDTO(id);
    }
    
    public List<ClientDTO> getAllClients() {
        return clientService.findAll();
    }
}
