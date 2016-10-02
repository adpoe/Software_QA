import java.util.LinkedList;
import java.util.Random;



/**
 * Driver class
 */
public class Driver {
    // fields
    static int max = 4; // used for setting bounds on RNG
    int driverNum;
    int cupsCoffee;
    Random rng;
    Graph city;    // the whole city as a graph
    Node location; // current location


    public Driver(int seed, int driverNum) {
        /* CREATE RNG */
        this.rng = new Random();
        rng.setSeed(seed);

        /* SET DRIVER NUM and COFFEE NUM */
        this.driverNum = driverNum;
        this.cupsCoffee = 0;

        /* CREATE NODES */
        Node hotel = new Node("Hotel", false);
        Node diner = new Node("Diner", false);
        Node library = new Node("Library", false);
        Node coffee = new Node("Coffee", true);
        Node outside_city = new Node("Outside City", false);


        /* CREATE STREET EDGES */
        // bill street edges
        Edge billStreet_HotelToLib = new Edge(hotel, library, "Bill St.", false);
        Edge billStreet_LibToHotel = new Edge(library, hotel, "Bill St.", false);
        // phil street edges
        Edge philStreet_DinerToCoffee = new Edge(diner, coffee, "Phil St.", false);
        Edge philStreet_CoffeeToDiner = new Edge(coffee, diner, "Phil St.", false);


        /* CREATE AVENUE EDGES */
        // fourth ave edges
        Edge fourth_OCtoHotel = new Edge(outside_city, hotel, "Fourth Ave.", false);
        Edge fourth_HotelToDiner = new Edge(hotel, diner, "Fourth Ave.", false);
        Edge fourth_DinerToOC = new Edge(diner, outside_city, "Fourth Ave.", true);
        // fifth ave edges
        Edge fifth_OCtoCoffee = new Edge(outside_city, coffee, "Fifth Ave.", false);
        Edge fifth_CoffeeToLib = new Edge(coffee, library, "Fifth Ave.", false);
        Edge fifth_LibToOC = new Edge(library, outside_city, "Fifth Ave.", true);


        /* ATTACH EDGES TO NODES */
        // hotel
        hotel.addStreet(billStreet_HotelToLib);
        hotel.addStreet(fourth_HotelToDiner);

        // diner
        diner.addStreet(philStreet_DinerToCoffee);
        diner.addStreet(fourth_DinerToOC);

        // coffee
        coffee.addStreet(philStreet_CoffeeToDiner);
        coffee.addStreet(fifth_CoffeeToLib);

        // library
        library.addStreet(billStreet_LibToHotel);
        library.addStreet(fifth_LibToOC);

        // outside city
        // not sure if should add exit nodes from outside of city... then can just check on assignment,
        // if no exit nodes, pick again


        /* INSERT NODES TO GRAPH */
        // put all the nodes in a set, then add set to the the graph, so this can all be done in one internal call
        LinkedList<Node> allNodes = new LinkedList<Node>();
        allNodes.addLast(hotel);
        allNodes.addLast(diner);
        allNodes.addLast(library);
        allNodes.addLast(coffee);
        allNodes.addLast(outside_city);
        this.city = new Graph(allNodes);

        /* GET ENTRY POINT TO THE GRAPH */
        // get the next random num
        int randNum = this.getRandomNum();
        randNum = this.getRandomNum();

        // index into the graph with this number
        this.location = this.city.getEntryPoint(randNum);
        // make sure that we don't start at 'outside city'
        while (this.location.getLocationName().equals("Outside City")) {
            randNum = this.getRandomNum();
            this.location = this.city.getEntryPoint(randNum);
        }

        //this.simulate();

    }

    public void printDetails(Edge street) {
        // print what happened last iteration
        System.out.printf("Driver %d heading from %s to %s via %s\n",
                            this.driverNum, street.from.getLocationName(), street.to.getLocationName(), street.streetName);

        // print where we are leaving to, and how many cups of coffee consumed
        if (street.to.getLocationName().equals("Outside City")) {
            if (street.streetName.equals("Fourth Ave.")) {
                // Philadelphia
                System.out.printf("Driver %d has gone to Philadelphia!\n", this.driverNum);
            }
            if (street.streetName.equals("Fifth Ave.")) {
                // Cleveland
                System.out.printf("Driver %d has gone to Cleveland!\n", this.driverNum);
            }
            // print how many cups of coffee
            System.out.printf("Driver %d got %d cup(s) of coffee.\n", this.driverNum, this.cupsCoffee);
        }

    }

    public void simulate() {
        // start simulation
        do {

            // check if location has coffee!
            if (this.location.getHasCoffee()) {
                // and increment coffee value if so
                this.cupsCoffee++;
            }

            // get the random street
            int randNum = this.getRandomNum();
            Edge nextStreet = this.getNextStreet(randNum);

            // pass edge to print details every iteration
            this.printDetails(nextStreet);

            // set location to the "to" value in our edge
            this.location = nextStreet.to;

            // run while we are NOT outside the city
        } while (!this.location.getLocationName().equals("Outside City"));

    }

    public Edge getNextStreet(int randNum) {
        return this.location.getRandStreet(randNum);
    }

    private int getRandomNum() {
        return rng.nextInt(max);
    }
}
