package application;

import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import engine.MSystem;

import javax.swing.GroupLayout.Alignment;

public class InputDiag extends JFrame {

	private JPanel contentPane;
	private JTextField textField_TDS;
	private JTextField textField_pH;
	private JTextField textField_T;
	private JTextField textField_PF;
	private JTextField textField_1Con;
	private JTextField textField_1Comp;
	private JTextField textField_2Con;
	private JTextField textField_2Comp;
	private JTextField textField_BP;
	private JTextField textField_RRate;
	private JTextField textField_PWater;

	private JLabel label_waterin;

	private JComboBox<Object> box_Section;
	private JComboBox<Object> box_mType;

	private JButton button_Calculate;
	private boolean state = true;

	private DisplayDiag displaydiag;
	private MSystem systemmodel;

	public InputDiag() {
		systemmodel = new MSystem();
		setTitle("RO工艺设计");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(571, 413);
		setLocationRelativeTo(null);
		setResizable(false);

		buildJpannel();
		buttonAction();
	}

	public void buildJpannel() {

		contentPane = new JPanel();
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		TitledBorder titledBorder = BorderFactory.createTitledBorder("原水水质");
		panel.setBorder(titledBorder);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 110, 100, 15, 30, 86, 100, 38 };
		gbl_panel.rowHeights = new int[] { 10, 32, 32 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0, 1.0, 1.0 };
		panel.setLayout(gbl_panel);

		JLabel label_TDS = new JLabel("TDS");
		GridBagConstraints gbc_label_TDS = new GridBagConstraints();
		gbc_label_TDS.anchor = GridBagConstraints.WEST;
		gbc_label_TDS.insets = new Insets(0, 0, 5, 5);
		gbc_label_TDS.gridx = 0;
		gbc_label_TDS.gridy = 1;
		panel.add(label_TDS, gbc_label_TDS);

		textField_TDS = new JTextField(String.format("%.2f", systemmodel.streams.tds));
		jtfevent(textField_TDS);
		GridBagConstraints gbc_textField_TDS = new GridBagConstraints();
		gbc_textField_TDS.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_TDS.insets = new Insets(0, 0, 5, 5);
		gbc_textField_TDS.gridx = 1;
		gbc_textField_TDS.gridy = 1;
		panel.add(textField_TDS, gbc_textField_TDS);

		JLabel label_tds = new JLabel("mg/l");
		GridBagConstraints gbc_label_tds = new GridBagConstraints();
		gbc_label_tds.anchor = GridBagConstraints.WEST;
		gbc_label_tds.insets = new Insets(0, 0, 5, 5);
		gbc_label_tds.gridx = 2;
		gbc_label_tds.gridy = 1;
		panel.add(label_tds, gbc_label_tds);

		JLabel label_pH = new JLabel("pH");
		GridBagConstraints gbc_label_pH = new GridBagConstraints();
		gbc_label_pH.anchor = GridBagConstraints.WEST;
		gbc_label_pH.insets = new Insets(0, 0, 5, 5);
		gbc_label_pH.gridx = 4;
		gbc_label_pH.gridy = 1;
		panel.add(label_pH, gbc_label_pH);

		textField_pH = new JTextField(String.format("%.1f", systemmodel.streams.pH()));
		jtfevent(textField_pH);
		GridBagConstraints gbc_textField_pH = new GridBagConstraints();
		gbc_textField_pH.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_pH.insets = new Insets(0, 0, 5, 5);
		gbc_textField_pH.gridx = 5;
		gbc_textField_pH.gridy = 1;
		panel.add(textField_pH, gbc_textField_pH);

		JLabel label_T = new JLabel("温度");
		GridBagConstraints gbc_label_T = new GridBagConstraints();
		gbc_label_T.anchor = GridBagConstraints.WEST;
		gbc_label_T.insets = new Insets(0, 0, 0, 5);
		gbc_label_T.gridx = 0;
		gbc_label_T.gridy = 2;
		panel.add(label_T, gbc_label_T);

		textField_T = new JTextField(String.format("%.1f", systemmodel.streams.temperature));
		jtfevent(textField_T);
		GridBagConstraints gbc_textField_T = new GridBagConstraints();
		gbc_textField_T.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_T.insets = new Insets(0, 0, 0, 5);
		gbc_textField_T.gridx = 1;
		gbc_textField_T.gridy = 2;
		panel.add(textField_T, gbc_textField_T);

