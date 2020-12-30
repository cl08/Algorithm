package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S_D5_1798_범준이의제주도여행계획 {
	static int N, M, max;
	static boolean[] visit;
	static int airport;
	static int[] resRoute;
	static Info[] info;
	static ArrayList<Integer> hotels;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			visit = new boolean[N];
			info = new Info[N];
			for (int i = 0; i < N; i++) {
				info[i] = new Info();
				info[i].dist = new int[N];
			}
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = i + 1; j < N; j++) {
					info[i].dist[j] = info[j].dist[i] = Integer.parseInt(st.nextToken());
				}
			}
			hotels = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				info[i].type = st.nextToken().charAt(0);
				if (info[i].type == 'P') {
					info[i].time = Integer.parseInt(st.nextToken());
					info[i].sat = Integer.parseInt(st.nextToken());
				} else if (info[i].type == 'A') {
					airport = i;
				} else {
					hotels.add(i);
				}
			}
			for(int i=0; i<N; i++) {
				int min = Integer.MAX_VALUE;
				for(int hotel : hotels) {
					if(min > info[i].dist[hotel])
						min = info[i].dist[hotel];
				}
				info[i].nearHotel = min;
			}
			int[] route = new int[N];
			max = 0;
			resRoute = new int[N];
			// 시작지점, 누적시간, 누적만족도, 이동경로, 이동경로idnex, day
			dfs(airport, 0, 0, route, 0, 0);
			sb.append('#').append(tc).append(' ').append(max);
			for(int i=0; i<N; i++) {
				if(resRoute[i] == 0)
					break;
				else
					sb.append(' ').append(resRoute[i]);
			}
			sb.append('\n');
			
		}
		System.out.println(sb);
	}

	public static void dfs(int start, int time, int sat, int[] route, int index, int day) {
		if(day == M) {
			// 최대값과 루트 저장
			if(max < sat) {
				max = sat;
				for(int i=0; i<N; i++) {
					resRoute[i] = route[i];
				}
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			// 여행 마지막 날일 경우 공항으로 돌아감
			if (day + 1 == M) {
				if (!visit[i] && info[i].type == 'P') {
					int temp = time + info[start].dist[i] + info[i].time;
					if (temp + info[i].dist[airport] <= 540) {
						visit[i] = true;
						route[index] = i + 1;
						dfs(i, temp, sat + info[i].sat, route, index + 1, day);
						route[index] = 0;
						visit[i] = false;
					}
				} else if (info[i].type == 'A') {
					route[index] = i + 1;
					dfs(i, 0, sat, route, index+1, day + 1);
					route[index] = 0;
				}
			}
			// 마지막 날이 아닐 경우 호텔로 돌아감
			else {
				// 간 적 없는 관광지 중에서
				if (!visit[i] && info[i].type == 'P') {
					// 관광지로 갔다가 호텔이로 되돌아오는 시간이 9시간이하이면 다음 관광지로
					int temp = time + info[start].dist[i] + info[i].time;
					if (temp + info[i].nearHotel <= 540) {
						visit[i] = true;
						route[index] = i + 1;
						dfs(i, temp, sat + info[i].sat, route, index + 1, day);
						route[index] = 0;
						visit[i] = false;
					}
				}
				// 호텔에 왔으면 하루가 지남(day++)
				else if (info[i].type == 'H') {
					if(time+info[start].dist[i] < 540) {
						route[index] = i + 1;
						dfs(i, 0, sat, route, index+1, day + 1);
						route[index] = 0;
					}
				}
			}
		}
	}

	public static class Info {
		char type;
		int time;
		int sat;
		int[] dist;
		int nearHotel;
	}
}
