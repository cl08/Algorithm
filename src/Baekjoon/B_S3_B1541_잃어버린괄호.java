package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B_S3_B1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int temp = 0;
		Stack<Integer> q1 = new Stack<Integer>();
		Stack<Character> q2 = new Stack<Character>();
		
		// 뺄셈은 바로 스택에 넣고, 덧셈은 연산해서 넣음
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if ('0' <= ch && ch <= '9') {
				temp = temp * 10 + (ch - '0');
			} else {
				q1.push(temp);
				temp = 0;
				if (!q2.isEmpty() && q2.peek() == '+') {
					q1.push(q1.pop() + q1.pop());
				}
				q2.push(ch);
			}
		}
		q1.push(temp);
		if (!q2.isEmpty() && q2.peek() == '+') {
			q1.push(q1.pop() + q1.pop());
		}
		
		
		if (q1.size() == 1)
			System.out.println(q1.pop());
		
		else {
			while (q1.size() != 2) {
				q1.push(q1.pop() + q1.pop());
			}
			int num1 = q1.pop();
			int num2 = q1.pop();
			System.out.println(num2 - num1);
		}
	}
}
