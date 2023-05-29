package semestr2num2;



import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        try (FileWriter writer = new FileWriter("seg.csv")){

            // Генерация массива из случайной последовательности 10000 целых чисел
            int[] array = new int[10000];
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt();
            }

            // Создание дерева отрезков и замер времени и количества операций для каждого добавления
            SegmentTree segmentTree = new SegmentTree(array, array.length);
            long totalInsertionTime = 0;
            long totalInsertionOperations = 0;

            for (int i = 0; i < array.length; i++) {
                segmentTree.count = 0;
                int randomValue = random.nextInt(array.length);
                long startTime = System.nanoTime();
                segmentTree.insertVal(array, array.length, i, randomValue);
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;
                totalInsertionTime += elapsedTime;
                int count = segmentTree.getCount();
                totalInsertionOperations += count;
                writer.write((i+1) + " Insertion " + array[i] + ": Time = " + elapsedTime + " ns, Operations = " + count + "\n");
            }

            // Выбор случайных 100 элементов и поиск их в структуре, замер времени и количества операций для каждого поиска
            long totalSearchTime = 0;
            long totalSearchOperations = 0;

            for (int i = 0; i < 100; i++) {
                segmentTree.count = 0;
                int randomIndex = random.nextInt(array.length);
                long startTime = System.nanoTime();
                segmentTree.searchVal(array.length, array[randomIndex]);
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;
                totalSearchTime += elapsedTime;
                int count = segmentTree.getCount();
                totalSearchOperations += count;
                writer.write((i+1) + " Search " + array[i] + ": Time = " + elapsedTime + " ns, Operations = " + count + "\n");
            }

// Выбор случайных 1000 элементов и удаление их из структуры, замер времени и количества операций для каждого удаления
            long totalDeletionTime = 0;
            long totalDeletionOperations = 0;

            for (int i = 0; i < 1000; i++) {
                segmentTree.count = 0;
                int randomIndex = random.nextInt(array.length);
                long startTime = System.nanoTime();
                segmentTree.deleteVal(array.length, randomIndex);
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;
                totalDeletionTime += elapsedTime;
                int count = segmentTree.getCount();
                totalDeletionOperations += count;
                writer.write((i+1) + " Deletion " + array[i] + ": Time = " + elapsedTime + " ns, Operations = " + count + "\n");
            }

            // Вычисление среднего времени и количества операций для вставки, поиска и удаления данных
            double averageInsertionTime = (double) totalInsertionTime / totalInsertionOperations;
            double averageSearchTime = (double) totalSearchTime / totalSearchOperations;
            double averageDeletionTime = (double) totalDeletionTime / totalDeletionOperations;



            writer.write("Average Insertion Time: " + totalInsertionTime/10000 + " ns, Operations: " + totalInsertionOperations/10000 + "\n");
            writer.write("Average Search Time: " + totalSearchTime/100 + " ns, Operations: " + totalSearchOperations/100 + "\n");
            writer.write("Average Deletion Time: " + totalDeletionTime/1000 + " ns, Operations: " + totalDeletionOperations/1000 + "\n");
        }  catch (IOException e) {
            e.printStackTrace();

        }
    }}
