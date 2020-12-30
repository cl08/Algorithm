package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_S3_B2630_색종이만들기 {
	static int[][] arr;
	static int white = 0, blue = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		quarter(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void quarter(int x, int y, int size) {
		// 기저 조건1 : size 1x1
		if (size == 1) {
			if (arr[x][y] == 1)
				blue++;
			else
				white++;
			return;
		}

		// 기저 조건2 : 모두 같은색
		int color = arr[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				// 같은색이 아니면 4분할 재귀
				if (arr[i][j] != color) {
					quarter(x, y, size / 2);
					quarter(x, y + size / 2, size / 2);
					quarter(x + size / 2, y, size / 2);
					quarter(x + size / 2, y + size / 2, size / 2);
					return;
				}
			}
		}
		if (color == 1)
			blue++;
		else
			white++;
	}
}
