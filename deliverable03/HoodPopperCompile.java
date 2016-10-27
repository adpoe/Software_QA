
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I would like to see HoodPopper compile input data correctly
 * so that I can ensure that my Ruby code is compiling efficiently
 * @author Anthony (Tony) Poerio
 *
 */
public class HoodPopperCompile {

    static WebDriver driver = new HtmlUnitDriver();

    // Navigate to the HoodPopper homepage before each test begins
    @Before
    public void setUp() throws Exception {
        driver.get("http://lit-bayou-7912.herokuapp.com/");
    }

    // Given that I am on home page
    // When I compile an input string containing the 'puts' function
    // Then I see that 'putstring' YARV operation is in the compilation output
    @Test
    public void testCompilePuts() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("puts 'hello world!'");

        // grab the compile button and click it, sending our data in the inputBox
        WebElement compileButton = driver.findElement(By.xpath("//input[@value='Compile']"));
        compileButton.click();

        // get the returned compiler data
        WebElement compilerData = driver.findElement(By.tagName("code"));
        String compile = compilerData.getText();

        // ensure that the returned compiled data contains the 'putstring' YARV operation
        assertTrue(compile.contains("putstring"));
    }

    // Given that I am on home page
    // When I compile an input string containing the '+' operation
    // Then I see that 'opt_plus' YARV operation, as well as any values specified on the stack,
    //      using the 'putobject' operation in the compilation output
    @Test
    public void testCompilePlus() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("a = 6 + 7");

        // grab the compile button and click it, sending our data in the inputBox
        WebElement compileButton = driver.findElement(By.xpath("//input[@value='Compile']"));
        compileButton.click();

        // get the returned compiler data
        WebElement compilerData = driver.findElement(By.tagName("code"));
        String compile = compilerData.getText();

        // ensure that the returned compiled data contains the 'opt_plus' YARV operation
        assertTrue(compile.contains("opt_plus"));

        // ensure that the returned compiled data contains the specified values on the stack,
        // along with the 'putobject' YARV operation
        assertTrue(compile.contains("putobject 6"));
        assertTrue(compile.contains("putobject 7"));
    }


    // Given that I am on home page
    // When I compile an input string containing the '-' operation
    // Then I see that 'opt_minus' YARV operation, as well as any values specified on the stack,
    //      using the 'putobject' operation in the compilation output
    @Test
    public void testCompileMinus() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("a = 6 - 7");

        // grab the compile button and click it, sending our data in the inputBox
        WebElement compileButton = driver.findElement(By.xpath("//input[@value='Compile']"));
        compileButton.click();

        // get the returned compiler data
        WebElement compilerData = driver.findElement(By.tagName("code"));
        String compile = compilerData.getText();

        // ensure that the returned compiled data contains the 'opt_minus' YARV operation
        assertTrue(compile.contains("opt_minus"));

        // ensure that the returned compiled data contains the specified values on the stack,
        // along with the 'putobject' YARV operation
        assertTrue(compile.contains("putobject 6"));
        assertTrue(compile.contains("putobject 7"));
    }


    // Given that I am on home page
    // When I compile an input string containing the '/' operation
    // Then I see that 'opt_div' YARV operation, as well as any values specified on the stack,
    //      using the 'putobject' operation in the compilation output
    @Test
    public void testCompileDivide() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("a = 6 / 7");

        // grab the compile button and click it, sending our data in the inputBox
        WebElement compileButton = driver.findElement(By.xpath("//input[@value='Compile']"));
        compileButton.click();

        // get the returned compiler data
        WebElement compilerData = driver.findElement(By.tagName("code"));
        String compile = compilerData.getText();

        // ensure that the returned compiled data contains the 'opt_minus' YARV operation
        assertTrue(compile.contains("opt_div"));

        // ensure that the returned compiled data contains the specified values on the stack,
        // along with the 'putobject' YARV operation
        assertTrue(compile.contains("putobject 6"));
        assertTrue(compile.contains("putobject 7"));
    }


    // Given that I am on home page
    // When I compile an input string containing the '*' operation
    // Then I see that 'opt_mult' YARV operation, as well as any values specified on the stack,
    //      using the 'putobject' operation in the compilation output
    @Test
    public void testCompileMultiply() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("a = 6 * 7");

        // grab the compile button and click it, sending our data in the inputBox
        WebElement compileButton = driver.findElement(By.xpath("//input[@value='Compile']"));
        compileButton.click();

        // get the returned compiler data
        WebElement compilerData = driver.findElement(By.tagName("code"));
        String compile = compilerData.getText();

        // ensure that the returned compiled data contains the 'opt_minus' YARV operation
        assertTrue(compile.contains("opt_mult"));

        // ensure that the returned compiled data contains the specified values on the stack,
        // along with the 'putobject' YARV operation
        assertTrue(compile.contains("putobject 6"));
        assertTrue(compile.contains("putobject 7"));
    }


    // Given that I am on home page
    // When I parse a string containing multiple numerical operators
    // Then I see that each numerical operator's YARV operation is contained in the compilation data
    @Test
    public void testCompileMultipleOperators() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("num = 5 + 6 + 7 + 8 + 9 + 10 - 2 -3");

        // grab the parse button and click it, sending our data in the inputBox
        WebElement parseButton = driver.findElement(By.xpath("//input[@value='Compile']"));
        parseButton.click();

        // get the returned compile data
        WebElement compilerData = driver.findElement(By.tagName("code"));
        String compile = compilerData.getText();

        // split tokens by commas so we can count how many of each operator was parsed
        String[] lines = compile.split("\n");
        int numPlus = 0;
        int numMinus = 0;

        // iterate through each line we just split
        for (String l : lines) {
            // count plus operators
            if (l.contains("opt_plus")) {
                numPlus++;
            }

            // count minus operators
            if (l.contains("opt_minus")) {
                numMinus++;
            }
        }

        // ensure that the returned parse data contains each operator
        assertEquals(numPlus, 5);
        assertEquals(numMinus, 2);
        // also ensure that no operators we did NOT input return in the compile
        assertFalse(compile.contains("opt_mult"));
    }

}
