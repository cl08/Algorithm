package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S_D4_6719_성수의프로그래밍강좌시청 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			double[] arr = new double[N];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Double.parseDouble(st.nextToken());
			}
			Arrays.sort(arr);
			double A = 0.0;
			for (int i = 0; i < K; i++) {
				A = (A + arr[N-K+i]) / 2.0;
			}
			sb.append('#').append(tc).append(' ').append(A).append('\n');
		}
		System.out.println(sb);
	}
}
