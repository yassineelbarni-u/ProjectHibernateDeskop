package service;

import dto.ClientDTO;
import java.util.List;

/**
 * Interface pour le service Client
 * Permet le découplage et facilite les tests
 */
public interface IClientService {
    void create(ClientDTO clientDTO);
    void update(ClientDTO clientDTO, int id);
    boolean delete(int id);
    ClientDTO getClientDTO(int id);
    List<ClientDTO> findAll();
}
