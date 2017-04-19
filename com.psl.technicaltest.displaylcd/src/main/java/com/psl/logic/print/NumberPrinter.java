package com.psl.logic.print;

import org.apache.log4j.Logger;

import com.psl.logic.display.DisplayLCD;
import com.psl.utils.Constants;
import com.psl.utils.LogMessages;

/**
 * NumberPrinter
 * Class responsible for receiving requests to print numbers on the LCD display</br>
 * and proceed to execute the action
 * */
class NumberPrinter {
    /** Logger of NumberPrinte class */
    private static Logger LOG = Logger.getLogger(NumberPrinter.class.getName());

    /**
     * printNumbersInLCDDisplay
     * Method in charge of printing the numbers indicated in text file on the LCD display
     * */
    public void printNumbersInLCDDisplay() {
        LOG.info(String.format(LogMessages.MSG_INFO_DEFAULT_PATHS, Constants.DEFAULT_INPUT_PATH, Constants.DEFAULT_OUTPUT_PATH));
        // We make use of the default paths
        printNumbersInLCDDisplay(Constants.DEFAULT_INPUT_PATH, Constants.DEFAULT_OUTPUT_PATH);
    }

    /**
     * printNumbersInLCDDisplay
     * Method in charge of printing the numbers indicated in text file on the LCD display
     * @param pInputPath, Path of the input file
     * @paran pOutputPath, Path of the output file
     * */
    public void printNumbersInLCDDisplay(final String pInputPath, final String pOutputPath) {
        // Validate that paths are not null or empty
        if (pInputPath != null && pOutputPath != null && !pInputPath.isEmpty() && !pOutputPath.isEmpty()) {
            // We create the instance of the LCD Display
            DisplayLCD displayLCD = new DisplayLCD(pInputPath, pOutputPath);
            // We invoke the functionality in charge of drawing the numbers included in the input file
            displayLCD.printNumbersOnLCD();
        } else {
            // In case you find that the paths are not valid, we generate a log
            LOG.info(String.format(LogMessages.MSG_INFO_INVALID_PATHS, Constants.DEFAULT_INPUT_PATH, Constants.DEFAULT_OUTPUT_PATH));
        }
    }

    /**
     * main</br>
     * A Main has been located in the present class in order to facilitate the execution of tests</br>
     * with maven commandsn
     * 
     * Instructions:
     * 
     * At the level of the project pom and once installed:
     * 
     * - Command sending by parameter the path of the files in.txt and out.txt
     *   mvn exec:java -Dexec.mainClass="com.psl.logic.print.NumberPrinter" -Dexec.args=["inputPath outputPath"]
     *   
     *   Example:
     *   mvn exec:java -Dexec.mainClass="com.psl.logic.print.NumberPrinter" -Dexec.args="C:/devel/PSL/in/ C:/devel/PSL/out/"
     * 
     * - Comando sin envio de parametros (Se toman los Path por defecto)
     *   mvn exec:java -Dexec.mainClass="com.psl.logic.print.NumberPrinter" 
     *   
     *   Default input path=> "src/main/resources/files/input/"
     *   Default output path=> "src/main/resources/files/output/"
     * 
     * 
     *  */
    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter();
        if (args.length == 0) {
            // If no parameters are indicated the default values are takento
            numberPrinter.printNumbersInLCDDisplay();
        } else {
            // Paths are mapped by parameter
            numberPrinter.printNumbersInLCDDisplay(args[0], args[1]);
        }
    }

}
