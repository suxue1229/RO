package console;

import java.util.ArrayList;
import java.util.Arrays;

import engine.MBranch;
import engine.MGroup;

public class ParaHandle {
	public void show(String init, String inputstr) {
		switch (init) {
		case "water":
			waterDetail(inputstr);
			break;
		case "process":
			processDetail(inputstr);
			break;
		case "result":
			resultDetail(inputstr);
			break;
		}
	}

	private void waterDetail(String inputstr) {
		ArrayList<String> str = new ArrayList<>();
		switch (inputstr) {
		case "tds":
			str.addAll(Arrays.asList("进水TDS:", String.format("%.2f", Output.systemmodel.streams.tds), "mg/L"));
			break;
		case "temperature":
			str.addAll(Arrays.asList("温度:", String.format("%.1f", Output.systemmodel.streams.temperature), "°C"));
			break;
		case "pollutionfactor":
			str.addAll(Arrays.asList("污染因子:", String.format("%.2f", Output.systemmodel.streams.ff), ""));
			break;
		case "ph":
			str.addAll(Arrays.asList("pH:", String.format("%.1f", Output.systemmodel.streams.pH()), ""));
			break;
		default:
			str.addAll(Arrays.asList("进水TDS:", String.format("%.2f", Output.systemmodel.streams.tds), "mg/L", "温度:",
					String.format("%.1f", Output.systemmodel.streams.temperature), "°C", "污染因子:",
					String.format("%.2f", Output.systemmodel.streams.ff), "", "pH:",
					String.format("%.1f", Output.systemmodel.streams.pH()), ""));
			break;
		}
		if (inputstr.equals("tds") || inputstr.equals("temperature") || inputstr.equals("pollutionfactor")
				|| inputstr.equals("ph")) {
			StrHandle.getstring(StrHandle.getarrays(str, 1, 3), 5, 1);
		} else {
			StrHandle.getstring(StrHandle.getarrays(str, 4, 3), 5, 1);
		}
	}

	private void processDetail(String inputstr) {
		ArrayList<String> str = new ArrayList<>();
		switch (inputstr) {
		case "section":
			str.addAll(Arrays.asList("系统段数:", Integer.toString(Output.systemmodel.groups.size()), "", ""));
			for (int i = 0; i < Output.systemmodel.groups.size(); i++) {
				str.addAll(Arrays.asList(String.format("第%d段", (i + 1)), "", "", "", "压力容器数:",
						Integer.toString(Output.systemmodel.groups.get(i).nvi), "膜元件数:",
						Integer.toString(Output.systemmodel.groups.get(i).ni)));
			}
			break;
		case "boost":
			str.addAll(Arrays.asList("段间增压:", String.format("%.3f", Output.systemmodel.dpf), "MPa"));
			break;
		case "membrane":
			str.addAll(Arrays.asList("膜元件类型:", Output.systemmodel.motype(), ""));
			break;
		case "prodution":
			str.addAll(Arrays.asList("系统产水量:", String.format("%.3f", Output.systemmodel.systemPQ), "m³/h"));
			break;
		case "recyclerate":
			str.addAll(Arrays.asList("系统回收率:", String.format("%.3f", Output.systemmodel.systemY()), ""));
			break;
		default:
			str.addAll(Arrays.asList("系统段数:", Integer.toString(Output.systemmodel.groups.size()), "一段压力容器数:",
					Integer.toString(Output.systemmodel.groups.get(0).nvi), "一段压力容器内膜元件数:",
					Integer.toString(Output.systemmodel.groups.get(0).ni), "膜元件类型:", Output.systemmodel.motype(),
					"系统产水量:", String.format("%.3f m³/h", Output.systemmodel.systemPQ), "系统回收率:",
					String.format("%.3f", Output.systemmodel.systemY())));
			break;
		}
		if (inputstr.equals("boost") || inputstr.equals("membrane") || inputstr.equals("prodution")
				|| inputstr.equals("recyclerate")) {
			StrHandle.getstring(StrHandle.getarrays(str, 1, 3), 5, 1);
		} else if (inputstr.equals("section")) {
			StrHandle.getstring(StrHandle.getarrays(str, 2 * Output.systemmodel.groups.size() + 1, 4), 5, 1);
		} else {
			if (Output.systemmodel.groups.size() == 1) {
				StrHandle.getstring(StrHandle.getarrays(str, 6, 2), 5, 1);
			}
			if (Output.systemmodel.groups.size() == 2) {
				str.addAll(Arrays.asList("二段压力容器数:", Integer.toString(Output.systemmodel.groups.get(1).nvi),
						"二段压力容器内膜元件数:", Integer.toString(Output.systemmodel.groups.get(1).ni), "段间增压:",
						String.format("%.3f MPa", Output.systemmodel.dpf)));
				StrHandle.getstring(StrHandle.getarrays(str, 9, 2), 5, 1);
			}
		}
	}

