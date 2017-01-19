package wb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.util.Stack;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Crtanje extends JFrame {

	private JPanel pnlOsnovni;

	private JButton kliknutoDugme = null;
	private Oblik oblik = null;
	private Oblik trenutnoSelektovan = null;
	private int brojKlikova = 0;
	private Stack<Oblik> stekOblika = new Stack<Oblik>();
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
		setBounds(100, 100, 605, 419);
		pnlOsnovni = new JPanel();
		pnlOsnovni.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOsnovni);
		pnlOsnovni.setLayout(new BorderLayout(0, 0));

		JPanel pnlAlat = new JPanel();
		pnlOsnovni.add(pnlAlat, BorderLayout.NORTH);
		pnlAlat.setLayout(new BorderLayout(0, 0));

		JPanel pnlOpcije = new JPanel();
		pnlAlat.add(pnlOpcije, BorderLayout.EAST);
		pnlOpcije.setLayout(new MigLayout("", "[101.00][114.00px]", "[14px][]"));

		JLabel lblUpravljanje = new JLabel("Upravljanje oblicima");
		pnlOpcije.add(lblUpravljanje, "cell 0 0 2 1,alignx center,aligny top");

		JButton btnSelektuj = new JButton("Selektuj");
		btnSelektuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton) e.getSource();
			}
		});
		pnlOpcije.add(btnSelektuj, "cell 0 1,growx,aligny center");

		JButton btnObrisi = new JButton("Obri\u0161i");
		btnObrisi.setEnabled(false);
		pnlOpcije.add(btnObrisi, "cell 1 1,growx,aligny center");



		JPanel pnlBoje = new JPanel();
		pnlAlat.add(pnlBoje, BorderLayout.CENTER);
		GridBagLayout gbl_pnlBoje = new GridBagLayout();
		gbl_pnlBoje.columnWidths = new int[]{54, 66, 0};
		gbl_pnlBoje.rowHeights = new int[]{0, 28, 0, 0};
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

		JButton btnBojaUnutrasnjosti = new JButton("");
		btnBojaUnutrasnjosti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izaberiBoju((JButton)e.getSource());
			}
		});
		btnBojaUnutrasnjosti.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnBojaUnutrasnjosti = new GridBagConstraints();
		gbc_btnBojaUnutrasnjosti.fill = GridBagConstraints.BOTH;
		gbc_btnBojaUnutrasnjosti.insets = new Insets(0, 0, 5, 5);
		gbc_btnBojaUnutrasnjosti.gridx = 0;
		gbc_btnBojaUnutrasnjosti.gridy = 1;
		pnlBoje.add(btnBojaUnutrasnjosti, gbc_btnBojaUnutrasnjosti);

		JButton btnBojaIvice = new JButton("");
		btnBojaIvice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izaberiBoju((JButton)e.getSource());
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

		JPanel pnlOblici = new JPanel();
		pnlAlat.add(pnlOblici, BorderLayout.WEST);



		JButton btnLinija = new JButton("Linija");
		btnLinija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
			}
		});

		JButton btnTacka = new JButton("Ta\u010Dka");
		btnTacka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
			}
		});
		pnlOblici.setLayout(new MigLayout("", "[62px][97px]", "[23px][23px][23px]"));

		JLabel lblOblici = new JLabel("Oblici");
		pnlOblici.add(lblOblici, "cell 0 0,alignx center,aligny center");
		pnlOblici.add(btnTacka, "cell 1 0,growx,aligny center");
		pnlOblici.add(btnLinija, "cell 0 1,growx,aligny center");

		JButton btnKrug = new JButton("Krug");
		btnKrug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
			}
		});

		JButton btnKvadrat = new JButton("Kvadrat");
		btnKvadrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();

			}
		});
		pnlOblici.add(btnKvadrat, "cell 1 1,growx,aligny center");
		pnlOblici.add(btnKrug, "cell 0 2,growx,aligny top");

		JButton btnPravougaonik = new JButton("Pravougaonik");
		btnPravougaonik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kliknutoDugme = (JButton)e.getSource();
			}
		});
		pnlOblici.add(btnPravougaonik, "cell 1 2,growx,aligny center");

		JPanel pnlPaletaZaCrtanje = new JPanel();
		pnlPaletaZaCrtanje.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(kliknutoDugme == btnTacka){
					oblik = new Tacka(e.getX(),e.getY(), btnBojaIvice.getBackground());	
					oblik.crtajSe(pnlPaletaZaCrtanje.getGraphics());
				}else if(kliknutoDugme == btnLinija){
					if(brojKlikova == 0){
						oblik = new Linija(new Tacka(e.getX(),e.getY()),new Tacka(0,0),btnBojaIvice.getBackground());
						brojKlikova++;
						return;
					}else{
						Linija pomocna = (Linija) oblik;
						pomocna.settKrajnja(new Tacka(e.getX(), e.getY()));
						pomocna.crtajSe(pnlPaletaZaCrtanje.getGraphics());
						brojKlikova = 0;
					}
				}else if(kliknutoDugme == btnKrug ){
					String unosPoluprecnika = JOptionPane.showInputDialog(null,"Unesite poluprecnik","Krug - unos poluprecnika", JOptionPane.QUESTION_MESSAGE);
					boolean neispravanUnos = true;
					while(neispravanUnos == true){
						try{
							int poluprecnik = Integer.parseInt(unosPoluprecnika);
							PovrsinskiOblik.proveraUnosa(poluprecnik);
							Krug pomocni = new Krug(new Tacka(e.getX(),e.getY()), poluprecnik, btnBojaIvice.getBackground(), btnBojaUnutrasnjosti.getBackground());
							pomocni.crtajSe(pnlPaletaZaCrtanje.getGraphics());
							neispravanUnos = false;
						}catch(Exception exc){
							if(exc.getMessage() == "null")
								return;
							unosPoluprecnika = JOptionPane.showInputDialog(null,"Unesite ceo broj veci od 0!","Krug - unos poluprecnika", JOptionPane.QUESTION_MESSAGE);
						}
					}
				}else if(kliknutoDugme == btnKvadrat ){
					String unosStranice = JOptionPane.showInputDialog(null,"Unesite duzinu stranice","Kvadrat - unos stranice", JOptionPane.QUESTION_MESSAGE);
					boolean neispravanUnos = true;
					while(neispravanUnos == true){
						try{
							int stranica = Integer.parseInt(unosStranice);
							PovrsinskiOblik.proveraUnosa(stranica);
							Kvadrat pomocni = new Kvadrat(new Tacka (e.getX(), e.getY()), stranica, btnBojaIvice.getBackground(), btnBojaUnutrasnjosti.getBackground());
							pomocni.crtajSe(pnlPaletaZaCrtanje.getGraphics());
							neispravanUnos = false;
						}catch(Exception exc){
							if(exc.getMessage() == "null")
								return;
							unosStranice = JOptionPane.showInputDialog(null,"Unesite ceo broj veci od 0!","Kvadrat - unos stranice", JOptionPane.QUESTION_MESSAGE);
						}
					}
				}else if(kliknutoDugme == btnPravougaonik){
					String unosSirine = JOptionPane.showInputDialog(null,"Unesite sirinu pravougaonika","Pravougaonik - unos sirine", JOptionPane.QUESTION_MESSAGE);
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
							unosSirine = JOptionPane.showInputDialog(null,"Unesite ceo broj veci od 0!","Pravougaonik - unos sirine", JOptionPane.QUESTION_MESSAGE);
						}
					}

					String unosVisine = JOptionPane.showInputDialog(null,"Unesite visinu pravougaonika","Pravougaonik - unos visine", JOptionPane.QUESTION_MESSAGE);
					neispravanUnos = true;
					while(neispravanUnos == true){
						try{
							int visina = Integer.parseInt(unosVisine);
							PovrsinskiOblik.proveraUnosa(visina);
							Pravougaonik pomocni = new Pravougaonik(new Tacka (e.getX(), e.getY()), sirina, visina, btnBojaIvice.getBackground(), btnBojaUnutrasnjosti.getBackground());
							pomocni.crtajSe(pnlPaletaZaCrtanje.getGraphics());
							neispravanUnos = false;
						}catch(Exception exc){
							if(exc.getMessage() == "null")
								return;
							unosVisine = JOptionPane.showInputDialog(null,"Unesite ceo broj veci od 0!","Pravougaonik - unos visine", JOptionPane.QUESTION_MESSAGE);
						}
					}
				}else if(kliknutoDugme == btnSelektuj){
					
					if (trenutnoSelektovan != null) {
						trenutnoSelektovan = null;
						
					}
					
					for (int i = stekOblika.size()-1; i >= 0 ; i--) {
						if (stekOblika.get(i).sadrzi(e.getX(), e.getY())) {
							repaint();
							trenutnoSelektovan = stekOblika.get(i);
							trenutnoSelektovan.selektovan(pnlPaletaZaCrtanje.getGraphics());
						}
					}

				}
				if (oblik != null) {
					stekOblika.add(oblik);
				}

				System.out.println("\n Oblici u staku:");
				for(int i = stekOblika.size()-1; i>= 0; i--)
					System.out.println(stekOblika.get(i));
			}
		});
		pnlPaletaZaCrtanje.setBackground(Color.WHITE);
		pnlOsnovni.add(pnlPaletaZaCrtanje, BorderLayout.CENTER);



	}

	public void izaberiBoju(JButton dugme){
		JColorChooser jccBoja = new JColorChooser();
		Color boja = jccBoja.showDialog(null, "Odabir boje", Color.BLACK);
		if(boja != null)
			dugme.setBackground(boja);
	}

}
