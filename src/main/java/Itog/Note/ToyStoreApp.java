//package Itog.Note;
//
//import java.util.*;
//
//public class LaptopFilterApp {
//    private static final List<Laptop> laptops = new ArrayList<>();
//    private static final Map<Integer, String> filterCriteria = new HashMap<>();
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        //
//        printMainMenu(scanner);
//    }
//
//    /**
//     * Выводим основное меню с выбором действий
//     * @param scanner
//     */
//    private static void printMainMenu(Scanner scanner) {
//        while (true) {
//            System.out.println("Меню:");
//            System.out.println("1 - Задать количество ноутбуков.");
//            System.out.println("2 - Выходю.");
//            System.out.print("Выберите действие: ");
//            int choice = scanner.nextInt();
//
//            if (choice == 1) {
//                System.out.print("Введите количество ноутбуков: ");
//                int count = scanner.nextInt();
//
//                getRandomLaptop(count);
//
//                printMenu(scanner);
//
//                filterLaptopAndPrint();
//
//                clearList();
//            } else if (choice == 2) {
//                break;
//            }
//        }
//    }
//
//    /**
//     * Очищаем список ноутбуков и критерии фильтрации для следующего запуска программы
//     */
//    private static void clearList() {
//        laptops.clear();
//        filterCriteria.clear();
//    }
//
//    /**
//     * Фильтруем ноутбуки по заданным критериям и выводим результат
//     */
//    private static void filterLaptopAndPrint() {
//        List<Laptop> filteredLaptops = filterLaptops();
//        System.out.println("\nОтфильтрованные ноутбуки:");
//        for (Laptop laptop : filteredLaptops) {
//            System.out.println(laptop);
//        }
//    }
//
//    /**
//     * Выводим меню с выбором критерия фильтрации
//     * @param scanner Пронумерованное меню
//     */
//    private static void printMenu(Scanner scanner) {
//        while (true) {
//            System.out.println("\nМеню фильтрации:");
//            System.out.println("1 - Объем ЖД");
//            System.out.println("2 - Операционная система");
//            System.out.println("3 - Цвет");
//            System.out.println("4 - RAM");
//            System.out.print("Выберите критерий фильтрации (0 - Вывод результата!): ");
//            int filterChoice = scanner.nextInt();
//
//            if (filterChoice == 0) {
//                break;
//            }
//
//            if (filterChoice >= 1 && filterChoice <= 4) {
////                        System.out.print("Введите минимальное значение: ");
//                int min;
//                int max;
//
//                switch (filterChoice) {
//                    case 1:
//                        System.out.print("Введите минимальное значение (128 - 2048): ");
//                        min = scanner.nextInt();
//                        System.out.print("Введите максимальное значение (128 - 2048): ");
//                        max = scanner.nextInt();
//                        filterCriteria.put(filterChoice, min + "," + max);
//                        break;
//                    case 4:
//                        System.out.print("Введите минимальное значение (4 - 32): ");
//                        min = scanner.nextInt();
//                        System.out.print("Введите максимальное значение (4 - 32): ");
//                        max = scanner.nextInt();
//                        filterCriteria.put(filterChoice, min + "," + max);
//                        break;
//                    case 2:
//                    case 3:
//                        scanner.nextLine();
//                        System.out.print("Введите значения через запятую: ");
//                        String values = scanner.nextLine();
//                        filterCriteria.put(filterChoice, values);
//                        break;
//                }
//            }
//        }
//    }
//
//    /**
//     * Создаем заданное количество ноутбуков со случайными данными
//     * @param count Заполненный список
//     */
//    private static void getRandomLaptop(int count) {
//        for (int i = 0; i < count; i++) {
//            String ram = getRandomValue(Arrays.asList("4", "8", "16", "32"));
//            String storage = getRandomValue(Arrays.asList("128", "256", "512", "1024", "2048"));
//            String operatingSystem = getRandomValue(Arrays.asList("Linux", "MacOS", "Windows"));
//            String color = getRandomValue(Arrays.asList("Белый", "Черный", "Серебристый"));
//            laptops.add(new Laptop(ram, storage, operatingSystem, color));
//        }
//    }
//
//    private static <T> T getRandomValue(List<T> list) {
//        return getRandom(list);
//    }
//
//    /**
//     * @param list Создает и наполняет список случайными значениями
//     * @return Случайное из заданных значений
//     */
//    private static <T> T getRandom(List<T> list) {
//        Random random = new Random();
//        int index = random.nextInt(list.size());
//        return list.get(index);
//    }
//
//    private static List<Laptop> filterLaptops() {
//        return filterLapt();
//    }
//
//    /**
//     * Метод для фильтрации списка ноутбуков по заданным критериям
//     * @return Отфильтрованные ноутбуки
//     */
//    private static List<Laptop> filterLapt() {
//        List<Laptop> filteredList = new ArrayList<>();
//
//        for (Laptop laptop : laptops) {
//            boolean isValid = true;
//
//            for (Map.Entry<Integer, String> entry : LaptopFilterApp.filterCriteria.entrySet()) {
//                int filterType = entry.getKey();
//                String filterValue = entry.getValue();
//
//                if (!laptop.matchesCriteria(filterType, filterValue)) {
//                    isValid = false;
//                    break;
//                }
//            }
//
//            if (isValid) {
//                filteredList.add(laptop);
//            }
//        }
//
//        return filteredList;
//    }
//}
//
//class Laptop {
//    private final String ram;
//    private final int storage;
//    private final String operatingSystem;
//    private final String color;
//
//    public Laptop(String ram, String storage, String operatingSystem, String color) {
//        this.ram = ram;
//        this.storage = Integer.parseInt(storage);
//        this.operatingSystem = operatingSystem;
//        this.color = color;
//    }
//
//    public boolean matchesCriteria(int filterType, String filterValue) {
//        return addCriterias(filterType, filterValue);
//    }
//
//    /**
//     * @param filterValue Метод обработки входных данных от пользователя
//     * @return Результат обработки
//     */
//    private boolean addCriterias(int filterType, String filterValue) {
//        switch (filterType) {
//            case 1:
//                String[] range1 = filterValue.split(",");
//                int min1 = Integer.parseInt(range1[0]);
//                int max1 = Integer.parseInt(range1[1]);
//                return (storage >= min1 && storage <= max1);
//            case 2:
//                String[] values2 = filterValue.split(",");
//                return Arrays.asList(values2).contains(operatingSystem);
//            case 3:
//                String[] values3 = filterValue.split(",");
//                return Arrays.asList(values3).contains(color);
//            case 4:
//                String[] range4 = filterValue.split(",");
//                int min4 = Integer.parseInt(range4[0]);
//                int max4 = Integer.parseInt(range4[1]);
//                return (Integer.parseInt(ram) >= min4 && Integer.parseInt(ram) <= max4);
//            default:
//                return false;
//        }
//    }
//
//    public String toString() {
//        return "Ноутбук {" +
//                "Оперативная память = " + ram +
//                ", Память накопителя = " + storage +
//                ", Операционная систама = " + operatingSystem +
//                ", Цвет = " + color + "}";
//    }
//}
package Itog.Note;

