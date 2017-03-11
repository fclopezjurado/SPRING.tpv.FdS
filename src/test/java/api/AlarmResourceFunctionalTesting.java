package api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.core.AlarmType;
import wrappers.AlarmWrapper;
import wrappers.AlarmsWrapper;

public class AlarmResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;

    @Before
    public void init() {

    }

    @Test
    public void testPostAlarm() {
        String nameAlarm = "alarma2";
        List<String> listProducts = new ArrayList<String>();
        listProducts.add("articulo1");
        AlarmWrapper newAlarm = new AlarmWrapper(nameAlarm, AlarmType.WARNING, listProducts, 5);
        new RestBuilder<Object>(RestService.URL).path(Uris.ALARMS).body(newAlarm).post().build();
        AlarmsWrapper wrapp = new RestBuilder<AlarmsWrapper>(RestService.URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        boolean found = false;
        int i = 0;
        while (!found && i < wrapp.getAlarms().size()) {
            if (wrapp.getAlarms().get(i).getName().equals(nameAlarm)) {
                found = true;
            }
            i++;
        }

        if (!found) {
            fail();
        }
    }

    @Test
    public void testGetAlarms() {
        AlarmsWrapper alarmsWrapper = new RestBuilder<AlarmsWrapper>(URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        assertEquals(0, alarmsWrapper.getAlarms().size());
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }
}
