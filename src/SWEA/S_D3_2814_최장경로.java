package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_D3_2814_최장경로 {
	static int N, M, temp1, temp2, max;
	static boolean[][] arr;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken())+1;
			M = Integer.parseInt(st.nextToken());
			arr = new boolean[N][N];
			visit = new boolean[N];
			max = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				temp1 = Integer.parseInt(st.nextToken());
				temp2 = Integer.parseInt(st.nextToken());
				arr[temp1][temp2] = true;
				arr[temp2][temp1] = true;
			}
			for (int i = 1; i < N; i++) {
				dfs(i,1);
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}
	public static void dfs(int index, int count) {
		if(max < count)
			max = count;
		visit[index] = true;
		for(int i=1; i<N; i++) {
			if(!visit[i] && arr[index][i])
				dfs(i,count+1);
		}
		visit[index] = false;
	}
}