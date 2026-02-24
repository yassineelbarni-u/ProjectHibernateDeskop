package bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
@Entity
public class Commande implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idcmd;
private Date datecmd;

@Transient
private float total;

// Relation avec Client - Un client peut avoir plusieurs commandes
@ManyToOne
@JoinColumn(name="id_client", referencedColumnName="id")
private Client client;

// Liste des lignes de commande associées
@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Ligne_Commande> lignes;

public int getIdcmd() {
	return idcmd;
}
public void setIdcmd(int idcmd) {
	this.idcmd = idcmd;
}
public Date getDatecmd() {
	return datecmd;
}

public void setDatecmd(Date datecmd) {
	this.datecmd = datecmd;
}

public Client getClient() {
	return client;
}

public void setClient(Client client) {
	this.client = client;
}

public Commande(int idcmd, Date datecmd, Client client) {
	super();
	this.idcmd = idcmd;
	this.datecmd = datecmd;
	this.client = client;
}

@Override
public String toString() {
	return "Commande [idcmd=" + idcmd + ", datecmd=" + datecmd + ", client=" + client + ", total=" + total + "]";
}

public Commande() {
	super();
}

public float getTotal() {
	return total;
}
public void setTotal(float total) {
	this.total = total;
}

public List<Ligne_Commande> getLignes() {
	return lignes;
}

public void setLignes(List<Ligne_Commande> lignes) {
	this.lignes = lignes;
}

public double calculerTotal() {
	if (lignes == null || lignes.isEmpty()) {
		return 0.0;
	}
	double sum = 0.0;
	for (Ligne_Commande ligne : lignes) {
		sum += ligne.getQuantite() * ligne.getProduit().getPrix();
	}
	return sum;
}

}
