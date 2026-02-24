package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ProduitController;
import dto.ProduitDTO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

public class Form_Produit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtLibelle;
	private JTextField txtPrix;
	private JTextField txtQtstock;
	private JButton btnNouveau;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnRechercher;
	private JPanel panelTitle;
	private JPanel panelForm;

	public void effacerText() {
		txtId.setText("");
		txtLibelle.setText("");
		txtPrix.setText("");
		txtQtstock.setText("");
	}
	
	public void activerText() {
		txtLibelle.setEnabled(true);
		txtPrix.setEnabled(true);
		txtQtstock.setEnabled(true);
	}
	
	public void desactiverText() {
		txtLibelle.setEnabled(false);
		txtPrix.setEnabled(false);
		txtQtstock.setEnabled(false);
	}
	
	public void modeRecherche() {
		txtId.setEnabled(true);
		desactiverText();
	}
	
	public void modeAjout() {
		txtId.setEnabled(false);
		activerText();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_Produit frame = new Form_Produit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Form_Produit() {
		setTitle("Gestion des Produits");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelTitle = new JPanel();
		panelTitle.setBackground(new Color(41, 128, 185));
		panelTitle.setBounds(0, 0, 584, 60);
		contentPane.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Gestion des Produits");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitle.setBounds(180, 15, 300, 30);
		panelTitle.add(lblTitle);
		
		panelForm = new JPanel();
		panelForm.setBackground(Color.WHITE);
		panelForm.setBorder(new LineBorder(new Color(189, 195, 199), 1));
		panelForm.setBounds(30, 80, 524, 280);
		contentPane.add(panelForm);
		panelForm.setLayout(null);
		
		JLabel lblId = new JLabel("ID (Auto):");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblId.setForeground(new Color(127, 140, 141));
		lblId.setBounds(30, 30, 120, 25);
		panelForm.add(lblId);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtId.setEnabled(false);
		txtId.setBackground(new Color(236, 240, 241));
		txtId.setBounds(160, 30, 320, 30);
		panelForm.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblLibelle = new JLabel("Libellé:");
		lblLibelle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLibelle.setBounds(30, 80, 120, 25);
		panelForm.add(lblLibelle);
		
		txtLibelle = new JTextField();
		txtLibelle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtLibelle.setEnabled(false);
		txtLibelle.setBounds(160, 80, 320, 30);
		panelForm.add(txtLibelle);
		txtLibelle.setColumns(10);
		
		JLabel lblPrix = new JLabel("Prix:");
		lblPrix.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPrix.setBounds(30, 130, 120, 25);
		panelForm.add(lblPrix);
		
		txtPrix = new JTextField();
		txtPrix.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPrix.setEnabled(false);
		txtPrix.setBounds(160, 130, 320, 30);
		panelForm.add(txtPrix);
		txtPrix.setColumns(10);
		
		JLabel lblQtstock = new JLabel("Quantité en Stock:");
		lblQtstock.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblQtstock.setBounds(30, 180, 120, 25);
		panelForm.add(lblQtstock);
		
		txtQtstock = new JTextField();
		txtQtstock.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtQtstock.setEnabled(false);
		txtQtstock.setBounds(160, 180, 320, 30);
		panelForm.add(txtQtstock);
		txtQtstock.setColumns(10);
		
		btnNouveau = new JButton("Nouveau");
		btnNouveau.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNouveau.setBackground(new Color(52, 152, 219));
		btnNouveau.setForeground(Color.WHITE);
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeAjout();
				effacerText();
			}
		});
		btnNouveau.setBounds(30, 230, 95, 35);
		panelForm.add(btnNouveau);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAjouter.setBackground(new Color(46, 204, 113));
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProduitDTO produitDTO = new ProduitDTO();
					// Ne pas définir l'ID - il sera généré automatiquement
					produitDTO.setLibelle(txtLibelle.getText());
					produitDTO.setPrix(Float.parseFloat(txtPrix.getText()));
					produitDTO.setQtstock(Integer.parseInt(txtQtstock.getText()));
					
					ProduitController produitController = new ProduitController();
					produitController.ajouterProduit(produitDTO);
					
					JOptionPane.showMessageDialog(null, "Produit ajouté avec succès!\nL'ID a été généré automatiquement.", "Succès", JOptionPane.INFORMATION_MESSAGE);
					modeRecherche();
					effacerText();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Erreur: Veuillez vérifier le format des nombres!", "Erreur", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAjouter.setBounds(135, 230, 95, 35);
		panelForm.add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnModifier.setBackground(new Color(241, 196, 15));
		btnModifier.setForeground(Color.WHITE);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez d'abord rechercher un produit!", "Attention", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					ProduitDTO produitDTO = new ProduitDTO();
					int id = Integer.parseInt(txtId.getText());
					produitDTO.setId(id);
					produitDTO.setLibelle(txtLibelle.getText());
					produitDTO.setPrix(Float.parseFloat(txtPrix.getText()));
					produitDTO.setQtstock(Integer.parseInt(txtQtstock.getText()));
					
					ProduitController produitController = new ProduitController();
					produitController.modifierProduit(produitDTO, id);
					
					JOptionPane.showMessageDialog(null, "Produit modifié avec succès!");
					modeRecherche();
					effacerText();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Erreur: Veuillez vérifier le format des nombres!", "Erreur", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erreur lors de la modification: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifier.setBounds(240, 230, 95, 35);
		panelForm.add(btnModifier);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSupprimer.setBackground(new Color(231, 76, 60));
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez d'abord rechercher un produit!", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int confirmation = JOptionPane.showConfirmDialog(null, 
					"Êtes-vous sûr de vouloir supprimer ce produit?", 
					"Confirmation", 
					JOptionPane.YES_NO_OPTION);
				
				if (confirmation == JOptionPane.YES_OPTION) {
					try {
						ProduitController produitController = new ProduitController();
						boolean success = produitController.supprimerProduit(Integer.parseInt(txtId.getText()));
						
						if (success) {
							JOptionPane.showMessageDialog(null, "Produit supprimé avec succès!");
							modeRecherche();
							effacerText();
						} else {
							JOptionPane.showMessageDialog(null, "Erreur lors de la suppression!", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSupprimer.setBounds(345, 230, 95, 35);
		panelForm.add(btnSupprimer);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRechercher.setBackground(new Color(155, 89, 182));
		btnRechercher.setForeground(Color.WHITE);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setEnabled(true);
				if (txtId.getText().isEmpty()) {
					txtId.requestFocus();
					JOptionPane.showMessageDialog(null, "Veuillez saisir un ID!", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					ProduitController produitController = new ProduitController();
					ProduitDTO produitDTO = produitController.getProduitDTO(Integer.parseInt(txtId.getText()));
					
					if (produitDTO != null) {
						txtLibelle.setText(produitDTO.getLibelle());
						txtPrix.setText(String.valueOf(produitDTO.getPrix()));
						txtQtstock.setText(String.valueOf(produitDTO.getQtstock()));
						activerText();
						txtId.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Produit non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID invalide! Veuillez saisir un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRechercher.setBounds(205, 380, 170, 40);
		contentPane.add(btnRechercher);
		
		JButton btnFermer = new JButton("Fermer");
		btnFermer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnFermer.setBackground(new Color(127, 140, 141));
		btnFermer.setForeground(Color.WHITE);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFermer.setBounds(30, 380, 120, 40);
		contentPane.add(btnFermer);
	}
}
