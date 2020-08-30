package JUNGOL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1828_냉장고_김유창 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 2차원 배열 정렬
		int index;
		for (int i = 0; i < n - 1; i++) {
			int[] min = new int[2];
			int temp;
			min[0] = 10000;
			index = i;
			for (int j = i; j < n; j++) {
				if (min[0] > arr[j][0]) {
					min[0] = arr[j][0];
					min[1] = arr[j][1];
					index = j;
				}
			}
			temp = arr[index][0];
			arr[index][0] = arr[i][0];
			arr[i][0] = temp;
			temp = arr[index][1];
			arr[index][1] = arr[i][1];
			arr[i][1] = temp;
		}
		
		//냉장고 갯수 세기
		index = 0;
		int count = 1;
		int a = arr[index][0];
		int b = arr[index][1];
		while (index != n) {
			if (arr[index][0] >= a && arr[index][0] <= b) {
				a = arr[index][0];
				if (arr[index][1] < b)
					b = arr[index][1];
			} else {
				a = arr[index][0];
				b = arr[index][1];
				count++;
			}
			index++;
		}
		System.out.println(count);
	}
}