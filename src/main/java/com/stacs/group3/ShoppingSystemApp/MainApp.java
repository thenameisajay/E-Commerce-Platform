package com.stacs.group3.ShoppingSystemApp;

import com.stacs.group3.ShoppingSystemApp.controller.AlphaController;
import com.stacs.group3.ShoppingSystemApp.view.CommandLine;

public class MainApp {
    public static void main(String[] args) {
        // Create an Instance of the Controller
        AlphaController alphaController = new AlphaController();

        //Create a new command line instance
        CommandLine commandLine = new CommandLine(alphaController);
        commandLine.start();
    }
}
