import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.util.LinkedList;

public class GraphTest {
  @SuppressWarnings("unchecked")

  @Mock
  Node mockedNode = Mockito.mock(Node.class);
  Node targetNode = Mockito.mock(Node.class);
  Edge edgeMock = Mockito.mock(Edge.class);
  Edge targetEdge = Mockito.mock(Edge.class);

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(mockedNode);
    MockitoAnnotations.initMocks(edgeMock);
  }

  @After
  public void tearDown() throws Exception {
    // none needed
  }

  ///////////////////////////////
  ////// TEST CONSTRUCTOR ///////
  ///////////////////////////////

  // Ensure that we are able to initialize a graph correctly.
  @Test
  public void testGraphConstructor() throws Exception {
      LinkedList<Node> ll = new LinkedList<Node>();
      // add three nodes to our list, with a stub
      Mockito.when(mockedNode.getLocationName()).thenReturn("Hillman");
      ll.addLast(mockedNode);
      ll.addLast(mockedNode);
      ll.addLast(mockedNode);

      // pass the list into our graph
      Graph g = new Graph(ll);

      assertEquals(g.elements.size(), 3);
      assertEquals(g.elements.get(2).getLocationName(), "Hillman");
  }

  ////////////////////////////////
  ///// TEST GET ENTRY POINT /////
  ////////////////////////////////

  // Test that we can successfully an entry point for the node we are
  // we are expecting.
  @Test
  public void testGetEntryPointReturnsExpectedValue() throws Exception {
      // Stub some function return values for mockedNode
      Mockito.when(mockedNode.getRandStreet(Mockito.any(Integer.class))).thenReturn(edgeMock);
      Mockito.when(mockedNode.getLocationName()).thenReturn("Hillman");

      // Stub some function return values for targetNode
      Mockito.when(targetNode.getRandStreet(Mockito.any(Integer.class))).thenReturn(targetEdge);
      Mockito.when(targetNode.getLocationName()).thenReturn("Target");

      // Make a list and fill it with all mock nodes, and one target
      LinkedList<Node> ll = new LinkedList<Node>();
      for (int i=0; i<10; i++) {
        ll.addLast(mockedNode);
      }

      // Add our target node at the end, it'll be in position 10
      ll.addLast(targetNode);
      Graph g = new Graph(ll);

      // Then get the node at 10, and ensure it is the target node.
      Integer i = 10;
      Node t = g.getEntryPoint(i);
      assertEquals(t.getLocationName(), "Target");
      assertEquals(t.getRandStreet(i), targetEdge);
      }

      // Ensure that getEntryPoint() doesn't ALWAYS return the same value,
      // by checkign that if we pass in a value which is NOT our target value,
      // then the return Node's attributes are NOT those of the targetNode.
      @Test
      public void testGetEntryPointIsConsistent() throws Exception {
          // Stub some function return values for mockedNode
          Mockito.when(mockedNode.getRandStreet(Mockito.any(Integer.class))).thenReturn(edgeMock);
          Mockito.when(mockedNode.getLocationName()).thenReturn("Hillman");

          // Stub some function return values for targetNode
          Mockito.when(targetNode.getRandStreet(Mockito.any(Integer.class))).thenReturn(targetEdge);
          Mockito.when(targetNode.getLocationName()).thenReturn("Target");

          // Make a list and fill it with all mock nodes, and one target
          LinkedList<Node> ll = new LinkedList<Node>();
          for (int i=0; i<10; i++) {
            ll.addLast(mockedNode);
          }

          // Add our target node at the end, it'll be in position 10
          ll.addLast(targetNode);
          Graph g = new Graph(ll);

          // Then get the node at 9, and ensure it is NOT the target node.
          Integer i = 9;
          Node t = g.getEntryPoint(i);
          assertNotEquals(t.getLocationName(), "Target");
          assertNotEquals(t.getRandStreet(i), targetEdge);
          }


}
