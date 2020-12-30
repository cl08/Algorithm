package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_4014_활주로건설 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			boolean[][] visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int count = 0;
			// 가로검사
			L1: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					// 평지
					if (map[i][j] == map[i][j + 1])
						continue;

					// 오르막길일 경우
					else if (map[i][j] + 1 == map[i][j + 1]) {
						// 내 칸포함 X칸 전까지 모든 칸이 나와 높이가 같고 발판이 없어야함
						for (int k = 0; k < X; k++) {
							if (j - k >= 0 && !visit[i][j - k] && map[i][j - k] == map[i][j]) {
								visit[i][j - k] = true;
							} else {
								continue L1;
							}
						}
					}

					// 내리막길일 경우
					else if (map[i][j] - 1 == map[i][j + 1]) {
						// 내 앞으로 X칸까지 모든칸이 나보다 높이가 1 높아야함
						for (int k = 1; k <= X; k++) {
							if (j + k < N && !visit[i][j + k] && map[i][j + k] + 1 == map[i][j]) {
								visit[i][j + k] = true;
							} else {
								continue L1;
							}
						}
					} else
						continue L1;
				}
				count++;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					visit[i][j] = false;
				}
			}
			// 세로 검사
			L2: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					// 평지
					if (map[j][i] == map[j + 1][i])
						continue;

					// 오르막길일 경우
					else if (map[j][i] + 1 == map[j + 1][i]) {
						// 내 칸포함 X칸 전까지 모든 칸이 나와 높이가 같고 발판이 없어야함
						for (int k = 0; k < X; k++) {
							if (j - k >= 0 && !visit[j - k][i] && map[j - k][i] == map[j][i]) {
								visit[j - k][i] = true;
							} else {
								continue L2;
							}
						}
					}
					
					// 내리막길일 경우
					else if (map[j][i] - 1 == map[j + 1][i]) {
						// 내 앞으로 X칸까지 모든칸이 나보다 높이가 1 높아야함
						for (int k = 1; k <= X; k++) {
							if (j + k < N && !visit[j + k][i] && map[j + k][i] + 1 == map[j][i]) {
								visit[j + k][i] = true;
							} else {
								continue L2;
							}
						}
					} else
						continue L2;
				}
				count++;
			}
			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
}
