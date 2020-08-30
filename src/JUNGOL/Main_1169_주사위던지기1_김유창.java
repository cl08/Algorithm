package JUNGOL;

import java.util.Scanner;

public class Main_1169_주사위던지기1_김유창 {
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		arr = new int[n];
		switch (m) {
		case 1:
			print1(n);
			break;
		case 2:
			print2(n);
			break;
		case 3:
			print3(n);
			break;
		default:
			;
		}
		sc.close();
	}

	public static void print1(int n) {
		for (int i = 1; i < 7; i++) {
			arr[n - 1] = i;
			if (n == 1)
				arrPrint();
			else 
				print1(n - 1);
		}
	}

	public static void print2(int n) {
		for (int i = 1; i < 7; i++) {
			arr[n - 1] = i;
			if (n == 1 && arr[n - 1] >= arr[n])
					arrPrint();
			else if(n == arr.length || arr[n - 1] >= arr[n])
					print2(n - 1);
		}
	}

	public static void print3(int n) {
		for (int i = 1; i < 7; i++) {
			arr[n - 1] = i;
			int flag = 0;
			for (int j = arr.length - 1; j > n - 1; j--) {
				if (arr[n - 1] == arr[j]) {
					flag = 1;
					break;
				}
			}
			if(flag==0) {
				if(n==1)
					arrPrint();
				else
					print3(n-1);
			}
		}
	}
	public static void arrPrint() {
		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}