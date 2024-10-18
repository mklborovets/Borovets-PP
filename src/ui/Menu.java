package ui;

import droids.*;
import battleLogic.Battle;
import Logger.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.Colors.*;


public class Menu{
    private List <Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);


    public void showMenu(){
        boolean exit = false;

        while(!exit){
            System.out.println(PURPLE+"===--==--==--==| Menu |==--==--==--===");
            System.out.println("1)Створити дроїда");
            System.out.println("2)Показати список створених дроїдів");
            System.out.println("3)Запустити бій 1 на 1");
            System.out.println("4)Запустити бій команда на команду");
            System.out.println("5)Записати бій у файл");
            System.out.println("6)Відтворити бій зі збереженого файлу");
            System.out.println("7)Вийти з гри");
            System.out.println("===--==--==--==| Menu |==--==--==--===\t"+RESET);
            System.out.println("Введіть від 1 до 7: ");

            int choise = 0;

            if(scanner.hasNextInt()) {
                choise = scanner.nextInt();
            }
            scanner.nextLine();

            switch(choise) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    battleOneOnOne();
                    break;
                case 4:
                    boolean exit2 = false;
                    while (!exit2) {
                        System.out.println("Який маштаб битви: ");
                        System.out.println("1)5 на 5: ");
                        System.out.println("2)4 на 4: ");
                        System.out.println("3)3 на 3: ");
                        System.out.println("4)2 на 2: ");
                        System.out.println("5)Кастомна битва: ");
                        int battleSize = 0;
                        if (scanner.hasNextInt()) {
                            battleSize = scanner.nextInt();
                        }
                        scanner.nextLine();

                        switch (battleSize) {
                            case 1:
                                teamBattle(5, 5);
                                exit2 = true;
                                break;
                            case 2:
                                teamBattle(4, 4);
                                exit2 = true;
                                break;
                            case 3:
                                teamBattle(3, 3);
                                exit2 = true;
                                break;
                            case 4:
                                teamBattle(2, 2);
                                exit2 = true;
                                break;
                            case 5:
                                while(true){
                                    System.out.println("Кількість дроїдів у команді Alpha: ");
                                    int teamAlphaSize =0;
                                    if (scanner.hasNextInt()) {
                                        teamAlphaSize = scanner.nextInt();
                                    }
                                    else continue;
                                    scanner.nextLine();

                                    System.out.println("Кількість дроїдів у команді Bravo: ");
                                    int teamBravoSize =0;
                                    if (scanner.hasNextInt()) {
                                        teamBravoSize = scanner.nextInt();
                                    }
                                    else continue;
                                    scanner.nextLine();
                                    teamBattle(teamAlphaSize,teamBravoSize);
                                    break;
                                }
                                exit2 = true;
                                break;
                            default:
                                System.out.println("Схоже ви ввели не вірні дані, спробуйте знову\n");
                        }
                    }
                    break;
                case 5:
                    BattleLogger.saveToFile("battle_log.txt");
                    break;
                case 6:
                    BattleReplay replay = new BattleReplay();
                    replay.replayFromFile("battle_log.txt");
                    break;
                case 7:
                    exit = true;
                    System.out.println("Вихід з гри. Гарного дня!");
                    break;
                default:
                    System.out.println("Схоже ви ввели не вірні дані, спробуйте знову\n");
            }

        }
    }

    private void createDroid(){

        while(true) {
            System.out.println("Виберіть унікальність дроїда: ");
            System.out.println("1)Створити 4 дроїди(вогневик, морозник, електро, непробивний)");
            System.out.println("2)Створити 5 дроїдів(всі типи)");
            System.out.println("3)Створити 10 дроїдів(всі типи на 2 команди)");
            System.out.println("4)Вогневик");
            System.out.println("5)Морозник");
            System.out.println("6)Електро");
            System.out.println("7)Вибуховник");
            System.out.println("8)Непробивний");
            int choiseD = 0;
            while (true) {
                if (scanner.hasNextInt()) {
                    choiseD = scanner.nextInt();
                    if (choiseD <= 0 || choiseD > 8) {
                        System.out.println("Схоже ви ввели не вірні дані, спробуйте знову");
                        continue;
                    }
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Ви ввели не ціле число, спробуйте знову\n");
                }
                scanner.nextLine();
            }

            if(choiseD==1){
                droids.add(new PyroDroid("EmberStorm"));
                droids.add(new FrostDroid("ScorchTitan"));
                droids.add(new ElectroDroid("ChillBringer"));
                droids.add(new IronDroid("GlacierFang"));
                return;
            }
            if(choiseD==2){
                droids.add(new PyroDroid("InfernoBlaze"));
                droids.add(new FrostDroid("Frostbite"));
                droids.add(new ElectroDroid("Shockwave"));
                droids.add(new Demolitionist("ExplosiveAce"));
                droids.add(new IronDroid("Fortress"));
                return;
            }
            if(choiseD==3){
                droids.add(new PyroDroid("InfernoBlaze"));
                droids.add(new FrostDroid("Frostbite"));
                droids.add(new ElectroDroid("Shockwave"));
                droids.add(new Demolitionist("ExplosiveAce"));
                droids.add(new IronDroid("Fortress"));

                droids.add(new PyroDroid("FlameCrusher"));
                droids.add(new FrostDroid("IceGuardian"));
                droids.add(new ElectroDroid("ThunderPulse"));
                droids.add(new Demolitionist("Wreckage"));
                droids.add(new IronDroid("SteelBehemoth"));
                return;
            }

            System.out.println("Введіть ім'я дроїда");
            String name = scanner.nextLine();
            switch (choiseD) {
                case 4:
                    droids.add(new PyroDroid(name));
                    System.out.println("Ви хочете використати рекомендовані характеристики?(1-ні, так-2):");
                    int recommended = scanner.nextInt();
                    if(recommended == 1){
                        droids.getLast().setCharacteristics();
                    }
                    break;
                case 5:
                    droids.add(new FrostDroid(name));
                    System.out.println("Ви хочете використати рекомендовані характеристики?(1-ні, так-2):");
                    int recommended2 = scanner.nextInt();
                    if(recommended2 == 1){
                        droids.getLast().setCharacteristics();
                    }
                    break;
                case 6:
                    droids.add(new ElectroDroid(name));
                    System.out.println("Ви хочете використати рекомендовані характеристики?(1-ні, так-2):");
                    int recommended3 = scanner.nextInt();
                    if(recommended3 == 1){
                        droids.getLast().setCharacteristics();
                    }
                    break;
                case 7:
                    droids.add(new Demolitionist(name));
                    System.out.println("Ви хочете використати рекомендовані характеристики?(1-ні, так-2):");
                    int recommended4 = scanner.nextInt();
                    if(recommended4 == 1){
                        droids.getLast().setCharacteristics();
                    }
                    break;
                case 8:
                    droids.add(new IronDroid(name));
                    System.out.println("Ви хочете використати рекомендовані характеристики?(1-ні, так-2):");
                    int recommended5 = scanner.nextInt();
                    if(recommended5 == 1){
                        droids.getLast().setCharacteristics();
                    }
                    break;

                default:
                    System.out.println("Схоже ви ввели не вірні дані, спробуйте знову\n");
            }

            System.out.println("Бажаєте добавити ще дроїдів?(так-1, ні-2)");
            int choiseEND = 0;
            while (true) {
                if (scanner.hasNextInt()) {
                    choiseEND = scanner.nextInt();
                    if (choiseEND < 1 || choiseEND > 2) {
                        System.out.println("Схоже ви ввели не вірні дані, спробуйте знову");
                        continue;
                    }
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Ви ввели не ціле число, спробуйте знову\n");
                }
                scanner.nextLine();

            }
            if(choiseEND==2){
                break;
            }
        }
    }


    private void showDroids(){
        if(droids.isEmpty()){
            System.out.println("Дроїди ще не створені!\n");
        }
        else{
            for(int i = 0;i<droids.size();i++){
                    System.out.print((i+1) +":");
                    droids.get(i).information();
            }
        }


    }

    private void showDroidsAvaible(List<Droid> availableDroids) {
        if (availableDroids.isEmpty()) {
            System.out.println("Дроїди ще не створені або всі вже вибрані!\n");
        } else {
            for (int i = 0; i < availableDroids.size(); i++) {
                System.out.print((i + 1) + ": ");
                availableDroids.get(i).information();
            }
        }
    }

    private void battleOneOnOne(){
        if(droids.size()<2){
            System.out.println("Недостатньо дроїдів для битви(необхідно 2 дроїди)");
            return;
        }


        System.out.println("Виберіть першого дроїда: ");
        showDroids();
        int droid1Index = scanner.nextInt()-1;

        System.out.println("Виберіть другого дроїда ");
        showDroids();
        int droid2Index = scanner.nextInt()-1;

        Battle.battleOneOnOne(droids.get(droid1Index), droids.get(droid2Index));
    }

    private void teamBattle(int sizeA, int sizeB){
        if (droids.size() < 4) {
            System.out.println("Необхідно принаймні 4 дроїдів.");
            return;
        }

        List<Droid> availableDroids = new ArrayList<>(droids);
        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();


        System.out.println("Виберіть дроїдів для команди Alpha:");
        for (int i = 0; i < sizeA; i++) {
            showDroidsAvaible(availableDroids);
            int choice = scanner.nextInt() - 1;
            team1.add(availableDroids.get(choice));
            availableDroids.remove(choice);
        }

        System.out.println("Виберіть дроїдів для команди Bravo:");
        for (int i = 0; i < sizeB; i++) {
            showDroidsAvaible(availableDroids);
            int choice = scanner.nextInt() - 1;
            team2.add(availableDroids.get(choice));
            availableDroids.remove(choice);
        }

        Battle.teamBattle(team1, team2, sizeA, sizeB);
    }

}
