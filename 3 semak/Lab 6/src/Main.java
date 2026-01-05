public class Main {
    public static void main(String[] args) {
        BankersAlgoritm lab6 = new BankersAlgoritm();

        lab6.initializeValues();

        // Calculate the Need Matrix
        lab6.calculateNeed();

        // Вывод на экран
        lab6.PrintArray();

        System.out.println();

        lab6.PrintArrayMax();

        // Check whether system is in safe state or not
        lab6.isSafe();
    }
}