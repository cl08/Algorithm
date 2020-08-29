package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D4_5678_팰린드롬_김유창 {
	static char[] str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			String temp = br.readLine().trim();
			str = temp.toCharArray();
			int n = str.length;
			int max = 0;
			for (int i = 0; i < n; i++) {
				int start = i;
				int end = n - 1;
				while (end - start >= max) {
					int result = isPalindrome(start, end);
					if (max < result)
						max = result;
					end--;
				}
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	public static int isPalindrome(int start, int end) {
		int count = 0;
		while (start < end) {
			if (str[start] == str[end]) {
				count += 2;
				start++;
				end--;
			} else
				return 1;
		}
		if (start == end) {
			count++;
		}
		return count;
	}
}
