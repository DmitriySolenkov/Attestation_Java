import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.*;

public class Presenter {
    private ArrayList<Toy> toys;
    

    public Presenter(ArrayList<Toy> toys) {
        this.toys = toys;
    }

    public void addToy() {
        int ID = toys.size();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter toy's name:\n");
        String name = scanner.nextLine();
        int amount = 0;
        int weight = 0;
        Boolean weightChange = true;
        int rarety = 3;
        while (true) {
            System.out.println("Enter amount of toys:\n");
            try {
                amount = Integer.parseInt(scanner.nextLine());
                if (amount > 0) {
                    break;
                } else {
                    System.out.println("Amount can't be negative!\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again!\n");
            }
        }
        while (true) {
            System.out.println("Enter the frequency of the toy drop(%):\n");
            try {
                weight = Integer.parseInt(scanner.nextLine());
                if (weight > 0 && weight < 20) {
                    break;
                } else {
                    System.out.println("Incorrect drop chance!\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again!\n");
            }
        }
        while (true) {
            System.out.println(
                    "Enter 1 if the drop chance can be changed, \nEnter 2 if the drop chance cannot be changed:\n");
            String mode = scanner.nextLine();
            if (mode.equals("1")) {
                break;
            }
            if (mode.equals("2")) {
                weightChange = false;
                break;
            }

        }
        Boolean check = true;
        while (check) {
            System.out.println("Enter the value of the lottery toy (from 1 to 3, 1 - for the highest):\n");
            String mode = scanner.nextLine();
            switch (mode) {
                case "1":
                    rarety = 1;
                    check = false;
                    break;
                case "2":
                    rarety = 2;
                    check = false;
                    break;
                case "3":
                    check = false;
                    break;
            }

        }
        Toy toy = new Toy(ID, name, amount, weight, rarety, weightChange);
        toys.add(toy);
        changeWeight(weight, -1, ID, ID);
        System.out.println("Toy added!");
    }

    public void playoutToy() {
        Random rand = new Random();
        int number = rand.nextInt(100) + 1;
        int minIndex = 1;
        int maxIndex = 1;
        for (Toy toy : toys) {
            maxIndex = maxIndex + toy.getWeight();
            if (number >= minIndex && number < maxIndex) {
                dropToy(toy);
                break;
            } else {
                minIndex = minIndex + toy.getWeight();
            }
        }
    }

    public void dropToy(Toy toy) {
        if (toy.getAmount() > 1) {
            System.out.printf("You won %s! Congratulations!\n", toy.getName());
            toy.setAmount(toy.getAmount() - 1);
        }
        if (toy.getAmount() == 1) {
            System.out.printf("You won %s! Congratulations!\n", toy.getName());
            toy.setAmount(toy.getAmount() - 1);
            System.out.printf("Toy '%s' is over! Restock it!\n", toy.getName());
            changeWeight(toy.getWeight(), 1, toy.getID(), toy.getID());
        } else if (toy.getAmount() == 0) {
            System.out.printf("Toy '%s' is over! Restock it!\n", toy.getName());
        }
    }

    public void changeWeight(int weight, int mode, int ID, int IDBlock) {
        Boolean check = true;
        while (check) {
            for (Toy toy : toys) {
                if (toy.getID() == IDBlock) {

                } else {
                    if (toy.getWeightChange() == true) {
                        if (mode == 1) {
                            toy.setWeight(toy.getWeight() + 1);

                        }
                        if (mode == -1) {
                            if (toy.getWeight() == 0) {
                                toys.get(ID).setWeight(toys.get(ID).getWeight() - weight);
                                weight = 0;
                            }
                            toy.setWeight(toy.getWeight() - 1);
                            if (toy.getWeight() == 0) {
                                toys.get(ID).setWeight(toys.get(ID).getWeight() - weight);
                                weight = 0;
                            }
                        }
                        weight = weight - 1;
                        if (weight == 0) {
                            check = false;
                            break;
                        }
                    }
                }
            }
        }

    }

    public void changeDropweight() {
        int mode = 0;
        int change = 0;
        for (Toy toy : toys) {
            System.out.printf("%d. %s - %d percent\n", toy.getID(), toy.getName(), toy.getWeight());
        }
        Scanner scanner = new Scanner(System.in);
        Boolean check = true;
        while (check) {
            System.out.println("Enter toy's ID:");
            try {
                int ID = Integer.parseInt(scanner.nextLine());
                if (ID >= 0 && ID < toys.size()) {
                    if (toys.get(ID).getWeightChange()) {
                        System.out.println("Enter new drop chance");
                        try {
                            int weight = Integer.parseInt(scanner.nextLine());
                            if (weight > 0 && weight <= 30) {
                                int oldWeight = toys.get(ID).getWeight();
                                if (oldWeight >= weight) {
                                    change = oldWeight - weight;
                                    mode = 1;
                                } else {
                                    change = weight - oldWeight;
                                    mode = -1;
                                }
                                toys.get(ID).setWeight(weight);
                                changeWeight(change, mode, ID, ID);
                                check = false;
                            } else {
                                System.out.println("Invalid input, please try again!\n");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input, please try again!\n");
                        }
                    } else {
                        System.out.println("Changing the drop rate for this toy is not possible!");
                    }
                } else {
                    System.out.println("Invalid input, please try again!\n");
                }

            } catch (Exception e) {
                System.out.println("Invalid input, please try again!\n");
            }
        }
    }

    public void lottery() {
        String[] names = partipiciants();
        Random rand = new Random();
        ArrayList<Toy> prizes = new ArrayList<>();
        addPrizes(prizes, 1, 1);
        addPrizes(prizes, 2, 2);
        int remainder = names.length - 3;
        addPrizes(prizes, 3, remainder);
        for (String name : names) {
            int prize = rand.nextInt(50);
            while (prize > 0) {
                for (Toy toy : prizes) {
                    prize = prize - 1;
                    if (prize == 0) {
                        System.out.printf("%s!\n", name);
                        dropToy(toy);
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

    public ArrayList<Toy> addPrizes(ArrayList<Toy> prizes, int tier, int amount) {
        Random rand = new Random();
        while (amount > 0) {
            int randInt = rand.nextInt(50);
            boolean check = true;
            while (check) {
                for (Toy toy : toys) {
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
