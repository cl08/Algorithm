package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_G5_B17471_게리맨더링 {
	static int n, result, min = Integer.MAX_VALUE;
	static int[][] link;
	static int[] arr, pick, group1, group2;
	static boolean[] visit, select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		visit = new boolean[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int size;
		link = new int[n][];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			link[i] = new int[size + 1];
			link[i][0] = size;
			for (int j = 1; j <= size; j++) {
				link[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}

		// 1. 조합 나누기
		for (int r = 1; r <= n / 2; r++) {
			pick = new int[n];
			group1 = new int[r];
			group2 = new int[n - r];
			for (int i = 0; i < r; i++) {
				pick[n - 1 - i] = 1;
			}
			L:do {
				int g1 = 0, g2 = 0;

				for (int i = 0; i < n; i++) {
					if (pick[i] == 1)
						group1[g1++] = i;
					else
						group2[g2++] = i;
				}
//				System.out.println();
//				System.out.println(Arrays.toString(group1));
//				System.out.println(Arrays.toString(group2));

				// 2. 나눈 조합이 연결 되어 있는지 확인
				Arrays.fill(visit, false);
				dfs(group1, group1[0]);
				for (int i = 0; i < r; i++) {
					if (!visit[group1[i]])
						continue L;
				}

				Arrays.fill(visit, false);
				dfs(group2, group2[0]);
				for (int i = 0; i < n - r; i++) {
					if (!visit[group2[i]])
						continue L;
				}

				// 3. 연결 되어 있다면 인구수 확인
				int sum1 = 0, sum2 = 0;
				for (int i = 0; i < r; i++) {
					sum1 = sum1 + arr[group1[i]];
				}
				for (int i = 0; i < n - r; i++) {
					sum2 = sum2 + arr[group2[i]];
				}
				if (sum1 > sum2)
					result = sum1 - sum2;
				else
					result = sum2 - sum1;
//				System.out.println(result);
				if (min > result)
					min = result;
					
			} while (np(pick));
		}
		if (min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);
	}

	private static boolean np(int[] p) {
		int N = p.length;
		// 1. 교환이 필요한 i-1 위치 찾기(i:꼭지)
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			i--;
		if (i == 0)
			return false;
		// 2. i-1 위치값과 교환이 필요한 j위치 찾기
		int j = N - 1;
		while (p[i - 1] >= p[j])
			j--;

		// 3. i-1위치 값과 j위치값 교환 : i-1 을 한단계 큰 수로 만듦
		int temp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = temp;

		// 4. i위치부터 맨 뒤까지 내림차순 형태로 오름차순 형태로 재조정
		j = N - 1;
		while (i < j) {
			temp = p[i];
			p[i] = p[j];
			p[j] = temp;
			i++;
			j--;
		}
		return true;
	}

	public static void dfs(int[] group, int index) {
		visit[index] = true;
		for (int i = 1; i <= link[index][0]; i++) {
			if (!visit[link[index][i]]) {
				for (int j = 0; j < group.length; j++) {
					if (link[index][i] == group[j])
						dfs(group, link[index][i]);
				}
			}
		}
	}
}