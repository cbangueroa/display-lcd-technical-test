package com.psl.utils;

/**
 * InstructionsToDrawNumbers
 * Enum class describing how to draw numbers on the LCD
 * */
public enum InstructionsToDrawNumbers
{
    // For this application it has been chosen to represent the blanks with the character B. 
    // Similarly is made use of the characters | And - to draw the requested numbers. 
    // The symbol * implies that the next character will be repeated n times depending on 
    // the size supplied in the input. 

    ONE('1', new String[] { "B", "|", "B", "|", "B" }),
    TWO('2', new String[] { "B*-B", "B*B|", "B*-B", "|B*B", "B*-B" }),
    THREE('3', new String[] { "*-B", "*B|", "*-B", "*B|", "*-B" }),
    FOUR('4', new String[] { "B*BB", "|*B|", "B*-B", "*BB|", "B*BB" }),
    FIVE('5', new String[] { "B*-B", "|*BB", "B*-B", "*BB|", "B*-B" }),
    SIX('6', new String[] { "B*-B", "|*BB", "B*-B", "|*B|", "B*-B" }),
    SEVEN('7', new String[] { "B*-B", "*BB|", "B*BB", "*BB|", "B*BB" }),
    EIGHT('8', new String[] { "B*-B", "|*B|", "B*-B", "|*B|", "B*-B" }),
    NINE('9', new String[] { "B*-B", "|*B|", "B*-B", "*BB|", "B*-B" }),
    ZERO('0', new String[] { "B*-B", "|*B|", "B*BB", "|*B|", "B*-B" });

    /** Atributo que indica el número a imprimir */
    private final char     number;
    /** Attribute indicating the instruction by which the number will be printed */
    private final String[] instructions;

    /**
     * InstructionsToDrawNumber
     * @param number, Number to print
     * @param instructions, Instruction to print the number
     * */
    InstructionsToDrawNumbers(char number, String[] instructions) {
        this.number = number;
        this.instructions = instructions;
    }

    /**
     * getInstruction
     * Method responsible for returns the instructions for printing a number
     * @param number, Number to print
     * @param id instructions, id instruction to print the number
     * */
    public static String getInstruction(final char pNumber, final int pIndexInstrution) {
        for (InstructionsToDrawNumbers intr : InstructionsToDrawNumbers.values()) {
            if (intr.getNumber() == pNumber) {
                return intr.getInstructions()[pIndexInstrution];
            }
        }
        return "";
    }

    /**
     * getNumber
     * Method to return the number to be printed
     * @return  Number to print
     */
    public char getNumber() {
        return number;
    }

    /**
     * getInstructions
     * Metodo encargado de retorna las instrucciones para imprimir un numero
     * @return instructions to print the number
     */
    public String[] getInstructions() {
        return instructions;
    }

}
