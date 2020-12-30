package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_1952_수영장 {
	static int[] price;
	static int[] month;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		price = new int[4];
		month = new int[12];
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			min = price[3];
			dfs(0, 0);

			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int count, int sum) {
		if (min < sum) {
			return;
		}
		if (count > 11) {
			if (min > sum) {
				min = sum;
			}
			return;
		}
		if (month[count] == 0) {
			dfs(count + 1, sum);
		} else {
			dfs(count + 1, sum + price[0] * month[count]);
			dfs(count + 1, sum + price[1]);
			dfs(count + 3, sum + price[2]);
		}
	}
}
