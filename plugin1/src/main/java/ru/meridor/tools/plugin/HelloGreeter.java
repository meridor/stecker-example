package ru.meridor.tools.plugin;

public class HelloGreeter implements Greeter {

    @Override
    public void greet(String[] args) {
        for (String arg: args) {
            System.out.println("Hello, " + arg + "!");
        }
    }
}
