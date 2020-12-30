package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_S1_B7576_토마토 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		int n, m, x, y;
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					x = i;
					y = j;
					qx.offer(x);
					qy.offer(y);
				}
			}
		}
		int size, curX, curY, count = 0;
		while (!qx.isEmpty()) {
			size = qx.size();
			while (size-- > 0) {
				x = qx.poll();
				y = qy.poll();
				for (int i = 0; i < 4; i++) {
					curX = x + dx[i];
					curY = y + dy[i];
					if (curX >= 0 && curX < n && curY >= 0 && curY < m && arr[curX][curY] == 0) {
						arr[curX][curY] = 1;
						qx.offer(curX);
						qy.offer(curY);
					}
				}
			}
			count++;
		}
		--count;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) {
					count = -1;
				}
			}
		}
		System.out.println(count);
	}
}
