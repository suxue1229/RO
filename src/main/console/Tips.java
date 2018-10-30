package console;

import java.util.ArrayList;
import java.util.Arrays;

public class Tips {
	private void inittip(String input) {
		ArrayList<String> stringlist = new ArrayList<>();
		stringlist.addAll(Arrays.asList(Output.initout[0], "-查看、设置水质相关参数值", Output.initout[1], "-查看、设置工艺相关参数值",
				Output.initout[2], "-查看计算结果", String.format("%s,%s", Output.initout[3], Output.initout[4]), "-退出程序"));
		if (input.equals("help")) {
			StrHandle.getstring(StrHandle.getarrays(stringlist, 4, 2), 5, 1);
		} else {
			stringlist.addAll(Arrays.asList("help", "-查看有效指令"));
			StrHandle.getstring(StrHandle.getarrays(stringlist, 5, 2), 5, 1);
		}
	}

	public void operatetips(String[] input) {
		String[] str = repeatstring(input.length);
		ArrayList<String> stringlist = new ArrayList<>();
		stringlist.addAll(Arrays.asList(String.format("%s%s%s", str[0], str[1], Output.operate[0]), "-查看参数值",
				String.format("%s%s%s", str[0], str[1], Output.operate[1]), "-设置参数值",
				String.format("%s%s%s,%s", str[0], str[1], Output.operate[2], Output.operate[3]), "-退出程序"));
		switch (Output.instr) {
		case "water":
			StrHandle.getstring(StrHandle.getarrays(stringlist, 3, 2), 5, 1);
			break;
		case "process":
			StrHandle.getstring(StrHandle.getarrays(stringlist, 3, 2), 5, 1);
			break;
		case "result":
			stringlist.remove(2);
			stringlist.remove(2);
			StrHandle.getstring(StrHandle.getarrays(stringlist, 2, 2), 5, 1);
			break;

		}
	}

