package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_S1_B7569_토마토3차원 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		Queue<Integer> qz = new LinkedList<Integer>();
		int n, m, h, x, y, z;
		int[] dx = { 1, 0, -1, 0, 0, 0 };
		int[] dy = { 0, 1, 0, -1, 0, 0 };
		int[] dz = { 0, 0, 0, 0, 1, -1 };
		st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		int[][][] arr = new int[h][n][m];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < m; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if (arr[i][j][k] == 1) {
						qz.offer(i);
						qx.offer(j);
						qy.offer(k);
					}
				}
			}
		}  //여기까지봄
		int size, curX, curY, curZ, count = 0;
		while (!qx.isEmpty()) {
			size = qx.size();
			while (size-- > 0) {
				z = qz.poll();
				x = qx.poll();
				y = qy.poll();
				for (int i = 0; i < 6; i++) {
					curZ = z + dz[i];
					curX = x + dx[i];
					curY = y + dy[i];
					if (curX >= 0 && curX < n && curY >= 0 && curY < m && curZ >= 0 && curZ < h
							&& arr[curZ][curX][curY] == 0) {
						arr[curZ][curX][curY] = 1;
						qz.offer(curZ);
						qx.offer(curX);
						qy.offer(curY);
					}
				}
			}
			count++;
		}
		--count;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if (arr[i][j][k] == 0) {
						count = -1;
					}
				}
			}
		}
		System.out.println(count);
	}
}