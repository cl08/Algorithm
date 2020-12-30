package SWEA;

import java.util.Scanner;

public class S_D4_4796_의석이의우뚝선산 {
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];

			boolean flag = false;
			int result = 0;
			int count = 0;
			arr[0] = sc.nextInt();
			for (int i = 1; i < N; i++) {
				arr[i] = sc.nextInt();
				// 감소하다가 증가함
				if (arr[i - 1] < arr[i] && !flag) {
					count = 1;
					flag = true;
				}
				// 증가하다가 증가함
				else if (arr[i - 1] < arr[i] && flag) {
					count++;
				}
				// 증가하다가 감소함
				else if (arr[i - 1] > arr[i] && flag) {
					result = result + count;
					flag = false;
				}
				// 감소하다가 감소함
				else if (arr[i - 1] > arr[i] && !flag) {
					result = result + count;
				}
			}
			System.out.println("#" + tc + " " + result);
		}
		sc.close();
	}
}
