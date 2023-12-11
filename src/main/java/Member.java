public class Member {
    String id;
    String fName;
    String lName;
    String phoneNumber;
    String email;
    String memberType;
    String startDate;
    String membershipLength;
    String membershipStatus;
    Integer monthlyVisits;
    String currMonth;


    /**
     * Member Constructor
     * */
    public Member(String id, String fName, String lName, String phoneNumber, String email, String memberType,
                  String startDate, String membershipLength, String membershipStatus) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.memberType = memberType;
        this.startDate = startDate;
        this.membershipLength = membershipLength;
        this.membershipStatus = membershipStatus;
        this.monthlyVisits = 0;
        this.currMonth = FileOps.getDTH(1);
    }

    /**
     * get member information
     * */
    public String toString() {
        return "ID: " + this.id + ", " + "First Name: " + this.fName + ", " + "Last Name: " + this.lName + ", "
                + "Phone Number: " + this.phoneNumber + ", " + "Email: " + this.email + ", " + "Membership Type: " +
                this.memberType + ", Start Date: " + this.startDate + ", Membership Length: " + this.membershipLength
                + ", Membership Status: " + this.membershipStatus;
    }
}
