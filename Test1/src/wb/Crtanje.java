package wb;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.javafx.geom.Rectangle;

import geometrija.Krug;
import geometrija.Kvadrat;
import geometrija.Linija;
import geometrija.Oblik;
import geometrija.PovrsinskiOblik;
import geometrija.Pravougaonik;
import geometrija.Tacka;

import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.FlowLayout;

public class Crtanje extends JFrame {

	private JPanel pnlOsnovni;
	private JPanel pnlPaletaZaCrtanje;
	private JButton kliknutoDugme = null;
	private JButton btnObrisi;
	private JButton btnBojaUnutrasnjosti;
	private JButton btnBojaIvice;
	private JButton btnSelektuj;
	private JButton btnPravougaonik;
	private JButton btnKvadrat;
	private JButton btnKrug;
	private JButton btnTacka;
	private JButton btnLinija;
	private Oblik oblik = null;
	private Oblik trenutnoSelektovan = null;
	private int brojKlikova = 0;
	private Stack<Oblik> stekOblika = new Stack<Oblik>();
	private ArrayList<Oblik> listaOblika = new ArrayList<Oblik>();
	private JTextField txtPocetnaX;
	private JTextField txtPocetnaY;
	private JTextField txtKrajnjaX;
	private JTextField txtKrajnjaY;
	private JTextField txtDuzina;
	private JTextField txtSirina;

