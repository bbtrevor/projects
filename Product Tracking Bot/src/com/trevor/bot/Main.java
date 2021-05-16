package com.trevor.bot;

/**
 * Runs the project.
 * @author Trevor Bagbey
 * @version 2021.05.14
 */
public class Main {
    
    /**
     * Method run on program exeuction
     * @param args Given arguments
     */
    public static void main(String[] args) {
        try {
            new GUI();
        }
        catch (Exception e) {
            System.out.println("caught.");
        }
    }
}
