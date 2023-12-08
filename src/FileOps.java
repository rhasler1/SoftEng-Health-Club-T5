import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class FileOps {

    // file names
    private static final String MEMBERS = "member.txt";

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
                String id = sc.next();
                String fName = sc.next();
                String lName = sc.next();
                String phoneNumber = sc.next();
                String email = sc.next();
                String membershipType = sc.next();
                String startDate = sc.next();
                String length = sc.next();
                String membershipStatus = sc.next();
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

    // TODO: 12/7/23 add method to build HashMap for reports
}
