package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ClientController;
import dto.ClientDTO;
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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Form_Client extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtCapital;
	private JTextField txtAdresse;
	private JButton btnNouveau;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnRafraichir;
	private JPanel panelTitle;
	private JPanel panelForm;
	private JTable tableClients;
	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTextField txtRecherche;

	public void effacerText() {
		txtId.setText("");
		txtNom.setText("");
		txtCapital.setText("");
		txtAdresse.setText("");
		tableClients.clearSelection();
	}
	
	public void activerText() {
		txtNom.setEnabled(true);
		txtCapital.setEnabled(true);
		txtAdresse.setEnabled(true);
	}
	
	public void desactiverText() {
		txtNom.setEnabled(false);
		txtCapital.setEnabled(false);
		txtAdresse.setEnabled(false);
	}
	
	public void modeSelection() {
		txtId.setEnabled(false);
		desactiverText();
		btnAjouter.setEnabled(false);
		btnModifier.setEnabled(false);
		btnSupprimer.setEnabled(false);
	}
	
	public void modeAjout() {
		txtId.setEnabled(false);
		activerText();
		btnAjouter.setEnabled(true);
		btnModifier.setEnabled(false);
		btnSupprimer.setEnabled(false);
		tableClients.clearSelection();
	}
	
	public void modeModification() {
		txtId.setEnabled(false);
		activerText();
		btnAjouter.setEnabled(false);
		btnModifier.setEnabled(true);
		btnSupprimer.setEnabled(true);
	}
	
	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	private void chargerClients() {
		tableModel.setRowCount(0);
		try {
			List<ClientDTO> clients = new ClientController().getAllClients();
			for (ClientDTO client : clients) {
				tableModel.addRow(new Object[] {
					client.getId(),
					client.getNom(),
					client.getCapital(),
					client.getAdresse()
				});
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, 
				"Erreur lors du chargement des clients: " + ex.getMessage(), 
				"Erreur", 
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void filtrerTableau() {
		String texte = txtRecherche.getText();
		if (texte.trim().isEmpty()) {
			sorter.setRowFilter(null);
		} else {
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texte));
		}
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
					Form_Client frame = new Form_Client();
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
	public Form_Client() {
		setTitle("Gestion des Clients");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Header Panel
		panelTitle = new JPanel();
		panelTitle.setBackground(new Color(52, 152, 219));
		panelTitle.setBounds(0, 0, 884, 60);
		contentPane.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Gestion des Clients");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitle.setBounds(330, 15, 300, 30);
		panelTitle.add(lblTitle);
		
		// Search Panel
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setBorder(new LineBorder(new Color(189, 195, 199), 1));
		searchPanel.setBounds(30, 80, 824, 70);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel lblRecherche = new JLabel("Rechercher:");
		lblRecherche.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblRecherche.setBounds(20, 20, 100, 25);
		searchPanel.add(lblRecherche);
		
		txtRecherche = new JTextField();
		txtRecherche.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtRecherche.setBounds(120, 20, 450, 30);
		searchPanel.add(txtRecherche);
		txtRecherche.setColumns(10);
		
		txtRecherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrerTableau();
			}
		});
		
		btnRafraichir = new JButton("Rafraîchir");
		btnRafraichir.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRafraichir.setBackground(new Color(155, 89, 182));
		btnRafraichir.setForeground(Color.WHITE);
		btnRafraichir.setFocusPainted(false);
		btnRafraichir.setBounds(590, 20, 110, 30);
		searchPanel.add(btnRafraichir);
		
		btnNouveau = new JButton("Nouveau");
		btnNouveau.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNouveau.setBackground(new Color(52, 152, 219));
		btnNouveau.setForeground(Color.WHITE);
		btnNouveau.setFocusPainted(false);
		btnNouveau.setBounds(710, 20, 100, 30);
		searchPanel.add(btnNouveau);
		
		// Table Panel
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(Color.WHITE);
		tablePanel.setBorder(new LineBorder(new Color(189, 195, 199), 1));
		tablePanel.setBounds(30, 165, 824, 250);
		contentPane.add(tablePanel);
		tablePanel.setLayout(null);
		
		String[] colonnes = {"ID", "Nom", "Capital", "Adresse"};
		tableModel = new DefaultTableModel(colonnes, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tableClients = new JTable(tableModel);
		tableClients.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tableClients.setRowHeight(25);
		tableClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClients.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		tableClients.getTableHeader().setBackground(new Color(52, 152, 219));
		tableClients.getTableHeader().setForeground(Color.WHITE);
		
		tableClients.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableClients.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableClients.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableClients.getColumnModel().getColumn(3).setPreferredWidth(300);
		
		sorter = new TableRowSorter<>(tableModel);
		tableClients.setRowSorter(sorter);
		
		JScrollPane scrollPane = new JScrollPane(tableClients);
		scrollPane.setBounds(10, 10, 804, 230);
		tablePanel.add(scrollPane);
		
		tableClients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && tableClients.getSelectedRow() != -1) {
					int selectedRow = tableClients.getSelectedRow();
					txtId.setText(tableClients.getValueAt(selectedRow, 0).toString());
					txtNom.setText(tableClients.getValueAt(selectedRow, 1).toString());
					txtCapital.setText(tableClients.getValueAt(selectedRow, 2).toString());
					txtAdresse.setText(tableClients.getValueAt(selectedRow, 3).toString());
					modeModification();
				}
			}
		});
		
		// Form Panel
		panelForm = new JPanel();
		panelForm.setBackground(Color.WHITE);
		panelForm.setBorder(new LineBorder(new Color(189, 195, 199), 1));
		panelForm.setBounds(30, 430, 824, 180);
		contentPane.add(panelForm);
		panelForm.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblId.setForeground(new Color(127, 140, 141));
		lblId.setBounds(30, 20, 120, 25);
		panelForm.add(lblId);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtId.setEnabled(false);
		txtId.setBackground(new Color(236, 240, 241));
		txtId.setBounds(160, 20, 250, 30);
		panelForm.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNom.setBounds(440, 20, 120, 25);
		panelForm.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNom.setEnabled(false);
		txtNom.setBounds(560, 20, 240, 30);
		panelForm.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblCapital = new JLabel("Capital:");
		lblCapital.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCapital.setBounds(30, 70, 120, 25);
		panelForm.add(lblCapital);
		
		txtCapital = new JTextField();
		txtCapital.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCapital.setEnabled(false);
		txtCapital.setBounds(160, 70, 250, 30);
		panelForm.add(txtCapital);
		txtCapital.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAdresse.setBounds(440, 70, 120, 25);
		panelForm.add(lblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtAdresse.setEnabled(false);
		txtAdresse.setBounds(560, 70, 240, 30);
		panelForm.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		// Action Buttons
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAjouter.setBackground(new Color(46, 204, 113));
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFocusPainted(false);
		btnAjouter.setEnabled(false);
		btnAjouter.setBounds(160, 120, 140, 40);
		panelForm.add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnModifier.setBackground(new Color(241, 196, 15));
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setFocusPainted(false);
		btnModifier.setEnabled(false);
		btnModifier.setBounds(340, 120, 140, 40);
		panelForm.add(btnModifier);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSupprimer.setBackground(new Color(231, 76, 60));
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFocusPainted(false);
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBounds(520, 120, 140, 40);
		panelForm.add(btnSupprimer);
		
		// Footer Button
		JButton btnFermer = new JButton("Fermer");
		btnFermer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnFermer.setBackground(new Color(127, 140, 141));
		btnFermer.setForeground(Color.WHITE);
		btnFermer.setFocusPainted(false);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFermer.setBounds(370, 625, 140, 35);
		contentPane.add(btnFermer);
		
		// Charger les clients au démarrage
		chargerClients();
		modeSelection();
		
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chargerClients();
				effacerText();
				modeSelection();
				txtRecherche.setText("");
			}
		});
		
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeAjout();
				effacerText();
			}
		});
		
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNom.getText().trim().isEmpty() || txtCapital.getText().trim().isEmpty() || txtAdresse.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!isDouble(txtCapital.getText())) {
					JOptionPane.showMessageDialog(null, "Saisie invalide! Le capital doit être un nombre réel.", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					new ClientController().ajouterClient(
						new ClientDTO(txtNom.getText(), Double.valueOf(txtCapital.getText()), txtAdresse.getText())
					);
					JOptionPane.showMessageDialog(null, "Client ajouté avec succès!\nL'ID a été généré automatiquement.", "Succès", JOptionPane.INFORMATION_MESSAGE);
					chargerClients();
					effacerText();
					modeSelection();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez d'abord sélectionner un client!", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (txtNom.getText().trim().isEmpty() || txtCapital.getText().trim().isEmpty() || txtAdresse.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!isDouble(txtCapital.getText())) {
					JOptionPane.showMessageDialog(null, "Saisie invalide! Le capital doit être un nombre réel.", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					new ClientController().modifierClient(
						new ClientDTO(txtNom.getText(), Double.valueOf(txtCapital.getText()), txtAdresse.getText()),
						Integer.valueOf(txtId.getText())
					);
					JOptionPane.showMessageDialog(null, "Client modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
					chargerClients();
					effacerText();
					modeSelection();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erreur lors de la modification: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez d'abord sélectionner un client!", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int i = JOptionPane.showConfirmDialog(null, 
					"Êtes-vous sûr de vouloir supprimer ce client?", 
					"Confirmation", 
					JOptionPane.YES_NO_OPTION);
				
				if (i == JOptionPane.YES_OPTION) {
					try {
						boolean res = new ClientController().supprimerClient(Integer.valueOf(txtId.getText()));
						if (res) {
							JOptionPane.showMessageDialog(null, "Client supprimé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
							chargerClients();
							effacerText();
							modeSelection();
						} else {
							JOptionPane.showMessageDialog(null, 
								"Impossible de supprimer un client qui a déjà passé des commandes.", 
								"Erreur", 
								JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
