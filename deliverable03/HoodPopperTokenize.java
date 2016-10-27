
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I would like to see HoodPopper tokenize input data correctly
 * so that I can ensure that my Ruby code is running efficiently
 * @author Anthony (Tony) Poerio
 *
 */

public class HoodPopperTokenize {

    static WebDriver driver = new HtmlUnitDriver();

    // Navigate to the HoodPopper homepage before each test begins
    @Before
    public void setUp() throws Exception {
        driver.get("http://lit-bayou-7912.herokuapp.com/");
    }

    // Given that I am on home page
    // When I tokenize a string containing a space
    // Then I see that its tokenization contains :on_sp
    @Test
    public void testTokenizeSpaces() {

        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code")) ;
        inputBox.sendKeys("a = 6");

        // grab the tokenize button and click it, sending our data in the inputBox
        WebElement tokenizeButton = driver.findElement(By.name("commit"));
        tokenizeButton.click();

        // get the returned token data
        WebElement tokenData = driver.findElement(By.tagName("code"));
        String tokens = tokenData.getText();

        // ensure that the returned token data contains an :on_sp token
        assertTrue(tokens.contains(":on_sp"));
    }


    // Given that I am on home page
    // When I tokenize a string containing a variable
    // Then I see that its tokenization contains :on_ident
    @Test
    public void testTokenizeIdentifiers() {

        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code")) ;
        inputBox.sendKeys("a");

        // grab the tokenize button and click it, sending our data in the inputBox
        WebElement tokenizeButton = driver.findElement(By.name("commit"));
        tokenizeButton.click();

        // get the returned token data
        WebElement tokenData = driver.findElement(By.tagName("code"));
        String tokens = tokenData.getText();

        // ensure that the returned token data contains an :on_sp token
        assertTrue(tokens.contains(":on_ident"));
    }

    // Given that I am on home page
    // When I tokenize a string containing a newline
    // Then I see that its tokenization contains :on_nl
    @Test
    public void testTokenizeNewlines() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code")) ;
        inputBox.sendKeys("a = 6\nb = 7");

        // grab the tokenize button and click it, sending our data in the inputBox
        WebElement tokenizeButton = driver.findElement(By.name("commit"));
        tokenizeButton.click();

        // get the returned token data
        WebElement tokenData = driver.findElement(By.tagName("code"));
        String tokens = tokenData.getText();

        // ensure that the returned token data contains an :on_sp token
        assertTrue(tokens.contains(":on_nl"));
    }

    // Given that I am on home page
    // When I tokenize a string containing an operator (+)
    // Then I see that its tokenization contains :on_op
    @Test
    public void testTokenizeOperators() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code")) ;
        inputBox.sendKeys("a = 6 + 7");

        // grab the tokenize button and click it, sending our data in the inputBox
        WebElement tokenizeButton = driver.findElement(By.name("commit"));
        tokenizeButton.click();

        // get the returned token data
        WebElement tokenData = driver.findElement(By.tagName("code"));
        String tokens = tokenData.getText();

        // ensure that the returned token data contains an :on_sp token
        assertTrue(tokens.contains(":on_op"));
    }

    // Given that I am on home page
    // When I tokenize a string containing a complex expression with multiple operators
    // Then I see that its tokenization contains each operator in the expression correctly
    @Test
    public void testTokenizeMultipleOperators() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code")) ;
        // send a more complex exprssion, with 8 total operators in it
        inputBox.sendKeys("expr =  6 + 7 + 8 + 9 - 10 * 2 * 3 / 4)");

        // grab the tokenize button and click it, sending our data in the inputBox
        WebElement tokenizeButton = driver.findElement(By.name("commit"));
        tokenizeButton.click();

        // get the returned token data
        WebElement tokenData = driver.findElement(By.tagName("code"));
        String tokens = tokenData.getText();

        // split tokens by newlines so we can count how many of each
        String[] lines = tokens.split("\n");

        int numOps = 0;
        for (String l : lines) {
            if (l.contains(":on_op")) {
                numOps++;
            }
        }

        // ensure that exactly 8 operators were parsed
        assertEquals(numOps, 8);
    }

}
