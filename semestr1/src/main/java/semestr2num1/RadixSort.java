package semestr2num1;

import java.lang.*;
import java.io.FileWriter;
import java.io.IOException;

public class RadixSort {
    static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];}
        }

        for (int s = 1; max / s > 0; s *= 10){

            countingSortForRadix(arr, s);}


    }
    static void countingSortForRadix(int[] arr, int s) {
        int[] countingArray = {0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < arr.length; i++){
            countingArray[(arr[i] / s) % 10]++;
            count++;}

        for (int i = 1; i < 10; i++){
            countingArray[i] += countingArray[i - 1]; count++;}

        int[] outputArray = new int[100000];
        for (int i = arr.length - 1; i >= 0; i--){
            outputArray[--countingArray[(arr[i] / s) % 10]] = arr[i]; count++; }

        for (int i = 0; i < arr.length; i++){
            arr[i] = outputArray[i];}
    }
    private static int count = 0;
    //private static int count2 = 0;
    public static void main(String[] args) {
        int[] arr;

        try (FileWriter writer = new FileWriter("radix.csv")) {

            for (int i = 100; i < 100000; i += 1000) {
                arr = new int[i];
                for (int j = 0; j < i; ++j) {
                    arr[j] = (int) (Math.random() * i);
                }

                long t = System.nanoTime();
                count = 0;
                //count2 = 0;

                radixSort(arr);

                writer.write(i +  "  " + (System.nanoTime() - t) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        //radixSort(arr);



    }
}
