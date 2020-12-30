package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_G4_B17135_캐슬디펜스 {
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][M];
		int[][] arrCopy = new int[N + 1][M]; // 2차원 배열 복사
		int[][] archer = new int[3][2];
		int archerLine = N, count = 0, maxCount = 0;
		int D = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				arrCopy[i][j] = arr[i][j];
			}
		}

		// 궁수위치설정
		int archerN = 3;
		L2: for (int a = (int) Math.pow(2, archerN) - 1; a < (1 << M); a++) {
			archerN = 0;
			for (int b = 0; b < M; b++) {
				if ((a >> b) % 2 == 1) {
					if (archerN == 3)
						continue L2;
					archer[archerN][1] = b;
					archerN++;
				}
			}
			if (archerN < 3)
				continue L2;

			// 맵리셋
			for (int i = 0; i < N + 1; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j] = arrCopy[i][j];
				}
			}
			archerLine = N;
			count = 0;
			archer[0][0] = archerLine;
			archer[1][0] = archerLine;
			archer[2][0] = archerLine;

			arr[archerLine][archer[0][1]] = 2;
			arr[archerLine][archer[1][1]] = 2;
			arr[archerLine][archer[2][1]] = 2;

			Queue<int[]> result = new LinkedList<int[]>();

			while (archerLine != 0) {
				// 궁수가 적을 죽이는 한사이클
				L:for (int k = 0; k < 3; k++) {
					Queue<int[]> q = new LinkedList<int[]>();
					boolean[][] visit = new boolean[N][M];
					int x = archer[k][0];
					int y = archer[k][1];
					int d = 0;
					q.offer(new int[] { x, y });
					while (D > d) {
						int size = q.size();
						d++;
						while (size-- > 0) {
							int[] temp = q.poll();
							for (int i = 0; i < 3; i++) {
								int curX = temp[0] + dx[i];
								int curY = temp[1] + dy[i];
								if (curX >= 0 && curX < N && curY >= 0 && curY < M && !visit[curX][curY]) {
									visit[curX][curY] = true;
									if (arr[curX][curY] == 1) {
										result.offer(new int[] { curX, curY, d });
										continue L;
									} else
										q.offer(new int[] { curX, curY });
								}
							}
						}
					}
				}
				while (!result.isEmpty()) {
					int xy[] = result.poll();
					if (arr[xy[0]][xy[1]] != 0) {
						arr[xy[0]][xy[1]] = 0;
						count++;
					}
				}

				// 궁수열을 올리고 궁수열의 적들 소거
				archerLine--;
				archer[0][0] = archerLine;
				archer[1][0] = archerLine;
				archer[2][0] = archerLine;
				arr[archerLine][archer[0][1]] = 2;
				arr[archerLine][archer[1][1]] = 2;
				arr[archerLine][archer[2][1]] = 2;

				for (int i = 0; i < M; i++) {
					if (arr[archerLine][i] == 1)
						arr[archerLine][i] = 0;
				}
			}
			if (maxCount < count)
				maxCount = count;
		}
		System.out.println(maxCount);
	}
}