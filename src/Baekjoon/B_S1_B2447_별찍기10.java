package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S1_B2447_별찍기10 {
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N =Integer.parseInt(br.readLine());
		int i=0,j=0;
		while(i<N){
			while(j<N) {
				if(condi(i,j,3))
					sb.append(' ');
				else
					sb.append('*');
				j++;
			}
			sb.append('\n');
			i++;
			j=0;
		}
		System.out.println(sb);
	}

	public static boolean condi(int i, int j, int n) {
		if (n == N)
			return i%n >= n * 1 / 3 && i%n < n * 2 / 3 && j%n >= n * 1 / 3 && j%n < n * 2 / 3;
		else
			return (i%n >= n * 1 / 3 && i%n < n * 2 / 3) && (j%n >= n * 1 / 3 && j%n < n * 2 / 3) || condi(i, j, n*3);
	}
}