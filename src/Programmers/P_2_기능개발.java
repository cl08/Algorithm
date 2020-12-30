package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 1. 완료된 기능이 배포될 때 연속된 다른 완료된 기능들도 같이 배포됨
 * 2. 기능이 완료되면 연속된 다른 완료된 기능들 체크 
 */
public class P_2_기능개발 {
	
	public static void main(String[] args) {
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
//		int[] progresses = {93, 30, 55};
//		int[] speeds = {1, 30, 5};
		System.out.println(Arrays.toString(solution(progresses, speeds)));
	}
	
	public static int[] solution(int[] progresses, int[] speeds) {
		int pointer = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		while(pointer < progresses.length) {
			// 작업 진행
			for(int i=pointer; i<progresses.length; i++) {
				if(progresses[i] < 100) {
					progresses[i] = progresses[i] + speeds[i];
				}
			}
			// 작업이 완료되었나 확인
			if(progresses[pointer] >= 100) {
				int complete = 1;
				for(int i=pointer+1; i<progresses.length; i++) {
					if(progresses[i] >= 100) {
						if(i == progresses.length-1) {
							complete++;
							pointer = progresses.length;
							break;
						}
						else {
							complete++;
						}
					}
					else {
						break;
					}
				}
				pointer = pointer + complete;
				q.offer(complete);
			}
		}
		int[] answer = new int[q.size()];
		for(int i=0; i<answer.length; i++) {
			answer[i] = q.poll();
		}
		return answer;
	}
}