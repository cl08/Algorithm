package Baekjoon;

import java.util.Scanner;

public class B_S3_11727_2n타일링2 {
	static final int size = 23;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		int[] f = new int[1001];
		f[1] = 1;
		f[2] = 3;
		for (int i = 3; i <= N; i++) {
			f[i] = (f[i - 1] + f[i - 2] + f[i - 2])  % 10007;
		}
		System.out.println(f[N]);
	}
}
