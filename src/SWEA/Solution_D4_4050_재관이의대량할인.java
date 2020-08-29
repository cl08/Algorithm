package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_4050_재관이의대량할인 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine().trim());
			int[] arr = new int[n];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < n; i++) {
				arr[i] = (Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(arr);
			int size = arr.length-1;
			int temp;
			for(int i=0; i<n/2; i++) {
				temp = arr[i];
				arr[i] = arr[size-i];
				arr[size-i] = temp;
			}
			
			int result = 0;
			for(int i=0; i<n; i++) {
				if((i+1)%3!=0)
					result = result + arr[i];
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}
