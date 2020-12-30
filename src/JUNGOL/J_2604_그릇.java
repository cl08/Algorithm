package JUNGOL;

import java.util.Scanner;

public class J_2604_그릇 {

	static Scanner sc = new Scanner(System.in);;
	public static void main(String[] args) {
		String str = sc.next();
		int count=10;
		for (int i = 1; i < str.length(); i++) {
			if(str.charAt(i)==str.charAt(i-1))
				count=count+5;
			else
				count=count+10;
		}
		System.out.println(count);
	}
}
