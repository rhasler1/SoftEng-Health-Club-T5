import java.util.Scanner;

public class MembershipReports {
    private static final String TRAFFIC = "clubTraffic.txt";
    //generate reports about member visits i.e. frequent members, busy hours, busiest days of the year, and total usage.
    public static void generateClubReport(){
        Scanner sc = FileOps.fileScanner(TRAFFIC);
        int totalVisits = 0;
        int[] hourlyVisits = new int[24];
        int[] monthlyVisits = new int[12];

        if(sc == null){
            System.out.println("Error, log file not found");
        }else{
            while(sc.hasNextLine()){
                String[] data = sc.nextLine().split(",");
                //String year = data[0];
                int month = Integer.parseInt(data[1]);
                //String day = data[2];
                int hour = Integer.parseInt(data[3]);

                monthlyVisits[month-1]++;
                hourlyVisits[hour-1]++;
                totalVisits += 1;
            }

            for (int i = 24; i > 0; i--) {
                if(hourlyVisits[i-1] > 0){
                    System.out.println("Hour " + i + ": " + hourlyVisits[i-1] + " visits");
                }
            }            
            for (int i = 12 ; i > 0; i--) {
                if(monthlyVisits[i-1] > 0){
                    System.out.println("Month " + i + ": " + monthlyVisits[i-1] + " visits");
                }
            }
            System.out.println("The club saw " + totalVisits + " total visits");
        }
    }
}