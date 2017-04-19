package com.psl.logic.display;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.psl.utils.Constants;

/**
 * DisplayLCDUnitTest
 * Unit testing class for the functionalities implemented in the DisplayLCD class*/
public class DisplayLCDUnitTest {
    /** Dependency class DisplayLCD */
    DisplayLCD displayLCD;

    /**
     * beforeMethod</br>
     * M&eacute;todo encargado de preparar el entorno para la ejecuci&eocute;n de las pruebas uniarias
     */
    @Before
    public void beforeMethod() {
        displayLCD = new DisplayLCD(Constants.DEFAULT_INPUT_PATH, Constants.DEFAULT_OUTPUT_PATH);
    }

    /**
     * proccesEmptyParams</br>
     * Unit test that validates the scenario in which the empty input parameters are sent.
     * Expected result:
     * - Functionality should return false
     * */
    @Test
    public void proccesEmptyParams() {
        try {
            Method printNumberMethod = DisplayLCD.class.getDeclaredMethod("printNumber", String.class, String.class);
            printNumberMethod.setAccessible(true);
            assertFalse("Functionality should return false", (Boolean) printNumberMethod.invoke(displayLCD, "", ""));
        } catch (Exception e) {
            fail("This exception should not be thrown: " + e.getMessage());;
        }
    }

    /**
     * ProcessingANonNumericSize</br>
     * Unit test that validates the scenario in which a non-numeric size is received as a parameter.
     * Expected result:
     * - Functionality should return false
     * */
    @Test
    public void ProcessingANonNumericSize() {
        try {
            Method printNumberMethod = DisplayLCD.class.getDeclaredMethod("printNumber", String.class, String.class);
            printNumberMethod.setAccessible(true);
            assertFalse("Functionality should return false", (Boolean) printNumberMethod.invoke(displayLCD, "A", "12345"));
        } catch (Exception e) {
            fail("This exception should not be thrown: " + e.getMessage());;
        }
    }

    /**
     * ProcessingZeroSizeParameter</br>
     * Unit test that validates the scenario where a zero size is received as parameter and is not the final instruction.
     * Expected result:
     * - Functionality should return false
     * */
    @Test
    public void ProcessingZeroSizeParameter() {
        try {
            Method printNumberMethod = DisplayLCD.class.getDeclaredMethod("printNumber", String.class, String.class);
            printNumberMethod.setAccessible(true);
            assertFalse("Functionality should return false", (Boolean) printNumberMethod.invoke(displayLCD, "0", "12345"));
        } catch (Exception e) {
            fail("This exception should not be thrown: " + e.getMessage());;
        }
    }
    
    
    /**
     * ProcessingNumberNotValid</br>
     * Unit test that validates the scenario where a zero size is received as parameter and is not the final instruction.
     * Expected result:
     * - The non-numeric characters are ignored and therefore the valid numbers will be drawn. Functionality should return true
     * */
    @Test
    public void ProcessingNumberNotValid() {
        try {
            Method printNumberMethod = DisplayLCD.class.getDeclaredMethod("printNumber", String.class, String.class);
            printNumberMethod.setAccessible(true);
            assertTrue("Functionality should return True", (Boolean) printNumberMethod.invoke(displayLCD, "2", "1XA3 5"));
        } catch (Exception e) {
            fail("This exception should not be thrown: " + e.getMessage());;
        }
    }
    
    
    /**
     * ProcessingNumberAndSizeValid</br>
     * Unit test that validates the scenario in which a valid record is processed
     * Expected result:
     * - Functionality should return true
     * */
    @Test
    public void ProcessingNumberAndSizeValid() {
        try {
            Method printNumberMethod = DisplayLCD.class.getDeclaredMethod("printNumber", String.class, String.class);
            printNumberMethod.setAccessible(true);
            assertTrue("Functionality should return True", (Boolean) printNumberMethod.invoke(displayLCD, "2", "123456789"));
        } catch (Exception e) {
            fail("This exception should not be thrown: " + e.getMessage());;
        }
    }

}