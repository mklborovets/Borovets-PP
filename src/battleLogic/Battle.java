package battleLogic;
import Logger.BattleLogger;
import droids.Droid;
import static ui.Colors.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Battle {

    private static BattleLogger battleLogger = new BattleLogger();

    public static void battleOneOnOne(Droid droid1, Droid droid2){

        System.out.println(PURPLE+"==--==--==--==--==--==--==--==--==");
        System.out.println("\t\tБІЙ ПОЧИНАЄТЬСЯ!");
        System.out.println("\n  "+droid1.getName()+" vs "+droid2.getName());
        System.out.println("==--==--==--==--==--==--==--==--==");

        System.out.println("\t\tСклад команд:");
        System.out.println("\tAlpha\t\t\tBravo");

        battleLogger.logEvent("==--==--==--==--==--==--==--==--==");
        battleLogger.logEvent("\t\tРеплей битви!");
        battleLogger.logEvent("\n  "+droid1.getName()+" vs "+droid2.getName());
        battleLogger.logEvent("==--==--==--==--==--==--==--==--=="+RESET);


        int i =1;

        Random rand = new Random();
        boolean isFirstDroidTurn = rand.nextBoolean();

        if(isFirstDroidTurn){
            System.out.println(droid1.getName()+" атакує першим \n");
            battleLogger.logEvent(droid1.getName()+" атакує першим \n");
        }
        else{
            System.out.println(droid2.getName()+" атакує першим \n");
            battleLogger.logEvent(droid2.getName()+" атакує першим \n");
        }

        while(droid1.isAlive() && droid2.isAlive()){
            System.out.println("Раунд "+i);
            battleLogger.logEvent("Раунд "+i);
            if (isFirstDroidTurn) {
                droid1.attack(droid2);
                if (!droid2.isAlive()) {
                    System.out.println(PURPLE+"==--==--==--==--==--==--==--==--==");
                    battleLogger.logEvent("==--==--==--==--==--==--==--==--==");
                    System.out.println(droid1.getName()+" переміг!");
                    battleLogger.logEvent(droid1.getName()+" переміг!");
                    System.out.println("==--==--==--==--==--==--==--==--==");
                    battleLogger.logEvent("==--==--==--==--==--==--==--==--=="+RESET);
                    break;
                }
            }
            else {
                droid2.attack(droid1);
                if (!droid1.isAlive()) {
                    System.out.println(PURPLE+"==--==--==--==--==--==--==--==--==");
                    battleLogger.logEvent("==--==--==--==--==--==--==--==--==");
                    System.out.println(droid2.getName()+" переміг!");
                    battleLogger.logEvent(droid2.getName()+" переміг!");
                    System.out.println("==--==--==--==--==--==--==--==--==");
                    battleLogger.logEvent("==--==--==--==--==--==--==--==--=="+RESET);
                    break;
                }
            }
            i++;
            isFirstDroidTurn = !isFirstDroidTurn;
        }
    }

    public static void teamBattle(List<Droid> teamAlpha, List<Droid> teamBravo,int sizeA , int sizeB){
        System.out.println(PURPLE+"==--==--==--==--==--==--==--==--==");
        System.out.println("\t\tБІЙ ПОЧИНАЄТЬСЯ!");
        System.out.println("\n  Команда Alpha vs Команда Bravo");
        System.out.println("==--==--==--==--==--==--==--==--==");

        System.out.println("\t\tСклад команд:");
        System.out.println("\tAlpha\t\t\tBravo");

        battleLogger.logEvent("==--==--==--==--==--==--==--==--==");
        battleLogger.logEvent("\t\tРеплей битви!");
        battleLogger.logEvent("\n  Команда Alpha vs Команда Bravo");
        battleLogger.logEvent("==--==--==--==--==--==--==--==--==");
        battleLogger.logEvent("\t\tСклад команд:");
        battleLogger.logEvent("\tAlpha\t\t\tBravo");
        for(int i = 0;i<teamAlpha.size()||i<teamBravo.size();i++){
            battleLogger.logEvent((i+1)+")"+teamAlpha.get(i).getName()+"\t"+(i+1)+")"+teamBravo.get(i).getName());
        }
        battleLogger.logEvent("-----------------------------------");
        battleLogger.logEvent("\n");

        int index =1;
        int ind = 0;

        for(int i = 0;i<teamAlpha.size()||i<teamBravo.size();i++){
            System.out.println((i+1)+")"+teamAlpha.get(i).getName()+"\t"+(i+1)+")"+teamBravo.get(i).getName());
        }
        System.out.println("-----------------------------------");
        System.out.println("\n"+RESET);


        Random rand = new Random();
        boolean team1GoesFirst = rand.nextBoolean();

        while (isTeamAlive(teamAlpha) && isTeamAlive(teamBravo)) {
            System.out.println("Раунд "+index+"\n");
            battleLogger.logEvent("Раунд "+index+"\n");
            if (team1GoesFirst) {
                System.out.println("Хід команди Alpha");
                battleLogger.logEvent("Хід команди Alpha\n");
                processTurn(teamAlpha, teamBravo);
                System.out.println("Хід команди Bravo");
                battleLogger.logEvent("Хід команди Bravo\n");
                processTurn(teamBravo, teamAlpha);
            } else {
                System.out.println("Хід команди Bravo");
                battleLogger.logEvent("Хід команди Bravo\n");
                processTurn(teamBravo, teamAlpha);
                System.out.println("Хід команди Alpha");
                battleLogger.logEvent("Хід команди Alpha\n");
                processTurn(teamAlpha, teamBravo);
            }
            System.out.println(PURPLE+"-----------------------------------"+RESET);
            battleLogger.logEvent(PURPLE+"-----------------------------------"+RESET);

            index++;
            team1GoesFirst = !team1GoesFirst;
        }

        if (isTeamAlive(teamAlpha)) {
            System.out.println(PURPLE+"==--==--==--==--==--==--==--==--==");
            battleLogger.logEvent("==--==--==--==--==--==--==--==--==");
            System.out.println("Команда Alpha перемогла!");
            battleLogger.logEvent("Команда Alpha перемогла!");
            System.out.println("==--==--==--==--==--==--==--==--==\n\n");
            battleLogger.logEvent("==--==--==--==--==--==--==--==--==\n\n"+RESET);
        } else {
            System.out.println(PURPLE+"==--==--==--==--==--==--==--==--==");
            battleLogger.logEvent("==--==--==--==--==--==--==--==--==");
            System.out.println("Команда Bravo перемогла!");
            battleLogger.logEvent("Команда Bravo перемогла!");
            System.out.println("==--==--==--==--==--==--==--==--==\n\n");
            battleLogger.logEvent("==--==--==--==--==--==--==--==--==\n\n"+RESET);
        }
    }


    private static void processTurn(List<Droid> attackingTeam, List<Droid> defendingTeam) {
        // Перемішуємо порядок у команді
        Collections.shuffle(attackingTeam);
        int attacks=0;
        for (Droid attacker : attackingTeam) {
            if (!attacker.isAlive()) continue;
            attacker.updateStatus();
            if (!attacker.isStunned() && !attacker.isFrozen()) {
                Droid target = attacker.chooseTarget(defendingTeam);

                if (target != null && target.isAlive()) {
                    attacker.attack(target);
                    attacks++;
                    if (attacks == attackingTeam.size()) {
                        break;
                    }
                    if (!isTeamAlive(defendingTeam)) {
                        break;
                    }
                }
            }
        }
    }

    private static boolean isTeamAlive(List<Droid> team) {
        int countLive=0;
        for (Droid droid : team) {
            if (droid.isAlive()) {
                countLive++;
            }
        }
        return countLive > 0;
    }
}




