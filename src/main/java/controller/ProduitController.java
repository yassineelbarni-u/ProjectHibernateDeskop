package controller;

import java.util.List;
import dto.ProduitDTO;
import service.IProduitService;
import service.ProduitService;

/**
 * Controller Produit avec injection de dépendances
 */
public class ProduitController {
    
    private final IProduitService produitService;
    
    public ProduitController(IProduitService produitService) {
        this.produitService = produitService;
    }
    
    public ProduitController() {
        this(new ProduitService());
    }
    
    public List<ProduitDTO> getAllProduit() {
        return produitService.retreive();
    }
    
    public void ajouterProduit(ProduitDTO produitDTO) {
        produitService.create(produitDTO);
    }
    
    public void modifierProduit(ProduitDTO produitDTO, int id) {
        produitService.update(produitDTO, id);
    }
    
    public boolean supprimerProduit(int id) {
        try {
            produitService.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public ProduitDTO getProduitDTO(int id) {
        return produitService.findById(id);
    }
    
    public void decrease_stock() {
        ((ProduitService) produitService).decrease_stock();
    }
}
