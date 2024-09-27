package org.example;

public enum Moving {
    WALKING("is walking"), SWIMMING("is swimming"), FLYING("is flying");

    private final String annotation;

    Moving(String annotation) {
        this.annotation = annotation;
    }

    public String walk() {
        return annotation;
    }
}
