package com.psl.logic.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;

import com.psl.utils.Constants;
import com.psl.utils.LogMessages;

/**
 * FileManager
 * Class in charge of file management
 * */
public class FileManager {
    /** Logger of FileManager class*/
    private static Logger LOG = Logger.getLogger(FileManager.class.getName());
    /** Atributo que indica la ruta en la que se deben generar los reportes */
    private String        outputPath;

    /** 
     * readFile</br>
     * Method to read the lines of a file and return a list with each </br>
     * string found in the file.
     * @param pPathInputFile, Path from which to read the file
     * @return list with each string found in the file
     */
    public List<String> readFile(final String pPathInputFile) {
        try {
            Path path = Paths.get(pPathInputFile);
            return Files.readAllLines(path, Constants.ENCODING);
        } catch (IOException exc) {
            LOG.error(String.format(LogMessages.MSG_ERROR_READ_INPUT_FILE, pPathInputFile), exc);
        }
        return null;
    }

    /** 
     * createFileOutPut</br>
     * Method responsible for creating the output file </br>
     * @return True in case of creating the file, false otherwise
     */
    public boolean createFileOutPut() {
        if (writeLine(Constants.HEADER_FILE, false)) {
            LOG.info(String.format(LogMessages.MSG_INFO_CREATE_OUTPUT_FILE, getOutputPath()));
            return true;
        } else {
            // In case it is not possible to create the file out.txt a log is generated
            LOG.info(String.format(LogMessages.MSG_INFO_NO_CREATE_OUTPUT_FILE, getOutputPath()));
            return false;
        }
    }

    /** 
     * writeLineReport</br>
     * Method to create and write in a line in the file out.txt
     * @param pMessage, Message to write to the file
     * @param pOverwriteFile, Boolean parameter indicating whether to overwrite the file
     * @return True if it is possible to register the line in the file, false otherwise
     * */
    public boolean writeLine(final String pMessage, final boolean pOverwriteFile) {
        // We validate if the path of the output file has been previously assigned
        if (getOutputPath() != null && !getOutputPath().isEmpty()) {
            PrintWriter pw = null;
            try {
                // Open or create the file in the pat
                File file = new File(getOutputPath());
                // We indicate whether to overwrite the file
                FileWriter fw = new FileWriter(file, pOverwriteFile);
                pw = new PrintWriter(fw);
                // We write the line in the file
                pw.println(pMessage);
            } catch (IOException exc) {
                // In case of a problem, a log is recorded
                LOG.error(String.format(LogMessages.MSG_ERROR_WRITE_IN_OUTPUT_FILE, getOutputPath()), exc);
                return false;
            } finally {
                // We closed the file
                if (pw != null) {
                    pw.close();
                }
            }
        }
        return true;
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
    public void setOutputPath(final String pOutputPath) {
        this.outputPath = String.format(Constants.PATH_FORMAT, pOutputPath, Constants.DEFAULT_OUTPUT_FILE_NAME);
    }

}
