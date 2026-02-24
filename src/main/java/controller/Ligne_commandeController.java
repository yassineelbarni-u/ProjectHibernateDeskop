package controller;

import dto.Ligne_CommandeDTO;
import dto.ProduitDTO;
import service.Ligne_CommandeService;

public class Ligne_commandeController {
public Ligne_CommandeDTO newLigne(int q,ProduitDTO p) {
	return new Ligne_CommandeService().getNewLigne(q,p);
	
}
public boolean exists(ProduitDTO p) {
	return new Ligne_CommandeService().exists(p);
	
}
}
