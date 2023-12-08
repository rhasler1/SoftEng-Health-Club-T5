import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class HealthClubSystem {
    /**
     * hashmap to store member information
     * */
    private static HashMap<String, Member> members = new HashMap<>();
    /**
     * scanner to take in user input
     * */
    private static final Scanner keyboard = new Scanner(System.in);
    
    /**
     * main - entry point into SysEng Health Club
     * */
    public static void main(String[] args) throws IOException {
        members = FileOps.getMembers();

        while (true) {
            System.out.println("SysEng Health Club Options: \n0 - Exit system \n1 - Access member information " +
                    "\n2 - Create member account \n3 - Print member list \n4 - Enter health club \n5 - Delete member account " +
                    "\n6 - Update status");
            System.out.println("Enter your choice: ");
            int choice = keyboard.nextInt();

            if (choice < 0 || choice > 6) {
                System.out.println("That is not an available option, try again.");
            }

            if (choice == 0) { // exit system
                exitSystem();
            }

            if (choice == 1) { // use case 4: Access Member Information
                accessMemberInformation();
            }

            if (choice ==  2) { // use case 2: Create Account
                createMemberAccount();
            }

            if (choice == 3) { // print list of members
                printMemberList();
            }

            if (choice == 4) { // use case 1: Enter Health Club
                enterHealthClub();
            }

            if (choice == 5) { // use case 3: Delete Account
                deleteMemberAccount();
            }

            if (choice == 6) { // not in our use cases...
                updateAllStatus();
            }
        }
    }

    /**
     * method to update all members' status
     * */
    public static void updateAllStatus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Map.Entry<String, Member> entry: HealthClubSystem.members.entrySet()) {
            Member thisMember = entry.getValue();
            String startDateStr = thisMember.startDate;
            String membershipLength = thisMember.membershipLength;
            int memLength = Integer.parseInt(membershipLength);
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate currentDate = LocalDate.now();
            long monthsPassed = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), currentDate.withDayOfMonth(1));
            if (memLength - monthsPassed < 0) {
                thisMember.membershipStatus = "Expired";
            }
            if (memLength - monthsPassed > 1) {
                thisMember.membershipStatus = "Good";
            }
            if (memLength - monthsPassed == 1 || memLength - monthsPassed == 0) {
                thisMember.membershipStatus = "In-Danger";
            }
        }
    }

    /**
     * method to remove member
     * */
    public static void deleteMemberAccount() {
        System.out.println("Enter member id of member to remove: ");
        String id = keyboard.next();
        if (members.containsKey(id)) {
            System.out.println("Are you sure you want to delete account associated with " +
                    "Member " + members.get(id).fName + " " + members.get(id).lName + "? (please enter yes or no)");
            String answer = keyboard.next();
            if (answer.equals("yes")) {
                System.out.println("Account: " + members.get(id).fName + " " + members.get(id).lName + " has been deleted.");
                removeMember(id);
            }
            else {
                System.out.println("Account: " + members.get(id).fName + " " + members.get(id).lName + " has NOT been deleted.");
            }
        }
        else {
            System.out.println("No member associated with provided id.");
        }
    }

    /**
     * method for member access
     * */
    public static void enterHealthClub() {
        System.out.println("Enter member id: ");
        String id = keyboard.next();
        if (validateMembershipID(id)) {
            System.out.println("Valid membership allow access.");
        }
        else {
            System.out.println("Invalid membership deny access.");
        }
    }

    /**
     * method to exit system gracefully
     * */
    public static void exitSystem() {
        updateMemberFile(); // update member.txt file
        keyboard.close();
        System.out.println("Exiting Health Club System...");
        System.exit(0);
    }

    /**
     * method to search for member
     * */
    public static void accessMemberInformation() {
        System.out.print("Enter the member's ID number: ");
        String id = keyboard.next();
        if (members.containsKey(id)){
            System.out.print("Member info ");
            String memberInfo = members.get(id).toString();
            System.out.println(memberInfo);
        }
        else {
            System.out.println("No member in the system is associated with the provided ID.");
        }
    }

    /**
     * method to print list of members
     * */
    public static void printMemberList() {
        System.out.println("#### Member Information ####");
        for (String id : members.keySet()) {
            String memberInfo = members.get(id).toString();
            System.out.println(memberInfo);
        }
    }

    /**
     * method for creating a new member
     * */
    public static void createMemberAccount() {
        System.out.print("Enter the member's first name ");
        String fName = keyboard.next();
        System.out.print("Enter the member's last name ");
        String lName = keyboard.next();
        System.out.print("Enter the member's phone number ");
        String phoneNumber = keyboard.next();
        System.out.print("Enter the member's email ");
        String email = keyboard.next();
        String membershipStatus = "Good";
        String id = generateMembershipID();
        LocalDate obj = LocalDate.now();
        String startDate = obj.toString(); // check this works
        String membershipLength = selectMembershipLength();
        while (membershipLength.isEmpty()) { membershipLength = selectMembershipLength(); }
        String membershipType = selectMembershipType();
        while (membershipType.isEmpty()) { membershipType = selectMembershipType(); }
        createMember(id, fName, lName, phoneNumber, email, membershipType, startDate, membershipLength, membershipStatus);
        System.out.println("Member " + fName + " " + lName + " successfully added to the system.");
    }

    /**
     * method to select membership type
     * */
    public static String selectMembershipType() {
        System.out.println("Enter age of new member: ");
        int input = keyboard.nextInt();
        String type = "";
        if (input <= 17) {
            type = "Student";
        }
        if (input >= 18 && input <= 64) {
            type = "Adult";
        }
        if (input >= 65) {
            type = "Senior";
        }
        return type;
    }

    /**
     * method to select membership length
     * */
    public static String selectMembershipLength() {
        System.out.println("Select Membership Length:\n1 - 6 month\n2 - 12 month\n3 - 36 month");
        int input = keyboard.nextInt();
        String length = "";
        if (input == 1) {
            length = "6";
        }
        if (input == 2) {
            length = "12";
        }
        if (input == 3) {
            length = "36";
        }
        return length;
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
     * method generateMembershipID
     * inputs: none
     * output: id: String
     * description: method calls generateRandom to get a potential membershipID, then validates that the generated
     * id is not already in use. If the id is already in use, the method will generate a new id until it produces
     * a unique id.
     * */
    public static String generateMembershipID() {
        String id = generateRandom();
        while (members.containsKey(id)) {
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
     * method createMember
     * inputs: fName: String, lName: String, phoneNumber: String, email: String, membershipStatus: String
     * output: void
     * description: Creates a new Member object with the member information provided as input, then adds the
     * new Member to the list of members (memberList: HashMap).
     * */
    public static void createMember(String id, String fName, String lName, String phoneNumber, String email,
                                    String membershipType, String startDate, String membershipLength, String membershipStatus) {
        Member thisMember = new Member(id, fName, lName, phoneNumber, email, membershipType, startDate, membershipLength,
                membershipStatus);
        members.put(id, thisMember);
    }

    /**
     * method to update member.txt file on system exit
     * */
    public static void updateMemberFile() {
        try {
            File members = new File("member.txt");
            FileWriter writer = new FileWriter(members);
            boolean first = true;
            for (Map.Entry<String, Member> entry: HealthClubSystem.members.entrySet()) {
                if (first) { first = false; }
                else { writer.write("\n"); }
                Member thisMember = entry.getValue();
                writer.write(thisMember.id + " " + thisMember.fName + " " + thisMember.lName
                + " " + thisMember.phoneNumber + " " + thisMember.email + " " + thisMember.memberType
                + " " + thisMember.startDate + " " + thisMember.membershipLength + " " + thisMember.membershipStatus);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * validate membershipID for entrance to health club
     * */
    public static boolean validateMembershipID(String membershipID) {
        return members.containsKey(membershipID) && !members.get(membershipID).membershipStatus.equals("Expired");
    }

    /**
     * method to remove member
     * */
    public static void removeMember(String membershipID) {
        members.remove(membershipID);
    }
}
