package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Form_Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
					Form_Main frame = new Form_Main();
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
	public Form_Main() {
		setResizable(false);
		setTitle("Système de Gestion Commerciale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
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
				int w = getWidth();
				int h = getHeight();
				Color color1 = new Color(41, 128, 185);
				Color color2 = new Color(44, 62, 80);
				java.awt.GradientPaint gp = new java.awt.GradientPaint(0, 0, color1, w, 0, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		headerPanel.setBounds(0, 0, 734, 100);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Système de Gestion Commerciale");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(50, 20, 634, 40);
		headerPanel.add(lblTitle);
		
		JLabel lblSubtitle = new JLabel("Application Java Desktop - Hibernate & JPA");
		lblSubtitle.setForeground(new Color(236, 240, 241));
		lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitle.setBounds(50, 60, 634, 25);
		headerPanel.add(lblSubtitle);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
		infoPanel.setBounds(40, 130, 654, 140);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel lblInfoTitle = new JLabel("À Propos du Projet");
		lblInfoTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblInfoTitle.setForeground(new Color(44, 62, 80));
		lblInfoTitle.setBounds(20, 15, 550, 25);
		infoPanel.add(lblInfoTitle);
		
		JLabel lblInfo1 = new JLabel("• Application développée dans le cadre de la formation ILISI - Cycle d'ingénieur");
		lblInfo1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblInfo1.setForeground(new Color(52, 73, 94));
		lblInfo1.setBounds(20, 45, 600, 25);
		infoPanel.add(lblInfo1);
		
		JLabel lblInfo2 = new JLabel("• Module: JEE / Framework Hibernate avec architecture MVC");
		lblInfo2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblInfo2.setForeground(new Color(52, 73, 94));
		lblInfo2.setBounds(20, 70, 600, 25);
		infoPanel.add(lblInfo2);
		
		JLabel lblInfo3 = new JLabel("• Encadré par: Pr. Omar EL BEGGAR");
		lblInfo3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblInfo3.setForeground(new Color(41, 128, 185));
		lblInfo3.setBounds(20, 95, 600, 25);
		infoPanel.add(lblInfo3);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(236, 240, 241));
		menuPanel.setBounds(40, 290, 654, 130);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		JLabel lblMenuTitle = new JLabel("Modules de Gestion");
		lblMenuTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblMenuTitle.setForeground(new Color(44, 62, 80));
		lblMenuTitle.setBounds(10, 0, 250, 30);
		menuPanel.add(lblMenuTitle);
		
		JButton btnClient = new JButton("Gestion Client");
		btnClient.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnClient.setBackground(new Color(52, 152, 219));
		btnClient.setForeground(Color.WHITE);
		btnClient.setFocusPainted(false);
		btnClient.setBorder(new LineBorder(new Color(41, 128, 185), 2, true));
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Form_Client().setVisible(true);
			}
		});
		btnClient.setBounds(35, 40, 180, 70);
		menuPanel.add(btnClient);
		
		JButton btnCommande = new JButton("Gestion Commande");
		btnCommande.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCommande.setBackground(new Color(46, 204, 113));
		btnCommande.setForeground(Color.WHITE);
		btnCommande.setFocusPainted(false);
		btnCommande.setBorder(new LineBorder(new Color(39, 174, 96), 2, true));
		btnCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Form_Commande().setVisible(true);
			}
		});
		btnCommande.setBounds(237, 40, 180, 70);
		menuPanel.add(btnCommande);
		
		JButton btnProduit = new JButton("Gestion Produit");
		btnProduit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnProduit.setBackground(new Color(155, 89, 182));
		btnProduit.setForeground(Color.WHITE);
		btnProduit.setFocusPainted(false);
		btnProduit.setBorder(new LineBorder(new Color(142, 68, 173), 2, true));
		btnProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Form_Produit().setVisible(true);
			}
		});
		btnProduit.setBounds(439, 40, 180, 70);
		menuPanel.add(btnProduit);
		
		JPanel historiquePanel = new JPanel();
		historiquePanel.setBackground(new Color(255, 255, 255));
		historiquePanel.setBorder(new LineBorder(new Color(189, 195, 199), 1, true));
		historiquePanel.setBounds(40, 430, 654, 50);
		contentPane.add(historiquePanel);
		historiquePanel.setLayout(null);
		
		JButton btnHistorique = new JButton("Historique des Commandes");
		btnHistorique.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnHistorique.setBackground(new Color(41, 128, 185));
		btnHistorique.setForeground(Color.WHITE);
		btnHistorique.setFocusPainted(false);
		btnHistorique.setBorder(new LineBorder(new Color(52, 152, 219), 2, true));
		btnHistorique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Form_Historique_Commandes().setVisible(true);
			}
		});
		btnHistorique.setBounds(190, 10, 274, 30);
		historiquePanel.add(btnHistorique);
		
		JButton btnExit = new JButton("Quitter l'Application");
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnExit.setBackground(new Color(231, 76, 60));
		btnExit.setForeground(Color.WHITE);
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = javax.swing.JOptionPane.showConfirmDialog(
					null, 
					"Êtes-vous sûr de vouloir quitter?", 
					"Confirmation", 
					javax.swing.JOptionPane.YES_NO_OPTION
				);
				if (result == javax.swing.JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(270, 490, 200, 40);
		contentPane.add(btnExit);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu Principal");
		mnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnMenu);
		
		JMenuItem mntmClient = new JMenuItem("Gestion Client");
		mntmClient.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Form_Client().setVisible(true);
			}
		});
		mnMenu.add(mntmClient);

		JMenuItem mntmCommande = new JMenuItem("Gestion Commande");
		mntmCommande.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Form_Commande().setVisible(true);
			}
		});
		mnMenu.add(mntmCommande);
		
		JMenuItem mntmProduit = new JMenuItem("Gestion Produit");
		mntmProduit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Form_Produit().setVisible(true);
			}
		});
		mnMenu.add(mntmProduit);
		
		mnMenu.addSeparator();
		
		JMenuItem mntmExit = new JMenuItem("Quitter");
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = javax.swing.JOptionPane.showConfirmDialog(
					null, 
					"Êtes-vous sûr de vouloir quitter?", 
					"Confirmation", 
					javax.swing.JOptionPane.YES_NO_OPTION
				);
				if (result == javax.swing.JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mnMenu.add(mntmExit);
	}
}
