
package com.psl.logic.display;

import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.psl.logic.file.FileManager;
import com.psl.utils.Constants;
import com.psl.utils.InstructionsToDrawNumbers;
import com.psl.utils.LogMessages;

/**
 * DisplayLCD </br>
 * Class in charge of managing the printing of numbers
 * */
public class DisplayLCD {
    /** Logger of DisplayLCD class */
    private static Logger LOG         = Logger.getLogger(DisplayLCD.class.getName());
    /** Dependency for the management of files*/
    private FileManager   fileManager = new FileManager();
    /** Attribute that indicates the input path */
    private String        outputPath;
    /** Attribute that indicates the output path*/
    private String        inputPath;

    /**
     * DisplayLCD, class constructor</br>
     * constructor in charge of initializing class
     * @param inputPath, parameter that indicates the input path
     * @param outputPath, parameter that indicates the output path
     * */
    public DisplayLCD(final String inputPath, final String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    /**
     * printNumbersOnLCD</br>
     * Method in charge of reading the numbers in the entry and </br>
     * proceed and print them in the output file   
     * */
    public void printNumbersOnLCD() {
        int numbersCount = 0;
        // We retrieve the list of numbers to print
        List<String> listNumbers = getFileManager().readFile(String.format(Constants.PATH_FORMAT, getInputPath(), Constants.DEFAULT_INPUT_FILE_NAME));
        // Validate if records have been retrieved correctly
        if (listNumbers != null && !listNumbers.isEmpty()) {
            // Assign the path of the output file
            getFileManager().setOutputPath(getOutputPath());
            // We created the output file
            if (getFileManager().createFileOutPut()) {
                // Initialize precondition
                boolean isFinalInstruction = false;
                while (listNumbers.size() > numbersCount && !isFinalInstruction) {
                    // We get the records
                    String record = listNumbers.get(numbersCount);
                    // In case of the instruction 0,0 we finish the execution
                    if (!Constants.FINAL_INSTRUCTION.equals(record)) {
                        // We do the split (by comma,) to get the two parameters
                        String[] parameters = record.split(",");
                        // Validate that the two required parameters are obtained and print the number
                        if (parameters != null && parameters.length == 2 && printNumber(parameters[0], parameters[1])) {
                            LOG.info((String.format(LogMessages.MSG_INFO_NUMBER_PRINTER, parameters[1], parameters[0])));
                            numbersCount++;
                            // We move on to the next iteration
                            continue;
                        } else {
                            LOG.info((String.format(LogMessages.MSG_INFO_REGISTRATION_PARAMETERS, record)));
                        }

                    } else {
                        LOG.info((String.format(LogMessages.MSG_INFO_FINAL_RECORD_PROCESSED, Constants.FINAL_INSTRUCTION)));

                    }
                    // We finished the execution
                    isFinalInstruction = true;
                }

            } else {
                // In case of NOT being able to recover records a log is generated
                LOG.info(String.format(LogMessages.MSG_INFO_NOT_FOUNT_RECORDS, getInputPath()));
            }
        }
    }

    /**
     * printNumber</br>
     * Method to print one by one the numbers entered in a file </br>
     * @param pSize, Size in which numbers are printed
     * @param pNumber, Number to print
     * @return True in case of printing the number, false otherwise
     * */
    private boolean printNumber(final String pSize, final String pNumber) {
        // We validate that the parameters are not vacuous or null        
        if (pSize != null && !pSize.isEmpty() && pNumber != null && !pNumber.isEmpty()) {
            try {
                // We convert to integer received size
                int size = Integer.parseInt(pSize);
                // We validate that the sent size is greater than zero
                if (size > 0) {
                    // Initialize index of instructions
                    int indexInstrution = 0;
                    // While the index is less than five we continue iterating
                    while (indexInstrution < 5) {
                        StringBuilder line = new StringBuilder();
                        // We get each number to print
                        for (char character : pNumber.toCharArray()) {
                            // We interpret how to print the number using the method  builLine
                            builLine(line, InstructionsToDrawNumbers.getInstruction(character, indexInstrution), size);
                        }
                        // En cada iteración impar aplicamos n(size) veces la instruccion
                        if (indexInstrution % 2 != 0) {
                            for (int sizeCount = 0; sizeCount < size; sizeCount++) {
                                // We print the line
                                getFileManager().writeLine(line.toString(), true);
                                LOG.info(String.format(LogMessages.MSG_INFO_PRINT_LINE, line.toString()));
                            }
                        } else {
                            // We print the line
                            getFileManager().writeLine(line.toString(), true);
                            LOG.info(String.format(LogMessages.MSG_INFO_PRINT_LINE, line.toString()));
                        }

                        indexInstrution++;
                    }
                    return true;
                } else {
                    // The size sent is not valid
                    LOG.info(String.format(LogMessages.MSG_SIZE_NOT_VALID, pSize));
                }
            } catch (Exception e) {
                LOG.error(String.format(LogMessages.MSG_ERROR_TYPE_SIZE, pSize));
            }
        }
        return false;
    }

    /**instruccion con la que se dibujara el numero
     * builLine</br>
     * Method to build the line to be printed </br>
     * @param pLine, Current line
     * @param pInstruction, Instruction with which to draw the number
     * @return pSize,Size in which numbers are printed
     * */
    private void builLine(final StringBuilder pLine, final String pInstruction, final int pSize) {
        int index = pInstruction.indexOf(Constants.REPETITIVE_EXPRESSION);
        // If there is a repetitive instruction(*) we perform it
        if (index >= 0) {
            // build the line to be printed
            pLine.append(pInstruction.replace(Constants.REPETITIVE_EXPRESSION, String.join("", Collections.nCopies(pSize - 1, Character.toString(pInstruction.charAt(index + 1))))).replace(Constants.BLANK_EXPRESSION, Constants.SPACE_EXPRESSION));
        } else {
            // In case there is NO repetitive instruction(*), we only apply once the instruction
            pLine.append(pInstruction.replace(Constants.BLANK_EXPRESSION, Constants.SPACE_EXPRESSION));
        }
        pLine.append(Constants.SPACE_EXPRESSION);
    }

    /**
     * getFileManager
     * Method getter for File Manager dependency
     * @return File Manager dependency
     */
    public FileManager getFileManager() {
        return fileManager;
    }

    /**
     * setFileManager
     * Setter method to assign File Manager dependency
     * @param File Manager dependency
     */
    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * getOutputPath
     * Method getter for path outputfile
     * @return path outputfile
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * setOutputPath
     * Setter method to assign  path outputfile
     * @param  path outputfile
     */
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * getInputPath
     * Method getter for  path inputfile
     * @return path inputfile
     */
    public String getInputPath() {
        return inputPath;
    }

    /**
     * setInputPath
     * Setter method to assign path inputfile
     * @param   path inputfile
     */
    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

}
