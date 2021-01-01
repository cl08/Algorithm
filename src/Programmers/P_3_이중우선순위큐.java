package Programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class P_3_이중우선순위큐 {
	public static void main(String[] args) {
		String[] operations = { "I 7", "I 5", "I -5", "D -1" };
		System.out.println(Arrays.toString(solution(operations)));
	}

	public static int[] solution(String[] operations) {
		int[] answer = new int[2];
		PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
		for (String operation : operations) {
			String[] cmd = operation.split(" ");
			if (cmd[0].equals("I")) {
				minQueue.offer(Integer.parseInt(cmd[1]));
				maxQueue.offer(Integer.parseInt(cmd[1]));
			}
			else {
				if (cmd[1].equals("1")) {
					minQueue.remove(maxQueue.poll());
				}
				else {
					maxQueue.remove(minQueue.poll());
				}
			}
		}
		if (maxQueue.isEmpty() && minQueue.isEmpty()) {
			answer[0] = 0;
			answer[1] = 0;
		}
		else {
			answer[0] = maxQueue.poll();
			answer[1] = minQueue.poll();
		}
		return answer;
	}
}
