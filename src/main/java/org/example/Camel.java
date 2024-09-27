package org.example;

public class Camel extends Animal{
    protected Camel(Diet species, Moving move_type) { super(species, move_type); }

    public void eat(Food food) {
        if (food.equals(Food.GRASS)) {
            System.out.println("Camel eats grass.");
        } else {
            System.out.println("Wrong food for camel.");
        }
    }
}
