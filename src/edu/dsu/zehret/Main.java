package edu.dsu.zehret;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        while(i < 10) {
            char[][] list = generateTestList(300, 100, 97, 122, System.currentTimeMillis());
            //printList(list);
            insertionSort(list);
            validateSorting(list);
            //printList(list);
        }
    }

    private static char[][] generateTestList(int N, int k, int minV, int maxV, long seed) {
        if(minV > maxV) {
            //Switch
            int temp = minV;
            minV = maxV;
            maxV = temp;
        }
        Random r = new Random(seed);
        char[][] list = new char[N][k+1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < k; j++) {
                list[i][j] = (char) (r.nextInt(maxV - minV + 1) + minV);
            }
            list[i][k] = 0;
        }
        return list;
    }

    private static void quickSort(char[][] list) {

    }


    //Used pseudocode found here: https://en.wikipedia.org/wiki/Merge_sort
    private static void mergeSort(char[][] list) {

    }

    //Used pseudocode found here https://en.wikipedia.org/wiki/Insertion_sort
    private static void insertionSort(char[][] list) {
        System.out.println("Insertion Sort...");
        int i = 1;
        while (i < list.length) {
            char[] x = list[i];
            int j = i - 1;
            //See compare function for explanation
            while (j >= 0 && compare(list[j], x) == -1) {
                list[j+1] = list[j];
                j = j - 1;
            }
            list[j+1] = x;
            i = i + 1;
        }
        System.out.println("Done.");
    }
    private static int compare(char[] list_n1, char[] list_1) {
                        // Are the lengths equal        /T: set to one of them   /F: compare, which is lesser     /Set as the lesser one's length
        int length = (list_n1.length == list_1.length) ? (list_n1.length) : (list_n1.length > list_1.length) ? list_1.length : list_n1.length;
        for(int i = 0; i < length; i++) {
            //If they are equal we must advance to the next letter
            if(list_n1[i] == list_1[i]) continue;
            // List n1 (-1)'s character has a bigger ascii code, return -1
            if((int)list_n1[i] > (int)list_1[i]) {
                return -1;
            } else {
                //List 1's character has a bigger ascii code, return 1
                return 1;
            }
        }
        //list's are identical
        System.out.println("Equal! : " + new String(list_n1) + ", " + new String(list_1));
        return 0;
    }

    private static void validateSorting(char[][] list) {
        System.out.println("Validating Sorting...");
        for(int i = 1; i < list.length; i++) {
            int compareResult = compare(list[i], list[i-1]);
            if((compareResult == 0) || (compareResult == -1)) {
                continue;
            }
            System.out.println("List is not sorted at indices " + (i-1) +", " + i);
            break;
        }
        System.out.println("List sort validation complete.");
        System.out.println("List is sorted.");
    }

    private static void printList(char[][] list) {
        for(int i = 0; i < list.length; i++) {
            System.out.print("["+i+"]\"");
            for(int j = 0; j < list[i].length; j++) {
                System.out.print((char)list[i][j]);
            }
            System.out.println("\"");
        }
        System.out.println("");
    }

    private static int sum(char[] chars) {
        int total = 0;
        for(char ch : chars) {
            total+=(int)ch;
        }
        return total;
    }
}