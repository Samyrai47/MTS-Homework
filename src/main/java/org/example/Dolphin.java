package org.example;

public class Dolphin extends Animal{
    protected Dolphin(Diet species, Moving move_type) { super(species, move_type); }

    public void eat(Food food) {
        if (food.equals(Food.FISH)) {
            System.out.println("Dolphin eats fish.");
        } else {
            System.out.println("Wrong food for dolphin.");
        }
    }
}
