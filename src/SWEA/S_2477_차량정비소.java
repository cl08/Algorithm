package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S_2477_차량정비소 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			Reception[] reception = new Reception[N];
			for (int i = 0; i < N; i++) {
				reception[i] = new Reception();
			}
			Repair[] repair = new Repair[M];
			for (int i = 0; i < M; i++) {
				repair[i] = new Repair();
			}
			Customer[] customer = new Customer[K];
			for (int i = 0; i < K; i++) {
				customer[i] = new Customer();
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				reception[i].time = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				repair[i].time = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < K; i++) {
				customer[i].arrivalTime = Integer.parseInt(st.nextToken());
			}

			// 시뮬 시작

			// 접수 대기 명단
			Queue<Integer> waitReception = new LinkedList<Integer>();
			// 정비 대기 명단
			Queue<Integer> waitRepair = new LinkedList<Integer>();
			// 접수 창구 이용중인 고객 리스트
			LinkedList<Integer> repairList = new LinkedList<Integer>();
			// 정비 창구 이용중인 고객 리스트
			LinkedList<Integer> receptionList = new LinkedList<Integer>();

			int time = 0;
			int count = 0;
			int result = 0;
			while (count != K) {

				// 도착한 고객이 있으면 번호를 접수 대기큐에 넣음
				for (int i = 0; i < K; i++) {
					if (customer[i].arrivalTime == time) {
						waitReception.offer(i);
					}
				}

				// 정비가 끝난 고객 repair 리스트에서 제거
				if (!repairList.isEmpty()) {
					for (int i = 0; i < M; i++) {
						int index = repair[i].number;
						if (repair[i].using && customer[index].repairTime == 0) {
							repair[i].using = false;
							for(int j=0; j<repairList.size(); j++) {
								if(repairList.get(j) == index)
									repairList.remove(j);
							}
							count++;
							if (customer[index].receptionNumber == A && customer[index].repairNumber == B)
								result = result + (index + 1);
						}
					}
				}

				// 접수가 끝난 고객 reception 리스트에서 제거하고 waitRepair 큐에 넣음
				if (!receptionList.isEmpty()) {
					for (int i = 0; i < N; i++) {
						int index = reception[i].number;
						if (reception[i].using && customer[index].receptionTime == 0) {
							waitRepair.offer(index);
							reception[i].using = false;
							for(int j=0; j<receptionList.size(); j++) {
								if(receptionList.get(j) == index)
									receptionList.remove(j);
							}
						}
					}
				}

				// 접수 대기자가 있고 빈 접수 창구가 있으면 waitReception 큐에서 제거하고 reception 리스트에 삽입
				for (int i = 0; i < N; i++) {
					if (!reception[i].using && !waitReception.isEmpty()) {
						int index = waitReception.poll();
						reception[i].using = true;
						reception[i].number = index;
						receptionList.add(index);
						customer[index].receptionTime = reception[i].time;
						customer[index].receptionNumber = i;
					}
				}
				if (!receptionList.isEmpty()) {
					int size = receptionList.size();
					for (int i = 0; i < size; i++) {
						customer[receptionList.get(i)].receptionTime--;
					}
				}

				// 정비 대기자가 있고 빈 정비 창구가 있으면 waitRepair 큐에서 제거하고 repair 리스트에 삽입
				for (int i = 0; i < M; i++) {
					if (!repair[i].using && !waitRepair.isEmpty()) {
						int index = waitRepair.poll();
						repair[i].using = true;
						repair[i].number = index;
						repairList.add(index);
						customer[index].repairTime = repair[i].time;
						customer[index].repairNumber = i;
					}
				}
				if (!repairList.isEmpty()) {
					int size = repairList.size();
					for (int i = 0; i < size; i++) {
						customer[repairList.get(i)].repairTime--;
					}
				}
				time++;
			}
			result = result == 0 ? -1 : result;
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static class Reception {
		public Reception(int time, int number, boolean using) {
			super();
			this.time = time;
			this.number = number;
			this.using = using;
		}

		public Reception() {

		}

		int time;
		int number;
		boolean using;
	}

	public static class Repair {
		public Repair(int time, int number, boolean using) {
			super();
			this.time = time;
			this.number = number;
			this.using = using;
		}

		public Repair() {
		}

		int time;
		int number;
		boolean using;
	}

	public static class Customer {

		public Customer(int arrivalTime, int receptionTime, int repairTime, int receptionNumber, int repairNumber) {
			super();
			this.arrivalTime = arrivalTime;
			this.receptionTime = receptionTime;
			this.repairTime = repairTime;
			this.receptionNumber = receptionNumber;
			this.repairNumber = repairNumber;
		}

		public Customer() {
		}

		int arrivalTime;
		int receptionTime;
		int repairTime;
		int receptionNumber;
		int repairNumber;
	}
}
