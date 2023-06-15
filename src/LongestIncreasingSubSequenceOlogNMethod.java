public class LongestIncreasingSubSequenceOlogNMethod {
    // Metoda pomocnicza do znajdowania indeksu, który jest najmniejszym elementem większym lub równym s w tablicy input
    private int ceilIndex(int input[], int T[], int end, int s){
        int start = 0;
        int middle;
        int len = end;
        while(start <= end){
            middle = (start + end)/2;
            if(middle < len && input[T[middle]] < s && s <= input[T[middle+1]]){
                return middle+1;
            }else if(input[T[middle]] < s){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }
        return -1;
    }
    // Główna metoda znajdująca najdłuższy rosnący podciąg
    public int longestIncreasingSubSequence(int input[]){
        int T[] = new int[input.length]; // Tablica do przechowywania indeksów elementów tworzących obecnie znaleziony rosnący podciąg
        int R[] = new int[input.length]; // Tablica do śledzenia poprzedniego indeksu dla danego elementu, aby odtworzyć podciąg
        for(int i=0; i < R.length ; i++) {
            R[i] = -1;
        }
        T[0] = 0;
        int len = 0;
        for(int i=1; i < input.length; i++){
            if(input[T[0]] > input[i]){
                T[0] = i;
            }else if(input[T[len]] < input[i]){
                len++;
                T[len] = i;
                R[T[len]] = T[len-1];
            }else{
                int index = ceilIndex(input, T, len,input[i]);
                T[index] = i;
                R[T[index]] = T[index-1];
            }
        }
// Wypisanie najdłuższego rosnącego podciągu w odwróconej kolejności
        System.out.print("Najdłuższy rosnący podciąg: ");
        int index = T[len];
        while(index != -1) {
            System.out.print(input[index] + " ");
            index = R[index];
        }
        System.out.println();
        return len+1;
    }
    public static void main(String args[]){
        int input[] = {3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10};
        LongestIncreasingSubSequenceOlogNMethod lis = new LongestIncreasingSubSequenceOlogNMethod();
        System.out.println("Długość najdłuższego rosnącego podciągu: " + lis.longestIncreasingSubSequence(input));
    }
}