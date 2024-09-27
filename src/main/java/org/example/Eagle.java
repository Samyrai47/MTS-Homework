package org.example;

public class Eagle extends Animal{
    protected Eagle(Diet species, Moving move_type) { super(species, move_type); }

    public void eat(Food food) {
        if (food.equals(Food.MEAT) || food.equals(Food.BEEF) || food.equals(Food.FISH)) {
            System.out.println("Eagle eats meat.");
        } else {
            System.out.println("Wrong food for eagle.");
        }
    }
}
