package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import controller.ClientController;
import controller.CommandeController;
import controller.Ligne_commandeController;
import controller.ProduitController;
import dto.ClientDTO;
import dto.CommandeDTO;
import dto.ProduitDTO;
import dto.Ligne_CommandeDTO;
import exception.ClientNotFoundException;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;


import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;

public class Form_Commande extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<ClientDTO> comboBoxClient;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox comboBox;
	private JTable table;
	private JTable table_1;
	private JTextField textField_8;
	private JFormattedTextField formattedTextField;
	
	private CommandeController commandeController;
	
	private void remplircombo() {
		comboBox.removeAllItems();
		comboBox.addItem("Sélectionner un produit");
		for (ProduitDTO p:new ProduitController().getAllProduit())
		 comboBox.addItem(p);
	}
	
	private void remplirComboClient() {
		comboBoxClient.removeAllItems();
		comboBoxClient.addItem(null);
		for (ClientDTO c : new ClientController().getAllClients())
			comboBoxClient.addItem(c);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_Commande frame = new Form_Commande();
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
	public Form_Commande() {
		commandeController = new CommandeController();
		
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			

			@Override
			public void windowOpened(WindowEvent e) {
				CommandeDTO dto = commandeController.createCommande();	
				remplircombo();
				remplirComboClient();
			}
			
		});
		setTitle("Gestion de Commandes");
		setBounds(100, 100, 764, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date Commande :");
		lblNewLabel.setBounds(39, 63, 103, 13);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(37, 103, 676, 155);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Sélectionner Client");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(15, 20, 130, 20);
		panel.add(lblNewLabel_1);
		
		comboBoxClient = new JComboBox<ClientDTO>();
		comboBoxClient.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxClient.setBounds(155, 18, 495, 28);
		comboBoxClient.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ClientDTO dto = (ClientDTO) comboBoxClient.getSelectedItem();
					if (dto != null) {
						textField_2.setText(dto.getNom());
						textField_3.setText(String.valueOf(dto.getCapital()));
						textField_4.setText(dto.getAdresse());
						commandeController.associerClient(dto);
					} else {
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
					}
				}
			}
		});
		panel.add(comboBoxClient);
		
		JLabel lblNewLabel_2 = new JLabel("Nom Client");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(15, 60, 90, 20);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.BOLD, 13));
		textField_2.setEnabled(false);
		textField_2.setBackground(new Color(240, 240, 240));
		textField_2.setBounds(110, 58, 240, 28);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Capital");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(370, 60, 70, 20);
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.BOLD, 13));
		textField_3.setEnabled(false);
		textField_3.setBackground(new Color(240, 240, 240));
		textField_3.setBounds(450, 58, 200, 28);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Adresse");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(15, 100, 90, 20);
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.BOLD, 13));
		textField_4.setEnabled(false);
		textField_4.setBackground(new Color(240, 240, 240));
		textField_4.setBounds(110, 98, 540, 28);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Nouvelle Commande");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(241, 23, 339, 13);
		contentPane.add(lblNewLabel_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(39, 275, 674, 119);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Quantité");
		lblNewLabel_6.setBounds(444, 68, 45, 13);
		panel_1.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setText("1");
		textField_5.setBounds(499, 65, 64, 19);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		 comboBox = new JComboBox();
		 comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sélectionner un produit"}));
		 comboBox.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		if (comboBox.getSelectedItem()!=comboBox.getItemAt(0)) {
		 		ProduitDTO dto=(ProduitDTO) comboBox.getSelectedItem();
		 		textField_6.setText(dto.getLibelle());
		 		textField_7.setText(String.valueOf(dto.getPrix()));
		 		}
		 		else {
		 			textField_6.setText("");
		 		textField_7.setText("");
		 		}
		 	}
		 });

		comboBox.setBounds(129, 10, 438, 21);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("Produits en stock");
		lblNewLabel_7.setBounds(10, 14, 118, 13);
		panel_1.add(lblNewLabel_7);
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel) table_1.getModel();
				if(comboBox.getSelectedIndex()!=0) 
				{
					
					ProduitDTO pdto=(ProduitDTO)comboBox.getSelectedItem();
					if(new Ligne_commandeController().exists(pdto))
						JOptionPane.showMessageDialog(null, "Produit déjà ajouté à votre commande!","Message",1);
					else 
					{
						int qt=Integer.valueOf(textField_5.getText());
						
						Ligne_CommandeDTO ligne=new Ligne_commandeController().newLigne(qt, pdto);
						if(ligne!=null) {
						Vector<Object> row=new Vector<Object>();
						row.add(ligne.getProduit().getId());
						row.add(ligne.getProduit().getLibelle());
						row.add(ligne.getQuantite()); 
						row.add(ligne.getProduit().getPrix()); 
						row.add(ligne.getSous_total());
						model.addRow(row);
						
						commandeController.ajouterLigne(ligne);
						textField_8.setText(String.valueOf(commandeController.getCommande().getTotal()));
						}
						else
							JOptionPane.showMessageDialog(null,"Quantité insuffisante en stocl!","Attention" ,0);
					}
				}
				else
					JOptionPane.showMessageDialog(null,"Vous devez sélectionner un produit!","Message",1);
			}
		});
		btnNewButton_1.setBounds(579, 64, 85, 21);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_8 = new JLabel("Désignation");
		lblNewLabel_8.setBounds(10, 68, 132, 13);
		panel_1.add(lblNewLabel_8);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Dialog", Font.BOLD, 14));
		textField_6.setEnabled(false);
		textField_6.setBounds(83, 65, 210, 19);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Prix");
		lblNewLabel_9.setBounds(303, 68, 45, 13);
		panel_1.add(lblNewLabel_9);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Dialog", Font.BOLD, 14));
		textField_7.setEnabled(false);
		textField_7.setBounds(338, 65, 96, 19);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 405, 670, 200);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		
		Vector<Vector<Object>> data=new Vector<Vector<Object>>();
		
		Vector<String> titles=new Vector<String>();
		List<String> liste=List.of("R\u00E9f\u00E9rence", "D\u00E9signation", "QTE", "Prix", "Sous Total");
		titles.addAll(liste);
		
		table_1.setModel(new DefaultTableModel(data, titles));
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel_10 = new JLabel("Total Commande");
		lblNewLabel_10.setBounds(507, 615, 95, 13);
		contentPane.add(lblNewLabel_10);
		
		textField_8 = new JTextField();
		textField_8.setForeground(new Color(0, 0, 255));
		textField_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_8.setEditable(false);
		textField_8.setBounds(617, 615, 96, 19);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Enregistrer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_2.getText().equals("") || table_1.getModel().getRowCount()==0) {
					JOptionPane.showMessageDialog(null, 
						"Merci de sélectionner un client et d'ajouter des produits !",
						"Message",
						1);
				} else {
						try {
							commandeController.saveCommande(commandeController.getCommande());
							JOptionPane.showMessageDialog(null, 
								"Commande enregistrée avec succès !",
								"Succès",
								1);
							new ProduitController().decrease_stock();
							remplircombo();
							
							commandeController.initialiserCommande();
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							formattedTextField.setText(sdf.format(new Date()));
							
						comboBoxClient.setSelectedIndex(0);
							textField_2.setText("");
							textField_3.setText("");
							textField_4.setText("");
							textField_5.setText("1");
							textField_6.setText("");
							textField_7.setText("");
							textField_8.setText("");
							comboBox.setSelectedIndex(0);
							DefaultTableModel model=(DefaultTableModel)table_1.getModel();
							model.setRowCount(0);
							
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, 
								" Erreur lors de l'enregistrement : " + ex.getMessage(),
								"Erreur",
								0);
							ex.printStackTrace();
						}
					}

			}
		});
		btnNewButton_2.setBounds(605, 652, 105, 21);
		contentPane.add(btnNewButton_2);
		
		formattedTextField = new JFormattedTextField();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		formattedTextField.setText(sdf.format(new Date()));
		formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		formattedTextField.setHorizontalAlignment(JTextField.CENTER);
		formattedTextField.setBounds(152, 60, 120, 20);
		formattedTextField.setEditable(false);
		formattedTextField.setBackground(new Color(240, 240, 240));
}

}