package droids;
import java.util.Random;

import Logger.BattleLogger;
import static ui.Colors.*;

public class IronDroid extends Droid{
    private static BattleLogger battleLogger = new BattleLogger();

    private int extraShieldEveryRound = 1;
    private int maxExtraShield = 10;
    private int difShield = 20;

    public IronDroid(String name) {
        super(name, 450, 25, 20, 120,
                20, 56, 72, 75);
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
        System.out.println("\n\t →атакує " + target.getName() + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        System.out.print("\n\t\tЗавдає " +RED+ actualDamage +RESET+ " шкоди.");
        System.out.println(" " +WHITE+ target.getShield() +RESET+ " шкоди не проходить броню.");

        battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        battleLogger.logEvent("\n\t →атакує " + target.getName() + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        battleLogger.logEvent("\n\t\tЗавдає " +RED+ actualDamage +RESET+ " шкоди.");
        battleLogger.logEvent(" " +WHITE+ target.getShield() +RESET+ " шкоди не проходить броню.");

        target.takeDamage(actualDamage);


        while (shield<difShield+maxExtraShield){
            shield += extraShieldEveryRound;
        }
        System.out.println("\t\t\t→Збільшує свій щит "+WHITE+shield+RESET+ " на "+extraShieldEveryRound+"\n");

        if(!target.isAlive()){
            System.out.println("\t\t\t→У "+target.getName()+" лишається "+GREEN+"0"+RESET+" здоров'я"+"\n");

            battleLogger.logEvent("\t\t\t→У "+target.getName()+" лишається "+GREEN+"0"+RESET+" здоров'я"+"\n");
            return;
        }
        System.out.println("\t\t\t→У "+target.getName()+" лишається " +GREEN+ target.getHealth()+RESET+" здоров'я"+"\n");
        battleLogger.logEvent("\t\t\t→У "+target.getName()+" лишається " +GREEN+ target.getHealth()+RESET+" здоров'я"+"\n");
    }

}
