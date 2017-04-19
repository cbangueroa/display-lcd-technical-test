package com.psl.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Constants
 * Class containing the application constants
 * */
public class Constants {
    /**Constant indicating the default output path */
    public static final String  DEFAULT_OUTPUT_PATH      = "src/main/resources/files/output/";
    /**  Constant indicating the name of the default output file */
    public static final String  DEFAULT_OUTPUT_FILE_NAME = "out.txt";
    /** Constant that constructs the routes of the files */
    public static final String  PATH_FORMAT              = "%s%s";
    /** Constant indicating the path in which records are to be read */
    public static final String  DEFAULT_INPUT_PATH       = "src/main/resources/files/input/";
    /** Constant indicating the name of the default input file*/
    public static final String  DEFAULT_INPUT_FILE_NAME  = "in.txt";
    /** Constant indicating the encoding with which the files will be manage*/
    public final static Charset ENCODING                 = StandardCharsets.UTF_8;
    /**  Constant indicating the final instruction */
    public static final String  FINAL_INSTRUCTION        = "0,0";
    /** Repetitive expression */
    public static final String  REPETITIVE_EXPRESSION    = "*";
    /** Blank expression */
    public static final String  BLANK_EXPRESSION         = "B";
    /** Space expression */
    public static final String  SPACE_EXPRESSION         = " ";
    /** Header files */
    public static final String  HEADER_FILE              = "DISPLAY LCD";
}
