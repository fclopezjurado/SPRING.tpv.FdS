package api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wrappers.AlarmsWrapper;
import wrappers.TokenWrapper;

public class AdminResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;

    @Before
    public void init() {
        new RestService().seedDatabase();
    }

    @Test
    public void testDatabaseSeeded() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS)
                .basicAuth(Long.toString(666000000), "pass").clazz(TokenWrapper.class).post().build();
        
        assertNotEquals(null, token);
        
        AlarmsWrapper alarmsWrapper = new RestBuilder<AlarmsWrapper>(URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        assertEquals(2, alarmsWrapper.getAlarms().size());
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }
}
