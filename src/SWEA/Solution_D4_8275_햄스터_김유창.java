package SWEA;

import java.io.IOException;
import java.util.Scanner;

public class Solution_D4_8275_햄스터_김유창 {
	static int N, X, M, max;
	static int[] cage, result;
	static int[][] info;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			X = sc.nextInt();
			M = sc.nextInt();
			cage = new int[N + 1];
			result = new int[N + 1];
			info = new int[M][3];
			for (int i = 0; i < M; i++) {
				info[i][0] = sc.nextInt();
				info[i][1] = sc.nextInt();
				info[i][2] = sc.nextInt();
			}
			// 순열 생성
			max = -1;
			permutation(1);

			if (max == -1)
				sb.append('#').append(tc).append(' ').append(max).append('\n');
			else {
				sb.append('#').append(tc);
				for (int i = 1; i <= N; i++) {
					sb.append(' ').append(result[i]);
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
		sc.close();
	}

	public static void permutation(int index) {
		if (index > N) {
			// 순열이 각 조건을 만족하는지 확인
			int sum;
			for (int i = 0; i < M; i++) {
				sum = 0;
				for (int j = info[i][0]; j <= info[i][1]; j++) {
					sum = sum + cage[j];
				}
				if (sum != info[i][2])
					return;
			}
			// 만족하면 최대값으로 저장
			sum = 0;
			for (int i = 1; i <= N; i++) {
				sum = sum + cage[i];
			}
			if (max < sum) {
				max = sum;
				for (int i = 0; i <= N; i++) {
					result[i] = cage[i];
				}
			}
			return;
		}
		for (int i = 0; i <= X; i++) {
			cage[index] = i;
			permutation(index + 1);
		}
	}
}
