package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_4530_극한의청소작업_김유창 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			long[] n = new long[2];
			long[] result = new long[2];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < 2; i++) {
				n[i] = Long.parseLong(st.nextToken());
				long temp;
				if (n[i] < 0)
					temp = n[i] * (-1);
				else
					temp = n[i];
				for (int j = 0; temp != 0; j++) {
					if (temp % 10 > 4)
						temp = temp - 1;
					result[i] = result[i] + (long) (temp % 10 * Math.pow(9, j));
					temp = temp / 10;
				}
			}
			long ans = 0;
			if (n[0] < 0 && 0 < n[1]) {
				ans = result[0] + result[1] - 1;
			} else if (n[0] < 0 && n[1] < 0) {
				ans = result[0] - result[1];
			} else if (0 < n[0] && 0 < n[1]) {
				ans = result[1] - result[0];
			}
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
