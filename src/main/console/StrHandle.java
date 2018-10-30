package console;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class StrHandle {
	public static boolean matchString(String str, String input) {
		if (str.length() >= input.length()) {
			String substr = str.substring(0, input.length());
			if (substr.equalsIgnoreCase(input)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static ArrayList<String> checkMatch(String[] str, String input) {
		ArrayList<String> stringlist = new ArrayList<>();
		for (String s : str) {
			if (s.length() >= input.length()) {
				String substr = s.substring(0, input.length());
				if (substr.equalsIgnoreCase(input)) {
					stringlist.add(s);
				}
			}
		}
		return stringlist;
	}

	public static ArrayList<String> spiltString(String str) {
		ArrayList<String> temp = new ArrayList<>();
		String[] tempstr = str.split("[ \t]");
		for (String t : tempstr) {
			if (!t.isEmpty()) {
				temp.add(t);
			}
		}
		return temp;
	}

	public static void getstring(String[][] str, int minwidth, int spacenum) {
		for (int h = 0; h < str[0].length; h++) {
			String[] colsarray = new String[str.length];
			for (int i = 0; i < str.length; i++) {
				colsarray[i] = str[i][h];
			}
			try {
				int n = colsarray[0].getBytes("GBK").length;
				for (String str1 : colsarray) {
					n = Math.max(n, str1.getBytes("GBK").length + spacenum);
				}
				if (n <= minwidth) {
					n = minwidth;
				}
				for (int f = 0; f < colsarray.length; f++) {
					int temp = colsarray[f].length() + (n - colsarray[f].getBytes("GBK").length);
					str[f][h] = String.format("%-" + temp + "s", colsarray[f]);
				}
			} catch (UnsupportedEncodingException e) {
			}
		}
		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < str[i].length; j++) {
				System.out.print(str[i][j]);
			}
			System.out.println();
		}
	}

	public static String[][] getarrays(ArrayList<String> str, int rols, int cols) {
		String[][] arr = new String[rols][cols];
		int ct = 0;
		for (int i = 0; i < rols; i++) {
			for (int j = 0; j < cols; j++) {
				arr[i][j] = str.get(ct++);
			}
		}
		return arr;
	}
}
