package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 	정보
 *	1. 4*4 맵에 크기와 방향을 가진 물고기가 있음(1~16, 8방향)
 *	2. 청소년 상어는 0,0 물고기 먹으면서 시작. 방향은 먹은 물고기의 방향
 *	3. 번호가 작은 물고기부터 차례대로 자신이 갖고 있는 방향으로 이동. 빈칸과 다른 물고기가 있는 칸으로 이동 가능하고 물고기가 있는 칸으로 이동시 자리 교환
 *	4. 이동 할 수 없으면 방향을 45도 반시계 회전하여 다시 이동 시도.
 *	5. 모든 물고기가 이동하면 상어가 이동. 자신이 갖고 있는 방향에 물고기가 있는 모든 칸으로 이동 가능
 *	6. 이동할 수 없으면 종료
 *	7. 먹을 수 있는 물고기 번호의 합이 최대가 되는 값 출력
 *
 *	1. 상어 0,0 배치
 *	2. 물고기 차례대로 이동
 *	3. 상어 이동 가능한 칸으로 이동(dfs)
 *	4. 2~3을 반복하면서 백트래킹으로 모든 케이스를 확인
 *	5. max값 출력
 *
 *	84ms 13148KB
 */
public class B_G3_19236_청소년상어 {

	static int max;
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { 1, 0, -1, -1, -1, 0, 1, 1 };
	static int[][] map;
	static int[][] dir;
	static Fish shark;
	static Fish[] fish;
	static PriorityQueue<Fish> q = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[4][4];
		dir = new int[4][4];
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dir[i][j] = Integer.parseInt(st.nextToken());
				q.offer(new Fish(i, j));
			}
		}
		shark = new Fish(0, 0);
		fish = new Fish[16];
		for(int i=0; i<16; i++) {
			fish[i] = q.poll();
		}
		max = map[0][0];
		fish[map[0][0] - 1].live = false;
		// 상어가 있는 칸은 -1로 표시
		map[0][0] = -1;
		dfs(max);
		System.out.println(max);
	}

	public static void dfs(int sum) {
		if (max < sum)
			max = sum;
		// 물고기 이동
		for (int i = 0; i < 16; i++) {
			if (fish[i].live == false)
				continue;
			int tx, ty;
			for (int j = 0; j < 8; j++) {
				tx = fish[i].x + dx[dir[fish[i].x][fish[i].y] % 8];
				ty = fish[i].y + dy[dir[fish[i].x][fish[i].y] % 8];
				if (tx >= 0 && tx < 4 && ty >= 0 && ty < 4) {
					if (map[tx][ty] > 0) {
						
						// 물고기 자리 교환
						int temp = map[fish[i].x][fish[i].y];
						int dirTemp = dir[fish[i].x][fish[i].y];
						int xTemp = fish[i].x;
						int yTemp = fish[i].y;

						map[fish[i].x][fish[i].y] = map[tx][ty];
						dir[fish[i].x][fish[i].y] = dir[tx][ty];
						fish[i].x = fish[map[tx][ty] - 1].x;
						fish[i].y = fish[map[tx][ty] - 1].y;

						fish[map[tx][ty] - 1].x = xTemp;
						fish[map[tx][ty] - 1].y = yTemp;
						dir[tx][ty] = dirTemp;
						map[tx][ty] = temp;
						
						break;
						
					} else if (map[tx][ty] == 0) {
						
						// 빈칸으로 물고기 이동
						dir[tx][ty] = dir[fish[i].x][fish[i].y];
						map[tx][ty] = map[fish[i].x][fish[i].y];
						map[fish[i].x][fish[i].y] = 0;
						fish[i].x = tx;
						fish[i].y = ty;
						break;
						
					} else 
						// 방향 회전
						dir[fish[i].x][fish[i].y] = dir[fish[i].x][fish[i].y] % 8 + 1;
				} else
					// 방향 회전
					dir[fish[i].x][fish[i].y] = dir[fish[i].x][fish[i].y] % 8 + 1;
			}
//			System.out.println((i+1)+"번 물고기 이동");
//			print();
		}
//		System.out.println("모든 물고기 이동 후");
//		print();
		
		// 상어 이동 가능한 지역으로 이동
		boolean flag = true;
		int tx, ty;
		int add = 1;
		while (flag) {

			tx = shark.x + dx[dir[shark.x][shark.y]%8] * add;
			ty = shark.y + dy[dir[shark.x][shark.y]%8] * add;
			if (tx >= 0 && tx < 4 && ty >= 0 && ty < 4) {
				if (map[tx][ty] > 0) {
				
					// 백업
					int[][] copyMap = new int[4][4];
					int[][] copyDir = new int[4][4];
					for(int i=0; i<4; i++) {
						for(int j=0; j<4; j++) {
							copyMap[i][j] = map[i][j];
							copyDir[i][j] = dir[i][j];
						}
					}
					
					Fish copyShark = new Fish(shark.x, shark.y);
					Fish[] copyFish = new Fish[16];
					for(int i=0; i<16; i++) {
						copyFish[i] = new Fish(fish[i].x, fish[i].y);
					}
					
					// 상어가 먹음
					int temp = map[tx][ty];
					int temp2 = map[tx][ty] - 1;
					fish[temp2].live = false;
					map[tx][ty] = -1;
					map[shark.x][shark.y] = 0;
					shark.x = tx;
					shark.y = ty;
					
//					System.out.println("상어 이동");
//					print();
					
					dfs(sum+temp);
					
					// 백트래킹
					fish[temp2].live = true;
					for(int i=0; i<4; i++) {
						for(int j=0; j<4; j++) {
							map[i][j] = copyMap[i][j];
							dir[i][j] = copyDir[i][j];
						}
					}
					shark.x = copyShark.x;
					shark.y = copyShark.y;
					for(int i=0; i<16; i++) {
						fish[i].x = copyFish[i].x;
						fish[i].y = copyFish[i].y;
					}
//					System.out.println("백트래킹");
//					print();
				}
				add++;
			}
			else
				flag = false;
		}
	}

	public static class Fish implements Comparable<Fish> {
		
		int x;
		int y;
		boolean live = true;

		Fish(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Fish o) {
			return map[this.x][this.y] - map[o.x][o.y];
		}
	}

	public static void print() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.printf("%3d", map[i][j]); 
			}
			System.out.print("     ");
			for (int j = 0; j < 4; j++) {
				System.out.printf("%3d", dir[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
