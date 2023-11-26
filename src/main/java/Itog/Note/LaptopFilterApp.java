package Itog.Note;

import java.util.*;

public class LaptopFilterApp {
    private static final List<Laptop> laptops = new ArrayList<>();
    private static final Map<Integer, String> filterCriteria = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //
        printMainMenu(scanner);
    }

    /**
     * Выводим основное меню с выбором действий
     * @param scanner
     */
    private static void printMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("Меню:");
            System.out.println("1 - Задать количество ноутбуков.");
            System.out.println("2 - Выходю.");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Введите количество ноутбуков: ");
                int count = scanner.nextInt();

                getRandomLaptop(count);

                printMenu(scanner);

                filterLaptopAndPrint();

                clearList();
            } else if (choice == 2) {
                break;
            }
        }
    }

    /**
     * Очищаем список ноутбуков и критерии фильтрации для следующего запуска программы
     */
    private static void clearList() {
        laptops.clear();
        filterCriteria.clear();
    }

    /**
     * Фильтруем ноутбуки по заданным критериям и выводим результат
     */
    private static void filterLaptopAndPrint() {
        List<Laptop> filteredLaptops = filterLaptops();
        System.out.println("\nОтфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    /**
     * Выводим меню с выбором критерия фильтрации
     * @param scanner Пронумерованное меню
     */
    private static void printMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nМеню фильтрации:");
            System.out.println("1 - Объем ЖД");
            System.out.println("2 - Операционная система");
            System.out.println("3 - Цвет");
            System.out.println("4 - RAM");
            System.out.print("Выберите критерий фильтрации (0 - Вывод результата!): ");
            int filterChoice = scanner.nextInt();

            if (filterChoice == 0) {
                break;
            }

            if (filterChoice >= 1 && filterChoice <= 4) {
//                        System.out.print("Введите минимальное значение: ");
                int min;
                int max;

                switch (filterChoice) {
                    case 1:
                        System.out.print("Введите минимальное значение (128 - 2048): ");
                        min = scanner.nextInt();
                        System.out.print("Введите максимальное значение (128 - 2048): ");
                        max = scanner.nextInt();
                        filterCriteria.put(filterChoice, min + "," + max);
                        break;
                    case 4:
                        System.out.print("Введите минимальное значение (4 - 32): ");
                        min = scanner.nextInt();
                        System.out.print("Введите максимальное значение (4 - 32): ");
                        max = scanner.nextInt();
                        filterCriteria.put(filterChoice, min + "," + max);
                        break;
                    case 2:
                    case 3:
                        scanner.nextLine();
                        System.out.print("Введите значения через запятую: ");
                        String values = scanner.nextLine();
                        filterCriteria.put(filterChoice, values);
                        break;
                }
            }
        }
    }

    /**
     * Создаем заданное количество ноутбуков со случайными данными
     * @param count Заполненный список
     */
    private static void getRandomLaptop(int count) {
        for (int i = 0; i < count; i++) {
            String ram = getRandomValue(Arrays.asList("4", "8", "16", "32"));
            String storage = getRandomValue(Arrays.asList("128", "256", "512", "1024", "2048"));
            String operatingSystem = getRandomValue(Arrays.asList("Linux", "MacOS", "Windows"));
            String color = getRandomValue(Arrays.asList("Белый", "Черный", "Серебристый"));
            laptops.add(new Laptop(ram, storage, operatingSystem, color));
        }
    }

    private static <T> T getRandomValue(List<T> list) {
        return getRandom(list);
    }

    /**
     * @param list Создает и наполняет список случайными значениями
     * @return Случайное из заданных значений
     */
    private static <T> T getRandom(List<T> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
    
    private static List<Laptop> filterLaptops() {
        return filterLapt();
    }

    /**
     * Метод для фильтрации списка ноутбуков по заданным критериям
     * @return Отфильтрованные ноутбуки
     */
    private static List<Laptop> filterLapt() {
        List<Laptop> filteredList = new ArrayList<>();

        for (Laptop laptop : laptops) {
            boolean isValid = true;

            for (Map.Entry<Integer, String> entry : LaptopFilterApp.filterCriteria.entrySet()) {
                int filterType = entry.getKey();
                String filterValue = entry.getValue();

                if (!laptop.matchesCriteria(filterType, filterValue)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                filteredList.add(laptop);
            }
        }

        return filteredList;
    }
}

class Laptop {
    private final String ram;
    private final int storage;
    private final String operatingSystem;
    private final String color;

    public Laptop(String ram, String storage, String operatingSystem, String color) {
        this.ram = ram;
        this.storage = Integer.parseInt(storage);
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public boolean matchesCriteria(int filterType, String filterValue) {
        return addCriterias(filterType, filterValue);
    }

    /**
     * @param filterValue Метод обработки входных данных от пользователя
     * @return Результат обработки
     */
    private boolean addCriterias(int filterType, String filterValue) {
        switch (filterType) {
            case 1:
                String[] range1 = filterValue.split(",");
                int min1 = Integer.parseInt(range1[0]);
                int max1 = Integer.parseInt(range1[1]);
                return (storage >= min1 && storage <= max1);
            case 2:
                String[] values2 = filterValue.split(",");
                return Arrays.asList(values2).contains(operatingSystem);
            case 3:
                String[] values3 = filterValue.split(",");
                return Arrays.asList(values3).contains(color);
            case 4:
                String[] range4 = filterValue.split(",");
                int min4 = Integer.parseInt(range4[0]);
                int max4 = Integer.parseInt(range4[1]);
                return (Integer.parseInt(ram) >= min4 && Integer.parseInt(ram) <= max4);
            default:
                return false;
        }
    }

    public String toString() {
        return "Ноутбук {" +
                "Оперативная память = " + ram +
                ", Память накопителя = " + storage +
                ", Операционная систама = " + operatingSystem +
                ", Цвет = " + color + "}";
    }
}