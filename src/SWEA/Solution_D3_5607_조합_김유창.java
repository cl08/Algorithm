package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_5607_조합_김유창 {
	static final int mod = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			long fac[] = new long[N + 1];
			fac[0] = 1;
			for (int i = 1; i <= N; i++)
				fac[i] = (fac[i - 1] * i) % mod;

			long bottom = (fac[R] * fac[N - R]) % mod;
			long reBottom = fermat(bottom, mod- 2);

			long result = (fac[N] * reBottom) % mod;
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static long fermat(long n, int x) {
		if (x == 0)
			return 1;
		long tmp = fermat(n, x / 2);
		long ret = (tmp * tmp) % mod;
		if (x % 2 == 0)
			return ret;
		else
			return (ret * n) % mod;
	}	
}