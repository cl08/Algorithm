package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G3_B12100_2048_김유창 {
	static int N, max = 0;
	static int[][] map, copy;
	static int[] pick;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = map[i][j];
			}
		}
		pick = new int[5];
		permutation(0, 0);
		System.out.println(max);
	}

	public static void permutation(int index, int count) {
		if (count == 5) {
			// 맵 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					copy[i][j] = map[i][j];
				}
			}
			cal();
			for (int i = 0; i < 5; i++) {
				move(pick[i]);
				cal();
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			pick[index] = i;
			permutation(index + 1, count + 1);
		}
	}

	public static void move(int dir) {
		// 왼쪽으로 밀기
		if (dir == 0) {
			// 같은 블록 합치기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					for (int k = j + 1; k < N; k++) {
						if (copy[i][k] == 0)
							continue;
						else if (copy[i][j] == copy[i][k]) {
							copy[i][j] = copy[i][j] * 2;
							copy[i][k] = 0;
						}
						break;
					}
				}
			}
			// 블록 모으기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (copy[i][j] == 0) {
						for (int k = j + 1; k < N; k++) {
							if (copy[i][k] != 0) {
								copy[i][j] = copy[i][k];
								copy[i][k] = 0;
								break;
							}
						}
					}
				}
			}
		}
		// 오른쪽으로 모으기
		else if (dir == 1) {
			// 같은 블록 합치기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					for (int k = j + 1; k < N; k++) {
						if (copy[i][N - 1 - k] == 0)
							continue;
						else if (copy[i][N - 1 - j] == copy[i][N - 1 - k]) {
							copy[i][N - 1 - j] = copy[i][N - 1 - j] * 2;
							copy[i][N - 1 - k] = 0;
						}
						break;
					}
				}
			}
			// 블록 모으기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (copy[i][N - 1 - j] == 0) {
						for (int k = j + 1; k < N; k++) {
							if (copy[i][N - 1 - k] != 0) {
								copy[i][N - 1 - j] = copy[i][N - 1 - k];
								copy[i][N - 1 - k] = 0;
								break;
							}
						}
					}
				}
			}
		}
		// 위로 모으기
		else if (dir == 2) {
			// 같은 블록 합치기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					for (int k = j + 1; k < N; k++) {
						if (copy[k][i] == 0)
							continue;
						else if (copy[j][i] == copy[k][i]) {
							copy[j][i] = copy[j][i] * 2;
							copy[k][i] = 0;
						}
						break;
					}
				}
			}
			// 블록 모으기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (copy[j][i] == 0) {
						for (int k = j + 1; k < N; k++) {
							if (copy[k][i] != 0) {
								copy[j][i] = copy[k][i];
								copy[k][i] = 0;
								break;
							}
						}
					}
				}
			}
		}
		// 아래로 모으기
		else {
			// 같은 블록 합치기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					for (int k = j + 1; k < N; k++) {
						if (copy[N - 1 - k][i] == 0)
							continue;
						else if (copy[N - 1 - j][i] == copy[N - 1 - k][i]) {
							copy[N - 1 - j][i] = copy[N - 1 - j][i] * 2;
							copy[N - 1 - k][i] = 0;
						}
						break;
					}
				}
			}
			// 블록 모으기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (copy[N - 1 - j][i] == 0) {
						for (int k = j + 1; k < N; k++) {
							if (copy[N - 1 - k][i] != 0) {
								copy[N - 1 - j][i] = copy[N - 1 - k][i];
								copy[N - 1 - k][i] = 0;
								break;
							}
						}
					}
				}
			}
		}
		cal();
	}

	public static void cal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (max < copy[i][j]) {
					max = copy[i][j];
				}
			}
		}
	}
}
