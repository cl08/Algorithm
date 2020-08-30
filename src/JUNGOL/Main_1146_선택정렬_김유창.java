package JUNGOL;

import java.util.Scanner;

public class Main_1146_선택정렬_김유창 {
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
		for (int i = 0; i < n - 1; i++) {
			int min_index=0, min=arr[i];
			for (int j = i; j < n; j++) {
				if (min > arr[j]) {
					min = arr[j];
					min_index = j;
				}
			}
			if(min_index!=0)
				swap(i, min_index);
			print(n);
		}
		sc.close();
	}

	public static void swap(int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static void print(int size) {
		for(int i=0; i<size; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
}
