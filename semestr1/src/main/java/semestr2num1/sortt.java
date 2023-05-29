package semestr2num1;

public class sortt {
    public static void main(String[] args) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int[] arr = {3, 0, 1, 111, 9, 7, 5, 5, 3, 2, 1, 4, 3};
        int[] countingArray = new int[112];

        for(int i = 0; i < arr.length; i++) {
            countingArray[arr[i]]++;
            count1++;
        }

        for(int i=1; i < countingArray.length; i++) {
            countingArray[i] += countingArray[i - 1];
            count2++;
        }
        int[] outputArray = new int[14];
        for(int i = arr.length-1; i >= 0; i--){
            outputArray[countingArray[arr[i]] - 1] = arr[i];
            countingArray[arr[i]]--;
            count3++;
        }

        for(int i = 0; i < arr.length; i++){
            arr[i] = outputArray[i];
            System.out.print(arr[i] + " ");
        }
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);
    }
}
