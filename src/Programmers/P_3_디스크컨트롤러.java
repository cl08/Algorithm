package Programmers;
/*
 * 1. 작업 리스트와 대기 리스트
 * 2. 작업 리스트에 작업들을 모두 넣고 요청시간 오름차순으로 정렬
 * 3. 현재시간보다 요청시간이 작거나 같은 작업들을 대기 리스트로 넣음
 * 4. 대기 리스트가 비어 있으면 현재시간을 다음 작업의 요청시간으로 변경
 * 5. 대기 리스트가 비어 있지 않다면 소요시간이 가장 짧은 작업부터 처리
 * 6. 3~5 반복
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P_3_디스크컨트롤러 {

	public static void main(String[] args) {
		int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };
		System.out.println(solution(jobs));
	}

	public static int solution(int[][] jobs) {
		int answer = 0, current_time = 0;
		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
		PriorityQueue<int[]> job_list = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		PriorityQueue<int[]> wait_list = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		for (int[] job : jobs) {
			job_list.offer(job);
		}
		while (!job_list.isEmpty() || !wait_list.isEmpty()) {
			// 현재시간보다 요청시간이 작거나 같은 작업들을 대기 리스트로 넣음
			while (!job_list.isEmpty() && job_list.peek()[0] <= current_time)
				wait_list.offer(job_list.poll());
			// 대기 리스트가 비어 있으면 현재시간을 다음 작업의 요청시간으로 변경
			if (wait_list.isEmpty()) {
				// int[] temp = job_list.poll();
				// current_time = current_time + temp[1];
				// answer = answer + current_time - temp[0];
				
				// 작업이 요청되는 시점이 같은 job이 있으므로 먼저 처리해버리면 안됨
				current_time = job_list.peek()[0];
			}
			// 대기 리스트가 비어 있지 않다면 소요시간이 가장 짧은 작업부터 처리
			else {
				int[] temp = wait_list.poll();
				current_time = current_time + temp[1];
				answer = answer + current_time - temp[0];
			}
		}
		return answer / jobs.length;
	}
}
