import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.util.LinkedList;
import java.io.*;

public class DriverTest {
  @SuppressWarnings("unchecked")

  @Mock
  Driver driverMock = Mockito.mock(Driver.class);
  Node mockedNode1  = Mockito.mock(Node.class);
  Node mockedNode2  = Mockito.mock(Node.class);
  Edge edgeMock     = Mockito.mock(Edge.class);
  Edge targetEdge   = Mockito.mock(Edge.class);
  Graph graphMock   = Mockito.mock(Graph.class);

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(driverMock);
    MockitoAnnotations.initMocks(mockedNode1);
    MockitoAnnotations.initMocks(mockedNode2);
    MockitoAnnotations.initMocks(edgeMock);
    MockitoAnnotations.initMocks(targetEdge);
    MockitoAnnotations.initMocks(graphMock);
  }

  @After
  public void tearDown() throws Exception {
    // none needed
  }

  //////////////////////////////
  ////// TEST CONSTRUCTOR //////
  //////////////////////////////
  // Ensure that we are able to initialize a Driver object correctly.
  @Test
  public void testDriverConstructorWorksAsExpected() throws Exception {
    // test the driver constructor
    Driver d = new Driver(1,1);

    // perform assertions
    assertEquals(d.max, 4);
    assertEquals(d.driverNum, 1);
    assertEquals(d.city.elements.size(), 5);
  }

  // Ensure that we are able to initialize a Driver object with expected values
  // consistently, and we are not getting false positives.
  @Test
  public void testDriverConstructorIsConsistent() throws Exception {
    // test the driver constructor
    Driver d = new Driver(1,1);

    // perform assertions
    assertNotEquals(d.max, 5);
    assertNotEquals(d.driverNum, 2);
    assertNotEquals(d.city.elements.size(), 6);
  }



  ///////////////////////////////
  /////// TEST SIMULATION ///////
  ///////////////////////////////

  // Ensure that internal driver fields are updated correctly as the simulation
  // takes a step. We do this by creating a set of stubs and calling the method
  // to get the the next street in our simulation, getNextStreet().
  // The goal is to ensure that we are getting the expected next street, and
  // thus we are NOT moving in an unexpected direction.
  @Test
  public void testSimulationStep() throws Exception {
    // Create stubs/mocks to insert into program and call intentionally
    Mockito.when(mockedNode1.getLocationName()).thenReturn("Hillman Library");
    Mockito.when(mockedNode2.getLocationName()).thenReturn("Sennott Square");
    Edge e = new Edge(mockedNode1, mockedNode2, "Forbes Ave.", true);

    // create our driver
    Driver d = new Driver(1,1);
    // ensure that calling get randStreet will return our example edge, "e"
    Mockito.when(mockedNode1.getRandStreet(Mockito.any(Integer.class))).thenReturn(e);

    // we know that the city has 5 nodes, so mockedNode1 will be the 6th
    d.city.elements.addLast(mockedNode1);
    d.city.elements.addLast(mockedNode2);

    // set the current location to mockedNode1
    d.location = mockedNode1;
    // ensure that we are currently at Hillman
    assertEquals(d.location.getLocationName(), "Hillman Library");

    // and ensure that we move to Sennott Square from there,
    // since we have it stubbed.
    // My goal is to make sure we are updating internal values correctly,
    // as this is the 'driver' of the whole simulation, essentially.
    // The ability to move forward in an expected way.
    assertEquals(d.getNextStreet(1).to.getLocationName(), "Sennott Square");
  }

}
