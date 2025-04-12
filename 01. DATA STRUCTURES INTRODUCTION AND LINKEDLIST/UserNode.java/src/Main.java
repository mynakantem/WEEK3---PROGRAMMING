import java.util.*;

class UserNode {
    int userID;
    String name;
    int age;
    ArrayList<Integer> friendIDs;
    UserNode next;

    public UserNode(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friendIDs = new ArrayList<>();
        this.next = null;
    }

    @Override
    public String toString() {
        return "UserID: " + userID + ", Name: " + name + ", Age: " + age + ", Friends: " + friendIDs;
    }
}

class SocialMedia {
    private UserNode head = null;

    // Add new user
    public void addUser(int userID, String name, int age) {
        UserNode newUser = new UserNode(userID, name, age);
        newUser.next = head;
        head = newUser;
        System.out.println("User added: " + name);
    }

    // Get user by ID
    private UserNode getUserByID(int userID) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userID == userID)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    // Add friend connection
    public void addFriend(int userID1, int userID2) {
        if (userID1 == userID2) {
            System.out.println("User cannot befriend themselves.");
            return;
        }
        UserNode user1 = getUserByID(userID1);
        UserNode user2 = getUserByID(userID2);
        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }
        if (!user1.friendIDs.contains(userID2)) user1.friendIDs.add(userID2);
        if (!user2.friendIDs.contains(userID1)) user2.friendIDs.add(userID1);
        System.out.println("Friend connection added between " + userID1 + " and " + userID2);
    }

    // Remove friend connection
    public void removeFriend(int userID1, int userID2) {
        UserNode user1 = getUserByID(userID1);
        UserNode user2 = getUserByID(userID2);
        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }
        user1.friendIDs.remove((Integer) userID2);
        user2.friendIDs.remove((Integer) userID1);
        System.out.println("Friend connection removed between " + userID1 + " and " + userID2);
    }

    // Display all friends of a specific user
    public void displayFriends(int userID) {
        UserNode user = getUserByID(userID);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("Friends of " + user.name + " (" + userID + "): " + user.friendIDs);
    }

    // Search user by Name or ID
    public void searchUser(String nameOrID) {
        UserNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (String.valueOf(temp.userID).equals(nameOrID) || temp.name.equalsIgnoreCase(nameOrID)) {
                System.out.println("User Found: " + temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("User not found.");
    }

    // Count friends of each user
    public void countAllFriends() {
        UserNode temp = head;
        while (temp != null) {
            System.out.println(temp.name + " (" + temp.userID + ") has " + temp.friendIDs.size() + " friend(s).");
            temp = temp.next;
        }
    }

    // Find mutual friends
    public void findMutualFriends(int userID1, int userID2) {
        UserNode user1 = getUserByID(userID1);
        UserNode user2 = getUserByID(userID2);
        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }

        Set<Integer> mutual = new HashSet<>(user1.friendIDs);
        mutual.retainAll(user2.friendIDs);

        System.out.println("Mutual friends between " + user1.name + " and " + user2.name + ": " + mutual);
    }

    // Display all users
    public void displayAllUsers() {
        UserNode temp = head;
        System.out.println("\n📘 All Users:");
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class SocialMediaFriendList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialMedia sm = new SocialMedia();

        sm.addUser(101, "Alice", 25);
        sm.addUser(102, "Bob", 23);
        sm.addUser(103, "Charlie", 27);
        sm.addUser(104, "Diana", 22);

        sm.addFriend(101, 102);
        sm.addFriend(101, 103);
        sm.addFriend(102, 104);

        sm.displayAllUsers();
        sm.displayFriends(101);
        sm.displayFriends(102);

        sm.findMutualFriends(101, 102);

        sm.searchUser("Charlie");
        sm.searchUser("104");

        sm.countAllFriends();

        sm.removeFriend(101, 102);
        sm.displayFriends(101);
        sm.displayFriends(102);
    }
}
