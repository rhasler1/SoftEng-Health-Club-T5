import java.lang.System;
public class Member {
    String fName;
    String lName;
    String phoneNumber;
    String email;

    /**
     * Member Constructor
     * */
    public Member(String fName, String lName, String phoneNumber, String email) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    /**
     * Print member information
     * */
    public void printMemberInfo() {
        System.out.println(this.fName);
    }

}
