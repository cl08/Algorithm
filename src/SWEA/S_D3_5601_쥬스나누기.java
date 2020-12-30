package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S_D3_5601_쥬스나누기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append('#').append(tc);
			for(int i=0; i<N; i++) {
				sb.append(' ').append('1').append('/').append(N);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
