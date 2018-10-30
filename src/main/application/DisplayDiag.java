package application;

import engine.MBranch;
import engine.MGroup;
import engine.MSystem;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class DisplayDiag {
	MSystem systemmodel;
	JFrame frame;
	JScrollPane jsp1, jsp2, jsp3, jsp4;// 显示表头

	public DisplayDiag(MSystem systemmodel) {
		this.systemmodel = systemmodel;
		initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.setTitle("输出报告");

		// panel1 系统细节面板
		JPanel panel1 = new JPanel();
		TitledBorder titledBorder = BorderFactory.createTitledBorder("系统细节");
		panel1.setBorder(titledBorder);
		// panel2 段内膜元件细节面板
		JPanel panel2 = new JPanel();
		TitledBorder titledBorder_1 = BorderFactory.createTitledBorder("段内膜元件细节");
		panel2.setBorder(titledBorder_1);
		// panel3 系统预警面板
		JPanel panel3 = new JPanel();
		TitledBorder titledBorder_2 = BorderFactory.createTitledBorder("系统预警");
		panel3.setBorder(titledBorder_2);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 990,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 990, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 990, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		int panel1Height = 0, panel2Height = 0, panel3Height = 0;
		if (systemmodel.groups.size() == 1) {
			frame.setSize(1000, 400);
			panel1Height = 120;
			panel2Height = 140;
			panel3Height = 140;
		} else if (systemmodel.groups.size() == 2) {
			frame.setSize(1000, 480);
			panel1Height = 140;
			panel2Height = 210;
			panel3Height = 130;
		}
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel1, GroupLayout.PREFERRED_SIZE, panel1Height,
										GroupLayout.PREFERRED_SIZE)
								.addGap(3)
								.addComponent(panel2, GroupLayout.DEFAULT_SIZE, panel2Height,
										GroupLayout.PREFERRED_SIZE)
								.addGap(3).addComponent(panel3, GroupLayout.PREFERRED_SIZE, panel3Height,
										GroupLayout.PREFERRED_SIZE)));
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth()) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight()) / 2;
		frame.setLocation(w, h);
		frame.setResizable(false); // 禁止最大化及缩放功能

		Object[][] data1 = new Object[3][10];
		String[] name1 = new String[10];
		for (int i = 0; i < 10; i++) {
			name1[i] = "";
		}
		JTable table1 = new JTable(data1, name1) {
			@Override // 选中但不可编辑
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabledata(table1, data1, name1, sysdata());
		TableColumnModel tcm = table1.getColumnModel();
		for (int i = 0; i < table1.getColumnCount() / 2; i++) {
			tcm.getColumn(2 * i).setPreferredWidth(50);
		}

		String[] name2 = { "段", "膜元件", "容器数", "元件数", "给水流量", "浓水流量", "产水流量", "进水压力", "浓水压力", "产水压力", "升压压力", "平均通量",
				"进水TDS", "产水TDS" };
		JTable table2 = new JTable(systemmodel.groups.size(), 14) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		Object[][] data2 = new Object[table2.getRowCount()][table2.getColumnCount()];
		tabledata(table2, data2, name2, groupdata());
		tableRender(table2);
		table2.getColumnModel().getColumn(0).setPreferredWidth(30);
		jsp1 = new JScrollPane(table2);

		GroupLayout gl_panel = new GroupLayout(panel1);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(table1, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
								.addComponent(jsp1, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(table1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(jsp1, GroupLayout.PREFERRED_SIZE,
								tableHeight(table2.getRowHeight(), table2.getRowCount(), 2),
								GroupLayout.PREFERRED_SIZE)));
		panel1.setLayout(gl_panel);

		String[] name3 = { "第一段", "进水流量(m³/h)", "产水流量(m³/h)", "回收率", "进水TDS(mg/L)", "产水TDS(mg/L)", "脱盐率", "进水压力(MPa)" };
		JTable table3 = new JTable(systemmodel.groups.get(0).ni, 8) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		Object[][] data3 = new Object[table3.getRowCount()][table3.getColumnCount()];
		tabledata(table3, data3, name3, branchdata(1));
		tableRender(table3);
		table3.getColumnModel().getColumn(0).setPreferredWidth(5);// 设置第一列的列宽
		jsp2 = new JScrollPane(table3);

		GroupLayout gl_panel_1 = new GroupLayout(panel2);
		if (systemmodel.groups.size() == 1) {
			gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(jsp2,
									GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))));
			gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
					gl_panel_1.createSequentialGroup()
							.addComponent(jsp2, GroupLayout.DEFAULT_SIZE,
									tableHeight(table3.getRowHeight(), table3.getRowCount(), 3),
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)));
		} else if (systemmodel.groups.size() == 2) {
			String[] name4 = { "第二段", "进水流量(m³/h)", "产水流量(m³/h)", "回收率", "进水TDS(mg/L)", "产水TDS(mg/L)", "脱盐率",
					"进水压力(MPa)" };
			JTable table4 = new JTable(systemmodel.groups.get(1).ni, 8) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			Object[][] data4 = new Object[table4.getRowCount()][table4.getColumnCount()];
			tabledata(table4, data4, name4, branchdata(2));
			tableRender(table4);
			table4.getColumnModel().getColumn(0).setPreferredWidth(5);// 设置第一列的列宽
			jsp3 = new JScrollPane(table4);

			gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(jsp2, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
									.addComponent(jsp3, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))));

			gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup().addComponent(jsp2, GroupLayout.PREFERRED_SIZE,
							tableHeight(table3.getRowHeight(), table3.getRowCount(), 3), GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED).addComponent(jsp3, GroupLayout.PREFERRED_SIZE,
							tableHeight(table4.getRowHeight(), table4.getRowCount(), 3), GroupLayout.PREFERRED_SIZE)));
		}
		panel2.setLayout(gl_panel_1);

		JTextArea textArea = new JTextArea(systemmodel.warning());
		textArea.setEditable(false);
		comEvent(textArea);
		jsp4 = new JScrollPane(textArea);
		GroupLayout gl_panel_2 = new GroupLayout(panel3);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(jsp4,
								GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))));
		gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(jsp4, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE).addContainerGap()));
		panel3.setLayout(gl_panel_2);
		frame.getContentPane().setLayout(groupLayout);
	}

	public ArrayList<Object> sysdata() {
		ArrayList<Object> res = new ArrayList<>();
		res.addAll(Arrays.asList("系统进水量", String.format("%.1f m³/h", systemmodel.streamf.q)));
		res.addAll(Arrays.asList("系统产水量", String.format("%.3f m³/h", systemmodel.streamp.q)));
		res.addAll(Arrays.asList("进水TDS", String.format("%.2f mg/L", systemmodel.streamf.tds)));
		res.addAll(Arrays.asList("进水压力", String.format("%.3f MPa", systemmodel.streamf.pf)));
		res.addAll(Arrays.asList("回收率", String.format("%.2f %%", systemmodel.systemHSL * 100)));
		res.addAll(Arrays.asList("产水TDS", String.format("%.2f mg/L", systemmodel.streamp.tds)));
		res.addAll(Arrays.asList("段间增压", String.format("%.3f MPa", systemmodel.dpf)));
		res.addAll(Arrays.asList("平均通量", String.format("%.2f LMH", systemmodel.systemFp)));
		res.addAll(Arrays.asList("系统脱盐率", String.format("%.2f %%", systemmodel.systemRs * 100)));
		res.addAll(Arrays.asList("总面积", String.format("%d m²", systemmodel.moN() * (int) systemmodel.moarea)));
		res.addAll(Arrays.asList("给水温度", String.format("%.1f ℃", systemmodel.streamf.temperature)));
		res.addAll(Arrays.asList("膜元件数量", String.format("%d 支", systemmodel.moN())));
		res.addAll(Arrays.asList("污染因子", String.format("%.2f", systemmodel.streamf.ff)));
		res.addAll(Arrays.asList(null, null, null, null));
		return res;
	}

	public ArrayList<Object> groupdata() {
		ArrayList<Object> res1 = new ArrayList<>();
		for (int i = 0; i < systemmodel.groups.size(); i++) {
			MGroup group = systemmodel.groups.get(i);
			res1.addAll(Arrays.asList(i + 1, group.model, group.nvi, group.ni, String.format("%.3f", group.streamf.q),
					String.format("%.3f", group.streamc.q), String.format("%.3f", group.streamp.q),
					String.format("%.3f", group.streamf.pf), String.format("%.3f", group.streamc.pf),
					String.format("%.3f", group.streamp.pf), String.format("%.3f", group.dpf),
					String.format("%.2f", group.groupavgFp), String.format("%.2f", group.streamf.tds),
					String.format("%.2f", group.streamp.tds)));
		}
		return res1;
	}

	public ArrayList<Object> branchdata(int m) {
		ArrayList<Object> detail = new ArrayList<>();
		for (int j = 0; j < systemmodel.groups.get(m - 1).branchs.size(); j++) {
			MBranch branch = systemmodel.groups.get(m - 1).branchs.get(j);
			detail.addAll(Arrays.asList(j + 1, String.format("%.3f", branch.streamf.q),
					String.format("%.3f", branch.streamp.q), String.format("%.3f", branch.moY()),
					String.format("%.2f", branch.streamf.tds), String.format("%.2f", branch.streamp.tds),
					String.format("%.3f", branch.moR()), String.format("%.3f", branch.streamf.pf)));
		}
		return detail;
	}

	public void comEvent(JComponent com) {
		com.setFocusable(true);
		com.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					frame.dispose();
				}
			}
		});
	}

	public int tableHeight(int rowheight, int rowCount, int showrows) {
		int tableHeight = 0;
		if (rowCount <= showrows && rowCount > 0) {
			tableHeight = rowheight * (rowCount + 1);
		} else if (rowCount > showrows) {
			tableHeight = rowheight * (showrows + 1);
		}
		return tableHeight;
	}

	public void tableRender(JTable table) {
		table.getTableHeader().setReorderingAllowed(false);// 表头不可拖动
		DefaultTableCellRenderer tcr4 = new DefaultTableCellRenderer();// 单元格渲染器
		tcr4.setHorizontalAlignment(JLabel.CENTER);// 居中显示
		table.setDefaultRenderer(Object.class, tcr4);// 设置渲染器
		JTableHeader header4 = table.getTableHeader();
		header4.setPreferredSize(new Dimension(header4.getWidth(), table.getRowHeight()));
		DefaultTableCellRenderer hr4 = (DefaultTableCellRenderer) header4.getDefaultRenderer();
		hr4.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);// 列名居中
		comEvent(table);
	}

	public void tabledata(JTable table, Object[][] data, String[] name, ArrayList<Object> modeldata) {
		Object[] inputStr = new Object[modeldata.size()];
		modeldata.toArray(inputStr);
		int num = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				data[i][j] = inputStr[num++];
			}
		}
		table.setModel(new DefaultTableModel(data, name));
	}
}