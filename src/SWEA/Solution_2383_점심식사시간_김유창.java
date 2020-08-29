package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간_김유창 {
	static int N, pLength, dLength, min;
	// index 정보 : 0-x좌표, 1-y좌표, 2-주어진 출구, 3-남은 계단 길이
	static int[][] person = new int[10][4];
	static int[][] copyPerson = new int[10][4];
	// index 정보 : 0-x좌표, 1-y좌표, 2-계단의 길이, 3-계단을 이용중인 사람 수
	static int[][] door = new int[2][4];
	static int[][] copyDoor = new int[2][4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			pLength = 0;
			dLength = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if (temp == 1) {
						person[pLength][0] = i;
						person[pLength][1] = j;
						pLength++;
					} else if (temp > 1) {
						door[dLength][0] = i;
						door[dLength][1] = j;
						door[dLength][2] = temp;
						dLength++;
					}
				}
			}
			// 조합
			min = Integer.MAX_VALUE;
			combination(0);
			System.out.println("#" + tc + " " + min);
		}
	}

	public static void combination(int index) {
		if (index == pLength) {
			// 원본 배열 백업
			for (int i = 0; i < pLength; i++) {
				for (int j = 0; j < 4; j++) {
					copyPerson[i][j] = person[i][j];
				}
			}
			for (int i = 0; i < dLength; i++) {
				for (int j = 0; j < 4; j++) {
					copyDoor[i][j] = door[i][j];
				}
			}
			// 시뮬 시작
			int result = simulatior();
			if (result < min)
				min = result;

			// 배열 복구
			for (int i = 0; i < pLength; i++) {
				for (int j = 0; j < 4; j++) {
					person[i][j] = copyPerson[i][j];
				}
			}
			for (int i = 0; i < dLength; i++) {
				for (int j = 0; j < 4; j++) {
					door[i][j] = copyDoor[i][j];
				}
			}
			return;
		}
		for (int i = 0; i < 2; i++) {
			person[index][2] = i;
			combination(index + 1);
		}
	}

	public static int simulatior() {
		int count = 0;
		int time = 0;
		while (count != pLength) {
			time++;
			for (int i = 0; i < pLength; i++) {
				if (person[i][0] == door[person[i][2]][0] && person[i][1] == door[person[i][2]][1]) {
					// 이동 완료 가지치기
					if (person[i][3] == -1)
						continue;
					else if (person[i][3] > 0) {
						// 내려가는 중
						person[i][3]--;
						// 이동 완료 확인
						if (person[i][3] == 0) {
							person[i][3] = -1;
							door[person[i][2]][3]--;
							count++;
						}
					}

				}
			}
			for (int i = 0; i < pLength; i++) {
				// 계단
				if (person[i][0] == door[person[i][2]][0] && person[i][1] == door[person[i][2]][1]) {
					// 이동 완료 가지치기
					if (person[i][3] == -1)
						continue;
					// 계단 입구 도착
					else if (person[i][3] == 0) {
						// 계단을 이용중인 사람이 3명일 경우 대기
						if (door[person[i][2]][3] == 3)
							continue;
						// 계단을 이용중인 사람이 3명이 아닐 경우 계단을 내려가기 시작
						else {
							if (person[i][3] == 0) {
								door[person[i][2]][3]++;
								person[i][3] = door[person[i][2]][2];
							}
						}
					}
				}
				// 계단을 향해 이동
				else {
					if (person[i][0] < door[person[i][2]][0])
						person[i][0]++;
					else if (person[i][0] > door[person[i][2]][0])
						person[i][0]--;
					else if (person[i][1] < door[person[i][2]][1])
						person[i][1]++;
					else if (person[i][1] > door[person[i][2]][1])
						person[i][1]--;
				}
			}
		}
		return time;
	}
}
