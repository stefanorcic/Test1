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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		setBounds(100, 100, 756, 424);
		pnlOsnovni = new JPanel();
		pnlOsnovni.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOsnovni);
		pnlOsnovni.setLayout(new BorderLayout(0, 0));

		JPanel pnlListe = new JPanel();
		pnlOsnovni.add(pnlListe, BorderLayout.CENTER);
		GridBagLayout gbl_pnlListe = new GridBagLayout();
		gbl_pnlListe.columnWidths = new int[]{106, 109, 88, 150, 0};
		gbl_pnlListe.rowHeights = new int[]{14, 23, 38, 159, 0};
		gbl_pnlListe.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnlListe.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnlListe.setLayout(gbl_pnlListe);

		JLabel lblPodnaslov = new JLabel("Sortiranje liste kvadrata");
		GridBagConstraints gbc_lblPodnaslov = new GridBagConstraints();
		gbc_lblPodnaslov.anchor = GridBagConstraints.NORTH;
		gbc_lblPodnaslov.insets = new Insets(0, 0, 5, 5);
		gbc_lblPodnaslov.gridwidth = 4;
		gbc_lblPodnaslov.gridx = 0;
		gbc_lblPodnaslov.gridy = 0;
		pnlListe.add(lblPodnaslov, gbc_lblPodnaslov);
		lblPodnaslov.setHorizontalAlignment(SwingConstants.CENTER);

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
		GridBagConstraints gbc_btnDodajKvadratU = new GridBagConstraints();
		gbc_btnDodajKvadratU.anchor = GridBagConstraints.NORTH;
		gbc_btnDodajKvadratU.insets = new Insets(0, 0, 5, 0);
		gbc_btnDodajKvadratU.gridwidth = 4;
		gbc_btnDodajKvadratU.gridx = 0;
		gbc_btnDodajKvadratU.gridy = 1;
		pnlListe.add(btnDodajKvadratU, gbc_btnDodajKvadratU);

		JLabel lblLista = new JLabel("Lista");
		GridBagConstraints gbc_lblLista = new GridBagConstraints();
		gbc_lblLista.gridwidth = 2;
		gbc_lblLista.insets = new Insets(0, 0, 5, 5);
		gbc_lblLista.gridx = 0;
		gbc_lblLista.gridy = 2;
		pnlListe.add(lblLista, gbc_lblLista);

		JLabel lblSortiranaLista = new JLabel("Sortirana lista");
		GridBagConstraints gbc_lblSortiranaLista = new GridBagConstraints();
		gbc_lblSortiranaLista.gridwidth = 2;
		gbc_lblSortiranaLista.insets = new Insets(0, 0, 5, 0);
		gbc_lblSortiranaLista.gridx = 2;
		gbc_lblSortiranaLista.gridy = 2;
		pnlListe.add(lblSortiranaLista, gbc_lblSortiranaLista);

		lstLista = new List();
		GridBagConstraints gbc_lstLista = new GridBagConstraints();
		gbc_lstLista.gridwidth = 2;
		gbc_lstLista.fill = GridBagConstraints.BOTH;
		gbc_lstLista.insets = new Insets(0, 0, 0, 5);
		gbc_lstLista.gridx = 0;
		gbc_lstLista.gridy = 3;
		pnlListe.add(lstLista, gbc_lstLista);

		lstSortirana = new List();
		GridBagConstraints gbc_lstSortirana = new GridBagConstraints();
		gbc_lstSortirana.fill = GridBagConstraints.BOTH;
		gbc_lstSortirana.gridwidth = 2;
		gbc_lstSortirana.gridx = 2;
		gbc_lstSortirana.gridy = 3;
		pnlListe.add(lstSortirana, gbc_lstSortirana);
	}

}