	public void sectionShow(String inputstr) {
		try {
			if (Integer.valueOf(inputstr) <= Output.systemmodel.groups.size()) {
				String[][] str = { { String.format("第%d段", Integer.valueOf(inputstr)), "" },
						{ "压力容器数:",
								Integer.toString(Output.systemmodel.groups.get(Integer.valueOf(inputstr) - 1).nvi) },
						{ "膜元件数:",
								Integer.toString(Output.systemmodel.groups.get(Integer.valueOf(inputstr) - 1).ni) } };
				StrHandle.getstring(str, 5, 1);
			} else {
				System.out.println(String.format("请输入小于等于%d的段数值", Output.systemmodel.groups.size()));
			}
		} catch (NumberFormatException e) {
		}
	}

	private void resultDetail(String inputstr) {
		switch (inputstr) {
		case "summary":
			System.out.println("系统计算结果:");
			summary();
			break;
		case "detail":
			System.out.println("段内膜元件参数细节:");
			detail();
			break;
		case "warning":
			System.out.println("系统预警:");
			System.out.println(Output.systemmodel.warning());
			break;
		default:
			System.out.println("系统计算结果:");
			summary();
			System.out.println();
			System.out.println("段内膜元件参数细节:");
			detail();
			System.out.println();
			System.out.println("系统预警:");
			System.out.println(Output.systemmodel.warning());
			break;
		}
	}

	public void set(String init, String inputstr, String val) {
		switch (init) {
		case "water":
			waterSet(inputstr, val);
			break;
		case "process":
			processSet(inputstr, val);
			break;
		}
	}

