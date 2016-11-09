import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import model.Facade;
import model.User;
import model.AccountType;
import model.exceptions.NonUniqueUsernameException;

public class TestGetUserByUsername {

    Facade facade = null;
    User user = null;
    String username = null;
    String password = null;
    String name = null;
    AccountType accountType = null;
    String usernameThatDoesNotExist = "georgie";
    User userTwo = null;
    String usernameTwo = null;
    String passwordTwo = null;
    String nameTwo = null;
    AccountType accountTypeTwo = null;

    protected void setUp(){
        Facade.initialize();
        facade = Facade.getFacade();
        username = "jim";
        password = "jimmyspassword";
        name = "Jimmy";
        accountType = AccountType.USER;
        user = new User(username, password, name, accountType);
        usernameTwo = "tina";
        passwordTwo = "tinaspassword";
        nameTwo = "Tina";
        accountTypeTwo = AccountType.MANAGER;
        userTwo = new User(usernameTwo, passwordTwo,
            nameTwo, accountTypeTwo);
        try {
            facade.createUser(username, password, name, accountType);
            facade.createUser(usernameTwo, passwordTwo,
                nameTwo, accountTypeTwo);
        } catch (NonUniqueUsernameException e) {
            System.out.println("should probably run ./update_schema.sh but"
                + " continuing with tests anyway cuz it'll probably be fine");
        }
    }

    @Test
    public void testRetrievesUser() {
        setUp();
        User retrievedUser = facade.getUserByUsername(username);
        assertEquals(retrievedUser, user);
        assertEquals(retrievedUser.getPassword(), user.getPassword());
        assertEquals(retrievedUser.getName(), user.getName());
        assertEquals(retrievedUser.getAccountType(), user.getAccountType());
    }

    @Test
    public void testHasException() {
        setUp();
        User retrievedUser
            = facade.getUserByUsername(usernameThatDoesNotExist);
        assertEquals(retrievedUser, null);
    }

    @Test
    public void testRetrievesMultipleUsers() {
        setUp();
        User retrievedUser = facade.getUserByUsername(username);
        User retrievedUserTwo = facade.getUserByUsername(usernameTwo);

        assertEquals(retrievedUser, user);
        assertEquals(retrievedUser.getPassword(), user.getPassword());
        assertEquals(retrievedUser.getName(), user.getName());
        assertEquals(retrievedUser.getAccountType(), user.getAccountType());

        assertEquals(retrievedUserTwo, userTwo);
        assertEquals(retrievedUserTwo.getPassword(), userTwo.getPassword());
        assertEquals(retrievedUserTwo.getName(), userTwo.getName());
        assertEquals(retrievedUserTwo.getAccountType(),
            userTwo.getAccountType());
    }
}
