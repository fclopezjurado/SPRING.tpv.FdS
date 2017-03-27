package api;

import entities.core.Alarm;
import entities.core.AlarmType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wrappers.AlarmWrapper;
import wrappers.AlarmsWrapper;
import wrappers.ArticleWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AlarmResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;

    @Before
    public void seedDataBase() {
        new RestService().seedDatabase();
    }

    @Test
    public void testPostAlarm() {
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES).clazz(ArticleWrapper[].class).get().build());
        AlarmWrapper newAlarm = new AlarmWrapper("alarma2", AlarmType.WARNING, articles, 5);
        new RestBuilder<Object>(RestService.URL).path(Uris.ALARMS).body(newAlarm).post().build();
        AlarmsWrapper wrapp = new RestBuilder<AlarmsWrapper>(RestService.URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        boolean found = false;
        int i = 0;
        while (!found && i < wrapp.getAlarms().size()) {
            if (wrapp.getAlarms().get(i).getName().equals(newAlarm.getName())) {
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
        assertEquals(2, alarmsWrapper.getAlarms().size());
    }

    @Test
    public void testEditAlarm() {
        AlarmsWrapper alarmsWrapper = new RestBuilder<AlarmsWrapper>(RestService.URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get()
                .build();
        AlarmWrapper alarmWrapper = new AlarmWrapper(alarmsWrapper.getAlarms().get(0).getId(), "Alarma modificada",
                new ArrayList<ArticleWrapper>(), AlarmType.CRITICAL, 4);
        new RestBuilder<AlarmWrapper>(RestService.URL).path(Uris.ALARMS).body(alarmWrapper).put().build();
        alarmsWrapper = new RestBuilder<AlarmsWrapper>(RestService.URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();

        boolean found = false;
        for (Alarm alarm : alarmsWrapper.getAlarms()) {
            if (alarm.getId() == alarmWrapper.getId()) {
                found = true;
                assertEquals("Alarma modificada", alarm.getName());
                assertEquals(0, alarm.getArticleList().size());
                assertEquals(AlarmType.CRITICAL, alarm.getType());
                assertEquals(4, alarm.getValue());
            }
        }
        if (!found)
            fail();
    }

    @Test
    public void testDelete() {
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES).clazz(ArticleWrapper[].class).get().build());
        AlarmWrapper newAlarm = new AlarmWrapper(String.valueOf((new Date()).getTime()), AlarmType.WARNING, articles, 5);
        new RestBuilder<Object>(RestService.URL).path(Uris.ALARMS).body(newAlarm).post().build();

        AlarmsWrapper wrapp = new RestBuilder<AlarmsWrapper>(RestService.URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        boolean found = false;
        int i = 0;
        while (!found && i < wrapp.getAlarms().size()) {
            if (wrapp.getAlarms().get(i).getName().equals(newAlarm.getName())) {
                found = true;
            } else {
                i++;
            }
        }

        new RestBuilder<Object>(RestService.URL).path(Uris.ALARMS).path("/" + String.valueOf(wrapp.getAlarms().get(i).getId())).delete()
                .build();

        wrapp = new RestBuilder<AlarmsWrapper>(RestService.URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        found = false;
        i = 0;
        while (!found && i < wrapp.getAlarms().size()) {
            if (wrapp.getAlarms().get(i).getName().equals(newAlarm.getName())) {
                found = true;
            } else {
                i++;
            }
        }

        if (found) {
            fail();
        }
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }
}
