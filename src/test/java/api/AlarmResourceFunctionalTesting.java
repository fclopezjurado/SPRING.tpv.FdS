package api;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import entities.alarm.Alarm;
import wrappers.AlarmsWrapper;

public class AlarmResourceFunctionalTesting {

    @Test
    public void testaGetAlarms() {
        RestService service = new RestService();
        AlarmsWrapper alarmWrapper = service.getAllAlarms();
        List<Alarm> alarms = alarmWrapper.getAlarms();
        int num = alarms.size();
        assertEquals(2, num);
    }
    
}
