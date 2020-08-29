package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취_김유창 {
	static int N, M, C, max;
	static int[][] map, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// map배열을 참조하여 dp배열에 해당 영역(가로로 M만큼)이익 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					int temp = 0;
					for (int k = j; k < j + M; k++) {
						dp[i][j] = dp[i][j] + map[i][k];
						temp = temp + (int) Math.pow(map[i][k],2); 
					}
					// 단, 값이 C보다 크면 해당 영역의 벌통 조합 중 꿀의 양이 C이하면서 이익이 최대값인 조합을 찾아서 저장
					if (dp[i][j] <= C) {
						dp[i][j] = temp;
					} else {
						max = 0;
						combination(i, j, 0, 0, 0);
						dp[i][j] = max;
					}
				}
			}

			// dp맵을 순회하며 두 일꾼의 벌꿀의 합이 최대인 값 출력
			max = 0;
			int scv1, scv2;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					// 첫번째 일꾼 선택
					scv1 = dp[i][j];
					for (int k = j + M; k <= N - M; k++) {
						// 같은 행의 두번째 일꾼 선택
						scv2 = dp[i][k];
						max = max < scv1 + scv2 ? scv1 + scv2 : max;
					}
					// 다음 행부터 두번째 일꾼 선택
					for (int k = i + 1; k < N; k++) {
						for (int l = 0; l <= N - M; l++) {
							scv2 = dp[k][l];
							max = max < scv1 + scv2 ? scv1 + scv2 : max;
						}
					}
				}
			}

			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	public static void combination(int x, int y, int count, int sum, int pow) {
		if (count == M) {
			if (sum <= C && max < pow) {
				max = pow;
			}
			return;
		}
		// 선택
		combination(x, y + 1, count + 1, sum + map[x][y], pow + (int) Math.pow(map[x][y], 2));
		// 비선택
		combination(x, y + 1, count + 1, sum, pow);
	}
}
