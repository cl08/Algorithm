package JUNGOL;

import java.util.Scanner;

public class J_1157_버블정렬 {
	static int[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n, temp;
		n = sc.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			temp = sc.nextInt();
			arr[i] = temp;
		}
		for (int i = 0; i < n; i++) {
			for(int j=0; j<n-i-1; j++) {
				if(arr[j] > arr[j+1]) {
					temp = arr[j+1];
					arr[j+1]=arr[j];
					arr[j]=temp;
				}
			}
			print(n);
		}
		sc.close();
	}
	public static void print(int size) {
		for(int i=0; i<size; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
}
