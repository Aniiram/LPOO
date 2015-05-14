package maze.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Opcoes extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static int tamanhoTab = 11;
	static int numDrag = 1;
	static char[] controles; // = new char[];
	static int modoJogo = 1;

	public Opcoes() {
		//JFrame opcoes = new JFrame();
		
		//Control.frm.setBounds(400, 200, 435, 480);
		//opcoes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{39, 101, 39, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		SpinnerModel model = new SpinnerNumberModel(11, 1, 99, 2);

		Hashtable<Integer, JLabel> labels =
				new Hashtable<Integer, JLabel>();

		//New Label
		JLabel lblNewLabel_1 = new JLabel("Op\u00E7\u00F5es:\r\n");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		Panel panel = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);


		//New Label
		JLabel lblNewLabel = new JLabel("Tamanho labirinto: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);

		//New Spinner
		JSpinner spinner = new JSpinner(model);
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.anchor = GridBagConstraints.NORTH;
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 3;
		add(spinner, gbc_spinner);


		//New Slider
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 99,1);//(int) spinner.getValue(), 1);
		for (int i = 1; i < 99;) {//(int) spinner.getValue(); ){
			labels.put(i, new JLabel(Integer.toString(i)));
			i += 15;//5;
		}


		//New Label
		JLabel lblNmeroDeDrages = new JLabel("N\u00FAmero de Drag\u00F5es:");
		GridBagConstraints gbc_lblNmeroDeDrages = new GridBagConstraints();
		gbc_lblNmeroDeDrages.anchor = GridBagConstraints.WEST;
		gbc_lblNmeroDeDrages.insets = new Insets(0, 0, 5, 5);
		gbc_lblNmeroDeDrages.gridx = 1;
		gbc_lblNmeroDeDrages.gridy = 4;
		add(lblNmeroDeDrages, gbc_lblNmeroDeDrages);


		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);

		slider.setLabelTable(labels);

		slider.setPaintLabels(true);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 2;
		gbc_slider.gridy = 4;
		add(slider, gbc_slider);

		//New Label
		JLabel lblModoDeJogo = new JLabel("Modo de Jogo:");
		GridBagConstraints gbc_lblModoDeJogo = new GridBagConstraints();
		gbc_lblModoDeJogo.anchor = GridBagConstraints.WEST;
		gbc_lblModoDeJogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModoDeJogo.gridx = 1;
		gbc_lblModoDeJogo.gridy = 5;

		add(lblModoDeJogo, gbc_lblModoDeJogo);


		String[] modo = { "Fácil", "Médio", "Díficil"};
		JComboBox<?> comboBox = new JComboBox<Object>(modo);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		add(comboBox, gbc_comboBox);

		Panel panel_2 = new Panel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 6;
		add(panel_2, gbc_panel_2);

		Label label = new Label("Objectivo do Jogo:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 7;
		add(label, gbc_label);

		JLabel lblMatarOsDrages = new JLabel(" Matar os Drag\u00F5es e sair do Labirinto.");
		GridBagConstraints gbc_lblMatarOsDrages = new GridBagConstraints();
		gbc_lblMatarOsDrages.anchor = GridBagConstraints.WEST;
		gbc_lblMatarOsDrages.gridwidth = 2;
		gbc_lblMatarOsDrages.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatarOsDrages.gridx = 1;
		gbc_lblMatarOsDrages.gridy = 8;
		add(lblMatarOsDrages, gbc_lblMatarOsDrages);

		Panel panel_1 = new Panel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 9;
		add(panel_1, gbc_panel_1);

		JLabel lblNewLabel_3 = new JLabel(" O Modo de Jogo apresenta tr\u00EAs possibilidades:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 10;
		add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(" Modo F\u00E1cil: Os drag\u00F5es est\u00E3o est\u00E1ticos.");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 11;
		add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(" Modo M\u00E9dio: Os drag\u00F5es movem-se aleat\u00F3riamente.");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 12;
		add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(" Modo D\u00EDficil: Os drag\u00F5es movem-se aleat\u00F3riamente, disparam");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 13;
		add(lblNewLabel_6, gbc_lblNewLabel_6);

		JLabel lblDisparamFogoAleatriamente = new JLabel(" fogo aleat\u00F3riamente e tem periodos de dormida aleat\u00F3ria.");
		GridBagConstraints gbc_lblDisparamFogoAleatriamente = new GridBagConstraints();
		gbc_lblDisparamFogoAleatriamente.anchor = GridBagConstraints.WEST;
		gbc_lblDisparamFogoAleatriamente.gridwidth = 2;
		gbc_lblDisparamFogoAleatriamente.insets = new Insets(0, 0, 5, 5);
		gbc_lblDisparamFogoAleatriamente.gridx = 1;
		gbc_lblDisparamFogoAleatriamente.gridy = 14;
		add(lblDisparamFogoAleatriamente, gbc_lblDisparamFogoAleatriamente);
		
		Panel panel_3 = new Panel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 16;
		add(panel_3, gbc_panel_3);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tamanhoTab = (int) spinner.getValue();
				numDrag = slider.getValue();
				modoJogo = 1 + comboBox.getSelectedIndex();
				Control.showPanel("menu1", 180, 253);
			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.anchor = GridBagConstraints.EAST;
		gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalvar.gridx = 2;
		gbc_btnSalvar.gridy = 17;
		add(btnSalvar, gbc_btnSalvar);
	}
	
}
