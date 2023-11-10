import java.util.HashMap;


public class System {
    /**
     * hashmap to store member information
     * */
    HashMap<String, Member> memberList = new HashMap<>();


    public static void main(String[] args) {
        // test
        Member Ryan = new Member("Ryan","H","4445557777","rhasler@luc.edu");
        Ryan.printMemberInfo();
        while (true) {
            // add logic for processing user input
            break;
        }

    }

    /**
     * method to generate a new unique membershipID
     * */
    public String generateMembershipID() {
        return "";
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
    public void addMember(String fName, String lName, String phoneNumber, String email) {
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
