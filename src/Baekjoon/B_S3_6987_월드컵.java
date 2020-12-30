package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_S3_6987_월드컵 {
	static int[][] info;
	static int[][] score;
	static boolean result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		info = new int[6][3];
		for (int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					info[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 초기화
			score = new int[6][3];
			result = false;
			
			// 시뮬
			dfs(0, 1);
			if (result)
				System.out.print(1 + " ");
			else
				System.out.print(0 + " ");
		}
	}

	public static void dfs(int team1, int team2) {
		if (team1 == 5) {
			for(int i=0; i<6; i++) {
				for(int j=0; j<3; j++) {
					if(info[i][j]==score[i][j])
						continue;
					else
						return;
				}
			}
			result = true;
			return;
		}
		if (team2 == 6) {
			dfs(team1 + 1, team1 + 2);
			return;
		}
		// team1이 이길경우
		score[team1][0]++;
		score[team2][2]++;
		if (score[team1][0] <= info[team1][0] && score[team2][2] <= info[team2][2])
			dfs(team1, team2 + 1);
		score[team1][0]--;
		score[team2][2]--;

		// 비길경우
		score[team1][1]++;
		score[team2][1]++;
		if (score[team1][1] <= info[team1][1] && score[team2][1] <= info[team2][1])
			dfs(team1, team2 + 1);
		score[team1][1]--;
		score[team2][1]--;

		// team2가 이길 경우
		score[team2][0]++;
		score[team1][2]++;
		if (score[team1][0] <= info[team1][0] && score[team2][2] <= info[team2][2])
			dfs(team1, team2 + 1);
		score[team2][0]--;
		score[team1][2]--;

	}
}
