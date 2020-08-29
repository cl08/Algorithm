package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ing_Solution_D5_7206_숫자게임_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			Integer.parseInt(st.nextToken());
			Integer.parseInt(br.readLine());
			sb.append('#').append(tc).append(' ').append(" ").append('\n');
		}
		System.out.println(sb);
	}
}
