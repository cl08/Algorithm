package Programmers;

import java.util.PriorityQueue;

/*
 * 1. PriorityQueue에 모두 집어 넣는다
 * 2. 스코빌 지수가 가장 낮은 음식의 값이 K보다 작으면 두개를 꺼내서 섞고 다시 넣는다
 * 3. 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
 * 4. 섞은 횟수를 카운트
 * 5. 음식이 하나 남았는데 스코ㅓ빌 지수가 K보다 낮으면 -1 리턴
 */
public class P_2_더맵게 {

	public static void main(String[] args) {
		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int K = 7;
		System.out.println(solution(scoville, K));
	}

	public static int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < scoville.length; i++) {
			pq.offer(scoville[i]);
		}
		while (pq.size() > 1) {
			if (pq.peek() < K) {
				answer++;
				pq.offer(pq.poll() + pq.poll() * 2);
			}
			else {
				break;
			}
		}
		if (pq.peek() < K)
			return -1;
		else
			return answer;
	}
}
