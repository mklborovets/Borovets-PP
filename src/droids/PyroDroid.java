package droids;

import Logger.BattleLogger;
import java.util.Random;
import static ui.Colors.*;

//health damage endurance damageCost  shield speed attackSpeed attackAccuracy
public class PyroDroid extends Droid {

    private static BattleLogger battleLogger = new BattleLogger();

    private int burnDuration = 2;
    private int burnDamage = 10;
    private int burnChance = 40;

    public PyroDroid(String name) {
        super(name, 300, 30, 30, 70,
                5, 88, 95, 82);
    }

    @Override
    public void attack(Droid target) {

        if((double) endurance<(double)maxEndurance/2.45){

            if(isDroidGoToBreak()){
                recoverEndurance();

                return;
            }
        }
        if(endurance<damageCost){
            recoverEndurance();
            return;
        }

        Random rand = new Random();
        double evadeChance = hitChance(target);
        double hitRoll = rand.nextDouble()*100;

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

        target.takeDamage(actualDamage);

        // Спеціальна властивість: Шанс підпалити ворога
        if (rand.nextInt(100) < burnChance) {
            System.out.println("\t\t🔥 " + target.getName() + " підпалений і буде отримувати " +RED+ burnDamage +RESET+ " шкоди кожен раунд!\n");
            battleLogger.logEvent("\t\t🔥 " + target.getName() + " підпалений і буде отримувати " +RED+ burnDamage +RESET+ " шкоди кожен раунд!\n");
            target.applyBurn(burnDuration, burnDamage);
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