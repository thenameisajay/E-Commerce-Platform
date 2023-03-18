package com.stacs.group3.ShoppingSystemApp;

import com.stacs.group3.ShoppingSystemApp.view.CommandLine;

public class MainApp {
    public static void main(String args[]) {
        System.out.println("Hello World from MainApp");

        //Create a new command line instance
        CommandLine commandLine = new CommandLine();
        commandLine.main();
    }
}
