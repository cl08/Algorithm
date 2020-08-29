package Reference;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {
	static int[] arr;
	static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		size = Integer.parseInt(br.readLine());
		arr = new int[size];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < size; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		split(0, size);
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void split(int low, int high) {
		if (high - low < 2)
			return;
		int mid = (low + high) / 2;
		split(low, mid);
		split(mid, high);
		merge(low, mid, high);
	}

	public static void merge(int low, int mid, int high) {
		int[] temp = new int[high - low];
		int t = 0, l = low, h = mid;
		while (l < mid && h < high) {
			if (arr[l] < arr[h])
				temp[t++] = arr[l++];
			else
				temp[t++] = arr[h++];
		}
		while(l<mid) {
			temp[t++] = arr[l++];
		}
		while(h<high){
			temp[t++] = arr[h++];
		}
		for(int i=low; i<high; i++) {
			arr[i] = temp[i-low];
		}
	}
}
