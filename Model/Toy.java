package Model;

public class Toy{
    
private int ID;
private String name;
private int amount;
private int weight;
private int rarety;
private Boolean weightChange;
public Toy(int iD, String name, int amount, int weight, int rarety, Boolean weightChange) {
    ID = iD;
    this.name = name;
    this.amount = amount;
    this.weight = weight;
    this.rarety= rarety;
    this.weightChange = weightChange;
}
public int getID() {
    return ID;
}
public String getName() {
    return name;
}
public int getAmount() {
    return amount;
}
public int getWeight() {
    return weight;
}
public Boolean getWeightChange() {
    return weightChange;
}
public void setAmount(int amount) {
    this.amount = amount;
}
public void setWeight(int weight) {
    this.weight = weight;
}
public int getRarety() {
    return rarety;
}

}

