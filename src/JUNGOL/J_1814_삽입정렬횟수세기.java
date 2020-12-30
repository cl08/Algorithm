package JUNGOL;

import java.util.Scanner;

public class J_1814_삽입정렬횟수세기 {
	static int[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n, temp, count=0;;
		n = sc.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			temp = sc.nextInt();
			arr[i] = temp;
		}
		for (int i = 1; i < n; i++) {
			int insert=i;
			temp = arr[i];
			for(int j=i; j>0; j--) {
				if(temp < arr[j-1]) {
					arr[j] = arr[j-1];
					insert = j-1;
					count++;
				}
			}
			arr[insert] = temp;
			print(n);
		}
		sc.close();
		System.out.println(count);
//		print(n);
	}
	public static void print(int size) {
		for(int i=0; i<size; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
}
