package service;

import dto.ProduitDTO;
import java.util.List;


public interface IProduitService {
    void create(ProduitDTO produitDTO);
    void delete(int id);
    List<ProduitDTO> retreive();
    void update(ProduitDTO produitDTO, int id);
    ProduitDTO findById(int id);
    List<ProduitDTO> findAll();
}
