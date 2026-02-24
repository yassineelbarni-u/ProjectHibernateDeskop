package service;

import bo.Ligne_Commande;
import dto.CommandeDTO;
import dto.Ligne_CommandeDTO;
import dto.ProduitDTO;

/**
 * Service pour gérer les lignes de commande
 */
public class Ligne_CommandeService {
    
    public Ligne_CommandeDTO getNewLigne(int q, ProduitDTO p) {
        Ligne_CommandeDTO ligne = null;
        if (q <= p.getQtstock()) {
            ligne = new Ligne_CommandeDTO();
            ligne.setQuantite(q);
            ligne.setSous_total(ligne.getQuantite() * p.getPrix());
            ligne.setProduit(p);
        }
        return ligne;
    }
    
    public Ligne_Commande toLigne(Ligne_CommandeDTO ligneDTO) {
        Ligne_Commande ligne = new Ligne_Commande();
        ligne.setQuantite(ligneDTO.getQuantite());
        ligne.setProduit(new ProduitService().toProduit(ligneDTO.getProduit()));
        ligne.setSous_total(ligneDTO.getSous_total());
        return ligne;
    }
    
    public Ligne_CommandeDTO fromLigne(Ligne_Commande ligne) {
        Ligne_CommandeDTO ligneDTO = new Ligne_CommandeDTO();
        ligneDTO.setIdligne(ligne.getIdligne());
        ligneDTO.setQuantite(ligne.getQuantite());
        ligneDTO.setSous_total(ligne.getSous_total());
        if (ligne.getProduit() != null) {
            ligneDTO.setProduit(new ProduitService().fromProduit(ligne.getProduit()));
        }
        return ligneDTO;
    }
    
    public boolean exists(ProduitDTO p) {
        CommandeDTO cmd = new CommandeService().getCommandeDTO();
        boolean trouve = false;
        
        for (Ligne_CommandeDTO l : cmd.getLignes()) {
            if (l.getProduit().getId() == p.getId()) {
                trouve = true;
                break;
            }
        }
        
        return trouve;
    }
}
