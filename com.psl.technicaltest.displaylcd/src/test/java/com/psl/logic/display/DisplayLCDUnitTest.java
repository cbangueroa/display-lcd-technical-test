package com.psl.logic.display;

import org.junit.Test;

public class DisplayLCDUnitTest {
    DisplayLCD displayLCD = new DisplayLCD("C:/devel/PSL/in/", "C:/devel/PSL/out/");

    @Test
    public void test() {
        displayLCD.getFileManager().setOutputPath("C:/devel/PSL/out/");
        displayLCD.getFileManager().createFileOutPut();
        displayLCD.printNumber("1", "1234567890");
        displayLCD.printNumber("1", "0987654321");
        displayLCD.printNumber("2", "1234567890");
        displayLCD.printNumber("2", "0987654321");
        displayLCD.printNumber("3", "1234567890");
        displayLCD.printNumber("4", "0987654321");

    }
    

    @Test
    public void test2() {
        displayLCD.printNumbersOnLCD();
    }
}