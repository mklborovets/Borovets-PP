package droids;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Logger.BattleLogger;

import static ui.Colors.*;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxHealth=health;
    protected int damage;
    protected int endurance;
    protected int damageCost;
    protected int maxEndurance=endurance;
    protected int shield;
    protected int speed;
    protected int attackSpeed;
    protected int attackAccuracy;

    public Droid(String name,int health, int damage, int damageCost, int endurance, int shield, int speed, int attackSpeed, int attackAccuracy){
        this.name = name;
        this.health = health;
        this.maxHealth=health;
        this.damage = damage;
        this.damageCost = damageCost;
        this.endurance = endurance;
        this.maxEndurance = endurance;
        this.shield = shield;
        this.speed = speed;
        this.attackSpeed = attackSpeed;
        this.attackAccuracy = attackAccuracy;

    }

    public Droid(){
        this.name = "Droid";
        this.health = 400;
        this.damage = 57;
        this.damageCost = 25;
        this.endurance = 100;
        this.shield = 10;
        this.speed = 10;
        this.attackSpeed = 90;
        this.attackAccuracy = 90;
    }


    private int frozenTurns = 0;
    private int burnTurns = 0;
    private int burnDamage = 0;
    private int stunnedTurns = 0;
    private int electroDamage = 0;

    public String getName(){return name;}
    public int getHealth(){return health;}
    public int getMaxHealth(){return maxHealth;}
    public int getDamage(){return damage;}
    public int getDamageCost(){return damageCost;}
    public int getEndurance(){return endurance;}
    public int getMaxEndurance(){return maxEndurance;}
    public int getShield(){return shield;}
    public int getSpeed(){return speed;}
    public int getAttackSpeed(){return attackSpeed;}
    public int getAttackAccuracy(){return attackAccuracy;}


    public void setName(String name) { this.name = name; }
    public void setHealth(int health) { this.health = health; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setDamageCost(int damageCost) { this.damageCost = damageCost; }
    public void setEndurance(int endurance) { this.endurance = endurance; }
    public void setMaxEndurance(int maxEndurance) { this.maxEndurance = maxEndurance; }
    public void setShield(int shield) { this.shield = shield; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setAttackSpeed(int attackSpeed) { this.attackSpeed = attackSpeed; }
    public void setAttackAccuracy(int attackAccuracy) { this.attackAccuracy = attackAccuracy; }



    private static BattleLogger battleLogger = new BattleLogger();

    public void setCharacteristics() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть здоров'я: ");
        setHealth(scanner.nextInt());
        setMaxHealth(getHealth());

        System.out.print("Введіть шкоду: ");
        setDamage(scanner.nextInt());

        System.out.print("Введіть витривалість: ");
        setEndurance(scanner.nextInt());
        setMaxEndurance(getEndurance());

        System.out.print("Введіть вартість атаки: ");
        setDamageCost(scanner.nextInt());

        System.out.print("Введіть броню: ");
        setShield(scanner.nextInt());

        System.out.print("Введіть швидкість: ");
        setSpeed(scanner.nextInt());

        System.out.print("Введіть швидкість атаки: ");
        setAttackSpeed(scanner.nextInt());

        System.out.print("Введіть точність атаки: ");
        setAttackAccuracy(scanner.nextInt());
    }



    public void takeDamage(int damage){
        this.health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }
    public void information(){
        System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
    }

    public Droid chooseTarget(List<Droid> enemies) {
        Random rand = new Random();
        int chance = rand.nextInt(100);

        if (chance < 35) {
            Droid target = null;
            int minHealth = Integer.MAX_VALUE;

            for (Droid enemy : enemies) {
                if (enemy.isAlive() && enemy.getHealth() < minHealth) {
                    minHealth = enemy.getHealth();
                    target = enemy;
                }
            }
            return target;
        } else {
            Droid randomTarget;
            do {
                int randomIndex = rand.nextInt(enemies.size());
                randomTarget = enemies.get(randomIndex);
            } while (!randomTarget.isAlive());

            return randomTarget;
        }
    }

    public void recoverEndurance() {
        System.out.print("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        System.out.print("\n\tпропускає раунд, щоб відновити витривалість."+CYAN+ "("+endurance+"->"+RESET);
        battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        battleLogger.logEvent("\n\tпропускає раунд, щоб відновити витривалість."+CYAN+ "("+endurance+"->");
        endurance += 40;
        if (endurance > maxEndurance) {
            endurance = maxEndurance;
        }
        System.out.println(CYAN+endurance+")\n"+RESET);
        battleLogger.logEvent(endurance+")\n"+ RESET);
    }

    public boolean isDroidGoToBreak(){
        Random rand = new Random();
        int isDroidGoToBreak= rand.nextInt(100);
        //System.out.println(name + " \nШАНС: "+ isDroidGoToBreak);
        int choice = 75;
        return isDroidGoToBreak <= choice;
    }

    public double staminaMultiplier(){
        return  (double) endurance / ((double) maxEndurance / 2.45);
    }

    public double hitChance(Droid target){
        double evade;

        double targetSpeed = target.speed;
        double attackSPEED = (double) attackSpeed;
        double attackACCURACY = (double)attackAccuracy;
        if(endurance<(maxEndurance/2.45)){
            attackSPEED *= staminaMultiplier();
            attackACCURACY *= staminaMultiplier();
        }
        if(target.endurance<(target.maxEndurance/2.45)){
            targetSpeed *= (double) target.endurance / ((double) target.maxEndurance / 2.45);
        }
        evade = (double) (attackSPEED * attackACCURACY) / targetSpeed;
        return evade;
    }

    public int calculateDamage(Droid target) {
        double staminaMultiplier = staminaMultiplier();
        double damageWithShield = damage - target.shield;

        if (damageWithShield < 0) {
            damageWithShield = 0;
        }
        if(endurance<(maxEndurance/2.45)){
            return (int) (damageWithShield * staminaMultiplier);
        }
        return (int)damageWithShield;
    }


    public void freeze(int turns) {
        this.frozenTurns += turns;
    }

    public void stun(int turns) {
        this.stunnedTurns += turns;
    }

    public void applyBurn(int turns, int damagePerTurn) {
        this.burnTurns += turns;
        this.burnDamage = damagePerTurn;
    }

    public void applyElectro(int turns, int damagePerTurn) {
        this.stunnedTurns += turns;
        this.electroDamage += damagePerTurn;
    }


    public void updateStatus() {

        if (stunnedTurns > 0) {
            System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
            System.out.println("\tоглушений і не може діяти ще " +YELLOW+ stunnedTurns +RESET+ " раунди.\n");
            battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
            battleLogger.logEvent("\tоглушений і не може діяти ще " +YELLOW+ stunnedTurns +RESET+ " раунди.\n");
            stunnedTurns--;
            if(electroDamage>0){
                takeDamage(electroDamage);
                System.out.println("\tстраждає від електрики і втрачає " +YELLOW+ burnDamage +RESET+ " здоров'я.\n");
            }
        }

        if (frozenTurns > 0) {
            System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
            System.out.println("\tзаморожений і не може діяти ще " +BLUE+ frozenTurns +RESET+ " раунди.\n");
            battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
            battleLogger.logEvent("\tзаморожений і не може діяти ще " +BLUE+ frozenTurns +RESET+ " раунди.\n");
            frozenTurns--;
        }



        if (burnTurns > 0) {
            System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
            System.out.println("\tстраждає від опіків і втрачає " +RED+ burnDamage +RESET+ " здоров'я.\n");
            battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
            battleLogger.logEvent("\tстраждає від опіків і втрачає " +RED+ burnDamage +RESET+ " здоров'я.\n");
            takeDamage(burnDamage);
            burnTurns--;

            if (!isAlive()) {
                System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                System.out.println("\tзагинув від опіків!\n");
                battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
                battleLogger.logEvent("\tзагинув від опіків!\n");
            }
        }
    }

    public boolean isStunned() {
        return stunnedTurns > 0;
    }

    public boolean isFrozen() {
        return frozenTurns > 0;
    }

    public void clearDebuffs() {
        this.frozenTurns = 0;
        this.stunnedTurns = 0;
        this.burnTurns = 0;
        this.burnDamage = 0;
        System.out.println("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        System.out.println("\tбільше не має негативних ефектів!\n");
        battleLogger.logEvent("▶"+name + GREEN+" ("+getHealth()+")"+ CYAN+"("+getEndurance()+")"+ RED+"("+getDamage()+")"+ WHITE+"("+getShield()+")"+RESET);
        battleLogger.logEvent("\tбільше не має негативних ефектів!\n");
    }

    public abstract void attack(Droid target);

    public int getElectroDamage() {
        return electroDamage;
    }

    public void setElectroDamage(int electroDamage) {
        this.electroDamage = electroDamage;
    }
}
