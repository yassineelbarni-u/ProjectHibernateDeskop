package dao;

import bo.Client;
import java.util.List;

/**
 * Interface pour les opérations DAO Client
 * Permet le découplage entre service et implémentation
 */
public interface IClientDAO {
    
    void create(Client client);
    void update(Client client);
    boolean delete(Client client);
    Client findById(int id);
    List<Client> findAll();
}
