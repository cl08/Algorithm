package JUNGOL;

import java.util.Scanner;

public class Main_1697_큐_김유창 {
	static int front = 0, rear = 0;
	static int[] arr = new int[100];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int data;
		int n = sc.nextInt();
		for(int i=0; i<n; i++) {
			String s = sc.next();
			char c = s.charAt(0);
			if(c=='i') {
				data = sc.nextInt();
				push(data);
			}
			else if(c=='o')
				pop();
			else if(c=='c')
				size();
		}
		sc.close();
		
	}
	public static boolean isEmpty() {
		if(front==rear)
			return true;
		else
			return false;
	}
	public static void push(int data) {
		arr[rear] = data;
		rear++;
		if(rear == 100)
			front=0;
	}
	public static boolean pop() {
		if(isEmpty()) {
			System.out.println("empty");
			return false;
		}
		else {
			System.out.println(arr[front]);
			front++;
			if(front==100)
				front=0;
			return true;
		}
	}
	public static void size() {
		System.out.println(rear-front);
	}
}