	private JLabel lblTackaP;
	private JLabel lblDuzina;
	private JLabel lblTackaK;
	private JLabel lblPocetnaX;
	private JLabel lblPocetnaY;
	private JLabel lblSirina;
	private JLabel lblKrajnjaY;
	private JLabel lblKrajnjaX;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crtanje frame = new Crtanje();
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
	public Crtanje() {
		setTitle("Or\u010Di\u0107 Stefan IT11-2015 / Crtanje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 562);
		pnlOsnovni = new JPanel();
		pnlOsnovni.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOsnovni);
		pnlOsnovni.setLayout(new BorderLayout(0, 0));

		JPanel pnlAlat = new JPanel();
		pnlOsnovni.add(pnlAlat, BorderLayout.NORTH);
		pnlAlat.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel pnlOblici = new JPanel();
		pnlAlat.add(pnlOblici);



		btnLinija = new JButton("Linija");
		btnLinija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
				trenutnoSelektovan = null;
				ponovoNacrtaj();
			}
		});

		btnTacka = new JButton("Ta\u010Dka");
		btnTacka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
				trenutnoSelektovan = null;
				ponovoNacrtaj();
			}
		});
		pnlOblici.setLayout(new MigLayout("", "[62px][97px]", "[32.00px][50.00px][46.00px]"));

		JLabel lblOblici = new JLabel("Oblici");
		pnlOblici.add(lblOblici, "cell 0 0,alignx center,growy");
		pnlOblici.add(btnTacka, "cell 1 0,growx,aligny center");
		pnlOblici.add(btnLinija, "cell 0 1,growx,aligny center");

		btnKrug = new JButton("Krug");
		btnKrug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
				trenutnoSelektovan = null;
				ponovoNacrtaj();
			}
		});

		btnKvadrat = new JButton("Kvadrat");
		btnKvadrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
				trenutnoSelektovan = null;
				ponovoNacrtaj();
			}
		});
		pnlOblici.add(btnKvadrat, "cell 1 1,growx,aligny center");
		pnlOblici.add(btnKrug, "cell 0 2,growx,aligny center");

		btnPravougaonik = new JButton("Pravougaonik");
		btnPravougaonik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
				trenutnoSelektovan = null;
				ponovoNacrtaj();
			}
		});
		pnlOblici.add(btnPravougaonik, "cell 1 2,growx,aligny center");



		JPanel pnlBoje = new JPanel();
		pnlAlat.add(pnlBoje);
		GridBagLayout gbl_pnlBoje = new GridBagLayout();
		gbl_pnlBoje.columnWidths = new int[]{54, 66, 0};
		gbl_pnlBoje.rowHeights = new int[]{29, 90, 0, 0};
		gbl_pnlBoje.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlBoje.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlBoje.setLayout(gbl_pnlBoje);

		JLabel lblBoja = new JLabel("Boja");
		GridBagConstraints gbc_lblBoja = new GridBagConstraints();
		gbc_lblBoja.gridwidth = 2;
		gbc_lblBoja.insets = new Insets(0, 0, 5, 0);
		gbc_lblBoja.fill = GridBagConstraints.VERTICAL;
		gbc_lblBoja.gridx = 0;
		gbc_lblBoja.gridy = 0;
		pnlBoje.add(lblBoja, gbc_lblBoja);

		btnBojaUnutrasnjosti = new JButton("");
		btnBojaUnutrasnjosti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izaberiBoju((JButton)e.getSource());
				if (trenutnoSelektovan instanceof PovrsinskiOblik) {
					((PovrsinskiOblik)trenutnoSelektovan).setBojaUnutra(btnBojaUnutrasnjosti.getBackground());
				} else if(trenutnoSelektovan instanceof Tacka || trenutnoSelektovan instanceof Linija){
					btnBojaUnutrasnjosti.setBackground(Color.white);
				}
				if(kliknutoDugme == btnTacka || kliknutoDugme == btnLinija)
					btnBojaUnutrasnjosti.setBackground(Color.white);
				ponovoNacrtaj();
			}
		});
		btnBojaUnutrasnjosti.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnBojaUnutrasnjosti = new GridBagConstraints();
		gbc_btnBojaUnutrasnjosti.fill = GridBagConstraints.BOTH;
		gbc_btnBojaUnutrasnjosti.insets = new Insets(0, 0, 5, 5);
		gbc_btnBojaUnutrasnjosti.gridx = 0;
		gbc_btnBojaUnutrasnjosti.gridy = 1;
		pnlBoje.add(btnBojaUnutrasnjosti, gbc_btnBojaUnutrasnjosti);

		btnBojaIvice = new JButton("");
		btnBojaIvice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izaberiBoju((JButton)e.getSource());
				if (trenutnoSelektovan instanceof PovrsinskiOblik) {
					((PovrsinskiOblik)trenutnoSelektovan).setBoja(btnBojaIvice.getBackground());
				} else if(trenutnoSelektovan instanceof Oblik){
					trenutnoSelektovan.setBoja(btnBojaIvice.getBackground());
				}

				ponovoNacrtaj();

			}
		});
		btnBojaIvice.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnBojaIvice = new GridBagConstraints();
		gbc_btnBojaIvice.fill = GridBagConstraints.BOTH;
		gbc_btnBojaIvice.insets = new Insets(0, 0, 5, 0);
		gbc_btnBojaIvice.gridx = 1;
		gbc_btnBojaIvice.gridy = 1;
		pnlBoje.add(btnBojaIvice, gbc_btnBojaIvice);

		JLabel lblUnutranja = new JLabel("Unutra\u0161nja");
		GridBagConstraints gbc_lblUnutranja = new GridBagConstraints();
		gbc_lblUnutranja.insets = new Insets(0, 0, 0, 5);
		gbc_lblUnutranja.gridx = 0;
		gbc_lblUnutranja.gridy = 2;
		pnlBoje.add(lblUnutranja, gbc_lblUnutranja);

		JLabel lblIvica = new JLabel("Ivica");
		GridBagConstraints gbc_lblIvica = new GridBagConstraints();
		gbc_lblIvica.gridx = 1;
		gbc_lblIvica.gridy = 2;
		pnlBoje.add(lblIvica, gbc_lblIvica);

		JPanel pnlOpcije = new JPanel();
		pnlAlat.add(pnlOpcije);
		pnlOpcije.setLayout(new MigLayout("", "[26.00][101.00,grow][29.00][83.00px,grow][]", "[14px][][][][][]"));

		JLabel lblUpravljanje = new JLabel("Upravljanje oblicima");
		pnlOpcije.add(lblUpravljanje, "cell 1 1,alignx center,aligny top");

		btnSelektuj = new JButton("Selektuj");
		btnSelektuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton) e.getSource();
				if(trenutnoSelektovan != null){
					trenutnoSelektovan = null;
					ponovoNacrtaj();
				}
				ponovoNacrtaj();
			}
		});
		pnlOpcije.add(btnSelektuj, "cell 3 1,growx,aligny center");

		btnObrisi = new JButton("Obri\u0161i");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcija = JOptionPane.showOptionDialog(null, "Da li ste sigurni da želite da obrisete selektovan oblik","Brisanje selektovanog oblika" , JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(opcija == 0){
					stekOblika.removeElement(trenutnoSelektovan);
					trenutnoSelektovan = null;
					ponovoNacrtaj();
					System.out.println(stekOblika);
				}
			}
		});
		btnObrisi.setEnabled(false);
		pnlOpcije.add(btnObrisi, "cell 4 1,growx,aligny center");

		lblTackaP = new JLabel("");
		pnlOpcije.add(lblTackaP, "cell 1 2,alignx left");

		lblTackaK = new JLabel("");
		pnlOpcije.add(lblTackaK, "cell 3 2");

		lblDuzina = new JLabel("");
		pnlOpcije.add(lblDuzina, "cell 4 2");

		lblPocetnaX = new JLabel("X");
		pnlOpcije.add(lblPocetnaX, "cell 0 3,alignx right");

		txtPocetnaX = new JTextField();
		txtPocetnaX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pomocna = 0;
				try{
					if (trenutnoSelektovan instanceof Tacka) {
						pomocna = ((Tacka)trenutnoSelektovan).getX();
						((Tacka)trenutnoSelektovan).setX(Integer.parseInt(txtPocetnaX.getText()));
					} else if(trenutnoSelektovan instanceof Linija) {
						pomocna = ((Linija)trenutnoSelektovan).gettPocetna().getX();
						((Linija)trenutnoSelektovan).gettPocetna().setX(Integer.parseInt(txtPocetnaX.getText()));
					} else if(trenutnoSelektovan instanceof Krug){
						pomocna = ((Krug)trenutnoSelektovan).getCentar().getX();
						((Krug)trenutnoSelektovan).getCentar().setX(Integer.parseInt(txtPocetnaX.getText()));
					} else if(trenutnoSelektovan instanceof Pravougaonik){
						pomocna = ((Pravougaonik)trenutnoSelektovan).getGoreLevo().getX();
						((Pravougaonik)trenutnoSelektovan).getGoreLevo().setX(Integer.parseInt(txtPocetnaX.getText()));
					} else if(trenutnoSelektovan instanceof Kvadrat){
						pomocna = ((Kvadrat)trenutnoSelektovan).getGoreLevo().getX();
						((Kvadrat)trenutnoSelektovan).getGoreLevo().setX(Integer.parseInt(txtPocetnaX.getText()));
					}
				}catch (Exception ex) {
					txtPocetnaX.setText("" + pomocna);
					JOptionPane.showMessageDialog(null, "Unesite pozitivan ceo broj","Greska u unosu", JOptionPane.ERROR_MESSAGE);
				}
				uzmiSveVrednostiTxt();
				ponovoNacrtaj();
			}
		});
		pnlOpcije.add(txtPocetnaX, "cell 1 3,alignx left,aligny center");
		txtPocetnaX.setColumns(10);

		txtDuzina = new JTextField();
		txtDuzina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pomocna = 0;
				try{
					if (trenutnoSelektovan instanceof Krug){
						pomocna = ((Krug)trenutnoSelektovan).getR();
						((Krug)trenutnoSelektovan).setR(Integer.parseInt(txtDuzina.getText()));
					} else if(trenutnoSelektovan instanceof Pravougaonik){
						pomocna = ((Pravougaonik)trenutnoSelektovan).getVisina();
						((Pravougaonik)trenutnoSelektovan).setVisina(Integer.parseInt(txtDuzina.getText()));
					} else if(trenutnoSelektovan instanceof Kvadrat){
						pomocna = ((Kvadrat)trenutnoSelektovan).getDuzinaStranice();
						((Kvadrat)trenutnoSelektovan).setDuzinaStranice(Integer.parseInt(txtDuzina.getText()));
					}
				}catch (Exception ex) {
					txtDuzina.setText("" + pomocna);
					JOptionPane.showMessageDialog(null, "Unesite pozitivan ceo broj","Greska u unosu", JOptionPane.ERROR_MESSAGE);
				}
				uzmiSveVrednostiTxt();
				ponovoNacrtaj();
			}
		});

		txtKrajnjaX = new JTextField();
		txtKrajnjaX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pomocna = 0;
				try{
					if (trenutnoSelektovan instanceof Linija) {
						pomocna = ((Linija)trenutnoSelektovan).gettKrajnja().getX();
						((Linija)trenutnoSelektovan).gettKrajnja().setX(Integer.parseInt(txtKrajnjaX.getText()));
					}
				}catch (Exception ex) {
					txtKrajnjaX.setText("" + pomocna);
					JOptionPane.showMessageDialog(null, "Unesite pozitivan ceo broj","Greska u unosu", JOptionPane.ERROR_MESSAGE);
				}
				uzmiSveVrednostiTxt();
				ponovoNacrtaj();
			}
		});

		lblKrajnjaX = new JLabel("X");
		pnlOpcije.add(lblKrajnjaX, "cell 2 3,alignx trailing");
		pnlOpcije.add(txtKrajnjaX, "cell 3 3,alignx left");
		txtKrajnjaX.setColumns(10);
		pnlOpcije.add(txtDuzina, "cell 4 3,alignx left");
		txtDuzina.setColumns(10);

		lblPocetnaY = new JLabel("Y");
		pnlOpcije.add(lblPocetnaY, "cell 0 4,alignx right");

		txtPocetnaY = new JTextField();
		txtPocetnaY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pomocna = 0;
				try{
					if (trenutnoSelektovan instanceof Tacka) {
						pomocna = ((Tacka)trenutnoSelektovan).getY();
						((Tacka)trenutnoSelektovan).setY(Integer.parseInt(txtPocetnaY.getText()));
						uzmiSveVrednostiTxt();
					} else if(trenutnoSelektovan instanceof Linija) {
						pomocna = ((Linija)trenutnoSelektovan).gettPocetna().getY();
						((Linija)trenutnoSelektovan).gettPocetna().setY(Integer.parseInt(txtPocetnaY.getText()));
						uzmiSveVrednostiTxt();
					} else if(trenutnoSelektovan instanceof Krug){
						pomocna = ((Krug)trenutnoSelektovan).getCentar().getY();
						((Krug)trenutnoSelektovan).getCentar().setY(Integer.parseInt(txtPocetnaY.getText()));
						uzmiSveVrednostiTxt();
					} else if(trenutnoSelektovan instanceof Pravougaonik){
						pomocna = ((Pravougaonik)trenutnoSelektovan).getGoreLevo().getY();
						((Pravougaonik)trenutnoSelektovan).getGoreLevo().setY(Integer.parseInt(txtPocetnaY.getText()));
						uzmiSveVrednostiTxt();
					} else if(trenutnoSelektovan instanceof Kvadrat){
						pomocna = ((Kvadrat)trenutnoSelektovan).getGoreLevo().getY();
						((Kvadrat)trenutnoSelektovan).getGoreLevo().setY(Integer.parseInt(txtPocetnaY.getText()));
						uzmiSveVrednostiTxt();
					}
				}catch (Exception ex) {
					txtPocetnaY.setText("" + pomocna);
					JOptionPane.showMessageDialog(null, "Unesite pozitivan ceo broj","Greska u unosu", JOptionPane.ERROR_MESSAGE);
				}
				ponovoNacrtaj();
			}
		});
		pnlOpcije.add(txtPocetnaY, "cell 1 4,alignx left,aligny center");
		txtPocetnaY.setColumns(10);

		txtKrajnjaY = new JTextField();
		txtKrajnjaY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pomocna = 0;
				try{
					if (trenutnoSelektovan instanceof Linija) {
						pomocna = ((Linija)trenutnoSelektovan).gettKrajnja().getY();
						((Linija)trenutnoSelektovan).gettKrajnja().setY(Integer.parseInt(txtKrajnjaY.getText()));
						uzmiSveVrednostiTxt();
					}
				}catch (Exception ex) {
					txtKrajnjaY.setText("" + pomocna);
					JOptionPane.showMessageDialog(null, "Unesite pozitivan ceo broj","Greska u unosu", JOptionPane.ERROR_MESSAGE);
				}
				ponovoNacrtaj();
			}
		});

		lblKrajnjaY = new JLabel("Y");
		pnlOpcije.add(lblKrajnjaY, "cell 2 4,alignx trailing");
		pnlOpcije.add(txtKrajnjaY, "cell 3 4,alignx left");
		txtKrajnjaY.setColumns(10);

		lblSirina = new JLabel("");
		pnlOpcije.add(lblSirina, "cell 4 4");

		txtSirina = new JTextField();
		txtSirina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pomocna = 0;
				try{
					if (trenutnoSelektovan instanceof Pravougaonik){
						pomocna = ((Pravougaonik)trenutnoSelektovan).getSirina();
						((Pravougaonik)trenutnoSelektovan).setSirina(Integer.parseInt(txtSirina.getText()));
						uzmiSveVrednostiTxt();
					}
				}catch (Exception ex) {
					txtSirina.setText("" + pomocna);
					JOptionPane.showMessageDialog(null, "Unesite pozitivan ceo broj","Greska u unosu", JOptionPane.ERROR_MESSAGE);
				}
				ponovoNacrtaj();
			}
		});
		pnlOpcije.add(txtSirina, "cell 4 5,alignx left");
		txtSirina.setColumns(10);

		pnlPaletaZaCrtanje = new JPanel();
		pnlPaletaZaCrtanje.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				ponovoNacrtaj();
			}
		});
		pnlPaletaZaCrtanje.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(kliknutoDugme == btnTacka){
					oblik = new Tacka(e.getX(),e.getY(), btnBojaIvice.getBackground());	
					oblik.crtajSe(pnlPaletaZaCrtanje.getGraphics());
					stekOblika.add(oblik);
				}else if(kliknutoDugme == btnLinija){
					if(brojKlikova == 0){
						oblik = new Linija(new Tacka(e.getX(),e.getY()),new Tacka(0,0),btnBojaIvice.getBackground());
						brojKlikova++;
						return;
					}else{
						Linija pomocna = (Linija) oblik;
						pomocna.settKrajnja(new Tacka(e.getX(), e.getY()));
						pomocna.crtajSe(pnlPaletaZaCrtanje.getGraphics());
						stekOblika.add(pomocna);
						brojKlikova = 0;
					}
				}else if(kliknutoDugme == btnKrug ){
					String unosPoluprecnika = JOptionPane.showInputDialog(null,"Unesite poluprečnik","Krug - unos poluprečnika", JOptionPane.QUESTION_MESSAGE);
					boolean neispravanUnos = true;
					while(neispravanUnos == true){
						try{
							int poluprecnik = Integer.parseInt(unosPoluprecnika);
							PovrsinskiOblik.proveraUnosa(poluprecnik);
							oblik = new Krug(new Tacka(e.getX(),e.getY()), poluprecnik, btnBojaIvice.getBackground(), btnBojaUnutrasnjosti.getBackground());
							oblik.crtajSe(pnlPaletaZaCrtanje.getGraphics());
							stekOblika.add(oblik);
							neispravanUnos = false;
						}catch(Exception exc){
							if(exc.getMessage() == "null")
								return;
							unosPoluprecnika = JOptionPane.showInputDialog(null,"Unesite pozitivan ceo broj","Krug - unos poluprecnika", JOptionPane.QUESTION_MESSAGE);
						}
					}
				}else if(kliknutoDugme == btnKvadrat ){
					String unosStranice = JOptionPane.showInputDialog(null,"Unesite dužinu stranice","Kvadrat - unos stranice", JOptionPane.QUESTION_MESSAGE);
					boolean neispravanUnos = true;
					while(neispravanUnos == true){
						try{
							int stranica = Integer.parseInt(unosStranice);
							PovrsinskiOblik.proveraUnosa(stranica);
							oblik = new Kvadrat(new Tacka (e.getX(), e.getY()), stranica, btnBojaIvice.getBackground(), btnBojaUnutrasnjosti.getBackground());
							oblik.crtajSe(pnlPaletaZaCrtanje.getGraphics());
							stekOblika.add(oblik);
							neispravanUnos = false;
						}catch(Exception exc){
							if(exc.getMessage() == "null")
								return;
							unosStranice = JOptionPane.showInputDialog(null,"Unesite pozitivan ceo broj","Kvadrat - unos stranice", JOptionPane.QUESTION_MESSAGE);
						}
					}
				}else if(kliknutoDugme == btnPravougaonik){
					String unosSirine = JOptionPane.showInputDialog(null,"Unesite širinu pravougaonika","Pravougaonik - unos širine", JOptionPane.QUESTION_MESSAGE);
					boolean neispravanUnos = true;
					int sirina = 0;
					while(neispravanUnos == true){
						try{
							sirina = Integer.parseInt(unosSirine);
							PovrsinskiOblik.proveraUnosa(sirina);
							neispravanUnos = false;
						}catch(Exception exc){
							if(exc.getMessage() == "null")
								return;
							unosSirine = JOptionPane.showInputDialog(null,"Unesite pozitivan ceo broj","Pravougaonik - unos širine", JOptionPane.QUESTION_MESSAGE);
						}
					}

					String unosVisine = JOptionPane.showInputDialog(null,"Unesite visinu pravougaonika","Pravougaonik - unos visine", JOptionPane.QUESTION_MESSAGE);
					neispravanUnos = true;
					while(neispravanUnos == true){
						try{
							int visina = Integer.parseInt(unosVisine);
							PovrsinskiOblik.proveraUnosa(visina);
							oblik = new Pravougaonik(new Tacka (e.getX(), e.getY()), sirina, visina, btnBojaIvice.getBackground(), btnBojaUnutrasnjosti.getBackground());
							oblik.crtajSe(pnlPaletaZaCrtanje.getGraphics());
							stekOblika.add(oblik);
							neispravanUnos = false;
						}catch(Exception exc){
							if(exc.getMessage() == "null")
								return;
							unosVisine = JOptionPane.showInputDialog(null,"Unesite pozitivan ceo broj","Pravougaonik - unos visine", JOptionPane.QUESTION_MESSAGE);
						}
					}
				}else if(kliknutoDugme == btnSelektuj){
					if (trenutnoSelektovan != null) {
						trenutnoSelektovan = null;
						btnObrisi.setEnabled(true);
						ponovoNacrtaj();

					}

					for (int i = stekOblika.size()-1; i >= 0 ; i--) {
						if (stekOblika.get(i).sadrzi(e.getX(), e.getY())) {

							trenutnoSelektovan = stekOblika.get(i);
							ponovoNacrtaj();
							trenutnoSelektovan.selektovan(pnlPaletaZaCrtanje.getGraphics());

							return;
						}else{

						}
					}

				}else{
					trenutnoSelektovan = null;
					ponovoNacrtaj();
				}
				/*Nikako, zato sto mi i na btnSelect kreira oblike
				 * if (oblik != null) {
					stekOblika.add(oblik);
				}*/

				System.out.println("\n Oblici u steku:");
				for(int i = stekOblika.size()-1; i>= 0; i--)
					System.out.println(stekOblika.get(i));
			}
		});
		pnlPaletaZaCrtanje.setBackground(Color.WHITE);
		pnlOsnovni.add(pnlPaletaZaCrtanje, BorderLayout.CENTER);




	}
	public void uzmiSveVrednostiTxt(){
		try{
			if (trenutnoSelektovan instanceof Tacka) {
				((Tacka)trenutnoSelektovan).setX(Integer.parseInt(txtPocetnaX.getText()));
				((Tacka)trenutnoSelektovan).setY(Integer.parseInt(txtPocetnaY.getText()));
			} else if(trenutnoSelektovan instanceof Linija) {
				((Linija)trenutnoSelektovan).gettPocetna().setX(Integer.parseInt(txtPocetnaX.getText()));
				((Linija)trenutnoSelektovan).gettPocetna().setY(Integer.parseInt(txtPocetnaY.getText()));
				((Linija)trenutnoSelektovan).gettKrajnja().setX(Integer.parseInt(txtKrajnjaX.getText()));
				((Linija)trenutnoSelektovan).gettKrajnja().setY(Integer.parseInt(txtKrajnjaY.getText()));
			} else if(trenutnoSelektovan instanceof Krug){
				((Krug)trenutnoSelektovan).getCentar().setX(Integer.parseInt(txtPocetnaX.getText()));
				((Krug)trenutnoSelektovan).getCentar().setY(Integer.parseInt(txtPocetnaY.getText()));
				((Krug)trenutnoSelektovan).setR(Integer.parseInt(txtDuzina.getText()));
			} else if(trenutnoSelektovan instanceof Pravougaonik){
				((Pravougaonik)trenutnoSelektovan).getGoreLevo().setX(Integer.parseInt(txtPocetnaX.getText()));
				((Pravougaonik)trenutnoSelektovan).getGoreLevo().setY(Integer.parseInt(txtPocetnaY.getText()));
				((Pravougaonik)trenutnoSelektovan).setSirina(Integer.parseInt(txtSirina.getText()));
				((Pravougaonik)trenutnoSelektovan).setVisina(Integer.parseInt(txtDuzina.getText()));
			} else if(trenutnoSelektovan instanceof Kvadrat){
				((Kvadrat)trenutnoSelektovan).getGoreLevo().setX(Integer.parseInt(txtPocetnaX.getText()));
				((Kvadrat)trenutnoSelektovan).getGoreLevo().setY(Integer.parseInt(txtPocetnaY.getText()));
				((Kvadrat)trenutnoSelektovan).setDuzinaStranice(Integer.parseInt(txtDuzina.getText()));
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Unesite pozitivan ceo broj","Greska u unosu", JOptionPane.ERROR_MESSAGE);
		}

	}
	public void selekcijaDugmeta(){
		if(btnSelektuj.hasFocus()){
			btnSelektuj.setBackground(new Color(190, 51, 51).brighter());
			btnKrug.setBackground(getBackground());
			btnKvadrat.setBackground(getBackground());
			btnLinija.setBackground(getBackground());
			btnPravougaonik.setBackground(getBackground());
			btnTacka.setBackground(getBackground());
		}else if(btnKrug.hasFocus()){
			btnKrug.setBackground(new Color(190, 51, 51).brighter());
			btnSelektuj.setBackground(getBackground());
			btnKvadrat.setBackground(getBackground());
			btnLinija.setBackground(getBackground());
			btnPravougaonik.setBackground(getBackground());
			btnTacka.setBackground(getBackground());
		}else if(btnKvadrat.hasFocus()){
			btnKrug.setBackground(getBackground());
			btnSelektuj.setBackground(getBackground());
			btnKvadrat.setBackground(new Color(190, 51, 51).brighter());
			btnLinija.setBackground(getBackground());
			btnPravougaonik.setBackground(getBackground());
			btnTacka.setBackground(getBackground());
		}else if(btnLinija.hasFocus()){
			btnKrug.setBackground(getBackground());
			btnSelektuj.setBackground(getBackground());
			btnKvadrat.setBackground(getBackground());
			btnLinija.setBackground(new Color(190, 51, 51).brighter());
			btnPravougaonik.setBackground(getBackground());
			btnTacka.setBackground(getBackground());
		}else if(btnPravougaonik.hasFocus()){
			btnKrug.setBackground(getBackground());
			btnSelektuj.setBackground(getBackground());
			btnKvadrat.setBackground(getBackground());
			btnLinija.setBackground(getBackground());
			btnPravougaonik.setBackground(new Color(190, 51, 51).brighter());
			btnTacka.setBackground(getBackground());
		}else if(btnTacka.hasFocus()){
			btnTacka.setBackground(new Color(190, 51, 51).brighter());
			btnKrug.setBackground(getBackground());
			btnSelektuj.setBackground(getBackground());
			btnKvadrat.setBackground(getBackground());
			btnLinija.setBackground(getBackground());
			btnPravougaonik.setBackground(getBackground());
		}
	}
	public void ponovoNacrtaj(){
		paint(getGraphics());
		selekcijaDugmeta();
		lblDuzina.setVisible(false);
		lblKrajnjaX.setVisible(false);
		lblKrajnjaY.setVisible(false);
		lblPocetnaX.setVisible(false);
		lblPocetnaY.setVisible(false);
		lblSirina.setVisible(false);
		lblTackaK.setVisible(false);
		lblTackaP.setVisible(false);
		txtDuzina.setVisible(false);
		txtKrajnjaX.setVisible(false);
		txtKrajnjaY.setVisible(false);
		txtPocetnaX.setVisible(false);
		txtPocetnaY.setVisible(false);
		txtSirina.setVisible(false);

		if (trenutnoSelektovan !=null) {

			btnObrisi.setEnabled(true);


			if(trenutnoSelektovan instanceof PovrsinskiOblik){
				btnBojaUnutrasnjosti.setBackground(((PovrsinskiOblik) trenutnoSelektovan).getBojaUnutra());
				btnBojaIvice.setBackground(((PovrsinskiOblik) trenutnoSelektovan).getBoja());
			}else{
				btnBojaUnutrasnjosti.setBackground(Color.WHITE);
				btnBojaIvice.setBackground(trenutnoSelektovan.getBoja());
			}



			if (trenutnoSelektovan instanceof Tacka) {
				lblTackaP.setVisible(true);
				lblTackaP.setText("Tačka");
				lblPocetnaX.setVisible(true);
				lblPocetnaY.setVisible(true);
				txtPocetnaX.setVisible(true);
				txtPocetnaX.setText("" + ((Tacka) trenutnoSelektovan).getX());
				txtPocetnaY.setVisible(true);
				txtPocetnaY.setText("" + ((Tacka) trenutnoSelektovan).getY());
			} else if (trenutnoSelektovan instanceof Linija){
				//pocetna tacka
				lblTackaP.setVisible(true);
				lblTackaP.setText("Početna tačka");
				lblPocetnaX.setVisible(true);
				lblPocetnaY.setVisible(true);
				txtPocetnaX.setVisible(true);
				txtPocetnaX.setText("" + ((Linija) trenutnoSelektovan).gettPocetna().getX());
				txtPocetnaY.setVisible(true);
				txtPocetnaY.setText("" + ((Linija) trenutnoSelektovan).gettPocetna().getY());
				//krajnja tacka
				lblTackaK.setVisible(true);
				lblTackaK.setText("Krajnja tačka");
				lblKrajnjaX.setVisible(true);
				lblKrajnjaY.setVisible(true);
				txtKrajnjaX.setVisible(true);
				txtKrajnjaX.setText("" + ((Linija) trenutnoSelektovan).gettKrajnja().getX());
				txtKrajnjaY.setVisible(true);
				txtKrajnjaY.setText("" + ((Linija) trenutnoSelektovan).gettKrajnja().getY());

			}else if (trenutnoSelektovan instanceof Krug){
				//centralna tacka
				lblTackaP.setVisible(true);
				lblTackaP.setText("Centar");
				lblPocetnaX.setVisible(true);
				lblPocetnaY.setVisible(true);
				txtPocetnaX.setVisible(true);
				txtPocetnaX.setText("" + ((Krug) trenutnoSelektovan).getCentar().getX());
				txtPocetnaY.setVisible(true);
				txtPocetnaY.setText("" + ((Krug) trenutnoSelektovan).getCentar().getY());
				//Poluprecnik
				lblDuzina.setText("Poluprečnik");
				lblDuzina.setVisible(true);
				txtDuzina.setVisible(true);
				txtDuzina.setText("" + ((Krug)trenutnoSelektovan).getR());
			}else if (trenutnoSelektovan instanceof Pravougaonik){
				//gore levo tacka
				lblTackaP.setVisible(true);
				lblTackaP.setText("Gore levo");
				lblPocetnaX.setVisible(true);
				lblPocetnaY.setVisible(true);
				txtPocetnaX.setVisible(true);
				txtPocetnaX.setText("" + ((Pravougaonik) trenutnoSelektovan).getGoreLevo().getX());
				txtPocetnaY.setVisible(true);
				txtPocetnaY.setText("" + ((Pravougaonik) trenutnoSelektovan).getGoreLevo().getY());
				//Visina
				lblDuzina.setText("Visina");
				lblDuzina.setVisible(true);
				txtDuzina.setVisible(true);
				txtDuzina.setText("" + ((Pravougaonik)trenutnoSelektovan).getVisina());
				//Sirina
				lblSirina.setText("Širina");
				lblSirina.setVisible(true);
				txtSirina.setVisible(true);
				txtSirina.setText("" + ((Pravougaonik)trenutnoSelektovan).getSirina());

			}else if (trenutnoSelektovan instanceof Kvadrat){
				//gore levo tacka
				lblTackaP.setVisible(true);
				lblTackaP.setText("Gore levo");
				lblPocetnaX.setVisible(true);
				lblPocetnaY.setVisible(true);
				txtPocetnaX.setVisible(true);
				txtPocetnaX.setText("" + ((Kvadrat) trenutnoSelektovan).getGoreLevo().getX());
				txtPocetnaY.setVisible(true);
				txtPocetnaY.setText("" + ((Kvadrat) trenutnoSelektovan).getGoreLevo().getY());
				//Duzina 
				lblDuzina.setText("Dužina stranice");
				lblDuzina.setVisible(true);
				txtDuzina.setVisible(true);
				txtDuzina.setText("" + ((Kvadrat)trenutnoSelektovan).getDuzinaStranice());
			}

		}else{
			btnObrisi.setEnabled(false);
		}
		for (Oblik oblik : stekOblika) {
			oblik.crtajSe(pnlPaletaZaCrtanje.getGraphics());
		}
		//Kad se sve iscrta, onda selektuje
		if (trenutnoSelektovan !=null) {
			trenutnoSelektovan.selektovan(pnlPaletaZaCrtanje.getGraphics());
		}

	}
	public void izaberiBoju(JButton dugme){
		JColorChooser jccBoja = new JColorChooser();
		Color boja = jccBoja.showDialog(null, "Odabir boje", Color.BLACK);
		if(boja != null)
			dugme.setBackground(boja);
	}

}
