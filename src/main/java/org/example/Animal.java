package org.example;

public class Animal {
    protected static Diet species;
    protected static Moving move_type;

    protected Animal(Diet species, Moving move_type) {
        this.species = species;
        this.move_type = move_type;
    }

    protected void move() {
        System.out.println(getClass().getSimpleName() + " " + move_type.walk() + ".");
    }

    protected void print() {
        System.out.println("This is " + getClass().getSimpleName() + ".");
    }
}
