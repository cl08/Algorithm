package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_4012_요리사 {
	static int N, min;
	static int[][] arr;
	static boolean[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			select = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min = Integer.MAX_VALUE;
			combination(0, 0);
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	private static void combination(int index, int Count) {
		if (Count == N / 2) {
			cal();
			return;
		}
		if (index == N)
			return;
		// 선택
		select[index] = true;
		combination(index + 1, Count + 1);
		// 비선택
		select[index] = false;
		combination(index + 1, Count);
	}

	private static void cal() {
		int sum1 = 0, sum2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (select[i] && select[j])
					sum1 = sum1 + arr[i][j];
				if (!select[i] && !select[j])
					sum2 = sum2 + arr[i][j];
			}
		}
		int result = Math.abs(sum1 - sum2);
		if (result < min)
			min = result;

	}
}
