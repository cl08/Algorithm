package JUNGOL;

import java.util.Scanner;

public class J_2858_쇠막대기 {

	static Scanner sc = new Scanner(System.in);;
	public static void main(String[] args) {
		String str = sc.next();
		int result=0, count=0;;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c=='(') {
				count++;
			}
			else {
				count--;
				if(str.charAt(i-1)=='(') {
					result=result+count;
				}
				else {
					result=result+1;
				}
			}
		}
		System.out.println(result);
	}
}
