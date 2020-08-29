package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1655_가운데를말해요_김유창 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> max = new PriorityQueue<>((x, y) -> y - x);
		PriorityQueue<Integer> min = new PriorityQueue<>();
		int number;
		for (int i = 0; i < N; i++) {
			number = Integer.parseInt(br.readLine());
			if (max.size() > min.size())
				min.offer(number);
			else 
				max.offer(number);
			if (!max.isEmpty() && !min.isEmpty() && max.peek() > min.peek()) {
				// swap
				int temp = max.poll();
				max.offer(min.poll());
				min.offer(temp);
			}
			sb.append(max.peek()).append('\n');
		}
		System.out.println(sb);
	}
}
