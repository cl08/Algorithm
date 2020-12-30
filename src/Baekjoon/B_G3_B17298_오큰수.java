package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_G3_B17298_오큰수 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> s = new Stack<Integer>();
		for(int i=N-1; i>=0; i--) {
			if(s.isEmpty()) {
				s.push(arr[i]);
				arr[i] = -1;
			}
			else {
				if(arr[i] < s.peek()) {
					int temp = s.peek();
					s.push(arr[i]);
					arr[i] = temp;
				}
				else {
					s.pop();
					i++;
				}
			}
		}
		for(int i=0; i<N; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
