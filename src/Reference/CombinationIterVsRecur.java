package Reference;
import java.util.Arrays;

public class CombinationIterVsRecur {

	private static char[] src = {'A', 'B', 'C', 'D', 'E'};
	private static char[] tgt;
	private static int N;
	
    public static void main(String[] args) {
        N = 3;
        tgt = new char[N];
        
        // src에서 3개를 뽑는 조합을 출력하시오.
        //byIter();
        System.out.println();
        //byRecurLeft(0, 0);
        byRecurRight(src.length - 1, 0);
    }

    public static void byRecurLeft(int srcIndex, int tgtIndex) {
        if( tgtIndex == tgt.length ) {
            System.out.println(Arrays.toString(tgt));
            return;
        } 
        
        if( srcIndex == src.length) return;
        
        tgt[tgtIndex] = src[srcIndex];
        byRecurLeft(srcIndex+1, tgtIndex+1);
        byRecurLeft(srcIndex+1, tgtIndex);

    }
    
    public static void byRecurRight(int srcIndex, int tgtIndex) {
        if( tgtIndex == tgt.length ) {
            System.out.println(Arrays.toString(tgt));
            return;
        } 
        
        if( srcIndex < 0) return;
        
        tgt[tgtIndex] = src[srcIndex];
        byRecurRight(srcIndex-1, tgtIndex+1);
        byRecurRight(srcIndex-1, tgtIndex);

    }

    public static void byIter() {
        for (int i = 0; i < src.length; i++) {
            for (int j = i + 1; j < src.length; j++) {
                for (int k = j + 1; k < src.length; k++) {
                	tgt[0] = src[i];
                	tgt[1] = src[j];
                	tgt[2] = src[k];
                	System.out.println(Arrays.toString(tgt));
                }
            }
        }
    }

}
