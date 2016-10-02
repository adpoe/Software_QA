import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;


/**
 * This test class tests all 4 public methods in the Nodes class for the project.
 * The public methods are:
 *    1) addStreet()
 *    2) getRandStreet()
 *    3) getLocationName()
 *    4) getHasCoffee()
 *
 * The Node class's Constructor method, which is also public is tested by these
 * implicitly, as we are testing node attributes using these public methods,
 * after creating a Node with its Constructor.
 */

public class NodeTest {

    @SuppressWarnings("unchecked")

    @Mock
    Node mockedNode = Mockito.mock(Node.class);
    Edge edgeMock = Mockito.mock(Edge.class);

    @Before
    public void setUp() throws Exception {
      MockitoAnnotations.initMocks(mockedNode);
      MockitoAnnotations.initMocks(edgeMock);
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

    // Test adding multiple Streets
    @Test
    public void testMultipleAdds() {
      Node n = new Node("Hillman", true);
      for (int j = 0; j < 10; j++) {
          n.addStreet(edgeMock);
      }
      assertEquals(n.edgeList.size(), 10);
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


    /////////////////////////////////////////
    //////// TEST GET LOCATION NAME /////////
    /////////////////////////////////////////

    // Ensure that we are able to store and return the Node's location name
    // correctly.
    @Test
    public void getLocationNameSucceeds() throws Exception {
      Node n = new Node("Hillman", true);
      assertEquals(n.getLocationName(), "Hillman");
    }

    // Ensure we can correctly identify incorrect return values from the
    // getLocationName() method. This is to avoid false positives in our testing.
    @Test
    public void getLocationNameFails() throws Exception {
      Node n = new Node("Library", true);
      assertNotEquals(n.getLocationName(), "Hillman");
    }


    //////////////////////////////////
    //////// TEST GET COFFEE /////////
    //////////////////////////////////

    // Ensure we are able to correctly identify when a Node (Location)
    // does have coffee.
    @Test
    public void getHasCoffeeSucceeds() throws Exception {
      Node n = new Node("Hillman", true);
      assertTrue(n.getHasCoffee());
    }

    // Ensure we are able to correctly identify when a Node (Location)
    // does NOT have coffee.
    @Test
    public void getHasCoffeeFails() throws Exception {
      Node n = new Node("Frick Building", false);
      assertFalse(n.getHasCoffee());
    }

}
