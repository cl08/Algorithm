package Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LIS {

	static int N;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sb.append('#').append(tc).append(' ').append(lis()).append('\n');
		}
		System.out.println(sb);
	}

	private static int lis() {

		int[] lis = new int[N];
		int size = 0;
		for (int i = 0; i < N; i++) {
			// 덮어쓰거나 추가될 위치의 인덱스(음수 : -해당 인덱스-1)
			int temp = -(Arrays.binarySearch(lis, 0, size, arr[i]))-1;
			lis[temp] = arr[i];
			if (temp == size)
				++size;
		}
		return size;
	}
}
