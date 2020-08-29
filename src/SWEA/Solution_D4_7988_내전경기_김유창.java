package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_7988_내전경기_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		L : for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int K, length = 0;
			int[] temp = new int[2];
			String str;
			int[][] link = new int[401][401];
			st = new StringTokenizer(br.readLine(), " ");
			K = Integer.parseInt(st.nextToken());
			LinkedList<String> list = new LinkedList<String>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 2; j++) {
					str = st.nextToken();
					if (list.contains(str)) {
						temp[j] = list.indexOf(str);
					} else {
						temp[j] = list.size();
						list.add(str);
						length++;
					}
				}
				link[temp[0]][temp[1]] = 1;
				link[temp[1]][temp[0]] = 1;
			}
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(0);
			link[0][0] = 2;
			int flag = 2;
			while (!q.isEmpty()) {
				int size = q.size();
				while (size-- > 0) {
					int t = q.poll();
					for (int i = 0; i < length; i++) {
						if (link[t][i] == 1 && link[i][i] == flag) {
							sb.append("No\n");
							continue L;
						}
						if (link[t][i] == 1 && link[i][i] == 0) {
							q.offer(i);
							if (flag == 3)
								link[i][i] = 2;
							else
								link[i][i] = 3;
						}
					}
				}
				if (flag == 3)
					flag = 2;
				else
					flag = 3;
			}
			sb.append("Yes\n");
		}
		System.out.println(sb);
	}
}
