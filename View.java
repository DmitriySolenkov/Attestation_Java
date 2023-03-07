import java.util.ArrayList;
import java.util.Scanner;

import Model.*;
import Presenter.*;

public class View {
    public static void main(String[] args) {
        Toy toy1 = new Toy(0, "Skipjack", 10, 20, 3, true);
        Toy toy2 = new Toy(1, "Bubblegum", 10, 20, 3, true);
        Toy toy3 = new Toy(2, "Rubic's Cube", 7, 15, 2, true);
        Toy toy4 = new Toy(3, "Bath duck", 7, 15, 2, true);
        Toy toy5 = new Toy(4, "Tetris", 5, 10, 1, false);
        Toy toy6 = new Toy(5, "Teddy-bear", 5, 10, 1, false);
        Toy toy7 = new Toy(6, "Lego set", 5, 10, 1, false);
        ArrayList<Toy> toys = new ArrayList<>();
        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);
        toys.add(toy4);
        toys.add(toy5);
        toys.add(toy6);
        toys.add(toy7);
        Presenter presenter = new Presenter(toys);
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println(
                    "Enter 1 to win a random toy,\nEnter 2 to add a new toy,\nEnter 3 to change toy drop chance,\nEnter 4 to start the lottery,\nEnter 5 to exit:");
            String mode = scanner.nextLine();
            switch (mode) {
                case "1":
                    presenter.playoutToy();
                    break;
                case "2":
                    presenter.addToy();
                    break;
                case "3":
                    presenter.changeDropweight();
                    break;
                case "4":
                    presenter.lottery();
                    break;
                case "5":
                    check = false;
                    break;
            }

        }
    }
}
