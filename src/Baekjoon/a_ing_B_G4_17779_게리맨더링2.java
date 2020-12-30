package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class a_ing_B_G4_17779_게리맨더링2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int[][] visit = new int[N][N];
		int[] area = new int[5];
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int x = 0; x < N-2; x++) {
			for (int y = 1; y < N-1; y++) {
			
			}
		}

		System.out.println(result);

	}

	public static void print(int[][] visit, int N) {
		System.out.println();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(visit[i][j] + " ");
			}
			System.out.println();
		}

	}
}
