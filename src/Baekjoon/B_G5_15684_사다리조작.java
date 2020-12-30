package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G5_15684_사다리조작 {
	static int N, M, H;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H+1][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int temp1 = Integer.parseInt(st.nextToken()) - 1;
			int temp2 = Integer.parseInt(st.nextToken()) - 1;
			map[temp1][temp2] = 1;
			map[temp1][temp2 + 1] = 2;
		}
		// 사다리 안 놓기
		if (simul()) {
			System.out.println(0);
			return;
		}

		// 사다리 한개 놓기
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					map[i][j] = 1;
					map[i][j + 1] = 2;
					if (simul()) {
						System.out.println(1);
						return;
					}
					map[i][j] = 0;
					map[i][j + 1] = 0;
				}
			}
		}

		// 사다리 두개 놓기
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					map[i][j] = 1;
					map[i][j + 1] = 2;
					for (int i2 = 0; i2 < H; i2++) {
						for (int j2 = 0; j2 < N - 1; j2++) {
							if (map[i2][j2] == 0 && map[i2][j2 + 1] == 0) {
								map[i2][j2] = 1;
								map[i2][j2 + 1] = 2;
								if (simul()) {
									System.out.println(2);
									return;
								}
								map[i2][j2] = 0;
								map[i2][j2 + 1] = 0;
							}
						}
					}
					map[i][j] = 0;
					map[i][j + 1] = 0;
				}
			}
		}

		// 사다리 세개 놓기
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					map[i][j] = 1;
					map[i][j + 1] = 2;
					for (int i2 = 0; i2 < H; i2++) {
						for (int j2 = 0; j2 < N - 1; j2++) {
							if (map[i2][j2] == 0 && map[i2][j2 + 1] == 0) {
								map[i2][j2] = 1;
								map[i2][j2 + 1] = 2;
								for (int i3 = 0; i3 < H; i3++) {
									for (int j3 = 0; j3 < N - 1; j3++) {
										if (map[i3][j3] == 0 && map[i3][j3 + 1] == 0) {
											map[i3][j3] = 1;
											map[i3][j3 + 1] = 2;
											if (simul()) {
												System.out.println(3);
												return;
											}
											map[i3][j3] = 0;
											map[i3][j3 + 1] = 0;
										}
									}
								}
								map[i2][j2] = 0;
								map[i2][j2 + 1] = 0;
							}
						}
					}
					map[i][j] = 0;
					map[i][j + 1] = 0;
				}
			}
		}

		System.out.println(-1);
	}

	public static boolean simul() {
		for (int i = 0; i < N; i++) {
			int x = 0;
			int y = i;
			boolean flag = false;
			while (x < H) {
				if (map[x][y] == 0 || flag) {
					x++;
					flag = false;
				} else if (map[x][y] == 1) {
					y++;
					flag = true;
				} else if (map[x][y] == 2) {
					y--;
					flag = true;
				} else
					System.out.println("error");
			}
			if (y != i)
				return false;
		}
		return true;
	}

	public static void print() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
