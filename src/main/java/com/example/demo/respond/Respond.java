package com.example.demo.respond;

public abstract class Respond {
    private final String text;

    public Respond(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
