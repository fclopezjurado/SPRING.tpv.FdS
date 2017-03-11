package entities.users;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import entities.users.Token;
import entities.users.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User(123456789, "u", "p");
        Token token = new Token(user);
        Date now = new Date(new Date().getTime() - 3600 * 1000);
        assertTrue(token.getValue().length() > 20);
        assertTrue(now.before(token.getCreationDate()));
    }

}
