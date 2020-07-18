package machine.model;

import static machine.model.State.*;

/**
 * This class represents a coffee machine object
 */
public class Machine {

    private int cash;
    private int waterInMl;
    private int milkInMl;
    private int beansInGrams;
    private int cups;

    private State state;

    public Machine(int cash, int waterInMl, int milkInMl, int beansInGrams, int cups) {
        this.cash = cash;
        this.waterInMl = waterInMl;
        this.milkInMl = milkInMl;
        this.beansInGrams = beansInGrams;
        this.cups = cups;
        this.state = MAIN;
    }

    public State getState() {
        return this.state;
    }

    public void operate(String input) {
        if (state == MAIN) {
            operateMainMenu(input);

        } else if (state == FILL_WATER) {
            this.waterInMl += Integer.parseInt(input);
            System.out.println("Write how many ml of milk to you want to add:");
            state = FILL_MILK;
        } else if (state == FILL_MILK) {
            this.milkInMl += Integer.parseInt(input);
            System.out.println("Write how many grams of coffee beans to you want to add:");
            state = FILL_COFFEE;
        } else if (state == FILL_COFFEE) {
            this.beansInGrams += Integer.parseInt(input);
            System.out.println("Write how many disposable cups of coffee do you want to add:");
            state = FILL_CUPS;
        } else if (state == FILL_CUPS) {
            this.cups += Integer.parseInt(input);
            state = MAIN;
        } else if (state == BUY) {
            handleBuyMenu(input);
        }
    }

    private void operateMainMenu(String input) {
        switch (input) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");
                state = BUY;
                break;
            case "fill":
                System.out.println("Write how many ml of water to you want to add:");
                state = FILL_WATER;
                break;
            case "take":
                System.out.println(String.format("I gave you $%d", takeMoney()));
                break;
            case "remaining":
                printResources();
                break;
            case "exit":
                state = OFF;
                break;
            default:
                break;
        }
    }

    private void handleBuyMenu(String input) {
        switch (input) {
            case "1":
                makeCoffee(Coffee.ESPRESSO);
                break;
            case "2":
                makeCoffee(Coffee.LATTE);
                break;
            case "3":
                makeCoffee(Coffee.CAPPUCCINO);
                break;
            default:
                break;
        }
        state = MAIN;
    }

    private void makeCoffee(Coffee coffee) {
        if (enoughResources(coffee)) {
            System.out.println("I have enough resources, making you a coffee!");
            waterInMl -= coffee.water;
            milkInMl -= coffee.milk;
            beansInGrams -= coffee.coffee;
            cups--;
            cash += coffee.price;
        }
        state = MAIN;
    }

    private boolean enoughResources(Coffee coffee) {
        boolean enough = false;
        if (this.waterInMl < coffee.water) {
            System.out.println("Sorry, not enough water!");
        } else if (milkInMl < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (this.beansInGrams < coffee.coffee) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (this.cups < 1) {
            System.out.println("Sorry, not enough cups!");
        } else enough = true;
        return enough;
    }

    private int takeMoney() {
        int money = cash;
        this.cash = 0;
        return money;
    }

    private void printResources() {
        System.out.println("The coffee machine has:");
        System.out.println(String.format("%d of water", waterInMl));
        System.out.println(String.format("%d of milk", milkInMl));
        System.out.println(String.format("%d of coffee beans", beansInGrams));
        System.out.println(String.format("%d of disposable cups", cups));
        System.out.println(String.format("$%d of money", cash));
    }

}
