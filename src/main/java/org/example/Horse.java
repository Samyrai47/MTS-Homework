package org.example;

public class Horse extends Animal {
    public Horse(Diet species, Moving move_type) { super(species, move_type); }

    public void eat(Food food) {
        if (food.equals(Food.GRASS)) {
            System.out.println("Horse eats grass.");
        } else {
            System.out.println("Wrong food for horse.");
        }
    }
}