		JLabel label_t = new JLabel("℃");
		GridBagConstraints gbc_label_t = new GridBagConstraints();
		gbc_label_t.anchor = GridBagConstraints.WEST;
		gbc_label_t.insets = new Insets(0, 0, 0, 5);
		gbc_label_t.gridx = 2;
		gbc_label_t.gridy = 2;
		panel.add(label_t, gbc_label_t);

		JLabel label_PF = new JLabel("污染因子");
		GridBagConstraints gbc_label_PF = new GridBagConstraints();
		gbc_label_PF.anchor = GridBagConstraints.WEST;
		gbc_label_PF.insets = new Insets(0, 0, 0, 5);
		gbc_label_PF.gridx = 4;
		gbc_label_PF.gridy = 2;
		panel.add(label_PF, gbc_label_PF);

		textField_PF = new JTextField(String.format("%.2f", systemmodel.streams.ff));
		jtfevent(textField_PF);
		GridBagConstraints gbc_textField_PF = new GridBagConstraints();
		gbc_textField_PF.insets = new Insets(0, 0, 0, 5);
		gbc_textField_PF.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_PF.gridx = 5;
		gbc_textField_PF.gridy = 2;
		panel.add(textField_PF, gbc_textField_PF);

		JPanel panel_1 = new JPanel();
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder("运行参数");
		panel_1.setBorder(titledBorder1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 105, 100, 15, 40, 86, 100, 28 };
		gbl_panel_1.rowHeights = new int[] { 10, 32, 32, 32, 32, 32 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_panel_1.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panel_1.setLayout(gbl_panel_1);

		JLabel label_section = new JLabel("段数");
		GridBagConstraints gbc_label_section = new GridBagConstraints();
		gbc_label_section.anchor = GridBagConstraints.WEST;
		gbc_label_section.insets = new Insets(0, 0, 5, 5);
		gbc_label_section.gridx = 0;
		gbc_label_section.gridy = 1;
		panel_1.add(label_section, gbc_label_section);

		box_Section = new JComboBox<>();
		box_Section.setPreferredSize(new Dimension(100, 24));
		GridBagConstraints gbc_box_Section = new GridBagConstraints();
		gbc_box_Section.fill = GridBagConstraints.HORIZONTAL;
		gbc_box_Section.insets = new Insets(0, 0, 5, 5);
		gbc_box_Section.gridx = 1;
		gbc_box_Section.gridy = 1;
		panel_1.add(box_Section, gbc_box_Section);
		box_Section.addItem(1);
		box_Section.addItem(2);
		box_Section.setSelectedItem(systemmodel.groups.size());

		box_mType = new JComboBox<>();
		box_mType.setPreferredSize(new Dimension(100, 24));
		box_mType.addItem("BW_8040");
		box_mType.addItem("ULP_8040");
		box_mType.addItem("SW_8040");
		box_mType.setSelectedItem(systemmodel.motype());

		JLabel label_mType = new JLabel("膜元件类型");
		GridBagConstraints gbc_label_mType = new GridBagConstraints();
		gbc_label_mType.anchor = GridBagConstraints.WEST;
		gbc_label_mType.insets = new Insets(0, 0, 5, 5);
		gbc_label_mType.gridx = 4;
		gbc_label_mType.gridy = 1;
		panel_1.add(label_mType, gbc_label_mType);
		GridBagConstraints gbc_box_mType = new GridBagConstraints();
		gbc_box_mType.fill = GridBagConstraints.HORIZONTAL;
		gbc_box_mType.insets = new Insets(0, 0, 5, 5);
		gbc_box_mType.gridx = 5;
		gbc_box_mType.gridy = 1;
		panel_1.add(box_mType, gbc_box_mType);

		JLabel label_1Ves = new JLabel("一段压力容器数");
		GridBagConstraints gbc_label_1Ves = new GridBagConstraints();
		gbc_label_1Ves.anchor = GridBagConstraints.WEST;
		gbc_label_1Ves.insets = new Insets(0, 0, 5, 5);
		gbc_label_1Ves.gridx = 0;
		gbc_label_1Ves.gridy = 2;
		panel_1.add(label_1Ves, gbc_label_1Ves);

		textField_1Con = new JTextField(String.format("%d", systemmodel.groups.get(0).nvi));
		jtfevent(textField_1Con);
		GridBagConstraints gbc_textField_1Con = new GridBagConstraints();
		gbc_textField_1Con.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1Con.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1Con.gridx = 1;
		gbc_textField_1Con.gridy = 2;
		panel_1.add(textField_1Con, gbc_textField_1Con);

		JLabel label_1vesUnit = new JLabel("支");
		GridBagConstraints gbc_label_1vesUnit = new GridBagConstraints();
		gbc_label_1vesUnit.anchor = GridBagConstraints.WEST;
		gbc_label_1vesUnit.insets = new Insets(0, 0, 5, 5);
		gbc_label_1vesUnit.gridx = 2;
		gbc_label_1vesUnit.gridy = 2;
		panel_1.add(label_1vesUnit, gbc_label_1vesUnit);

		JLabel label_BP = new JLabel("段间增压");
		GridBagConstraints gbc_label_BP = new GridBagConstraints();
		gbc_label_BP.anchor = GridBagConstraints.WEST;
		gbc_label_BP.insets = new Insets(0, 0, 5, 5);
		gbc_label_BP.gridx = 4;
		gbc_label_BP.gridy = 2;
		panel_1.add(label_BP, gbc_label_BP);

		textField_BP = new JTextField(String.format("%.3f", systemmodel.dpf));
		jtfevent(textField_BP);
		GridBagConstraints gbc_textField_BP = new GridBagConstraints();
		gbc_textField_BP.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_BP.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BP.gridx = 5;
		gbc_textField_BP.gridy = 2;
		panel_1.add(textField_BP, gbc_textField_BP);

		JLabel label_bp = new JLabel("MPa");
		GridBagConstraints gbc_label_bp = new GridBagConstraints();
		gbc_label_bp.anchor = GridBagConstraints.WEST;
		gbc_label_bp.insets = new Insets(0, 0, 5, 0);
		gbc_label_bp.gridx = 6;
		gbc_label_bp.gridy = 2;
		panel_1.add(label_bp, gbc_label_bp);

		JLabel label_1Comp = new JLabel("一段膜元件数");
		GridBagConstraints gbc_label_1Comp = new GridBagConstraints();
		gbc_label_1Comp.anchor = GridBagConstraints.WEST;
		gbc_label_1Comp.insets = new Insets(0, 0, 5, 5);
		gbc_label_1Comp.gridx = 0;
		gbc_label_1Comp.gridy = 3;
		panel_1.add(label_1Comp, gbc_label_1Comp);

		textField_1Comp = new JTextField(String.format("%d", systemmodel.groups.get(0).ni));
		jtfevent(textField_1Comp);
		GridBagConstraints gbc_textField_1Comp = new GridBagConstraints();
		gbc_textField_1Comp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1Comp.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1Comp.gridx = 1;
		gbc_textField_1Comp.gridy = 3;
		panel_1.add(textField_1Comp, gbc_textField_1Comp);

		JLabel label_1CompUnit = new JLabel("个");
		GridBagConstraints gbc_label_1CompUnit = new GridBagConstraints();
		gbc_label_1CompUnit.anchor = GridBagConstraints.WEST;
		gbc_label_1CompUnit.insets = new Insets(0, 0, 5, 5);
		gbc_label_1CompUnit.gridx = 2;
		gbc_label_1CompUnit.gridy = 3;
		panel_1.add(label_1CompUnit, gbc_label_1CompUnit);

		JLabel label_WaterIn = new JLabel("系统进水量");
		GridBagConstraints gbc_label_WaterIn = new GridBagConstraints();
		gbc_label_WaterIn.anchor = GridBagConstraints.WEST;
		gbc_label_WaterIn.insets = new Insets(0, 0, 5, 5);
		gbc_label_WaterIn.gridx = 4;
		gbc_label_WaterIn.gridy = 3;
		panel_1.add(label_WaterIn, gbc_label_WaterIn);

		label_waterin = new JLabel();
		label_waterin.setText(String.format("%.1f", systemmodel.systemPQ / systemmodel.systemY()));
		GridBagConstraints gbc_label_waterin = new GridBagConstraints();
		gbc_label_waterin.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_waterin.insets = new Insets(0, 0, 5, 5);
		gbc_label_waterin.gridx = 5;
		gbc_label_waterin.gridy = 3;
		panel_1.add(label_waterin, gbc_label_waterin);

		JLabel waterin_Unit = new JLabel("m3/h");
		GridBagConstraints gbc_waterin_Unit = new GridBagConstraints();
		gbc_waterin_Unit.anchor = GridBagConstraints.WEST;
		gbc_waterin_Unit.insets = new Insets(0, 0, 5, 0);
		gbc_waterin_Unit.gridx = 6;
		gbc_waterin_Unit.gridy = 3;
		panel_1.add(waterin_Unit, gbc_waterin_Unit);

		JLabel label_2Ves = new JLabel("二段压力容器数");
		GridBagConstraints gbc_label_2Ves = new GridBagConstraints();
		gbc_label_2Ves.anchor = GridBagConstraints.WEST;
		gbc_label_2Ves.insets = new Insets(0, 0, 5, 5);
		gbc_label_2Ves.gridx = 0;
		gbc_label_2Ves.gridy = 4;
		panel_1.add(label_2Ves, gbc_label_2Ves);

		textField_2Con = new JTextField(String.format("%d", systemmodel.groups.get(1).nvi));
		jtfevent(textField_2Con);
		GridBagConstraints gbc_textField_2Con = new GridBagConstraints();
		gbc_textField_2Con.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2Con.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2Con.gridx = 1;
		gbc_textField_2Con.gridy = 4;
		panel_1.add(textField_2Con, gbc_textField_2Con);

		JLabel label_2ves = new JLabel("支");
		GridBagConstraints gbc_label_2ves = new GridBagConstraints();
		gbc_label_2ves.anchor = GridBagConstraints.WEST;
		gbc_label_2ves.insets = new Insets(0, 0, 5, 5);
		gbc_label_2ves.gridx = 2;
		gbc_label_2ves.gridy = 4;
		panel_1.add(label_2ves, gbc_label_2ves);

		JLabel label_RRate = new JLabel("系统回收率");
		GridBagConstraints gbc_label_RRate = new GridBagConstraints();
		gbc_label_RRate.anchor = GridBagConstraints.WEST;
		gbc_label_RRate.insets = new Insets(0, 0, 5, 5);
		gbc_label_RRate.gridx = 4;
		gbc_label_RRate.gridy = 4;
		panel_1.add(label_RRate, gbc_label_RRate);

		textField_RRate = new JTextField(String.format("%.2f", systemmodel.systemY() * 100));
		jtfevent(textField_RRate);
		GridBagConstraints gbc_textField_RRate = new GridBagConstraints();
		gbc_textField_RRate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_RRate.insets = new Insets(0, 0, 5, 5);
		gbc_textField_RRate.gridx = 5;
		gbc_textField_RRate.gridy = 4;
		panel_1.add(textField_RRate, gbc_textField_RRate);

		JLabel label_rrate = new JLabel("%");
		GridBagConstraints gbc_label_rrate = new GridBagConstraints();
		gbc_label_rrate.anchor = GridBagConstraints.WEST;
		gbc_label_rrate.insets = new Insets(0, 0, 5, 0);
		gbc_label_rrate.gridx = 6;
		gbc_label_rrate.gridy = 4;
		panel_1.add(label_rrate, gbc_label_rrate);

		JLabel label_2DM = new JLabel("二段膜元件数");
		GridBagConstraints gbc_label_2DM = new GridBagConstraints();
		gbc_label_2DM.anchor = GridBagConstraints.WEST;
		gbc_label_2DM.insets = new Insets(0, 0, 0, 5);
		gbc_label_2DM.gridx = 0;
		gbc_label_2DM.gridy = 5;
		panel_1.add(label_2DM, gbc_label_2DM);

		textField_2Comp = new JTextField(String.format("%d", systemmodel.groups.get(1).ni));
		jtfevent(textField_2Comp);
		GridBagConstraints gbc_textField_2Comp = new GridBagConstraints();
		gbc_textField_2Comp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2Comp.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2Comp.gridx = 1;
		gbc_textField_2Comp.gridy = 5;
		panel_1.add(textField_2Comp, gbc_textField_2Comp);

		JLabel label_2dm = new JLabel("个");
		GridBagConstraints gbc_label_2dm = new GridBagConstraints();
		gbc_label_2dm.anchor = GridBagConstraints.WEST;
		gbc_label_2dm.insets = new Insets(0, 0, 0, 5);
		gbc_label_2dm.gridx = 2;
		gbc_label_2dm.gridy = 5;
		panel_1.add(label_2dm, gbc_label_2dm);

		JLabel label_PWater = new JLabel("系统产水量");
		GridBagConstraints gbc_label_PWater = new GridBagConstraints();
		gbc_label_PWater.anchor = GridBagConstraints.WEST;
		gbc_label_PWater.insets = new Insets(0, 0, 0, 5);
		gbc_label_PWater.gridx = 4;
		gbc_label_PWater.gridy = 5;
		panel_1.add(label_PWater, gbc_label_PWater);

		textField_PWater = new JTextField(String.format("%.2f", systemmodel.systemPQ));
		jtfevent(textField_PWater);
		GridBagConstraints gbc_textField_PWater = new GridBagConstraints();
		gbc_textField_PWater.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_PWater.insets = new Insets(0, 0, 0, 5);
		gbc_textField_PWater.gridx = 5;
		gbc_textField_PWater.gridy = 5;
		panel_1.add(textField_PWater, gbc_textField_PWater);

		JLabel pWater_unit = new JLabel("m3/h");
		GridBagConstraints gbc_pWater_unit = new GridBagConstraints();
		gbc_pWater_unit.anchor = GridBagConstraints.WEST;
		gbc_pWater_unit.gridx = 6;
		gbc_pWater_unit.gridy = 5;
		panel_1.add(pWater_unit, gbc_pWater_unit);

		button_Calculate = new JButton("计算");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(412, Short.MAX_VALUE)
						.addComponent(button_Calculate).addGap(90)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addContainerGap(15, Short.MAX_VALUE)
				.addComponent(button_Calculate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE).addContainerGap(15, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

		box_Section.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					systemmodel.group((int) box_Section.getSelectedItem());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
				}
				textField_BP.setText(String.format("%.3f", systemmodel.groups.get(0).dpf));
				state = false;
				if (box_Section.getSelectedIndex() == 1) {
					state = true;
				}
				label_2Ves.setEnabled(state);
				textField_2Con.setEnabled(state);
				label_2ves.setEnabled(state);
				label_2DM.setEnabled(state);
				textField_2Comp.setEnabled(state);
				label_2dm.setEnabled(state);
				label_BP.setEnabled(state);
				textField_BP.setEnabled(state);
				label_bp.setEnabled(state);
			}
		});
		box_mType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					systemmodel.motype(box_mType.getSelectedItem().toString());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void buttonAction() {
		// 计算
		button_Calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					systemmodel.sysCalc();
					displaydiag = new DisplayDiag(systemmodel);
					displaydiag.frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void jtfevent(JTextField jtf) {
		jtf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					jtfoutput(jtf);
				}
			}
		});
		jtf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				{
					jtfoutput(jtf);
				}
			}
		});
	}

	public void jtfoutput(JTextField jtf) {
		try {
			if (jtf == textField_TDS) {
				systemmodel.streams.tds = Double.parseDouble(textField_TDS.getText());
			} else if (jtf == textField_T) {
				systemmodel.streams.temperature = Double.parseDouble(textField_T.getText());
			} else if (jtf == textField_PF) {
				systemmodel.streams.ff = Double.parseDouble(textField_PF.getText());
			} else if (jtf == textField_1Con) {
				systemmodel.groups.get(0).nvi = Integer.parseInt(textField_1Con.getText());
			} else if (jtf == textField_1Comp) {
				systemmodel.groups.get(0).ni = Integer.parseInt(textField_1Comp.getText());
			} else if (jtf == textField_2Con) {
				systemmodel.groups.get(1).nvi = Integer.parseInt(textField_2Con.getText());
			} else if (jtf == textField_2Comp) {
				systemmodel.groups.get(1).ni = Integer.parseInt(textField_2Comp.getText());
			} else if (jtf == textField_BP) {
				systemmodel.dpf = Double.parseDouble(textField_BP.getText());
			} else if (jtf == textField_PWater) {
				systemmodel.systemPQ = Double.parseDouble(textField_PWater.getText());
			} else if (jtf == textField_pH) {
				systemmodel.streams.pH(Double.parseDouble(textField_pH.getText()));
			} else if (jtf == textField_RRate) {
				systemmodel.systemY(Double.parseDouble(textField_RRate.getText()) / 100);
			}
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
		}
		textField_TDS.setText(String.format("%.2f", systemmodel.streams.tds));
		textField_T.setText(String.format("%.1f", systemmodel.streams.temperature));
		textField_pH.setText(String.format("%.1f", systemmodel.streams.pH()));
		textField_PF.setText(String.format("%.2f", systemmodel.streams.ff));
		textField_1Con.setText(String.format("%d", systemmodel.groups.get(0).nvi));
		textField_1Comp.setText(String.format("%d", systemmodel.groups.get(0).ni));
		if (systemmodel.groups.size() == 2) {
			textField_2Con.setText(String.format("%d", systemmodel.groups.get(1).nvi));
			textField_2Comp.setText(String.format("%d", systemmodel.groups.get(1).ni));
		}
		textField_BP.setText(String.format("%.3f", systemmodel.dpf));
		textField_RRate.setText(String.format("%.2f", systemmodel.systemY() * 100));
		textField_PWater.setText(String.format("%.2f", systemmodel.systemPQ));
		label_waterin.setText(String.format("%.1f", systemmodel.systemPQ / systemmodel.systemY()));
	}
}
