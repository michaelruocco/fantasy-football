package uk.co.mruoc.fantasyfootball.app.web;

public class LastPageCalculator {

    public static int calculate(int totalPages) {
        if (totalPages > 0) {
            return totalPages - 1;
        }
        return 0;
    }

}
