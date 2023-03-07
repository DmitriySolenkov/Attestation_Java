package Presenter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.*;
public class Lottery {
    public Lottery() {
    }

    public void lottery(Presenter presenter) {
        String[] names = partipiciants();
        Random rand = new Random();
        ArrayList<Toy> prizes = new ArrayList<>();
        prizes=addPrizes(presenter, prizes, 1, 1);
        prizes=addPrizes(presenter, prizes, 2, 2);
        int remainder = names.length - 3;
        addPrizes(presenter, prizes, 3, remainder);
        for (String name : names) {
            int prize = rand.nextInt(50);
            while (prize > 0) {
                for (Toy toy : prizes) {
                    prize = prize - 1;
                    if (prize == 0) {
                        System.out.printf("%s!\n", name);
                        presenter.dropToy(toy);
                        prizes.remove(toy);
                        break;
                    }
                }
            }
        }
        System.out.println("Lottery is over!");
    }

    public String[] partipiciants() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the names of the participants separated by a space (min - 5)");
            String[] names = scanner.nextLine().split(" ");
            if (names.length > 4) {
                return names;
            }
        }
    }

    public ArrayList<Toy> addPrizes(Presenter presenter, ArrayList<Toy> prizes, int tier, int amount) {
        Random rand = new Random();
        while (amount > 0) {
            int randInt = rand.nextInt(50);
            boolean check = true;
            while (check) {
                for (Toy toy : presenter.getToys()) {
                    if (toy.getRarety() == tier) {
                        randInt = randInt - 1;
                        if (randInt == 0) {
                            prizes.add(toy);
                            amount=amount-1;
                            check = false;
                        }
                    }
                }
            }
        }
        return prizes;
    }
}
