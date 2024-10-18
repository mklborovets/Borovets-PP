package droids;
import java.util.Random;

import Logger.BattleLogger;
import static ui.Colors.*;

public class ElectroDroid extends Droid {

    private static BattleLogger battleLogger = new BattleLogger();

    private int stunChance = 35;
    private int electroDamage = 5;
    private int electroDuration = 1;

    public ElectroDroid(String name) {
        super(name, 350, 40, 15, 90, 12, 87, 85, 90);
    }

    @Override
    public void attack(Droid target) {
        if (endurance < maxEndurance / 2.45) {
            if (isDroidGoToBreak()) {
                recoverEndurance();
                return;
            }
        }
        if (endurance < damageCost) {
            recoverEndurance();
            return;
        }

        Random rand = new Random();
        double evadeChance = hitChance(target);
        double hitRoll = rand.nextDouble() * 100;

        if (hitRoll > evadeChance) {
            System.out.print("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
            System.out.println("\tне влучив по " + target.name+"\n");
            battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")\n"+RESET);
            battleLogger.logEvent("\tне влучив по " + target.name+"\n");
            return;
        }

        int actualDamage = calculateDamage(target);

        endurance -= damageCost;
        if (endurance < 0) endurance = 0;

        System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        System.out.println("\t →атакує " + target.getName() + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        System.out.print("\t\tЗавдає " +RED+ actualDamage +RESET+ " шкоди.");
        System.out.println(" " +WHITE+ target.getShield() +RESET+ " шкоди не проходить броню.");

        battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        battleLogger.logEvent("\t →атакує " + target.getName() + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        battleLogger.logEvent("\t\tЗавдає " +RED+ actualDamage +RESET+ " шкоди.");
        battleLogger.logEvent(" " +WHITE+ target.getShield() +RESET+ " шкоди не проходить броню.");

        System.out.println("\t\tНакладає додаткової " +YELLOW+ 2 +RESET+ " шкоди під час стану(кожен раунд збільшення шкоди).");
        target.applyBurn(0, 2);

        target.takeDamage(actualDamage);


        if (rand.nextDouble(100) < 40) {
            System.out.println("\t\t⚡ " + target.getName() + " оглушений і не може діяти 1 хід!\n");
            battleLogger.logEvent("\t\t⚡ " + target.getName() + " оглушений і не може діяти 1 хід!\n");
            target.applyBurn(electroDuration, electroDamage);
        }

        if(!target.isAlive()){
            System.out.println("\t\t\t→У "+target.getName()+" лишається "+GREEN+"0"+RESET+" здоров'я"+"\n");

            battleLogger.logEvent("\t\t\t→У "+target.getName()+" лишається "+GREEN+"0"+RESET+" здоров'я"+"\n");
            return;
        }
        System.out.println("\t\t\t→У "+target.getName()+" лишається " +GREEN+ target.getHealth()+RESET+" здоров'я"+"\n");
        battleLogger.logEvent("\t\t\t→У "+target.getName()+" лишається " +GREEN+ target.getHealth()+RESET+" здоров'я"+"\n");
    }
}
