package ru.meridor.tools.plugin;

public class HeyGreeter implements Greeter {

    @Override
    public void greet(String[] args) {
        for (String arg: args) {
            System.out.println("Hey, " + arg + "!");
        }
    }
}
