import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestHealthClubSystem {

    //@RepeatedTest(100)
    @Test
    @DisplayName("Test generate membership id")
    public void testGenerateID() {
        HealthClubSystem.setMembers(); // set state of hashmap
        HashMap<String, Member> members = HealthClubSystem.getMembers(); // get state
        String membershipID = HealthClubSystem.generateMembershipID(); // generate member id
        assertNotNull(membershipID);
        assertFalse(membershipID.isEmpty());
        assertNotNull(members);
        assertFalse(members.isEmpty());
        assertEquals(12, membershipID.length());
        assertFalse(members.containsKey(membershipID));
    }

    @Test
    @DisplayName("Test delete member")
    public void testDeleteAccount() {
        String id = "";
        HealthClubSystem.setMembers(); // set state of hashmap
        HashMap<String, Member> members = HealthClubSystem.getMembers(); // get state
        assertNotNull(members);
        assertFalse(members.isEmpty());
        for (Map.Entry<String, Member> entry: members.entrySet()) {
            id = entry.getValue().id;
            break;
        }
        assertTrue(members.containsKey(id));
        HealthClubSystem.removeMember(id);
        assertFalse(members.containsKey(id));
    }

    @Test
    @DisplayName("Test create member")
    public void createAccount() {
        HealthClubSystem.setMembers(); // set state of hashmap
        HashMap<String, Member> members = HealthClubSystem.getMembers(); // get state
        String id = HealthClubSystem.generateMembershipID();
        int initialSize = members.size();
        HealthClubSystem.createMember(id, "Michael", "White", "9998887777",
                "mwhite@gmail.com", "Student", "2023-02-05" ,"12", "Good");
        assertEquals(members.size(), initialSize+1);
        assertTrue(members.containsKey(id));
    }
}
