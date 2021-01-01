package Programmers;

import java.util.PriorityQueue;

public class P_1_K번째수 {

	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		for (int i = 0; i < commands.length; i++) {
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for (int j = commands[i][0] - 1; j <= commands[i][1] - 1; j++) {
				pq.offer(array[j]);
			}
			for (int j = 0; j < commands[i][2] - 1; j++) {
				pq.poll();
			}
			answer[i] = pq.poll();
		}
		return answer;
	}
}
