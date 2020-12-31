package Programmers;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 1. 트럭이 순서대로 지나감
 * 2. 1초에 1만큼 움직임
 * 3. 트럭 무게의 합은 다리 무게를 초과할 수 없음
 */
public class P_2_다리를지나는트럭 {

	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = { 7, 4, 5, 6 };
		System.out.println(solution(bridge_length, weight, truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		Queue<Integer> trucks = new LinkedList<Integer>();
		Queue<Integer> bridge = new LinkedList<Integer>();
		for (int i = 0; i < truck_weights.length; i++) {
			trucks.offer(truck_weights[i]);
		}
		int current_weight = 0;
		int start = 0;
		int[] truck_times = new int[truck_weights.length];
		int count = 0;
		while (!trucks.isEmpty() || !bridge.isEmpty()) {
			// 다리 위에 트럭이 있으면 한칸씩 이동
			for (int i = start; i < truck_times.length; i++) {
				if (truck_times[i] == 1) {
					start++;
					truck_times[i] = -1;
					current_weight = current_weight - bridge.poll();
				}
				else if (truck_times[i] == 0) {
					break;
				}
				else {
					truck_times[i]--;
				}
			}
			// 다리가 꽉 차 있지 않고, 트럭을 넣어도 무게가 초과되지 않으면 트럭을 다리에 넣음
			if (trucks.size() > 0 && bridge.size() < bridge_length && current_weight + trucks.peek() <= weight) {
				int truck = trucks.poll();
				current_weight = current_weight + truck;
				bridge.offer(truck);
				truck_times[count++] = bridge_length;
			}
			answer++;
		}
		return answer;
	}
}
