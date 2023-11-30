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
    public String printMemberInfo() {
        return this.fName + " ," + this.lName + ", " + this.email + ", " + this.phoneNumber;
    }

}
