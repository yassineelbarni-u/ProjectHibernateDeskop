package dto;





public class Ligne_CommandeDTO {
	

private int idligne;

private int quantite;


private float sous_total;


private ProduitDTO produitdto;


private CommandeDTO commandedto;


public int getIdligne() {
	return idligne;
}
public void setIdligne(int idligne) {
	this.idligne = idligne;
}
public int getQuantite() {
	return quantite;
}
public void setQuantite(int quantite) {
	this.quantite = quantite;
}
public ProduitDTO getProduit() {
	return produitdto;
}
public void setProduit(ProduitDTO produitdto) {
	this.produitdto = produitdto;
}
public CommandeDTO getCommande() {
	return commandedto;
}
public void setCommande(CommandeDTO commandedto) {
	this.commandedto = commandedto;
}
@Override
public String toString() {
	return "Ligne_Commande [id=" + idligne + ", quantite=" + quantite + "]";
}
public Ligne_CommandeDTO(int idligne, int quantite) {
	super();
	this.idligne = idligne;
	this.quantite = quantite;
}
public Ligne_CommandeDTO() {
	super();
}
public float getSous_total() {
	return sous_total;
}

public void setSous_total(float sous_total) {
	this.sous_total = sous_total;
}
}
