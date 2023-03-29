package com.stacs.group3.ShoppingSystemApp;

import com.stacs.group3.ShoppingSystemApp.view.CommandLine;

public class MainApp {
    public static void main() {
        //Create a new command line instance
        CommandLine commandLine = new CommandLine("http://localhost:8080");
        commandLine.start();
    }
}
