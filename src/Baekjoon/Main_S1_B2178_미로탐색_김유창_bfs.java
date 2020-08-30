package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_B2178_미로탐색_김유창_bfs {

	static boolean[][] arr;
	static int n, m;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new boolean[n][m];
		String str;
		for (int i = 0; i < n; i++) {
			str = br.readLine();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == '1')
					arr[i][j] = true;
				else
					arr[i][j] = false;
			}
		}
		bfs();
	}

	public static void bfs() {
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		boolean[][] visited = new boolean[n][m];
		int x, y, curX, curY, size, count=1;
		visited[0][0] = true;
		qx.offer(0);
		qy.offer(0);
		while (!qx.isEmpty()) {
			size = qx.size();
			while (size-- > 0) {
				x = qx.poll();
				y = qy.poll();
				if(x==n-1 && y==m-1)
					System.out.println(count);
				for (int i = 0; i < 4; i++) {
					curX = x + dx[i];
					curY = y + dy[i];
					if (curX >= 0 && curX < n && curY >= 0 && curY < m && !visited[curX][curY] && arr[curX][curY]) {
						visited[curX][curY] = true;
						qx.offer(curX);
						qy.offer(curY);
					}
				}
			}
			count++;
		}
	}
}
