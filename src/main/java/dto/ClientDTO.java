package dto;


import java.util.List;




public class ClientDTO {
	private int id;
	private String nom;
	private double capital;
	private String adresse;
	
	private List<CommandeDTO> commandes;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public List<CommandeDTO> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<CommandeDTO> commandes) {
		this.commandes = commandes;
	}
	@Override
	public String toString() {
		return nom;
	}
	
	
	public ClientDTO(  String nom, double capital, String adresse) {
		super();
		
		this.nom = nom;
		this.capital = capital;
		this.adresse = adresse;

	}
	public ClientDTO() {
	}
	
}
