package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G3_B17143_낚시왕 {
	public static class Shark {
		int r;
		int c;
		int s;
		int d;
		int z;
		boolean live;

		Shark(int r, int c, int s, int d, int z, boolean live) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.live = live;
		}
	}

	static Shark[] sk;
	static int R, C, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken())+1;
		C = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken());
		int r, c, s, d, z;
		int[] fisher = new int[2];
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = -1;
			}
		}

		sk = new Shark[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken()); // 1:위, 2:아래, 3:오른, 4:왼
			z = Integer.parseInt(st.nextToken());
			map[r][c] = i;
			sk[i] = new Shark(r, c, s, d, z, true);
		}
		for (int i = 0; i < C-1; i++) {
			// 낚시왕이 한칸 이동
			fisher[0]++;

			// 가까운 상어 포획
			int index = 0;
			for (int j = 1; j < R; j++) {
				if (map[j][fisher[0]] != -1) {
					index = map[j][fisher[0]];
					map[j][fisher[0]] = -1;
					sk[index].live = false;
					fisher[1] = fisher[1] + sk[index].z;
					break;
				}
			}

			// 상어들 이동
			for (int j = 0; j < M; j++) {
				if (sk[j].live) {
					map[sk[j].r][sk[j].c] = -1;
					move(j);
				}
			}

			// 겹치는 상어 제거
			for (int j = 0; j < M; j++) {
				if (sk[j].live) {
					r = sk[j].r;
					c = sk[j].c;

					if (map[r][c] == -1)
						map[r][c] = j;
					else if (map[r][c] != -1 && sk[j].z > sk[map[r][c]].z) {
						sk[map[r][c]].live = false;
						map[r][c] = j;
					}
					else
						sk[j].live = false;
				}
			}			
		}
		System.out.println(fisher[1]);
	}

	public static void move(int n) {
		int m = sk[n].s;
		while (m > 0) {
			switch (sk[n].d) {
			case 1:
				if (sk[n].r == 1) {
					sk[n].d = 2;
					sk[n].r++;
				} else
					sk[n].r--;
				break;
			case 2:
				if (sk[n].r == R-1) {
					sk[n].d = 1;
					sk[n].r--;
				} else
					sk[n].r++;
				break;
			case 3:
				if (sk[n].c == C-1) {
					sk[n].d = 4;
					sk[n].c--;
				} else
					sk[n].c++;
				break;
			case 4:
				if (sk[n].c == 1) {
					sk[n].d = 3;
					sk[n].c++;
				} else
					sk[n].c--;
				break;
			}
			m--;
		}
	}
	public static void print() {
		for(int j=0; j<R; j++) {
			for(int k=0; k<C; k++) {
				System.out.print(map[j][k]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
