package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ing_Solution_D3_3307_최장증가부분수열_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] dp = new int[N];
			dp[0] = 1;
			int max = 0;
			st = new StringTokenizer(br.readLine().trim()," ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				for(int j=0; j<i; j++) {
					if(arr[j] < arr[i] && dp[j] >= dp[i]) {
						dp[i] = dp[j]+1;
						if(max < dp[i])
							max = dp[i];
					}
				}
				if(dp[i]==0) dp[i]=1;
			}
			
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}
}
