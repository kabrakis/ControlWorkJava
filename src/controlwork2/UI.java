package controlwork2;
import java.util.ArrayList;
import java.util.Scanner;


public class UI {
    private Counter_id counter;
    private Store store;
    private Lottery lottery;
    private ArrayList<Toy> toys;

    public void showStore(ArrayList<Toy> toys) {

        System.out.printf("%-5s%-20s%-15s%-22s\n", "Id", "Имя", "Количество", "Частота");
        for (Toy toy : toys)
            System.out.printf("%-5d%-20s%-15d%-22f\n", toy.getId(), toy.getName(), toy.getQuantity(), toy.getFrequency());

    }


    public void userMenu(Store store, Counter_id counter, ArrayList <Toy> toys) {
        boolean flag = true;
        while (flag) {
            System.out.println("\n         Выберите пункт меню\n" +
                    "1 - Показать список игрушек в магазине\n" +
                    "2 - Начать лоттерею\n" +
                    "3 - Поменять частоту игрушки по ID\n" +
                    "4 - Добавить игрушку в магазин\n" +
                    "5 - Удалить игрушку из магазина\n" +
                    "6 - Поменять параметры игрушки в магазине\n" +
                    "7 - Выход");

            Scanner in = new Scanner(System.in);

            String user_input = in.next();
            if (user_input.contains("1")) {
                System.out.println("\nигрушка в магазине:");
                showStore(store.getToys());
            } else if (user_input.contains("2")) {
                Lottery lottery = new Lottery(store.getToys());
                lottery.choiceOfPrizesRandom(store.getToys());
                Toy prizeToy = lottery.getPrizeToy();
                if (prizeToy != null) {
                    System.out.println("\nВы выиграли игрушку: " + prizeToy.getName() + '\n');
                }
            } else if (user_input.contains("3")) {
                System.out.println("Вставьте ID: ");
                int id = Integer.parseInt(in.next());
                System.out.println("Введите новую частоту игрушки: ");
                double newFrequency = Double.parseDouble(in.next());
                if (newFrequency > 0 && newFrequency < 100) {
                    if (!store.changeToyFrequency(id, newFrequency)) {
                        System.out.println("id не найден");
                    } else {
                        System.out.println("изменения частоты игрушки сохранены\n");
                    }
                } else {
                    System.out.println("Новая частота игрушки должен быть в промежутке от 0 до 100\n");
                }

            } else if (user_input.contains("4")) {

                System.out.println("Введите новое имя игрушки: ");
                String name = in.next();
                System.out.println("Введите новое колличество игрушки: ");
                int quantity = Integer.parseInt(in.next());
                boolean flag1 = true;
                System.out.println("Введите новую частоту игрушки: ");
                double frequency = Double.parseDouble(in.next());
                while (flag1) {
                    if (frequency > 0 && frequency < 100) {
                        flag1 = false;
                    } else {
                        System.out.println("Новая частота игрушки должен быть в промежутке от 0 до 100");
                        frequency = Double.parseDouble(in.next());
                    }
                }
                store.addToy(new Toy(counter.getId(), name, quantity, frequency));
                System.out.println("Новая игрушка добавлена ");

            }else if (user_input.contains("5")) {
                System.out.println("Введите имя игрушки которую вы хотите удалить: ");
                String name = in.next();
                store.deleteToyFromStore(toys, name);
            }else if (user_input.contains("6")){
                System.out.println("Введите имя игрушки для изменения их параметров: ");
                String name = in.next();
                System.out.println("Введите количество игрушек: ");
                int quantity = Integer.parseInt(in.next());
                boolean flag2 = true;
                System.out.println("Введите частоту игрушек: ");
                double frequency = Double.parseDouble(in.next());
                while (flag2) {
                    if (frequency > 0 && frequency < 100) {
                        flag2 = false;
                    } else {
                        System.out.println("Новая частота игрушки должен быть в промежутке от 0 до 100");
                        frequency = Double.parseDouble(in.next());
                    }
                }
                if(store.changeToy(name, quantity, frequency)){
                    System.out.println("параметры игрушки " + name + " были изменены" );
                }
                else{
                    System.out.println(name + " не найдено в магазине" );
                }

            }
            else {
                flag = false;

            }
        }

    }

}
