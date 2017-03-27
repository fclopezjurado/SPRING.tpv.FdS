package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import api.Uris;
import wrappers.UserForEditListWrapper;
import wrappers.UserForEditWrapper;
import wrappers.UserWrapper;

public class UserResourceFunctionalTesting {
    @BeforeClass
    public static void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }
    
    @Test
    public void testCreateManager() {
        String token = new RestService().loginAdmin();
        for (int i = 0; i < 4; i++) {
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserWrapper(777000005 + i, "user" + i, "pass"))
                    .basicAuth(token, "").post().build();
        }
    }

    @Test
    public void testCreateManagerUnauthorized() {
        try {
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserWrapper(667000000, "user", "pass")).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateManagerUnauthorized (" + httpError.getMessage() + "):\n     " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testBadRequestCreate() {
        String token = new RestService().loginAdmin();
        try {
            UserWrapper userWrapper = new UserWrapper(0, "", "pass");
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).basicAuth(token, "").post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testBadRequestCreate (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testRepeatingFieldCreate() {
        String token = new RestService().loginAdmin();
        UserWrapper userWrapper = new UserWrapper(777000000, "user", "pass");
        new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).basicAuth(token, "").post().build();
        try {
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).basicAuth(token, "").post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.CONFLICT, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testRepeatingFieldCreate (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testCreateCustomer() {
        String token = new RestService().registerAndLoginManager();
        new RestBuilder<Object>(RestService.URL).path(Uris.CUSTOMERS).body(new UserWrapper(777000001, "customer", "pass"))
                .basicAuth(token, "").post().build();
    }

    @Test
    public void testCreateCustomerForbidden() {
        try {
            String token = new RestService().loginAdmin();
            new RestBuilder<Object>(RestService.URL).path(Uris.CUSTOMERS).body(new UserWrapper(777000001, "customer", "pass"))
                    .basicAuth(token, "").post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateCustomerForbidden (" + httpError.getMessage() + "):\n " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testGetAllUsers() {
        try {
            UserForEditListWrapper users = new RestBuilder<UserForEditListWrapper>(RestService.URL).path(Uris.USERS)
                    .clazz(UserForEditListWrapper.class).get().build();
            assertEquals(6, users.getUserList().size());
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testGetAllUsersForbidden (" + httpError.getMessage() + "):\n " + httpError.getResponseBodyAsString());
        }
    }
    
    @Test
    public void testUpdateUser() {
        try {
            String token = new RestService().loginAdmin();
            new RestBuilder<String>(RestService.URL).path(Uris.USERS).body(new UserForEditWrapper(666000000,"customer0",true,"calle prueba","12345678Z","prueba@mail.com",Calendar.getInstance())).clazz(String.class)
                    .basicAuth(token, "").put().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testUpdateUserForbidden (" + httpError.getMessage() + "):\n " + httpError.getResponseBodyAsString());
        }
    }
    
    @Test
    public void testDeleteUser() {
        try {
            String token = new RestService().loginAdmin();
            new RestBuilder<String>(RestService.URL).path(Uris.USERS + "/" + 666000001).clazz(String.class)
                .basicAuth(token, "").delete().build();
            
            UserForEditListWrapper users = new RestBuilder<UserForEditListWrapper>(RestService.URL).path(Uris.USERS)
                    .clazz(UserForEditListWrapper.class).get().build();
            assertEquals(11, users.getUserList().size());
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testDeleteUserForbidden (" + httpError.getMessage() + "):\n " + httpError.getResponseBodyAsString());
        }
    }
    

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
