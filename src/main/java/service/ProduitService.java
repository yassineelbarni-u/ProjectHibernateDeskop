package service;

import java.util.List;
import java.util.stream.Collectors;
import bo.Produit;
import dao.IProduitDAO;
import dao.ProduitDAO;
import dto.CommandeDTO;
import dto.Ligne_CommandeDTO;
import dto.ProduitDTO;

/**
 * Service Produit avec injection de dépendances
 */
public class ProduitService implements IProduitService {
    
    private final IProduitDAO produitDAO;
    
    public ProduitService(IProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }
    
    public ProduitService() {
        this(new ProduitDAO());
    }
    
    @Override
    public void create(ProduitDTO produitDTO) {
        Produit produit = toProduit(produitDTO);
        produitDAO.create(produit);
    }
    
    @Override
    public void delete(int id) {
        Produit produit = produitDAO.findById(id);
        if (produit != null) {
            produitDAO.delete(produit);
        }
    }
    
    @Override
    public List<ProduitDTO> retreive() {
        List<Produit> produits = produitDAO.findAllInStock();
        return produits.stream()
            .map(this::fromProduit)
            .collect(Collectors.toList());
    }
    
    @Override
    public void update(ProduitDTO produitDTO, int id) {
        Produit produit = toProduit(produitDTO);
        produit.setId(id);
        produitDAO.update(produit);
    }
    
    @Override
    public ProduitDTO findById(int id) {
        Produit produit = produitDAO.findById(id);
        return produit != null ? fromProduit(produit) : null;
    }
    
    @Override
    public List<ProduitDTO> findAll() {
        List<Produit> produits = produitDAO.findAll();
        return produits.stream()
            .map(this::fromProduit)
            .collect(Collectors.toList());
    }
    
    public void decrease_stock() {
        CommandeDTO cmd = new CommandeService().getCommandeDTO();
        for (Ligne_CommandeDTO l : cmd.getLignes()) {
            ProduitDTO p = l.getProduit();
            p.setQtstock(p.getQtstock() - l.getQuantite());
            update(p, p.getId());
        }
    }
    
    public ProduitDTO fromProduit(Produit produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setId(produit.getId());
        produitDTO.setQtstock(produit.getQtstock());
        produitDTO.setLibelle(produit.getLibelle());
        produitDTO.setPrix(produit.getPrix());
        return produitDTO;
    }
    
    public Produit toProduit(ProduitDTO produitDTO) {
        Produit produit = new Produit();
        produit.setId(produitDTO.getId());
        produit.setQtstock(produitDTO.getQtstock());
        produit.setLibelle(produitDTO.getLibelle());
        produit.setPrix(produitDTO.getPrix());
        return produit;
    }
}
