import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class HealthClubSystem {
    /**
     * hashmap to store member information
     * */
    static HashMap<String, Member> memberList = new HashMap<>();


    public static void main(String[] args) {
        // test - will input members into map from text file
        Member Ryan = new Member("Ryan","H","4445557777","rhasler@luc.edu");
        Member David = new Member("David","Ludington","1111111111","dludington@lu");

        memberList.put(generateMembershipID(), Ryan);
        memberList.put(generateMembershipID(), David);
        while (true) {
            // add logic for processing user input
            System.out.println("Enter: \n1 - search for member \n2 - add new member \n3 - print member list");
            Scanner scan = new Scanner(System.in);

            int choice = scan.nextInt();

            if(choice == 1){
                System.out.print("Enter the member's ID number ");
                String id = scan.next();
                if(memberList.containsKey(id)){
                    System.out.println(memberList.get(id).printMemberInfo());
                }

            }

            if(choice ==  2){
                System.out.print("Enter the member's first name ");
                String fName = scan.next();
                System.out.print("Enter the member's lastName ");
                String lName = scan.next();
                System.out.print("Enter the member's phone Number ");
                String phoneNumber = scan.next();
                System.out.print("Enter the member's email ");
                String email = scan.next();

                addMember(fName, lName, phoneNumber, email);

                System.out.println("Member created: " + fName + " " + lName + " ," + phoneNumber + " ," + email);
            }


            if(choice == 3){
                for(String ID : memberList.keySet()){
                    String id = ID;
                    String member = memberList.get(id).printMemberInfo();
                    System.out.println(id + " " + member);
                }
            }
        }

    }

    /**
     * methods to generate a new unique membershipID
     * */
    public static String generateRandom(){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append(rand.nextInt(9) + 1);

        for(int i = 0; i < 11; i++){
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
    public static String generateMembershipID() {
        String id = generateRandom();

        while(memberList.containsKey(id)){
            id = generateRandom();
        }

        return id;
    }

    /**
     * validate membershipID for entrance to health club
     * */
    public boolean validateMembershipID(String membershipID) {
        return memberList.containsKey(membershipID);
    }

    /**
     * method to create new member
     * */
    public static void addMember(String fName, String lName, String phoneNumber, String email) {
        // still need to implement file writer/reader
        String thisID = generateMembershipID();
        Member thisMember = new Member(fName, lName, phoneNumber, email);
        memberList.put(thisID, thisMember);
    }

    /**
     * method to remove member
     * */
    public void removeMember(String membershipID) {
        memberList.remove(membershipID);
        // still need to implement file writer/reader
    }
}
