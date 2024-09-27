package org.example;

public class Main {
    public static void main(String[] args) {
        Horse horse = new Horse(Diet.HERBIVORE, Moving.WALKING);
        horse.print();
        horse.move();
        horse.eat(Food.GRASS);
        /*
        This is Horse.
        Horse is walking.
        Horse eats grass.
        */

        System.out.println("----------------");

        Tiger tiger = new Tiger(Diet.PREDATOR, Moving.WALKING);
        tiger.print();
        tiger.move();
        tiger.eat(Food.BEEF);
        /*
        This is Tiger.
        Tiger is walking.
        Tiger eats beef.
        */

        System.out.println("----------------");

        Dolphin dolphin = new Dolphin(Diet.PREDATOR, Moving.SWIMMING);
        dolphin.print();
        dolphin.move();
        dolphin.eat(Food.FISH);
        /*
        This is Dolphin.
        Dolphin is swimming.
        Dolphin eats fish.
        */

        System.out.println("----------------");

        Eagle eagle = new Eagle(Diet.PREDATOR, Moving.FLYING);
        eagle.print();
        eagle.move();
        eagle.eat(Food.FISH);
        /*
        This is Eagle.
        Eagle is flying.
        Eagle eats meat.
        */

        System.out.println("----------------");

        Camel camel = new Camel(Diet.HERBIVORE, Moving.WALKING);
        camel.print();
        camel.move();
        camel.eat(Food.MEAT);
        /*
        This is Camel.
        Camel is walking.
        Wrong food for camel.
        */
    }
}