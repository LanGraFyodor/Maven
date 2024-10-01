package org.example;

import java.util.Random;
import java.util.Scanner;

public class ArrayMinAbsoluteValue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод количества элементов массива
        System.out.print("Введите количество элементов в массиве (n >= 0): ");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("Количество элементов не может быть отрицательным.");
            return;
        }

        // Создание массива
        int[] array = new int[n];

        // Заполнение массива случайными числами
        Random random = new Random();
        System.out.print("Массив: ");
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(201) - 100; // Генерация случайных чисел от -100 до 100
            System.out.print(array[i] + " ");
        }

        // Поиск минимального значения по модулю
        int minAbsValue = Integer.MAX_VALUE; // Максимальное возможное значение для int
        for (int value : array) {
            if (Math.abs(value) < Math.abs(minAbsValue)) {
                minAbsValue = value;
            } else if (Math.abs(value) == Math.abs(minAbsValue) && value < minAbsValue) {
                minAbsValue = value; // В случае одинаковых модулей, выбираем наименьшее значение
            }
        }

        // Вывод минимального значения по модулю
        System.out.println("\nМинимальное значение по модулю: " + minAbsValue);
    }
}
