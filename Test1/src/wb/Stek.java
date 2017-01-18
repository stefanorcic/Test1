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
	private DefaultListModel listaKvadrata = new DefaultListModel();
	private JLabel lblObimSteka;
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
		setBounds(100, 100, 416, 330);
		pnlOsnovni = new JPanel();
		pnlOsnovni.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOsnovni);
						pnlOsnovni.setLayout(new BorderLayout(0, 0));
				
						JPanel pnlCentar = new JPanel();
						pnlOsnovni.add(pnlCentar, BorderLayout.NORTH);
						
						
						
						
								JButton btnDodajKvadrat = new JButton("Dodaj kvadrat");
								btnDodajKvadrat.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										DlgKvadrat dlgK = new DlgKvadrat();
										dlgK.setVisible(true);
										Kvadrat pomocniKvadrat = dlgK.pomKvadrat;
										stekKvadrata.push(pomocniKvadrat);
										listaKvadrata.add(0,pomocniKvadrat.opis());
										lblObimSteka.setText("Obim steka: " + stekKvadrata.size());
										
									}
								});
										pnlCentar.setLayout(new MigLayout("", "[400.00px]", "[14px][23px][23px][182.00]"));
										
										JLabel lblSuperProgramBrata = new JLabel("Super program brata orcica");
										lblSuperProgramBrata.setHorizontalAlignment(SwingConstants.CENTER);
										pnlCentar.add(lblSuperProgramBrata, "cell 0 0,growx");
										pnlCentar.add(btnDodajKvadrat, "cell 0 1,growx,aligny bottom");
										
												JButton btnIzuzmiKvadrat = new JButton("Izuzmi kvadrat");
												btnIzuzmiKvadrat.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														try{
															Kvadrat pomocniKvadrat = stekKvadrata.pop();
															stekKvadrata.push(pomocniKvadrat);
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
												pnlCentar.add(btnIzuzmiKvadrat, "cell 0 2,growx,aligny center");
												
												List list = new List();
												pnlCentar.add(list, "cell 0 3,grow");
														
														lblObimSteka = new JLabel("");
														pnlOsnovni.add(lblObimSteka, BorderLayout.SOUTH);
														lblObimSteka.setText("Obim steka: " + stekKvadrata.size());
	}

}
