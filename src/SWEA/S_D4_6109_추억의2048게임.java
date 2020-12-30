package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_D4_6109_추억의2048게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int N = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			switch (dir) {
			case "left":
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N - 1; j++) {
						for (int k = j + 1; k < N; k++) {
							if (map[i][k] == 0)
								continue;
							else if (map[i][j] == map[i][k]) {
								map[i][j] = map[i][j] * 2;
								map[i][k] = 0;
							}
							break;
						}
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N - 1; j++) {
						if (map[i][j] == 0) {
							for (int k = j + 1; k < N; k++) {
								if (map[i][k] != 0) {
									map[i][j] = map[i][k];
									map[i][k] = 0;
									break;
								}
							}
						}
					}
				}
				break;
			case "down":
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N - 1; j++) {
						for (int k = j + 1; k < N; k++) {
							if (map[N - 1 - k][i] == 0)
								continue;
							else if (map[N - 1 - j][i] == map[N - 1 - k][i]) {
								map[N - 1 - j][i] = map[N - 1 - j][i] * 2;
								map[N - 1 - k][i] = 0;
							}
							break;
						}
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N - 1; j++) {
						if (map[N - 1 - j][i] == 0) {
							for (int k = j + 1; k < N; k++) {
								if (map[N - 1 - k][i] != 0) {
									map[N - 1 - j][i] = map[N - 1 - k][i];
									map[N - 1 - k][i] = 0;
									break;
								}
							}
						}
					}
				}
				break;
			case "right":
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N - 1; j++) {
						for (int k = j + 1; k < N; k++) {
							if (map[i][N - 1 - k] == 0)
								continue;
							else if (map[i][N - 1 - j] == map[i][N - 1 - k]) {
								map[i][N - 1 - j] = map[i][N - 1 - j] * 2;
								map[i][N - 1 - k] = 0;
							}
							break;
						}
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N - 1; j++) {
						if (map[i][N - 1 - j] == 0) {
							for (int k = j + 1; k < N; k++) {
								if (map[i][N - 1 - k] != 0) {
									map[i][N - 1 - j] = map[i][N - 1 - k];
									map[i][N - 1 - k] = 0;
									break;
								}
							}
						}
					}
				}
				break;
			case "up":
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N - 1; j++) {
						for (int k = j + 1; k < N; k++) {
							if (map[k][i] == 0)
								continue;
							else if (map[j][i] == map[k][i]) {
								map[j][i] = map[j][i] * 2;
								map[k][i] = 0;
							}
							break;
						}
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N - 1; j++) {
						if (map[j][i] == 0) {
							for (int k = j + 1; k < N; k++) {
								if (map[k][i] != 0) {
									map[j][i] = map[k][i];
									map[k][i] = 0;
									break;
								}
							}
						}
					}
				}
				break;
			}
			sb.append('#').append(tc).append('\n');
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]);
					sb.append(' ');
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
}
