package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3234_준환이의양팔저울_김유창 {

	static int N, count;
	static int[] sinker;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			sinker = new int[N];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				sinker[i] = Integer.parseInt(st.nextToken());
			}
			count = 0;
			dfs(0, 0, 0);
			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int index, int left, int right) {
		// 기저조건
		if (index == N) {
			count++;
			return;
		}
		for (int i = index; i < N; i++) {
			int temp = sinker[index];
			sinker[index] = sinker[i];
			sinker[i] = temp;
			dfs(index + 1, left + sinker[index], right);
			if (left >= right + sinker[index])
				dfs(index + 1, left, right + sinker[index]);
			temp = sinker[index];
			sinker[index] = sinker[i];
			sinker[i] = temp;
		}
	}
}
