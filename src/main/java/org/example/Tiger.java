package org.example;

public class Tiger extends Animal {
    public Tiger(Diet species, Moving move_type) {
        super(species, move_type);
    }

    public void eat(Food food) {
        if (food.equals(Food.BEEF)) {
            System.out.println("Tiger eats beef.");
        } else {
            System.out.println("Wrong food for tiger.");
        }
    }
}
