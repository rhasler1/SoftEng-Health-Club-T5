import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class FileOps {

    // file names
    private static final String MEMBERS = "member.txt";
    private static final String TRAFFIC = "clubTraffic.txt";

    /**
     *
     * */
    public static Scanner fileScanner(String filename) {
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            sc = null;
        }
        return sc;
    }

    /**
     * method to build HashMap for members
     * */
    public static HashMap<String, Member> getMembers() {
        HashMap<String, Member> members = new HashMap<>();
        Scanner sc = fileScanner(MEMBERS);
        if (sc != null) {
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                String id = data[0];
                String fName = data[1];
                String lName = data[2];
                String phoneNumber = data[3];
                String email = data[4];
                String membershipType = data[5];
                String startDate = data[6];
                String length = data[7];
                String membershipStatus = data[8];
                Member newMember = new Member(id, fName, lName, phoneNumber, email, membershipType, startDate,
                        length, membershipStatus);
                members.put(id, newMember);
            }
        }
        return members;
    }

    public static void writeToFile(String filename, Object o) throws IOException {
        FileWriter fileWriter = new FileWriter(filename, true);
        fileWriter.append(o.toString());
        fileWriter.close();
    }



    public static void logNewVisit() throws IOException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy,MM,dd,HH");
        LocalDateTime now = LocalDateTime.now();
        writeToFile(TRAFFIC, dtf.format(now));
        writeToFile(TRAFFIC, "\n");

    }

//Allows for the construction of relavant date information. 0 for year, 1 for month, 2 for day, 3 for hour
    public static String getDTH(int input){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy,MM,dd,HH");
        LocalDateTime now = LocalDateTime.now();
        String[] data = dtf.format(now).split(",");
        return(data[input]);

    }

    // TODO: 12/7/23 add method to build HashMap for reports
}
