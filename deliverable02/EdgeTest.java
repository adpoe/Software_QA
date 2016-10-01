import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class EdgeTest {

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

    ////////////////////////////////////////
    //////// TEST EDGE CONSTRUCTOR /////////
    ////////////////////////////////////////

    // Test that we can successfully create an Edge and set its attributes,
    // as expected.
    @Test
    public void edgeConstructorWorks() throws Exception {
      Edge e = new Edge(mockedNode, mockedNode, "Fifth Ave.", true);
      assertNotEquals(e, edgeMock);
      assertEquals(e.to, mockedNode);
      assertEquals(e.from, mockedNode);
      assertEquals(e.streetName, "Fifth Ave.");
      assertTrue(e.exitNode);
    }


    // Test that edge constructor is not giving us false positives for its
    // expected values.
    @Test
    public void edgeConstructorFalsePositives() throws Exception {
      Edge e = new Edge(mockedNode, mockedNode, "Fifth Ave.", false);
      assertNotEquals(e.to, e);
      assertNotEquals(e.from, e);
      assertNotEquals(e.streetName, "Fourth Ave.");
      assertFalse(e.exitNode);
    }

}
