package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G5_17070_파이프옮기기1 {

	static int N, count;
	static int[] dx = {0,1,1};
	static int[] dy = {1,0,1};
	
	static int[][] map;
	static int[][] dp;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 파이프 초기값
		count = 0;
		Pipe pipe = new Pipe(0, 1, 0);

		dfs(pipe);
		System.out.println(count);

	}

	public static void dfs(Pipe pipe) {
		if (pipe.x == N - 1 && pipe.y == N - 1) {
			count++;
			return;
		}
		int x, y;
		boolean flag;
		// 파이프가 가로로 놓여진 경우
		if (pipe.shape == 0) {
			// 가로로 이동가능 하면 이동
			x = pipe.x;
			y = pipe.y + 1;
			if (x >= 0 && x < N && y >= 0 && y < N && map[x][y] == 0)
				dfs(new Pipe(x, y, 0));

			// 대각선으로 이동가능 하면 이동
			flag = true;
			for(int i=0; i<3; i++) {
				x = pipe.x+dx[i];
				y = pipe.y+dy[i];
				if (x < 0 || x >= N || y < 0 || y >= N || map[x][y] == 1) {
					flag = false;
					break;
				}
			}
			if(flag)
				dfs(new Pipe(x, y, 2));
			
		}

		// 파이프가 세로로 놓여진 경우
		else if (pipe.shape == 1) {
			// 세로로 이동가능 하면 이동
			x = pipe.x + 1;
			y = pipe.y;
			if (x >= 0 && x < N && y >= 0 && y < N && map[x][y] == 0)
				dfs(new Pipe(x, y, 1));

			// 대각선으로 이동가능 하면 이동
			flag = true;
			for(int i=0; i<3; i++) {
				x = pipe.x+dx[i];
				y = pipe.y+dy[i];
				if (x < 0 || x >= N || y < 0 || y >= N || map[x][y] == 1) {
					flag = false;
					break;
				}
			}
			if(flag)
				dfs(new Pipe(x, y, 2));

		}

		// 파이프가 대각선으로 놓여진 경우
		else if (pipe.shape == 2) {
			// 가로로 이동가능 하면 이동
			x = pipe.x;
			y = pipe.y + 1;
			if (x >= 0 && x < N && y >= 0 && y < N && map[x][y] == 0)
				dfs(new Pipe(x, y, 0));

			// 세로로 이동가능 하면 이동
			x = pipe.x + 1;
			y = pipe.y;
			if (x >= 0 && x < N && y >= 0 && y < N && map[x][y] == 0)
				dfs(new Pipe(x, y, 1));

			// 대각선으로 이동가능 하면 이동
			flag = true;
			for(int i=0; i<3; i++) {
				x = pipe.x+dx[i];
				y = pipe.y+dy[i];
				if (x < 0 || x >= N || y < 0 || y >= N || map[x][y] == 1) {
					flag = false;
					break;
				}
			}
			if(flag)
				dfs(new Pipe(x, y, 2));
		}
	}

	public static class Pipe {
		int x;
		int y;
		int shape;

		public Pipe(int x, int y, int shape) {
			this.x = x;
			this.y = y;
			this.shape = shape;
		}

	}
}
