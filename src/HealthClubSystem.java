import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class HealthClubSystem {
    /**
     * hashmap to store member information
     * */
    static HashMap<String, Member> memberList = new HashMap<>();
    protected static Scanner keyboard = new Scanner(System.in);


    public static void main(String[] args){
        // test - will input members into map from text file
        Member Ryan = new Member("77777777","Ryan","H","4445557777","rhasler@luc.edu", "normal");
        //Member David = new Member("David","Ludington","1111111111","dludington@lu");

        memberList.put(generateMembershipID(), Ryan);
        //memberList.put(generateMembershipID(), David);

        populateMemberList();

        while (true) {

            System.out.println("SysEng Health Club Options: \n0 - exit system \n1 - search for member \n2 - add new member \n3 - print member list");
            System.out.println("Enter your choice: ");

            int choice = keyboard.nextInt();

            if (choice < 0 || choice > 3) {
                System.out.println("That is not an available option, try again.");
            }

            if (choice == 0) { // exit system
                System.out.println("Exiting Health Club System...");
                System.exit(0);
            }

            if (choice == 1) { // search for member
                System.out.print("Enter the member's ID number ");
                String id = keyboard.next();
                if (memberList.containsKey(id)){
                    System.out.print("Member info: ");
                    memberList.get(id).printMemberInfo();
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
                System.out.print("Enter desired membership status ");
                String membershipStatus = keyboard.next(); // should probably create a method call that ensures membershipStatus data is correct.
                String id = generateMembershipID();
                createMember(id, fName, lName, phoneNumber, email, membershipStatus);
            }

            if(choice == 3){ // print list of members
                for (String id : memberList.keySet()) {
                    memberList.get(id).printMemberInfo();
                }
            }
        }

    }



    /**
     * method to remove member
     * */
    public void removeMember(String membershipID) {
        memberList.remove(membershipID);
        // still need to implement file writer/reader
    }

    public static String accessMemberInfo(){
        return "";
    }


    /**
     * method populateMemberList
     * inputs: none
     * output: void
     * description: This method reads in member data from txt file and populates the memberList: HashMap.
     */
    public static void populateMemberList(){
        File members = new File("member.txt");
        try {
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
    public boolean validateMembershipID(String membershipID) {
        return memberList.containsKey(membershipID);
    }

}
