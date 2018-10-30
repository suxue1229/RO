package console;

import java.util.Scanner;

public class Main {
	static Output out;
	static Scanner input;

	public static void main(String[] args) {
		out = new Output();
		System.out.print("> ");
		input = new Scanner(System.in);
		String Str;
		while (true) {
			Str = input.nextLine();
			out.output(Str);
		}
	}
}