	private void waterSet(String inputstr, String value) {
		ArrayList<String> str = new ArrayList<>();
		try {
			switch (inputstr) {
			case "tds":
				Output.systemmodel.streams.tds = Double.parseDouble(value);
				str.addAll(Arrays.asList("进水TDS:", String.format("%.2f", Output.systemmodel.streams.tds), "mg/L"));
				break;
			case "temperature":
				Output.systemmodel.streams.temperature = Double.parseDouble(value);
				str.addAll(Arrays.asList("温度:", String.format("%.1f", Output.systemmodel.streams.temperature), "°C"));
				break;
			case "pollutionfactor":
				Output.systemmodel.streams.ff = Double.parseDouble(value);
				str.addAll(Arrays.asList("污染因子:", String.format("%.2f", Output.systemmodel.streams.ff), ""));
				break;
			case "ph":
				Output.systemmodel.streams.pH(Double.parseDouble(value));
				str.addAll(Arrays.asList("pH:", String.format("%.1f", Output.systemmodel.streams.pH()), ""));
				break;
			}
			StrHandle.getstring(StrHandle.getarrays(str, 1, 3), 5, 1);
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void processSet(String inputstr, String value) {
		ArrayList<String> str = new ArrayList<>();
		try {
			switch (inputstr) {
			case "section":
				Output.systemmodel.group(Integer.valueOf(value));
				str.addAll(Arrays.asList("段数:", Integer.toString(Output.systemmodel.groups.size()), ""));
				break;
			case "boost":
				Output.systemmodel.dpf = Double.parseDouble(value);
				str.addAll(Arrays.asList("段间增压:", String.format("%.3f", Output.systemmodel.dpf), "MPa"));
				break;
			case "membrane":
				Output.systemmodel.motype(value);
				str.addAll(Arrays.asList("膜元件类型:", Output.systemmodel.motype(), ""));
				break;
			case "prodution":
				Output.systemmodel.systemPQ = Double.parseDouble(value);
				str.addAll(Arrays.asList("系统产水量:", String.format("%.3f", Output.systemmodel.systemPQ), "m³/h"));
				break;
			case "recyclerate":
				Output.systemmodel.systemY(Double.parseDouble(value));
				str.addAll(Arrays.asList("系统回收率:", String.format("%.3f", Output.systemmodel.systemY()), ""));
				break;
			}
			StrHandle.getstring(StrHandle.getarrays(str, 1, 3), 5, 1);
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void sectionSet(String sectionnum, String nvival, String nval) {
		if (Integer.valueOf(sectionnum) <= Output.systemmodel.groups.size()) {
			Output.systemmodel.groups.get(Integer.valueOf(sectionnum) - 1).nvi = Integer.valueOf(nvival);
			Output.systemmodel.groups.get(Integer.valueOf(sectionnum) - 1).ni = Integer.valueOf(nval);
			String[][] str = {
					{ String.format("第%d段压力容器数:", Integer.valueOf(sectionnum)),
							Integer.toString(Output.systemmodel.groups.get(Integer.valueOf(sectionnum) - 1).nvi) },
					{ String.format("第%d段压力容器内膜元件数:", Integer.valueOf(sectionnum)),
							Integer.toString(Output.systemmodel.groups.get(Integer.valueOf(sectionnum) - 1).ni) } };
			StrHandle.getstring(str, 5, 1);
		} else {
			System.out.println(String.format("请输入小于等于%d的段数值", Output.systemmodel.groups.size()));
		}
	}

	private void summary() {
		String[][] str11 = {
				{ "系统进水量:", String.format("%.1f", Output.systemmodel.streamf.q), "m3/h", "系统产水量:",
						String.format("%.3f", Output.systemmodel.streamp.q), "m3/h", "进水TDS:",
						String.format("%.2f", Output.systemmodel.streamf.tds), "mg/L" },
				{ "进水压力:", String.format("%.3f", Output.systemmodel.streamf.pf), "MPa", "回收率:",
						String.format("%.2f", Output.systemmodel.systemHSL * 100), "%", "产水TDS:",
						String.format("%.2f", Output.systemmodel.streamp.tds), "mg/L" },
				{ "段间增压:", String.format("%.3f", Output.systemmodel.dpf), "MPa", "平均通量:",
						String.format("%.2f", Output.systemmodel.systemFp), "LMH", "脱盐率:",
						String.format("%.2f", Output.systemmodel.systemRs * 100), "%" },
				{ "总面积:", Integer.toString(Output.systemmodel.moN() * (int) Output.systemmodel.moarea), "m2", "给水温度:",
						Integer.toString((int) Output.systemmodel.streams.temperature), "°C", "", "", "", "" },
				{ "膜元件数量:", Integer.toString(Output.systemmodel.moN()), "支", "污染因子:",
						String.format("%.2f", Output.systemmodel.streams.ff), "", "", "", "", "" } };
		StrHandle.getstring(str11, 5, 1);
		System.out.println();
		ArrayList<String> str = new ArrayList<>();
		str.addAll(Arrays.asList("段", "膜元件", "容器数量", "元件数量", "给水流量", "浓水流量", "产水流量", "进水压力", "", "", "", "", "(m3/h)",
				"(m3/h)", "(m3/h)", "(MPa)"));
		for (int i = 0; i < Output.systemmodel.groups.size(); i++) {
			MGroup group = Output.systemmodel.groups.get(i);
			str.addAll(Arrays.asList(Integer.toString(i + 1), group.model, Integer.toString(group.nvi),
					Integer.toString(group.ni), String.format("%.3f", group.streamf.q),
					String.format("%.3f", group.streamc.q), String.format("%.3f", group.streamp.q),
					String.format("%.3f", group.streamf.pf)));
		}
		str.addAll(Arrays.asList("段", "浓水压力", "产水压力", "升压压力", "平均通量", "进水TDS", "产水TDS", "", "", "(MPa)", "(MPa)",
				"(MPa)", "(LMH)", "(mg/L)", "(mg/L)", ""));
		for (int i = 0; i < Output.systemmodel.groups.size(); i++) {
			MGroup group = Output.systemmodel.groups.get(i);
			str.addAll(Arrays.asList(Integer.toString(i + 1), String.format("%.3f", group.streamc.pf),
					String.format("%.3f", group.streamp.pf), String.format("%.3f", group.dpf),
					String.format("%.2f", group.groupavgFp), String.format("%.2f", group.streamf.tds),
					String.format("%.2f", group.streamp.tds), ""));
		}
		StrHandle.getstring(StrHandle.getarrays(str, 2 * (Output.systemmodel.groups.size() + 2), 8), 5, 1);
	}

	private void detail() {
		ArrayList<String> str = new ArrayList<>();
		for (int i = 0; i < Output.systemmodel.groups.size(); i++) {
			str.addAll(Arrays.asList(String.format("第%d段", (i + 1)), "进水流量", "产水流量", "回收率", "进水TDS", "产水TDS", "脱盐率",
					"进水压力", "", "(m3/h)", "(m3/h)", "", "(mg/L)", "(mg/L)", "", "(MPa)"));
			for (int j = 0; j < Output.systemmodel.groups.get(i).branchs.size(); j++) {
				MBranch branch = Output.systemmodel.groups.get(i).branchs.get(j);
				str.addAll(Arrays.asList(Integer.toString(j + 1), String.format("%.3f", branch.streamf.q),
						String.format("%.3f", branch.streamp.q), String.format("%.3f", branch.moY()),
						String.format("%.2f", branch.streamf.tds), String.format("%.2f", branch.streamp.tds),
						String.format("%.3f", branch.moR()), String.format("%.3f", branch.streamf.pf)));
			}
		}
		StrHandle
				.getstring(StrHandle.getarrays(str,
						Output.systemmodel.groups.size() * (2
								+ Output.systemmodel.groups.get(Output.systemmodel.groups.size() - 1).branchs.size()),
				8), 8, 1);
	}

}
