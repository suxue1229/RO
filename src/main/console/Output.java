package console;

import engine.MSystem;

import java.util.ArrayList;

/*
 * 将键盘输入字符数组String[] inputStr；其输入的形式为：tipstring>inputStr[0] inputStr[1] inputStr[2]..
 * 输出的形式为：tipstring>instr inputStr[1] inputStr[2]..
 */
public class Output {

	static String tipstring = "";
	static String instr;
	static int strlength;
	static MSystem systemmodel;
	Tips operatip = new Tips();
	ParaHandle paracont;

	static String[] initout = { "water", "process", "result", "exit", "quit" };
	static String[] waterpara = { "tds", "temperature", "pollutionfactor", "ph" };
	static String[] procpara = { "section", "boost", "membrane", "prodution", "recyclerate" };
	static String[] syspara = { "summary", "detail", "warning" };
	static String[] operate = { "show", "set", "exit", "quit", "help" };

	public Output() {
		systemmodel = new MSystem();
		paracont = new ParaHandle();
	}

	public void output(String Str) {
		ArrayList<String> inputstring = StrHandle.spiltString(Str);
		String[] inputStr = new String[inputstring.size()];// 输入的字符数组
		inputstring.toArray(inputStr);
		ArrayList<String> inString1, inString2;// 第一位、第二位输入的字符

		if (inputStr.length > 0) {
			inString1 = StrHandle.checkMatch(initout, inputStr[0]);
			if (inString1.size() == 1) {
				inputStr[0] = inString1.get(0);
				instr = inputStr[0];
				if (inputStr.length == 1) {
					tipstring = inputStr[0];
				} else {
					inputstring.remove(0);
					inputstring.toArray(inputStr);
					strlength = inputStr.length - 1;
				}
			} else if (inString1.size() == 0) {
				instr = tipstring;
				strlength = inputStr.length;
			}

			inString2 = StrHandle.checkMatch(operate, inputStr[0]);
			if (inString2.size() == 1) {
				inputStr[0] = inString2.get(0);
				operate(inputStr, instr);
			} else if (inString2.size() > 1) {
				if (instr != null && !instr.equals("")) {
					operatip.repeattips(operate, "", inputStr[0], inputStr);
				} else {
					System.out.println("下列指令有效：");
					operatip.helptips(inputStr);
				}
			} else if (inString2.size() == 0) {
				if (!inputStr[0].contains("water") && !inputStr[0].contains("process")
						&& !inputStr[0].contains("result")) {
					System.out.println("下列指令有效：");
					operatip.helptips(inputStr);
				}
			}
		}
		System.out.println();
		System.out.print(tipstring + "> ");
	}

	private void operate(String[] input, String instr) {
		String[] parameter = null;
		switch (input[0]) {
		case "show":
			switch (instr) {
			case "water":
				parameter = waterpara;
				break;
			case "process":
				parameter = procpara;
				break;
			case "result":
				parameter = syspara;
				try {
					systemmodel.sysCalc();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			if (strlength == 1) {
				paracont.show(instr, "");
			} else if (strlength == 2) {
				if (StrHandle.checkMatch(parameter, input[1]).size() == 1) {
					input[1] = StrHandle.checkMatch(parameter, input[1]).get(0);
					paracont.show(instr, input[1]);
				} else if (StrHandle.checkMatch(parameter, input[1]).size() > 1) {
					operatip.repeattips(parameter, input[0], input[1], input);
				} else if (StrHandle.checkMatch(parameter, input[1]).size() == 0) {
					System.out.println("下列指令有效：");
					operatip.showtips(input);
				}
			} else if (strlength > 2) {
				if (instr.equals("process")) {
					if (strlength == 3 && StrHandle.matchString("section", input[1])) {
						paracont.sectionShow(input[2]);
					}
				} else {
					System.out.println("下列指令有效：");
					operatip.showtips(input);
				}
			}
			break;
		case "set":
			switch (instr) {
			case "water":
				parameter = waterpara;
				break;
			case "process":
				parameter = procpara;
				break;
			case "result":
				System.out.println("下列指令有效：");
				operatip.operatetips(input);
				break;
			}
			if (instr.equals("water") || instr.equals("process")) {
				if (strlength > 1) {
					if (StrHandle.checkMatch(parameter, input[1]).size() == 1) {
						input[1] = StrHandle.checkMatch(parameter, input[1]).get(0);
						if (instr.equals("water")) {
							if (strlength == 3) {
								paracont.set(instr, input[1], input[2]);
							} else {
								System.out.println("下列指令有效：");
								operatip.settips(input);
							}
						} else if (instr.equals("process")) {
							if (strlength == 3) {
								paracont.set(instr, input[1], input[2]);
							} else if (strlength == 5 && input[1].equals("section")) {
								paracont.sectionSet(input[2], input[3], input[4]);
							} else if (strlength == 2 && input[1].equals("section")) {
								System.out.println("下列指令有效：");
								operatip.settips(input);
							} else {
								System.out.println("下列指令有效：");
								operatip.settips(input);
							}
						}
					} else if (StrHandle.checkMatch(parameter, input[1]).size() > 1) {
						operatip.repeattips(parameter, input[0], input[1], input);
					} else if (StrHandle.checkMatch(parameter, input[1]).size() == 0) {
						System.out.println("下列指令有效：");
						operatip.settips(input);
					}
				} else {
					System.out.println("下列指令有效：");
					operatip.settips(input);
				}
			}
			break;
		case "exit":
			System.exit(0);
			break;
		case "quit":
			System.exit(0);
			break;
		case "help":
			operatip.helptips(input);
			break;
		default:
			System.out.println("下列指令有效：");
			operatip.helptips(input);
			break;
		}
	}

}
