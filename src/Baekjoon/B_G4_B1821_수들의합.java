package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G4_B1821_수들의합 {
	static int numbers[], pick[], copy[];
	static int N, F;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		// 1~N 순열
		numbers = new int[N];
		pick = new int[N];
		copy = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = i + 1;
		}
		permutation(0, 0);
	}

	private static void permutation(int index, int selected) {
		if (flag)
			return;
		if (index == N) {
			// 삼각형 계산해서 F값이 나오면 출력
			for (int i = 0; i < N; i++) {
				copy[i] = pick[i];
			}
			cal();
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((selected & 1 << i) == 0) {
				pick[index] = numbers[i];
				permutation(index + 1, selected | 1 << i);
			}
		}
	}

	public static void cal() {
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N - i; j++) {
				copy[j] = copy[j] + copy[j + 1];
			}
		}
		if (copy[0] == F) {
			for (int i = 0; i < N; i++) {
				System.out.print(pick[i] + " ");
			}
			flag = true;
			return;
		}
	}
}
