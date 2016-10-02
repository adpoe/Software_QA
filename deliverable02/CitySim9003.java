
public class CitySim9003 {

    public static void main(String[] args) {

        // ensure we have one, and EXACTLY one argument
        if (args.length != 1) {
            System.out.println("Usage:  java citySim9003 [Integer]");
            System.out.println("The integer you pass in will be used as a seed for the simulation.");
            System.out.println("You must enter EXACTLY one integer for your seed.");
            System.exit(-1);
        }

        // parse the seed value we need
        int seed = 0;
        try {
            seed = Integer.parseInt(args[0]);
        } catch(NumberFormatException nfe) {
            System.out.println("Usage:  java citySim9003 [Integer]");
            System.out.println("The integer you pass in will be used as a seed for the simulation.");
            System.out.println("You must enter EXACTLY one integer for your seed.");
            System.exit(-1);
        }

        // ensure that the seed isn't negative and won't overflow,
        // keeping in mind that I am increasing the seed value by 13 every iteration,
        // mostly because 13 is a prime, but still small enough to reasonably be an incrementer.
        if (seed < 0) {
            seed = Math.abs(seed);
        }

        if (seed > Integer.MAX_VALUE - (5 * 13)) {
            seed -= 1000;
        }

        // set the driver number before starting the simulation itself
        int driverNum = 1;


        // Run the simulation for 5 drivers, and change the seed every time
        while (driverNum <= 5) {
            // initialize the simulation with our random seed
            Driver driver = new Driver(seed, driverNum);
            // take a simulation step
            driver.simulate();
            // increment driver number and seed
            driverNum++;
            seed+=13;
            // separator between drivers, for visual clarity
            System.out.println("-----");
        }

        // done!
        return;
    }

}
