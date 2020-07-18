package machine;

import machine.model.Machine;

import java.util.Scanner;

import static machine.model.State.OFF;
import static machine.model.State.MAIN;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine(550, 400, 540, 120, 9);

        while (machine.getState() != OFF) {

            if (machine.getState() == MAIN) {
                System.out.println("Write action (buy, fill, take, remaining, exit)");
            }
            String input = scanner.nextLine();
            machine.operate(input);
        }
    }
}
