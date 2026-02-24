package service;

import java.util.List;
import java.util.stream.Collectors;
import bo.Client;
import dao.IClientDAO;
import dao.ClientDAO;
import dto.ClientDTO;
import exception.ClientNotFoundException;


public class ClientService implements IClientService {

    private final IClientDAO clientDAO;

    public ClientService(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    
    public ClientService() {
        this(new ClientDAO());
    }
    
    @Override
    public void create(ClientDTO clientDTO) {
        Client client = toClient(clientDTO);
        clientDAO.create(client); // delegue la création au DAO
    }
    
    @Override
    public void update(ClientDTO clientDTO, int id) {
        Client existingClient = clientDAO.findById(id);
        if (existingClient != null) {
            // Conversion DTO  vers Entity
            Client client = toClient(clientDTO);
            client.setId(id);
            clientDAO.update(client);
        }
    }

    
    @Override
    public boolean delete(int id) {
        Client client = clientDAO.findById(id);
        if (client != null) {
            return clientDAO.delete(client);
        }
        return false;
    }
    
    @Override
    public ClientDTO getClientDTO(int id) {
        Client client = clientDAO.findById(id);
        if (client != null) {
            return fromClient(client);
        }
        throw new ClientNotFoundException("Client introuvable! Il faut choisir un autre Id.");
    }
    
    @Override
    public List<ClientDTO> findAll() {
        List<Client> clients = clientDAO.findAll();
        // conversion client
        return clients.stream()
            .map(this::fromClient)
            .collect(Collectors.toList());
    }
    
    public Client toClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setNom(clientDTO.getNom());
        client.setCapital(clientDTO.getCapital());
        client.setAdresse(clientDTO.getAdresse());
        return client;
    }
    
    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setNom(client.getNom());
        clientDTO.setCapital(client.getCapital());
        clientDTO.setAdresse(client.getAdresse());
        return clientDTO;
    }
}
