package wb;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometrija.Kvadrat;
import geometrija.Tacka;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class DlgKvadrat extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtDuzina;
	private JComboBox cbxBojaIvice;
	private JComboBox cbxBojaUnutrašnjosti;
	private JLabel lblGreska;
	public Kvadrat pomKvadrat;
	private boolean porukaX = false;
	private boolean porukaY = false;
	private boolean porukaDuzina = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgKvadrat dialog = new DlgKvadrat();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgKvadrat() {
		setTitle("Kvadrat");
		setModal(true);
		setBounds(100, 100, 372, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		{
			JLabel lblTackaGL = new JLabel("Unesite koordinate ta\u010Dke gore levo");
			contentPanel.add(lblTackaGL, "cell 0 0 2 1,alignx left");
		}
		{
			JLabel lblKoordinataX = new JLabel("Koordinata x");
			contentPanel.add(lblKoordinataX, "cell 0 1,alignx center");
		}
		{
			txtX = new JTextField();
			contentPanel.add(txtX, "cell 1 1,alignx left");
			txtX.setColumns(10);
		}
		{
			JLabel lblKoordinataY = new JLabel("Koordinata y");
			contentPanel.add(lblKoordinataY, "cell 0 2,alignx center");
		}
		{
			txtY = new JTextField();
			contentPanel.add(txtY, "cell 1 2,alignx left");
			txtY.setColumns(10);
		}
		{
			JLabel lblDuzina = new JLabel("Unesite du\u017Einu stranice");
			contentPanel.add(lblDuzina, "cell 0 3,alignx center");
		}
		{
			txtDuzina = new JTextField();
			contentPanel.add(txtDuzina, "cell 1 3,alignx left");
			txtDuzina.setColumns(10);
		}
		{
			JLabel lblBojaUnutranjosti = new JLabel("Boja unutra\u0161njosti");
			contentPanel.add(lblBojaUnutranjosti, "cell 0 4,alignx trailing");
		}
		{
			cbxBojaUnutrašnjosti = new JComboBox();
			cbxBojaUnutrašnjosti.setModel(new DefaultComboBoxModel(new String[] {"Crna", "Bela", "Plava", "Zuta", "Zelena", "Pink", "Crvena"}));
			cbxBojaUnutrašnjosti.setSelectedIndex(1);
			contentPanel.add(cbxBojaUnutrašnjosti, "cell 1 4,growx");
		}
		{
			JLabel lblBojaIvice = new JLabel("Boja ivice");
			contentPanel.add(lblBojaIvice, "cell 0 5,alignx trailing");
		}
		{
			cbxBojaIvice = new JComboBox();
			cbxBojaIvice.setModel(new DefaultComboBoxModel(new String[] {"Crna", "Bela", "Plava", "Zuta", "Zelena", "Pink", "Crvena"}));
			contentPanel.add(cbxBojaIvice, "cell 1 5,growx");
		}
		{
			lblGreska = new JLabel("");
			lblGreska.setHorizontalAlignment(SwingConstants.CENTER);
			lblGreska.setForeground(Color.RED);
			contentPanel.add(lblGreska, "cell 0 7 2 2,alignx center");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Potvrdi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Exception exept;
						try{

							int x = Integer.parseInt(txtX.getText());

							int y = Integer.parseInt(txtY.getText());


							int duzina = Integer.parseInt(txtDuzina.getText());

							String bojaUnutra = cbxBojaUnutrašnjosti.getSelectedItem().toString();

							String bojaIvice = cbxBojaIvice.getSelectedItem().toString();

							if(x>0 && y>0 && duzina > 0){
								pomKvadrat = new Kvadrat(new Tacka(x, y), duzina, bojaIvice, bojaUnutra);
							}else{
								exept = new Exception("<html>Koordinate x, y i duzina stranice<br>treba da budu pozitivni celi brojevi</html>");
								throw exept ;
							}
							
							setVisible(false);

						}catch (Exception ex) {
							System.out.println("greska " + ex);
							lblGreska.setText("<html>Koordinate x, y i duzina stranice<br>treba da budu pozitivni celi brojevi</html>");


						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Otka\u017Ei");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
