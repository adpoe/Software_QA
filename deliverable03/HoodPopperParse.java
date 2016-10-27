
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I would like to see HoodPopper parse input data correctly
 * so that I can ensure that my Ruby code is running efficiently
 * @author Anthony (Tony) Poerio
 *
 */
public class HoodPopperParse {

    static WebDriver driver = new HtmlUnitDriver();

    // Navigate to the HoodPopper homepage before each test begins
    @Before
    public void setUp() throws Exception {
        driver.get("http://lit-bayou-7912.herokuapp.com/");
    }

    // Given that I am on home page
    // When I parse a string containing a newline white-space element
    // Then I see that newline whitespace tokens are NOT contained in the AST parse tree
    @Test
    public void testParseWhiteSpaceNewline() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("a=6\nb=7\n");

        // grab the parse button and click it, sending our data in the inputBox
        WebElement parseButton = driver.findElement(By.xpath("//input[@value='Parse']"));
        parseButton.click();

        // get the returned parse data
        WebElement parseData = driver.findElement(By.tagName("code"));
        String parse = parseData.getText();

        // ensure that the returned parse data does not contain a newline
        assertFalse(parse.contains("\"\n\""));
    }


    // Given that I am on home page
    // When I parse a string containing a blank-space white-space element
    // Then I see that blank-space whitespace tokens are NOT contained in the AST parse tree
    @Test
    public void testParseBlankSpace() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("a  =  6\n b  =  7   \n");

        // grab the parse button and click it, sending our data in the inputBox
        WebElement parseButton = driver.findElement(By.xpath("//input[@value='Parse']"));
        parseButton.click();

        // get the returned parse data
        WebElement parseData = driver.findElement(By.tagName("code"));
        String parse = parseData.getText();

        // ensure that the returned parse data does not contain a blank-space
        assertFalse(parse.contains("\" \""));
    }

    // Given that I am on home page
    // When I parse a string containing a variable name
    // Then I see that variable name as a token contained in the AST parse tree
    @Test
    public void testParseVariableNames() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("num = 6\nfloat = 6.7");

        // grab the parse button and click it, sending our data in the inputBox
        WebElement parseButton = driver.findElement(By.xpath("//input[@value='Parse']"));
        parseButton.click();

        // get the returned parse data
        WebElement parseData = driver.findElement(By.tagName("code"));
        String parse = parseData.getText();

        // ensure that the returned parse data contains the variables we passed in
        assertTrue(parse.contains("\"num\""));
        assertTrue(parse.contains("\"float\""));
    }


    // Given that I am on home page
    // When I parse a string containing a numerical operator
    // Then I see that numerical operator contained in the AST parse tree
    @Test
    public void testParseOperators() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("c = a + (b * 4) - 12 / 3");

        // grab the parse button and click it, sending our data in the inputBox
        WebElement parseButton = driver.findElement(By.xpath("//input[@value='Parse']"));
        parseButton.click();

        // get the returned parse data
        WebElement parseData = driver.findElement(By.tagName("code"));
        String parse = parseData.getText();

        // ensure that the returned parse data contains each operator
        assertTrue(parse.contains(":+"));
        assertTrue(parse.contains(":*"));
        assertTrue(parse.contains(":-"));
        assertTrue(parse.contains(":/"));
    }


    // Given that I am on home page
    // When I parse a string containing a standard Ruby function name
    // Then I see that function name as token contained in the AST parse tree
    @Test
    public void testParseFunctions() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("puts 'hello world!'");

        // grab the parse button and click it, sending our data in the inputBox
        WebElement parseButton = driver.findElement(By.xpath("//input[@value='Parse']"));
        parseButton.click();

        // get the returned parse data
        WebElement parseData = driver.findElement(By.tagName("code"));
        String parse = parseData.getText();

        // ensure that the returned parse contains the function name we passed in (puts)
        assertTrue(parse.contains("\"puts\""));
    }


    // Given that I am on home page
    // When I parse a string containing multiple numerical operators
    // Then I see that each numerical operator is contained in the AST parse tree
    @Test
    public void testParseMultipleOperators() {
        // grab the input box and send data to it
        WebElement inputBox = driver.findElement(By.id("code_code"));
        inputBox.sendKeys("num = 5 + 6 + 7 + 8 + 9 + 10 - 2 -3");

        // grab the parse button and click it, sending our data in the inputBox
        WebElement parseButton = driver.findElement(By.xpath("//input[@value='Parse']"));
        parseButton.click();

        // get the returned parse data
        WebElement parseData = driver.findElement(By.tagName("code"));
        String parse = parseData.getText();

        // split tokens by commas so we can count how many of each operator was parsed
        String[] lines = parse.split(",");
        int numPlus = 0;
        int numMinus = 0;

        // iterate through each line we just split
        for (String l : lines) {
            // count plus operators
            if (l.contains(":+")) {
                numPlus++;
            }

            // count minus operators
            if (l.contains(":-")) {
                numMinus++;
            }
        }

        // ensure that the returned parse data contains each operator
        assertEquals(numPlus, 5);
        assertEquals(numMinus, 2);
        // also ensure that no operators we did NOT input return in the parse
        assertFalse(parse.contains(":*"));
    }


}
