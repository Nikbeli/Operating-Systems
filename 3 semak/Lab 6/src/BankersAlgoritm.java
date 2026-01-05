public class BankersAlgoritm {

    int n = 5; // Количество процессов
    int m = 3; // Количество ресурсов

    int need[][] = new int[n][m]; // Матрица запросов
    int [][] max; // Матрица всех ресурсов
    int [][] allocation; // Матрица распределения
    int [] availabel; // Массив доступных ресурсов
    int safeSequence[] = new int[n]; // Порядок выполнения безопасных процессов

    void initializeValues() {
        allocation = new int[][] {
                { 0, 1, 0 },    // проц0
                { 2, 0, 0 },    // проц1
                { 3, 0, 2 },    // проц2
                { 2, 1, 1 },    // проц3
                { 0, 0, 2 }     // проц4
        };

        max = new int[][] {
                { 7, 5, 3 }, //проц0
                { 3, 2, 2 }, //проц1
                { 11, 0, 2 }, //проц2   (11!)
                { 2, 2, 2 }, //проц3
                { 4, 3, 3 }  //проц4
        };

        availabel = new int[] { 3, 3, 2 };
    }

    // Проверка на безопасность
    void isSafe() {
        int count = 0; // количество безопасных процессов

        // состояние системы
        // массив (не)безопасности процессов
        boolean visited[] = new boolean[n];

        // Сначала все небезопасны (т.к. пока не проверены)
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        // массив обработки (изначально равен массиву доступных ресурсов)
        int work[] = availabel;

        while (count < n) {
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if(visited[i] == false) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if(need[i][j] > work[j])
                            break;
                    }

                    if(j == m) {
                        safeSequence[count++] = i;
                        visited[i] = true;
                        flag = true;

                        for(j = 0; j < m; j++) {
                            work[j] = work[j] + allocation[i][j];
                        }
                    }
                }
            }
            if (flag == false)
                break;
        }

        if(count < n) {
            System.out.println("===================================================");
            System.out.println("!!! СИСТЕМА НЕБЕЗОПАСНА !!!");
            System.out.println("Количество забракованных процессов: " + (n - count));
            System.out.println("===================================================");
        } else {
            //System.out.println("The given System is Safe");
            System.out.println("===================================================");
            System.out.println("!!! СИСТЕМА БЕЗОПАСНА !!!");
            System.out.println("Безопасная последовательность действий:");

            for (int i = 0; i < n; i++) {
                System.out.print("P" + safeSequence[i]);
                if (i != n - 1)
                    System.out.print(" -> ");
            }

            System.out.println("\n===================================================");
        }
    }

    // Вычисление матрицы запросов
    // (матрица доступных ресурсов - матрица распределения)

    void calculateNeed() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    void PrintArray() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }
    }

    void PrintArrayMax() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(max[i][j] + " ");
            }
            System.out.println();
        }
    }
}