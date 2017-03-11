package api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wrappers.AlarmsWrapper;

public class AlarmResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;
    
    @Before
    public void init() {
        
    }
    
    @Test
    public void testGetAlarms() {
        AlarmsWrapper alarmsWrapper = new RestBuilder<AlarmsWrapper>(URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        assertEquals(0, alarmsWrapper.getAlarms().size());
    }
    
    @After
    public void end() {
        
    }
    
}
