package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_B17135_캐슬디펜스_김유창_np {
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
		int[] numbers = new int[M];
		for (int i = 0; i < M; i++) {
			numbers[i] = i;
		}
		int[] pick = new int[M];
		pick[M - 1] = 1;
		pick[M - 2] = 1;
		pick[M - 3] = 1;

		// 궁수위치설정
		do {
			int index=0;
			for (int i = 0; i < M; i++) {
				if (pick[i] == 1) {
					archer[index][1] =numbers[i];
					index++;
				}
			}
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
				L: for (int k = 0; k < 3; k++) {
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
		} while (np(pick));
		System.out.println(maxCount);
	}

	private static boolean np(int[] p) {
		int N = p.length;
		// 1. 교환이 필요한 i-1 위치 찾기(i:꼭지)
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			i--;
		if (i == 0)
			return false;
		// 2. i-1 위치값과 교환이 필요한 j위치 찾기
		int j = N - 1;
		while (p[i - 1] >= p[j])
			j--;

		// 3. i-1위치 값과 j위치값 교환 : i-1 을 한단계 큰 수로 만듦
		int temp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = temp;

		// 4. i위치부터 맨 뒤까지 내림차순 형태로 오름차순 형태로 재조정
		j = N - 1;
		while (i < j) {
			temp = p[i];
			p[i] = p[j];
			p[j] = temp;
			i++;
			j--;
		}
		return true;
	}
}