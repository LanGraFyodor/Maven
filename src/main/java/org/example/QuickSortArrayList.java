package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuickSortArrayList {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов в списке (n >= 0): ");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("Количество элементов не может быть отрицательным.");
            return;
        }

        ArrayList<Double> list = new ArrayList<>();
        Random random = new Random();
        System.out.print("Исходный список: ");
        for (int i = 0; i < n; i++) {
            double value = random.nextDouble() * 200 - 100; // Генерация случайных чисел от -100.0 до 100.0
            list.add(value);
            System.out.print(value + " ");
        }

        quickSort(list, 0, list.size() - 1);

        System.out.print("\nОтсортированный список: ");
        for (double value : list) {
            System.out.print(value + " ");
        }
    }

    private static void quickSort(ArrayList<Double> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static int partition(ArrayList<Double> list, int low, int high) {
        double pivot = list.get(high);  // Выбираем последний элемент как опорный (pivot)
        int i = low - 1;  // Индекс наименьшего элемента

        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                // Меняем местами элементы
                double temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        // Меняем местами опорный элемент с элементом, на который указывает i+1
        double temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}
