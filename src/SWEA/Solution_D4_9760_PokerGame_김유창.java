package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_9760_PokerGame_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			char[][] card = new char[5][2];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < 5; i++) {
				card[i] = st.nextToken().toCharArray();
			}
			int[] suit = new int[4];
			int[] rank = new int[14];
			for (int i = 0; i < 5; i++) {
				switch (card[i][0]) {
				case 'S':
					suit[0]++;
					break;
				case 'D':
					suit[1]++;
					break;
				case 'H':
					suit[2]++;
					break;
				case 'C':
					suit[3]++;
					break;
				}
				switch (card[i][1]) {
				case 'A':
					rank[1]++;
					break;
				case 'T':
					rank[10]++;
					break;
				case 'J':
					rank[11]++;
					break;
				case 'Q':
					rank[12]++;
					break;
				case 'K':
					rank[13]++;
					break;
				default:
					rank[card[i][1] - '0']++;
				}
			}
			String result = null;
			int[] flag = new int[10];
			for (int i = 1; i < 14; i++) {
				if (rank[i] == 2)
					flag[8]++;
				if (rank[i] == 3)
					flag[6]++;
				if (rank[i] == 4) {
					flag[2]++;
				}
			}
			// 스트레이트 체크
			for (int i = 1; i < 10; i++) {
				if (rank[i] == 1 && rank[i + 1] == 1 && rank[i + 2] == 1 && rank[i + 3] == 1 && rank[i + 4] == 1)
					flag[5]++;
			}
			if (rank[1] == 1 && rank[10] == 1 && rank[11] == 1 && rank[12] == 1 && rank[13] == 1)
				flag[5]++;

			// 플러시 체크
			for (int i = 0; i < 4; i++) {
				if (suit[i] == 5)
					flag[4]++;
			}

			if (flag[4] > 0 && flag[5] > 0)
				result = "Straight Flush";
			else if (flag[2] > 0)
				result = "Four of a Kind";
			else if (flag[6] > 0 && flag[8] > 0)
				result = "Full House";
			else if (flag[4] > 0)
				result = "Flush";
			else if (flag[5] > 0)
				result = "Straight";
			else if (flag[6] > 0)
				result = "Three of a kind";
			else if (flag[8] == 2)
				result = "Two pair";
			else if (flag[8] == 1)
				result = "One pair";
			else
				result = "High card";

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}
