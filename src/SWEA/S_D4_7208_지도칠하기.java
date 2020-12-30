package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_D4_7208_지도칠하기 {

	static int N, min;
	static int[] info;
	static int[] color;
	static boolean flag;
	static boolean[] visit;
	static boolean[][] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			adj = new boolean[N][N];
			info = new int[N];
			color = new int[N];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				info[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					if (Integer.parseInt(st.nextToken()) == 1)
						adj[i][j] = true;
				}
			}
			min = Integer.MAX_VALUE;
			permutation(0);
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	public static void permutation(int index){
		if(index==N) {
			visit = new boolean[N];
			flag = false;
			for(int i=0; i<N; i++) {
				dfs(i,0);
				if(flag)
					break;
			}
			if(!flag) {
				int count =0;
				for(int i=0; i<N; i++)
					if(info[i] != color[i])
						count++;
				if(count < min)
					min = count;
			}
			return;
		}
		for(int i=1; i<=4; i++) {
			color[index] = i;
			permutation(index+1);
		}
	}
	
	public static void dfs(int n, int c) {
		//같은 색이면 return
		if(c == color[n]) {
			flag = true;
			return;
		}
		visit[n] = true;
		for(int i=0; i<N; i++) {
			if(adj[n][i] && !visit[i]) {
				dfs(i, color[n]);
			}
		}
	}
}
