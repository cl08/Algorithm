package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_5643_키순서_김유창 {
	static int N, M, count;
	static boolean[][] map;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			map = new boolean[N][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				map[x][y] = true;
			}
			int result = 0;
			for(int i=0; i<N; i++) {
				count = 0;
				visit = new boolean[N];
	
				// 순방향 카운트
				dfs1(i);

				// 역방향 카운트
				dfs2(i);
				
				if(count == N-1) {
					result++;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	public static void dfs1(int index) {
		visit[index] = true;
		for(int i=0; i<N; i++) {
			if(!visit[i] && map[index][i] == true) {
				count++;
				dfs1(i);
			}
		}
	}
	public static void dfs2(int index) {
		visit[index] = true;
		for(int i=0; i<N; i++) {
			if(!visit[i] && map[i][index] == true) {
				count++;
				dfs2(i);
			}
		}
	}
}
