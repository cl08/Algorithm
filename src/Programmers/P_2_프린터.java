package Programmers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 1. 대기목록에서 가장 앞에 있는 문서(J)를 꺼냄
 * 2. 나머지 대기목록 중에 J보다 중요도가 높은 문서가 있으면 J를 맨 뒤로 보냄
 * 3. 없으면 J를 인쇄
 */
public class P_2_프린터 {

	public static void main(String[] args) {
//		int[] priorities = { 2, 1, 3, 2 };
//		int location = 2;
		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 0;

		System.out.println(solution(priorities, location));
	}

	public static int solution(int[] priorities, int location) {
		int answer = 0;
		// 인쇄목록을 큐에 넣는다
		Queue<Integer> print_list = new LinkedList<Integer>();
		PriorityQueue<Integer> priority_list = new PriorityQueue<Integer>(Collections.reverseOrder());
		for (int i = 0; i < priorities.length; i++) {
			print_list.offer(priorities[i]);
			priority_list.offer(priorities[i]);
		}
		while (location >= 0) {
			int number = print_list.poll();
			// 우선순위에 해당하면 출력
			if (number == priority_list.peek()) {
				priority_list.poll();
				location = location - 1;
				answer++;
			}
			// 우선순위가 아니면 맨 뒤로 보내기
			else {
				print_list.offer(number);
				if (location > 0) {
					location = location - 1;
				}
				else {
					location = print_list.size()-1;
				}
			}
		}
		return answer;
	}
}
