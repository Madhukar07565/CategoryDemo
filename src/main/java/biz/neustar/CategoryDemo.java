package biz.neustar;

import biz.neustar.process.CategoryProcessor;

import java.io.File;

/**
 * @author Madhukar Reddy
 *
 */

public class CategoryDemo {

    public static void main(String[] args) throws Exception {
        CategoryProcessor categoryProcessor = new CategoryProcessor();
        if (args.length < 1) {
            System.out.println("please pass the file name as argument");
        } else {
            File inputFile = new File(args[0]);
            if (inputFile.exists()) {
                categoryProcessor.printOutput(inputFile);
            } else {
                System.out.println("File not found!, please enter a valid file");
            }
        }
    }

}