import java.io.*;
import java.util.*;

public class ToyStoreApp {

    private static final String FILE_PATH = "toys.csv";
    private static final String WINNERS_FILE_PATH = "winners.csv";

    private static final List<Toy> toyStore = new ArrayList<>();
    private static final List<Toy> winners = new ArrayList<>();

    public static void main(String[] args) {
        loadToyStore();

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Меню магазина игрушек ===");
            System.out.println("1. Разыграть игрушки");
            System.out.println("2. Посмотреть выигранные игрушки");
            System.out.println("3. Добавить игрушки");
            System.out.println("4. Посмотреть наличие игрушек в магазине");
            System.out.println("5. Изменить количество игрушек в магазине");
            System.out.println("6. Изменить вероятность выпадения игрушки");
            System.out.println("7. Выход");

            System.out.print("Выберети действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playWithToys(scanner);
                    break;
                case 2:
                    viewWinningToys();
                    break;
                case 3:
                    addToys(scanner);
                    break;
                case 4:
                    viewToyStoreStock();
                    break;
                case 5:
                    changeToyQuantity(scanner);
                    break;
                case 6:
                    changeToyProbability(scanner);
                    break;
                case 7:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Такого пункта нет, попробуйте еще раз!");
            }
        }

        saveToyStore();
        saveWinningToys();
    }

    private static void loadToyStore() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("; ");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] fields = line.split("; ");
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    int quantity = Integer.parseInt(fields[2]);
                    double probability = Double.parseDouble(fields[3]);
                    toyStore.add(new Toy(id, name, quantity, probability));
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading toy store: " + e.getMessage());
        }
    }

    private static void saveToyStore() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH));
            for (Toy toy : toyStore) {
                writer.println(toy.getId() + "; " + toy.getName() + "; " + toy.getQuantity() + "; " + toy.getProbability());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private static void saveWinningToys() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(WINNERS_FILE_PATH));
            for (Toy toy : winners) {
                writer.println(toy.getId() + "; " + toy.getName());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка сохранения выигранных игрушек: " + e.getMessage());
        }
    }

    private static void playWithToys(Scanner scanner) {
        Toy chosenToy = getRandomToy();
        if (chosenToy != null) {
            System.out.println("Поздравляем! Вы выиграли " + chosenToy.getName() + "!");
            chosenToy.decreaseQuantity();
            winners.add(chosenToy);
        } else {
            System.out.println("Таких игрушек больше нет или они закончились!");
        }
        System.out.println("Нажмите enter чтобы продолжить...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static void viewWinningToys() {
        System.out.println("\n=== Выигранные игрушки ===");
        for (Toy toy : winners) {
            System.out.println(toy.getId() + ": " + toy.getName());
        }
        System.out.println("Нажмите enter чтобы продолжить...");
        new Scanner(System.in).nextLine();
    }

    private static void addToys(Scanner scanner) {
        boolean addMoreToys = true;
        while (addMoreToys) {
            System.out.print("\nВведите название игрушки: ");
            String name = scanner.next();
            System.out.print("Введите количество игрушек: ");
            int quantity = scanner.nextInt();
            System.out.print("Введите выроятность выпадения игрушки: ");
            double probability = scanner.nextDouble();

            Toy toy = new Toy(getNextToyId(), name, quantity, probability);
            toyStore.add(toy);

            System.out.print("\nХотите добавить еще игрушку? (да/нет): ");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("да")) {
                addMoreToys = false;
            }
        }
    }

    private static void viewToyStoreStock() {
        System.out.println("\n=== Витрина магазина ===");
        for (Toy toy : toyStore) {
            System.out.println(toy.getId() + ": " + toy.getName() + " - Количество: " + toy.getQuantity() + ", Вероятность: " + toy.getProbability());
        }
        System.out.println("Нажмите enter чтобы продолжить...");
        new Scanner(System.in).nextLine();
    }

    private static void changeToyQuantity(Scanner scanner) {
        System.out.print("\nВведите ID игрушки: ");
        int id = scanner.nextInt();
        Toy toy = getToyById(id);
        if (toy != null) {
            System.out.print("Введите новое количество игрушек: ");
            int newQuantity = scanner.nextInt();
            toy.setQuantity(newQuantity);
            System.out.println("Количество игрушек успешно обновлено!");
        } else {
            System.out.println("Игрушка не найдена!");
        }
        System.out.println("Нажмите enter чтобы продолжить...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static void changeToyProbability(Scanner scanner) {
        System.out.print("\nВведите ID игрушки: ");
        int id = scanner.nextInt();
        Toy toy = getToyById(id);
        if (toy != null) {
            System.out.print("Введите новую вероятность выпадения игрушки: ");
            double newProbability = scanner.nextDouble();
            toy.setProbability(newProbability);
            System.out.println("Вероятность выпадения игрушки успешно обновлена!");
        } else {
            System.out.println("Игрушка не найдена!");
        }
        System.out.println("Нажмите enter чтобы продолжить...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static Toy getRandomToy() {
        List<Toy> availableToys = new ArrayList<>();
        for (Toy toy : toyStore) {
            if (toy.getQuantity() > 0) {
                availableToys.add(toy);
            }
        }
        if (!availableToys.isEmpty()) {
            double totalProbability = 0;
            for (Toy toy : availableToys) {
                totalProbability += toy.getProbability();
            }
            double randomNumber = Math.random() * totalProbability;

            double cumulativeProbability = 0;
            for (Toy toy : availableToys) {
                cumulativeProbability += toy.getProbability();
                if (randomNumber <= cumulativeProbability) {
                    return toy;
                }
            }
        }
        return null;
    }

    private static Toy getToyById(int id) {
        for (Toy toy : toyStore) {
            if (toy.getId() == id) {
                return toy;
            }
        }
        return null;
    }

    private static int getNextToyId() {
        int nextId = 1;
        for (Toy toy : toyStore) {
            if (toy.getId() >= nextId) {
                nextId = toy.getId() + 1;
            }
        }
        return nextId;
    }

}

class Toy {
    private final int id;
    private final String name;
    private int quantity;
    private double probability;

    public Toy(int id, String name, int quantity, double probability) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.probability = probability;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public void decreaseQuantity() {
        quantity--;
    }
}