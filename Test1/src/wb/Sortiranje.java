package wb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometrija.Kvadrat;
import geometrija.Tacka;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.List;

public class Sortiranje extends JFrame {

	private JPanel pnlOsnovni;
	private List lstSortirana;
	private List lstLista;
	private ArrayList<Kvadrat> sortKvadrati = new ArrayList<Kvadrat>();
	private ArrayList<Kvadrat> lstKvadrata = new ArrayList<Kvadrat>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sortiranje frame = new Sortiranje();
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
	public Sortiranje() {
		setTitle("Or\u010Di\u0107 Stefan IT11-2015 / Sortiranje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pnlOsnovni = new JPanel();
		pnlOsnovni.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOsnovni);
		pnlOsnovni.setLayout(new BorderLayout(0, 0));

		JPanel pnlListe = new JPanel();
		pnlOsnovni.add(pnlListe, BorderLayout.CENTER);
		pnlListe.setLayout(new MigLayout("", "[95.00,grow][119.00][138.00][155.00][94.00,grow][130.00]", "[][][39.00][84.00,grow]"));

		JButton btnDodajKvadratU = new JButton("Dodaj kvadrat u listu");
		btnDodajKvadratU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgKvadrat dlgK = new DlgKvadrat();
				dlgK.setVisible(true);
				Kvadrat pomocniKvadrat = dlgK.pomKvadrat;
				lstKvadrata.add(pomocniKvadrat);
				sortKvadrati.add(pomocniKvadrat);
				
				lstLista.removeAll();
				lstSortirana.removeAll();
				sortKvadrati.sort(null);
				for (Kvadrat kvadrat : lstKvadrata) {
					lstLista.add(kvadrat.opis());
				}

				for (Kvadrat kvadrat : sortKvadrati) {
					lstSortirana.add(kvadrat.opis());
				}
				
				System.out.println();
			}
		});
		
				JLabel lblPodnaslov = new JLabel("Sortiranje liste kvadrata");
				pnlListe.add(lblPodnaslov, "cell 2 0 2 1,alignx center");
				lblPodnaslov.setHorizontalAlignment(SwingConstants.CENTER);
		pnlListe.add(btnDodajKvadratU, "cell 2 1 2 1,growx");

		JLabel lblLista = new JLabel("Lista");
		pnlListe.add(lblLista, "cell 0 2 3 1,alignx center,aligny center");

		JLabel lblSortiranaLista = new JLabel("Sortirana lista");
		pnlListe.add(lblSortiranaLista, "cell 3 2 3 1,alignx center,aligny center");
		
		lstLista = new List();
		pnlListe.add(lstLista, "cell 0 3 3 1,growx,aligny top");
		
		lstSortirana = new List();
		pnlListe.add(lstSortirana, "cell 3 3 3 1,growx,aligny top");
	}

}
