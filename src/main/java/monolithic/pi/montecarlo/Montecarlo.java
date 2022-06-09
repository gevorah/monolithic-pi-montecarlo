package monolithic.pi.montecarlo;

import java.util.Random;

public class Montecarlo {

    public Montecarlo() {
    }

    public String computePi(int targetPointsExponent, int epsilonExp, int seed) {
        long n = (long) Math.pow(10, targetPointsExponent);
        double epsilon = Math.pow(10, epsilonExp);
        Random rnd = new Random(seed);
        int nInside = 0;
        double x, y;

        long start = System.currentTimeMillis();

        for(int i=0; i<n; i++) {
            x = rnd.nextDouble();
            y = rnd.nextDouble();

            if( ((x*x) + (y*y)) <= 1 ) {
                nInside++;
            }
        }

        double pi = 4 * ((double)nInside / (double)n);

        long time = System.currentTimeMillis() - start;
        long minutes = (time/1000)/60;
        long seconds = (time/1000)%60;
        long millis = time;

        return String.format("%f-%d-%02d:%02d:%07d", pi, n, minutes, seconds, millis);
    }
}
