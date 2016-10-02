
public class Main {

    public static void main(String[] args) {

        // grab the seed from command line... pass to driver, increment 5x
	    // create random seed
        // need to take this as an input arg
        int seed = 1;
        int driverNum = 1;


        // Run the simulation for 5 drivers, and change the seed every time
        while (driverNum <= 5) {
            // initialize the simulation with our random seed
            Driver driver = new Driver(seed, driverNum);
            // run the simulation
            driver.simulate();
            driverNum++;
            seed+=13; // need to make sure we don't go OVER the limit...
                      // because he'll test probably need to start over if we go above max
            System.out.println("-----");
        }

        // done!
        return;
    }

}
