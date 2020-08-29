package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_보호필름_김유창 {
	static int D, W, K, result;
	static int[] select;
	static int[][] arr, copy;
	static boolean stop;
	static boolean[] chemical;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[D][W];
			copy = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					copy[i][j] = arr[i][j];
				}
			}
			result = Integer.MAX_VALUE;
			stop = false;
			if (check()) {
				result = 0;
			} else {
				for (int i = 1; i <= D; i++) {
					if (stop)
						break;
					select = new int[i];
					chemical = new boolean[i];
					combination(0, 0, i);
				}
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void combination(int count, int index, int end) {
		if (stop)
			return;
		if (count == end) {
			selectChemical(0, end);
			return;
		}
		for (int i = index; i < D; i++) {
			select[count] = i;
			combination(count + 1, i + 1, end);
		}
	}

	private static void selectChemical(int index, int size) {
		if (stop)
			return;
		if (index == size) {
			useChemical(size);
			return;
		}
		chemical[index] = true;
		selectChemical(index + 1, size);
		chemical[index] = false;
		selectChemical(index + 1, size);
	}

	private static void useChemical(int size) {
		if (stop)
			return;
		for (int i = 0; i < size; i++) {
			int temp;
			if (chemical[i])
				temp = 1;
			else
				temp = 0;
			for (int j = 0; j < W; j++) {
				arr[select[i]][j] = temp;
			}
		}
		if (check() && size < result) {
			result = size;
			stop = true;
		}
		// 맵 복구
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				arr[i][j] = copy[i][j];
			}
		}
	}

	private static boolean check() {
		L: for (int i = 0; i < W; i++) {
			int count = 0;
			int flag = arr[0][i];
			for (int j = 0; j < D; j++) {
				if (arr[j][i] == flag) {
					count++;
				} else {
					flag = arr[j][i];
					count = 1;
				}
				if (count == K) {
					continue L;
				}
			}
			return false;
		}
		return true;
	}
}
