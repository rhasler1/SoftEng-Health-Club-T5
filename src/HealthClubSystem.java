import java.io.*;
import java.util.*;

// TODO: 12/6/23 track when a membership is about to expire and update membership status' accordingly.  

public class HealthClubSystem {
    /**
     * hashmap to store member information
     * */
    static HashMap<String, Member> memberList = new HashMap<>();
    /**
     * scanner to take in user input
     * */
    protected static Scanner keyboard = new Scanner(System.in);

    /**
     * main - entry point into SysEng Health Club
     * */
    public static void main(String[] args){
        populateMemberList();

        while (true) {
            System.out.println("SysEng Health Club Options: \n0 - exit system \n1 - search for member " +
                    "\n2 - add new member \n3 - print member list \n4 - club access \n5 - remove member");
            System.out.println("Enter your choice: ");
            int choice = keyboard.nextInt();

            if (choice < 0 || choice > 5) {
                System.out.println("That is not an available option, try again.");
            }

            if (choice == 0) { // exit system
                updateMemberFile(); // update member.txt file
                keyboard.close();
                System.out.println("Exiting Health Club System...");
                System.exit(0);
            }

            if (choice == 1) { // search for member
                System.out.print("Enter the member's ID number ");
                String id = keyboard.next();
                if (memberList.containsKey(id)){
                    System.out.print("Member info: ");
                    String memberInfo = memberList.get(id).getMemberInfo();
                    System.out.println(memberInfo);
                }
                else {
                    System.out.println("No member in the system is associated with the provided ID.");
                }
            }

            if (choice ==  2) { // add new member
                System.out.print("Enter the member's first name ");
                String fName = keyboard.next();
                System.out.print("Enter the member's lastName ");
                String lName = keyboard.next();
                System.out.print("Enter the member's phone Number ");
                String phoneNumber = keyboard.next();
                System.out.print("Enter the member's email ");
                String email = keyboard.next();
                System.out.println("Enter desired membership status ");
                String membershipStatus = selectStatus();
                while (membershipStatus.isEmpty()) { membershipStatus = selectStatus(); } // run selectStatus method failed.
                String id = generateMembershipID();
                createMember(id, fName, lName, phoneNumber, email, membershipStatus);
                System.out.println("Member " + fName + " " + lName + " successfully added to the system.");
            }

            if (choice == 3) { // print list of members
                System.out.println("#### Member Information ####");
                for (String id : memberList.keySet()) {
                    String memberInfo = memberList.get(id).getMemberInfo();
                    System.out.println(memberInfo);
                }
            }

            if (choice == 4) { // member access
                System.out.println("Enter member id: ");
                String id = keyboard.next();
                if (validateMembershipID(id)) {
                    System.out.println("Valid membership allow access.");
                }
                else {
                    System.out.println("Invalid membership deny access.");
                }
            }

            if (choice == 5) { // remove member
                System.out.println("Enter member id of member to remove.");
                String id = keyboard.next();
                if (memberList.containsKey(id)) {
                    System.out.println("Member " + memberList.get(id).fName + " " + memberList.get(id).lName +
                            " successfully removed.");
                    removeMember(id);
                }
                else {
                    System.out.println("No member associated with provided id.");
                }
            }
        }
    }

    /**
     * method to update member.txt file
     * */
    public static void updateMemberFile() {
        try {
            File members = new File("member.txt");
            FileWriter writer = new FileWriter(members);
            boolean first = true;
            for (Map.Entry<String, Member> entry: memberList.entrySet()) {
                if (first) { first = false; }
                else { writer.write("\n"); }
                Member thisMember = entry.getValue();
                writer.write(thisMember.id + " " + thisMember.fName + " " + thisMember.lName
                + " " + thisMember.phoneNumber + " " + thisMember.email + " " + thisMember.memberType);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to select membership status
     * */
    public static String selectStatus() {
        System.out.println("1 - Good\n2 - In-Danger\n3 - Expired\n4 - Terminated");
        int input = keyboard.nextInt();
        String status = "";
        if (input == 1) {
            status = "Good";
        }
        if (input == 2) {
            status = "In-Danger";
        }
        if (input == 3) {
            status = "Expired";
        }
        if (input == 4) {
            status = "Terminated";
        }
        return status;
    }

    /**
     * method populateMemberList
     * inputs: none
     * output: void
     * description: This method reads in member data from txt file and populates the memberList: HashMap.
     */
    public static void populateMemberList(){
        try {
            File members = new File("member.txt");
            Scanner sc = new Scanner(members);
            while (sc.hasNextLine()) {
                String id = sc.next();
                String fName = sc.next();
                String lName = sc.next();
                String phoneNumber = sc.next();
                String email = sc.next();
                String membershipStatus = sc.next();
                createMember(id, fName, lName, phoneNumber, email, membershipStatus);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * method createMember
     * inputs: fName: String, lName: String, phoneNumber: String, email: String, membershipStatus: String
     * output: void
     * description: Creates a new Member object with the member information provided as input, then adds the
     * new Member to the list of members (memberList: HashMap).
     * */
    public static void createMember(String id, String fName, String lName, String phoneNumber, String email, String membershipStatus) {
        Member thisMember = new Member(id, fName, lName, phoneNumber, email, membershipStatus);
        memberList.put(id, thisMember);
    }

    /**
     * method generateMembershipID
     * inputs: none
     * output: id: String
     * description: method calls generateRandom to get a potential membershipID, then validates that the generated
     * id is not already in use. If the id is already in use, the method will generate a new id until it produces
     * a unique id.
     * */
    public static String generateMembershipID() {
        String id = generateRandom();
        while (memberList.containsKey(id)) {
            id = generateRandom();
        }
        return id;
    }

    /**
     * method generateRandom
     * inputs: none
     * output: id: String
     * description: method generates a random String of 12 integers.
     * */
    public static String generateRandom(){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(rand.nextInt(9) + 1);
        for (int i = 0; i < 11; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * validate membershipID for entrance to health club
     * */
    public static boolean validateMembershipID(String membershipID) {
        return memberList.containsKey(membershipID);
    }

    /**
     * method to remove member
     * */
    public static void removeMember(String membershipID) {
        memberList.remove(membershipID);
    }

}
