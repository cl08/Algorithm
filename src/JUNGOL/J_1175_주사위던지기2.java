package JUNGOL;

import java.util.Scanner;

public class J_1175_주사위던지기2 {
	static int[] arr;
	static int result=0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		arr = new int[n];
		dice(n, m);
		sc.close();
	}
	public static void dice(int n, int m) {
		for(int i=1; i<7; i++) {
			arr[n-1] = i;
			if(n==1) {
				for(int j=0; j<arr.length; j++) {
					result = result+arr[j];
				}
				if(result == m) {
					arrPrint();
				}
				result = 0;
			}
			else
				dice(n-1, m);
		}
	}
	public static void arrPrint() {
		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}