	public void showtips(String[] input) {
		String[] str = repeatstring(input.length);
		switch (Output.instr) {
		case "water":
			String[][] string = {
					{ String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.waterpara[0]), "-查看进水TDS" },
					{ String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.waterpara[1]), "-查看温度" },
					{ String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.waterpara[2]), "-查看污染因子" },
					{ String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.waterpara[3]), "-查看pH" } };
			StrHandle.getstring(string, 5, 1);
			break;
		case "process":
			ArrayList<String> string1 = new ArrayList<>();
			string1.addAll(
					Arrays.asList(String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.procpara[0]),
							"-查看系统段数及段内压力容器数和膜元件数",
							String.format("%s%s%s %s <num>", str[0], str[1], Output.operate[0], Output.procpara[0]),
							"-查看某段压力容器数和膜元件数", String.format("%s%s%s %s", str[0], str[1], Output.operate[0],
									Output.procpara[2]),
							"-查看膜元件类型",
							String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.procpara[3]),
							"-查看系统产水量",
							String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.procpara[4]),
							"-查看系统回收率"));
			if (Output.systemmodel.groups.size() == 1) {
				StrHandle.getstring(StrHandle.getarrays(string1, 5, 2), 5, 1);
			} else if (Output.systemmodel.groups.size() == 2) {
				string1.add(2, String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.procpara[1]));
				string1.add(3, "-查看段间增压");
				StrHandle.getstring(StrHandle.getarrays(string1, 6, 2), 5, 1);
			}
			break;
		case "result":
			String[][] string2 = {
					{ String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.syspara[0]), "-查看系统计算结果" },
					{ String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.syspara[1]), "-查看段内详细计算结果" },
					{ String.format("%s%s%s %s", str[0], str[1], Output.operate[0], Output.syspara[2]), "-查看预警" } };
			StrHandle.getstring(string2, 5, 1);
			break;
		}
	}

	public void settips(String[] input) {
		ArrayList<String> tempstring = new ArrayList<>();
		String[] str = repeatstring(input.length);
		switch (Output.instr) {
		case "water":
			ArrayList<String> string = new ArrayList<>();
			string.addAll(Arrays.asList(
					String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.waterpara[0]),
					"-设定进水TDS值",
					String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.waterpara[1]),
					"-设定温度值",
					String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.waterpara[2]),
					"-设定污染因子值",
					String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.waterpara[3]),
					"-设定pH值"));
			if (Output.strlength == 1
					|| (StrHandle.checkMatch(Output.waterpara, input[1]).size() == 0 && Output.strlength > 1)
					|| StrHandle.checkMatch(Output.waterpara, input[1]).size() > 1) {
				StrHandle.getstring(StrHandle.getarrays(string, 4, 2), 5, 1);
			} else if (StrHandle.checkMatch(Output.waterpara, input[1]).size() == 1) {
				if (StrHandle.matchString(Output.waterpara[0], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.waterpara[0]),
							"-设定进水TDS值"));
				} else if (StrHandle.matchString(Output.waterpara[1], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.waterpara[1]),
							"-设定温度值"));
				} else if (StrHandle.matchString(Output.waterpara[2], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.waterpara[2]),
							"-设定污染因子值"));
				} else if (StrHandle.matchString(Output.waterpara[3], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.waterpara[3]),
							"-设定pH值"));
				}
				StrHandle.getstring(StrHandle.getarrays(tempstring, 1, 2), 5, 1);
			}
			break;
		case "process":
			ArrayList<String> string1 = new ArrayList<>();
			string1.addAll(Arrays.asList(
					String.format("%s%s%s %s <count>", str[0], str[1], Output.operate[1], Output.procpara[0]),
					"-设定系统段数",
					String.format("%s%s%s %s <num> <container> <components>", str[0], str[1], Output.operate[1],
							Output.procpara[0]),
					"-设定压力容器数和膜元件数",
					String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.procpara[1]),
					"-设定段间增压", String.format("%s%s%s %s <type>", str[0], str[1], Output.operate[1], Output.procpara[2]),
					"-设定膜元件类型",
					String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.procpara[3]),
					"-设定系统产水量",
					String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.procpara[4]),
					"-设定系统回收率"));
			if (Output.strlength == 1
					|| (StrHandle.checkMatch(Output.procpara, input[1]).size() == 0 && Output.strlength > 1)) {
				if (Output.systemmodel.groups.size() == 1) {
					string1.remove(4);
					string1.remove(4);
					StrHandle.getstring(StrHandle.getarrays(string1, 5, 2), 5, 1);
				} else if (Output.systemmodel.groups.size() == 2) {
					StrHandle.getstring(StrHandle.getarrays(string1, 6, 2), 5, 1);
				}
			} else if (StrHandle.checkMatch(Output.procpara, input[1]).size() == 1 && Output.strlength == 2) {
				if (StrHandle.matchString(Output.procpara[0], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <count>", str[0], str[1], Output.operate[1], Output.procpara[0]),
							"-设定系统段数", String.format("%s%s%s %s <num> <container> <components>", str[0], str[1],
									Output.operate[1], Output.procpara[0]),
							"-设定压力容器数和膜元件数"));
				} else if (StrHandle.matchString(Output.procpara[1], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.procpara[1]),
							"-设定段间增压"));
				} else if (StrHandle.matchString(Output.procpara[2], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <type>", str[0], str[1], Output.operate[1], Output.procpara[2]),
							"-设定膜元件类型"));
				} else if (StrHandle.matchString(Output.procpara[3], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.procpara[3]),
							"-设定系统产水量"));
				} else if (StrHandle.matchString(Output.procpara[4], input[1])) {
					tempstring.addAll(Arrays.asList(
							String.format("%s%s%s %s <number>", str[0], str[1], Output.operate[1], Output.procpara[4]),
							"-设定系统回收率"));
				}
				if (StrHandle.matchString(Output.procpara[0], input[1])) {
					StrHandle.getstring(StrHandle.getarrays(tempstring, 2, 2), 5, 1);
				} else {
					StrHandle.getstring(StrHandle.getarrays(tempstring, 1, 2), 5, 1);
				}
			} else if (StrHandle.matchString(Output.procpara[0], input[1]) && Output.strlength > 2) {
				tempstring.addAll(Arrays.asList(String.format("%s%s%s %s <num> <container> <components>", str[0],
						str[1], Output.operate[1], Output.procpara[0]), "-设定压力容器数和膜元件数"));
				StrHandle.getstring(StrHandle.getarrays(tempstring, 1, 2), 5, 1);
			}

			break;
		}
	}

	public void helptips(String[] input) {
		if (Output.instr.equals("water") || Output.instr.equals("process") || Output.instr.equals("result")) {
			operatetips(input);
		} else {
			inittip(input[0]);
		}
	}

	public void repeattips(String[] operater, String input, String input0, String[] inputStr) {
		System.out.println("是否要输入以下指令：");
		String tempstr;
		if (operater[0].equals("show") && operater[1].equals("set")) {
			if (Output.tipstring != null && !Output.tipstring.equals("") && inputStr.length == 1) {
				tempstr = "";
			} else {
				tempstr = String.format("%s ", Output.instr);
			}
		} else {
			if (Output.tipstring != null && !Output.tipstring.equals("") && inputStr.length == 2) {
				tempstr = String.format("%s ", input);
			} else {
				tempstr = String.format("%s %s ", Output.instr, input);
			}
		}
		for (int m = 0; m < StrHandle.checkMatch(operater, input0).size(); m++) {
			System.out.println(String.format("%s%s", tempstr, StrHandle.checkMatch(operater, input0).get(m)));
		}
	}

	private String[] repeatstring(int inputlen) {
		String[] str = new String[2];
		String temp = "";
		String out;
		if (Output.tipstring != null && !Output.tipstring.equals("") && Output.strlength == inputlen) {
			out = "";
		} else {
			out = Output.instr;
			temp = " ";
		}
		str[0] = out;
		str[1] = temp;
		return str;
	}
}
