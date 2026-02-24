package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import bo.Commande;
import controller.CommandeController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class Form_Historique_Commandes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtRecherche;
	private TableRowSorter<DefaultTableModel> sorter;
	private CommandeController commandeController;

	public Form_Historique_Commandes() {
		commandeController = new CommandeController();
		
		setTitle("Historique des Commandes");
		setResizable(false);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(236, 240, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel headerPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth(), h = getHeight();
				Color color1 = new Color(52, 152, 219);
				Color color2 = new Color(41, 128, 185);
				GradientPaint gp = new GradientPaint(0, 0, color1, w, 0, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		headerPanel.setBounds(0, 0, 900, 80);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblTitre = new JLabel("Historique des Commandes");
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitre.setBounds(30, 20, 400, 40);
		headerPanel.add(lblTitre);
		
		JLabel lblSubtitle = new JLabel("Rechercher et consulter toutes les commandes");
		lblSubtitle.setForeground(new Color(255, 255, 255, 200));
		lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSubtitle.setBounds(30, 50, 400, 20);
		headerPanel.add(lblSubtitle);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setBounds(30, 100, 830, 60);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel lblRecherche = new JLabel("Rechercher:");
		lblRecherche.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRecherche.setBounds(20, 18, 100, 25);
		searchPanel.add(lblRecherche);
		
		txtRecherche = new JTextField();
		txtRecherche.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtRecherche.setBounds(120, 15, 500, 30);
		txtRecherche.setBorder(BorderFactory.createCompoundBorder(
			new LineBorder(new Color(189, 195, 199), 1, true),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		txtRecherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrerTableau();
			}
		});
		searchPanel.add(txtRecherche);
		
		JButton btnRafraichir = new JButton("Rafraîchir");
		btnRafraichir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRafraichir.setBackground(new Color(46, 204, 113));
		btnRafraichir.setForeground(Color.WHITE);
		btnRafraichir.setFocusPainted(false);
		btnRafraichir.setBounds(640, 15, 160, 30);
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chargerCommandes();
				txtRecherche.setText("");
			}
		});
		searchPanel.add(btnRafraichir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 180, 830, 350);
		contentPane.add(scrollPane);
		
		String[] colonnes = {"N° Commande", "Date", "Client", "Nombre Articles", "Montant Total"};
		tableModel = new DefaultTableModel(colonnes, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(tableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		table.getTableHeader().setBackground(new Color(52, 73, 94));
		table.getTableHeader().setForeground(Color.WHITE);
		table.setSelectionBackground(new Color(52, 152, 219));
		table.setSelectionForeground(Color.WHITE);
		table.setGridColor(new Color(189, 195, 199));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		sorter = new TableRowSorter<>(tableModel);
		table.setRowSorter(sorter);
		
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("Total commandes: 0");
		lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTotal.setBounds(30, 540, 300, 20);
		contentPane.add(lblTotal);
		
		chargerCommandes();
		
		lblTotal.setText("Total commandes: " + tableModel.getRowCount());
	}
	
	private void chargerCommandes() {
		tableModel.setRowCount(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Commande> commandes = commandeController.getAllCommandes();
		
		for (Commande cmd : commandes) {
			Object[] row = new Object[5];
			row[0] = cmd.getIdcmd();
			row[1] = cmd.getDatecmd() != null ? sdf.format(cmd.getDatecmd()) : "N/A";
			row[2] = cmd.getClient() != null ? cmd.getClient().getNom() : "Client inconnu";
			row[3] = cmd.getLignes() != null ? cmd.getLignes().size() : 0;
			row[4] = String.format("%.2f DH", cmd.calculerTotal());
			
			tableModel.addRow(row);
		}
	}
	
	private void filtrerTableau() {
		String texte = txtRecherche.getText().trim().toLowerCase();
		
		if (texte.isEmpty()) {
			sorter.setRowFilter(null);
		} else {
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texte));
		}
	}
}
