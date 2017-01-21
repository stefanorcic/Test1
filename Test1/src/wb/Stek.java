package wb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometrija.Kvadrat;
import geometrija.Tacka;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ItemListener;
import java.util.Stack;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class Stek extends JFrame {

	private JPanel pnlOsnovni;
	private Stack<Kvadrat> stekKvadrata = new Stack<Kvadrat>();
	private JLabel lblObimSteka;
	List listaKvadrata;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stek frame = new Stek();
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
	public Stek() {
		setTitle("Or\u010Di\u0107 Stefan IT11-2015 / Stek");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 402);
		pnlOsnovni = new JPanel();
		pnlOsnovni.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOsnovni);
		pnlOsnovni.setLayout(new BorderLayout(0, 0));

		JPanel pnlCentar = new JPanel();
		pnlOsnovni.add(pnlCentar, BorderLayout.NORTH);
		GridBagLayout gbl_pnlCentar = new GridBagLayout();
		gbl_pnlCentar.columnWidths = new int[]{400, 0};
		gbl_pnlCentar.rowHeights = new int[]{14, 23, 23, 244, 0};
		gbl_pnlCentar.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlCentar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCentar.setLayout(gbl_pnlCentar);




		JButton btnDodajKvadrat = new JButton("Dodaj kvadrat");
		btnDodajKvadrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgKvadrat dlgK = new DlgKvadrat();
				dlgK.setVisible(true);
				Kvadrat pomocniKvadrat = dlgK.pomKvadrat;
				stekKvadrata.push(pomocniKvadrat);
				listaKvadrata.add(pomocniKvadrat.opis(),0);
				lblObimSteka.setText("Obim steka: " + stekKvadrata.size());

			}
		});

		JLabel lblSuperProgramBrata = new JLabel("Super program brata orcica");
		lblSuperProgramBrata.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSuperProgramBrata = new GridBagConstraints();
		gbc_lblSuperProgramBrata.anchor = GridBagConstraints.NORTH;
		gbc_lblSuperProgramBrata.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSuperProgramBrata.insets = new Insets(0, 0, 5, 0);
		gbc_lblSuperProgramBrata.gridx = 0;
		gbc_lblSuperProgramBrata.gridy = 0;
		pnlCentar.add(lblSuperProgramBrata, gbc_lblSuperProgramBrata);
		GridBagConstraints gbc_btnDodajKvadrat = new GridBagConstraints();
		gbc_btnDodajKvadrat.anchor = GridBagConstraints.SOUTH;
		gbc_btnDodajKvadrat.insets = new Insets(0, 0, 5, 0);
		gbc_btnDodajKvadrat.gridx = 0;
		gbc_btnDodajKvadrat.gridy = 1;
		pnlCentar.add(btnDodajKvadrat, gbc_btnDodajKvadrat);

		JButton btnIzuzmiKvadrat = new JButton("Izuzmi kvadrat");
		btnIzuzmiKvadrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Kvadrat pomocniKvadrat = stekKvadrata.peek();
					int opcija = JOptionPane.showOptionDialog(null, pomocniKvadrat.opis(),"Izuzimanje kvadrata" , JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					if(opcija == 0){
						pomocniKvadrat = stekKvadrata.pop();
						System.out.println(pomocniKvadrat.opis());
						listaKvadrata.remove(0);
						lblObimSteka.setText("Obim steka: " + stekKvadrata.size());
					}
				}catch (Exception exc){
					JOptionPane.showMessageDialog(null, "Stek je prazan!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnIzuzmiKvadrat = new GridBagConstraints();
		gbc_btnIzuzmiKvadrat.insets = new Insets(0, 0, 5, 0);
		gbc_btnIzuzmiKvadrat.gridx = 0;
		gbc_btnIzuzmiKvadrat.gridy = 2;
		pnlCentar.add(btnIzuzmiKvadrat, gbc_btnIzuzmiKvadrat);

		listaKvadrata = new List();
		GridBagConstraints gbc_listaKvadrata1 = new GridBagConstraints();
		gbc_listaKvadrata1.fill = GridBagConstraints.BOTH;
		gbc_listaKvadrata1.gridx = 0;
		gbc_listaKvadrata1.gridy = 3;
		pnlCentar.add(listaKvadrata, gbc_listaKvadrata1);

		lblObimSteka = new JLabel("");
		pnlOsnovni.add(lblObimSteka, BorderLayout.SOUTH);
		lblObimSteka.setText("Obim steka: " + stekKvadrata.size());
	}

}
