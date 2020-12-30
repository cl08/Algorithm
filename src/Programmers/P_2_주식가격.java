package Programmers;

import java.util.Arrays;

public class P_2_주식가격 {

	public static void main(String[] args) {
		int[] prices = {1,2,3,2,3};
		int[] answer = new int[5];
		for(int i=0; i<prices.length-1; i++){
			answer[i] = prices.length-1-i;
			for(int j=i+1; j<prices.length; j++){
				if(prices[i] > prices[j]){
					answer[i] = j-i;
					break;
				}
			}
		}
		System.out.println(Arrays.toString(answer));
	}
}
