import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;


public class NodeTest {

    @SuppressWarnings("unchecked")

    @Mock
    Node mockedNode = Mockito.mock(Node.class);
    Edge edgeMock = Mockito.mock(Edge.class);

    @Before
    public void setUp() throws Exception {
      MockitoAnnotations.initMocks(mockedNode);
    }

    @After
    public void tearDown() throws Exception {
      // none needed
    }


    //////////////////////////////////
    //////// TEST ADD STREET /////////
    //////////////////////////////////

    // Test that we can add streets to a node, by making sure that adding an Edge
    // increases the size of the internal edge-list.
    @Test
    public void addStreetSucceeds() throws Exception {
      // ensure that size is 0 before adding a street
      Node n = new Node("Hillman", true);
      assertEquals(n.edgeList.size(), 0);
      // then add a street and make sure that the size is 1 afterwards
      n.addStreet(edgeMock);
      assertEquals(n.edgeList.size(),1);
    }


    //////////////////////////////////
    //////// TEST GET STREET /////////
    //////////////////////////////////
    // Test that we successfully get a null value if there are no values
    // added to a node's edge-list yet.
    @Test
    public void getStreetFails() throws Exception {
      Node n = new Node("Hillman", true);
      // make the edge, but don't add it
      Edge e = new Edge(mockedNode, mockedNode, "StreetName", false);
      assertNull(n.getRandStreet(1));
    }

    // Test that we can get the expected node back, after passing it in.
    @Test
    public void getStreetSucceeds() throws Exception {
      Node n = new Node("Hillman", true);
      n.addStreet(edgeMock);
      assertEquals(n.getRandStreet(1), edgeMock);
    }


    /*
    /////////////////////////////////////////
    //////// TEST GET LOCATION NAME /////////
    /////////////////////////////////////////
    @Test
    public void getLocationName() throws Exception {

    }


    //////////////////////////////////
    //////// TEST GET COFFEE /////////
    //////////////////////////////////
    @Test
    public void getHasCoffee() throws Exception {

    }

    // The following two tests should always pass.
    // They don't really check anything.

    @Test
    public void testShouldPass() {
    	assertEquals(1, 1);
    }

    @Test
    public void testShouldAlsoPass() {
    	int x = 1;
    	int y = 1;
    	int z = x + y;
    	assertTrue(z == 2);
    }

    // This test should ALWAYS fail - it creates an object
    // and then checks if its ref is null.

    @Test
    public void testShouldFail() {
    	Object o = null;
    	assertNull(o);
    }

    // Actual Noogie Tests - from here to the end of the file,
    // these actually test aspects of the Noogie class.

    // Test to see that if we create a Noogie object with 0 cats,
    // getting the number of cats will return 0.

    @Test
    public void testNoogieNumCats0() {
    	Noogie n = new Noogie(0);
    	assertEquals(n.getNumCats(), 0);
    }

    // Test to see that if we create a Noogie object with 10 cats,
    // getting the number of cats will return 10.

    @Test
    public void testNoogieNumCats10() {
    	Noogie n = new Noogie(10);
    	assertEquals(n.getNumCats(), 10);
    }

    // Test that for a Noogie with a positive # of cats, if we call
    // the negativeCats() method, it will return the correct
    // number of (negative) cats.

    @Test
    public void testNegative() {
    	Noogie n = new Noogie(5);
    	assertEquals(n.negativeCats(), -5);
    }

    // Test that for a Noogie with a negative # of cats, if we call
    // the negativeCats() method, it will return the correct
    // number of (positive) cats.

    @Test
    public void testDoubleNegative() {
    	Noogie n = new Noogie(-5);
    	assertEquals(n.negativeCats(), 5);
    }

    // Test adding a positive number of cats.

    @Test
    public void testAdd1() {
    	Noogie n = new Noogie(0);
    	n.addSomeCats(1);
    	assertEquals(n.getNumCats(), 1);
    }

    // Test adding a negative number of cats throws an exception.

    @Test
    public void testNegativeAdd() {
    	Noogie n = new Noogie(0);
    	try {
    	    n.addSomeCats(-2);
    	    // Note that if fail() is called, result will be "null" and that is
    	    // what will be displayed in the TestRunner
    	    fail();
    	} catch (ArithmeticException aex) {
    	    // expected behavior
    	}
    	// Number of cats should remain 0 (initial value)
    	assertEquals(n.getNumCats(), 0);

    }

    // Test adding cats multiple times.

    @Test
    public void testMultipleAdds() {
    	Noogie n = new Noogie(0);
    	for (int j = 0; j < 10; j++) {
    	    n.addSomeCats(5);
    	}
    	assertEquals(n.getNumCats(), 50);
    }
    */


}
