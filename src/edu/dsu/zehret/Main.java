package edu.dsu.zehret;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n------- Insertion Sort --------");
        char[][] list = generateTestList(15, 8, 97, 122, System.currentTimeMillis());
        printList(list);
        insertionSort(list);
        validateSorting(list);
        printList(list);

        System.out.println("\n\n------- Merge Sort --------");
        list = generateTestList(15, 8, 97, 122, System.currentTimeMillis());
        printList(list);
        list = mergeSort(list);
        validateSorting(list);
        printList(list);

        System.out.println("\n\n------- Quick Sort --------");
        list = generateTestList(15, 8, 97, 122, System.currentTimeMillis());
        printList(list);
        quickSort(list, 0, list.length-1);
        validateSorting(list);
        printList(list);
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

    //Thanks Joe James : https://www.youtube.com/watch?v=Fiot5yuwPAg
   /* private static void quickSort(char[][] list) {
        Random r = new Random();

        int pivotIndex = r.nextInt(list.length);

        char[] pivotValue = list[pivotIndex];
        char[][] partitionLeft = new char[list.length][list[0].length], partitionRight = new char[list.length][list[0].length];

        while(list)

    }*/
    private static void quickSort(char[][] list, int indexLow, int indexHigh) {
        //If more than one in the range of indices
        if(indexLow < indexHigh+1) {
            int p = partition(list, indexLow, indexHigh);
        }
    }
    //Swap values in the array at i and j
    private static void swapValues(char[][] list, int i, int j) {
        char[] t = list[i];
        list[i] = list[j];
        list[j] = t;
    }

    private static int getPivotPointIndex(int indexLow, int indexHeight) {
        Random r = new Random();
        return r.nextInt(indexHeight - indexLow + 1) + indexLow; //Generate in range of indexLow - indexHigh
    }

    private static int partition(char[][] list, int indexLow, int indexHigh) {
        //Move pivot
        swapValues(list, indexLow, getPivotPointIndex(indexLow, indexHigh));
        //Next index
        int offset = 1;
        for(int i = indexLow+offset; i <=indexHigh; i++) {
            if(compare(list[i], list[indexLow]) == 1) {
                swapValues(list, i, offset);
                offset++;
            }
        }
        swapValues(list, indexLow, indexLow+offset-1);
        //Return index of pivot value;
        return indexLow+offset-1;
    }

    //Used pseudocode found here: https://en.wikipedia.org/wiki/Merge_sort
    private static char[][] merge(char[][] left, char[][] right) {
        char[][] result = new char[left.length + right.length][left[0].length];

        int leftIndex = 0, rightIndex = 0, resultIndex = 0;

        while((leftIndex < left.length) || (rightIndex < right.length)) {
            //Ensures something is there to merge.

            if((leftIndex < left.length) && (rightIndex < right.length)) {
                //Make sure both arrays have elements in them

                if(compare(left[leftIndex], right[rightIndex]) == 1) {
                    //if Left[leftIndex] is "<" right[rightIndex]
                    result[resultIndex] = left[leftIndex];

                    resultIndex++;
                    leftIndex++; 
                    //Increment to assign in next value of array
                } else {
                    result[resultIndex] = right[rightIndex];
                    resultIndex++;
                    rightIndex++;
                }               
            }
            else if(leftIndex < left.length) {
                //Only elements in the left array
                result[resultIndex] = left[leftIndex];
                resultIndex++;
                leftIndex++;
            }
            else if(rightIndex < right.length) {
                //Only elements in the right array
                result[resultIndex] = right[rightIndex];
                resultIndex++;
                rightIndex++;
            }
        }
        return result;
    }


    //Used pseudocode found here: https://en.wikipedia.org/wiki/Merge_sort
    private static char[][] mergeSort(char[][] list) {
        if(list.length <= 1) { return list; } //Recursive exit condition

        int middle = list.length/2;

        char[][] left = new char[middle][list[0].length];
        char[][] right;
        
        //If the list lenght is even, it splits nicely
        if(list.length % 2 == 0)
            right = new char[middle][list[0].length];
        else
            right = new char[middle+1][list[0].length];
            //If it's odd add an offset


        //Populate the "left" array
        for(int i=0; i < middle; i++) {
            left[i] = list[i];
        }

        //Populate the "right" array
        for(int j=0; j < right.length; j++) {
            right[j] = list[middle+j];
        }

        char[][] result = new char[list.length][list[0].length];
        //Recurse to split left again
        left = mergeSort(left);
        right = mergeSort(right);

        //Merge the results
        result = merge(left, right);
        return result;
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
        for(int i = 0; i < length-1; i++) {
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
        //System.out.println("Equal! : " + new String(list_n1) + ", " + new String(list_1));
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
            return;
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