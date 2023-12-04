import java.lang.System;
public class Member {
    String fName;
    String lName;
    String phoneNumber;
    String email;
    int memberType;
    //1, student; 2, adult; 3, senior
    String recentVisit;
    int monthlyVisits;

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
