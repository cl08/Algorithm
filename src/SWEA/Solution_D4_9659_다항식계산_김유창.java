package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_9659_다항식계산_김유창 {
	static final long MOD = 998244353;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] t = new int[N + 1];
			int[] a = new int[N + 1];
			int[] b = new int[N + 1];
			for (int i = 2; i <= N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				t[i] = Integer.parseInt(st.nextToken());
				a[i] = Integer.parseInt(st.nextToken());
				b[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine().trim());
			int[] x = new int[M + 1];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 1; i <= M; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			sb.append('#').append(tc);
			for (int i = 1; i <= M; i++) {
				long temp = x[i];
				long[] f = new long[N + 1];

				f[0] = 1;
				f[1] = temp;

				for (int j = 2; j <= N; j++) {
					switch (t[j]) {
					case 1:
						f[j] = (f[a[j]] + f[b[j]]) % MOD;
						break;
					case 2:
						f[j] = (a[j] * f[b[j]]) % MOD;
						break;
					case 3:
						f[j] = (f[a[j]] * f[b[j]]) % MOD;
						break;
					}
				}
				sb.append(' ').append(f[N]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
