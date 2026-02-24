package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ClientDTO;
import dto.Ligne_CommandeDTO;


public class CommandeDTO {
	
	private int idcmd;

	private Date datecmd;

	
	private float total;

	
	private ClientDTO clientdto;

	
	private List<Ligne_CommandeDTO> lignes=new ArrayList<Ligne_CommandeDTO>();

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
	public ClientDTO getClient() {
		return clientdto;
	}
	public void setClient(ClientDTO clientdto) {
		this.clientdto= clientdto;
	}
	public CommandeDTO(int idcmd, Date datecmd) {
		super();
		this.idcmd = idcmd;
		this.datecmd = datecmd;
		
	}





	@Override
	public String toString() {
		return "Commande [idcmd=" + idcmd + ", datecmd=" + datecmd + "]";
	}
	
	/**
	 * Constructeur par défaut
	 * Initialise automatiquement la date de commande à la date du jour
	 */
	public CommandeDTO() {
		super();
		this.datecmd = new Date();
	}
	public CommandeDTO(Date date) {
		datecmd=date;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<Ligne_CommandeDTO> getLignes() {
		return lignes;
	}
	public void setLignes(List<Ligne_CommandeDTO> lignes) {
		this.lignes = lignes;
	}

	

}
