package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
	N*N
	흰 빨 파
	1~K개 말 겹칠 수 있음
	4방향으로 이동
	이동하는 칸이 흰색인 경우 > 말이 가장 위로 올라감
	의동하는 칸이 빨간색인 경우 > 말순서를 뒤집은 뒤 올라감
	파란색인 경우 > 반대로 이동 > 또 파란색이면 정지
	말 4개 쌓이면 정지 > 몇턴 후 정지? > 1000보다 크거나 멈추면 -1 출력
	
	4 <= N <= 12
	4 <= K <= 10
	
	리스트를 2차원 배열로 만든다 > 
	01:13
*/
public class ing_Main_G2_17837_새로운게임2 {

	static int N, K, count;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] map;

	public static class Mal {
		int x;
		int y;
		int num;
		int dir;

		public Mal(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ArrayList<Mal>[][] al = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				al[i][j] = new ArrayList<Mal>();
			}
		}
		Mal[] mals = new Mal[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			mals[i] = new Mal(x, y, i, Integer.parseInt(st.nextToken()) - 1);
			al[x][y].add(mals[i]);
		}
		count = 0;
		boolean flag = true;
		while (flag) {
			count++;
			if (count > 1000) {
				flag = false;
				break;
			}
			L:for (int i = 0; i < K; i++) {
				int x = mals[i].x;
				int y = mals[i].y;
				int tx = mals[i].x + dx[mals[i].dir];
				int ty = mals[i].y + dy[mals[i].dir];
				if (tx >= 0 && tx < N && ty >= 0 && ty < N) {
					// 흰색일 경우
					if (map[tx][ty] == 0) {
						int temp = 0;
						int size = al[mals[i].x][mals[i].y].size();
						for (int j = 0; j < size; j++) {
							if (al[mals[i].x][mals[i].y].get(j).num == mals[i].num) {
								temp = j;
							}
						}
						for (int j = temp; j < size; j++) {
							al[tx][ty].add(mals[al[x][y].get(temp).num]);
							mals[al[x][y].get(temp).num].x = tx;
							mals[al[x][y].get(temp).num].y = ty;
							al[x][y].remove(temp);
						}
					}

					// 빨간색일 경우
					else if (map[tx][ty] == 1) {
						int temp = 0;
						int size = al[mals[i].x][mals[i].y].size();
						for (int j = 0; j < size; j++) {
							if (al[mals[i].x][mals[i].y].get(j).num == mals[i].num) {
								temp = j;
							}
						}
						for (int j = temp; j < size; j++) {
							al[tx][ty].add(mals[al[x][y].get(al[x][y].size() - 1).num]);
							mals[al[x][y].get(al[x][y].size() - 1).num].x = tx;
							mals[al[x][y].get(al[x][y].size() - 1).num].y = ty;
							al[x][y].remove(al[x][y].get(al[x][y].size() - 1));
						}
					}
					if (al[tx][ty].size() > 3) {
						flag = false;
						break;
					}

					// 파란색일 경우
					else if (map[tx][ty] == 2) {
						// 방향 전환
						if (mals[i].dir == 0)
							mals[i].dir = 1;
						else if (mals[i].dir == 1)
							mals[i].dir = 0;
						else if (mals[i].dir == 2)
							mals[i].dir = 3;

						else if (mals[i].dir == 3)
							mals[i].dir = 2;

						// 반대 방향이 흰색 또는 빨간색 일 경우
						int ox = mals[i].x - dx[mals[i].dir];
						int oy = mals[i].y - dy[mals[i].dir];
						if (ox >= 0 && ox < N && oy >= 0 && oy < N && map[ox][oy] < 2) {
							count--;
							continue L;
						}
					}
				}
				else {
					// 방향 전환
					if (mals[i].dir == 0)
						mals[i].dir = 1;
					else if (mals[i].dir == 1)
						mals[i].dir = 0;
					else if (mals[i].dir == 2)
						mals[i].dir = 3;

					else if (mals[i].dir == 3)
						mals[i].dir = 2;

					// 반대 방향이 흰색 또는 빨간색 일 경우
					int ox = mals[i].x + dx[mals[i].dir];
					int oy = mals[i].y + dy[mals[i].dir];
					if (ox >= 0 && ox < N && oy >= 0 && oy < N && map[ox][oy] < 2) {
						i--;
						continue L;
					}
				}
			}

		}
		if (count > 1000)
			System.out.println(-1);
		else
			System.out.println(count);
	}
}
