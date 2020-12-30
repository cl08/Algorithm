package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_B2_13458_시험감독 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		long count = 0;
		int N = Integer.parseInt(bf.readLine());
		int[] A = new int[N];
		tk = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(tk.nextToken());
		}
		tk = new StringTokenizer(bf.readLine());
		int B = Integer.parseInt(tk.nextToken());
		int C = Integer.parseInt(tk.nextToken());
		for (int i = 0; i < N; i++) {
			A[i] = A[i] - B;
			count++;
			if (A[i] <= 0) {
				continue;
			} else {
				count = count + A[i] / C;
				if (A[i] % C > 0)
					count++;
			}
		}
		System.out.println(count);
	}
}