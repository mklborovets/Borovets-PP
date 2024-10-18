package droids;

import java.util.Random;
import Logger.BattleLogger;
import static ui.Colors.*;



public class Demolitionist extends Droid {

    private static BattleLogger battleLogger = new BattleLogger();

    private double critChance = 42;
    private double critMultiplier = 2;
    private int stunChance = 28;

    public Demolitionist(String name) {
        super(name, 400, 30, 35, 100, 8, 60, 75, 85);
    }

    // Атакує всіх ворогів одночасно
    public void attack(Droid target) {
        if ((double) endurance < (double) maxEndurance / 2.45) {
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


            if (rand.nextInt(100) < critChance) {
                actualDamage *= critMultiplier;
                System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                System.out.println("\t →атакує " + target.getName() + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                System.out.println("\t\t💥 Критичний удар! завдає " +YELLOW+ actualDamage +RESET+ " шкоди!");
                System.out.println(" " +WHITE+ target.getShield() +RESET+ " шкоди не проходить броню.");

                battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                battleLogger.logEvent("\t →атакує " + target.getName() + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                battleLogger.logEvent("\t\t💥 Критичний удар! завдає " +YELLOW+ actualDamage +RESET+ " шкоди!");
                battleLogger.logEvent(" " +WHITE+ target.getShield() +RESET+ " шкоди не проходить броню.");
            } else {
                System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                System.out.println("\t →атакує " + target.getName() + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                System.out.print("\t\tЗавдає " +RED+ actualDamage +RESET+ " шкоди.");
                System.out.println(" " +WHITE+ target.getShield() +RESET+ " шкоди не проходить броню.");

                battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                battleLogger.logEvent("\t →атакує " + target.getName() + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                battleLogger.logEvent("\t\tЗавдає " +RED+ actualDamage +RESET+ " шкоди.");
                battleLogger.logEvent(" " +WHITE+ target.getShield() +RESET+ " шкоди не проходить броню.");
            }

            target.takeDamage(actualDamage);

            if (rand.nextInt(100) < stunChance) {
                System.out.println("\t\t⚡ " + target.getName() + " оглушений і не може діяти 1 хід!\n");
                battleLogger.logEvent("\t\t⚡ " + target.getName() + " оглушений і не може діяти 1 хід!\n");
                target.stun(1);
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

