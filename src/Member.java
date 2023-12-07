import java.lang.System;
public class Member {
    String id;
    String fName;
    String lName;
    String phoneNumber;
    String email;
    String memberType;

    /**
     * Member Constructor
     * */
    public Member(String id, String fName, String lName, String phoneNumber, String email, String memberType) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.memberType = memberType;
    }

    /**
     * get member information
     * */
    public String getMemberInfo() {
        return "ID: " + this.id + ", " + "First Name: " + this.fName + ", " + "Last Name: " + this.lName + ", "
                + "Phone Number: " + this.phoneNumber + ", " + "Email: " + this.email + ", " + "Membership Type: " +
                this.memberType;
    }
}
