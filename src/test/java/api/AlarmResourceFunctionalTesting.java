package api;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Alarm;
import entities.core.AlarmType;
import entities.core.Article;
import entities.core.Provider;
import wrappers.AlarmWrapper;
import wrappers.AlarmsWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class AlarmResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;

    @Before
    public void seedDataBase(){
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }

    @Test
    public void testPostAlarm() {
        List<Article> articles = new ArrayList<>();      
        Provider provider = new Provider("company0", "address0", 666100000L, 916661000L, "No", "No");
        Article article = new Article(84000001111L, "article0", new BigDecimal(20), "article0", new BigDecimal(10), provider);
        articles.add(article);

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
                new ArrayList<Article>(), AlarmType.CRITICAL, 4);
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
    public void testDelete(){
        List<Article> articles = new ArrayList<>();      
        Provider provider = new Provider("company0", "address0", 666100000L, 916661000L, "No", "No");
        Article article = new Article(84000001111L, "article0", new BigDecimal(20), "article0", new BigDecimal(10), provider);
        articles.add(article);

        AlarmWrapper newAlarm = new AlarmWrapper(String.valueOf((new Date()).getTime()), AlarmType.WARNING, articles, 5);
        new RestBuilder<Object>(RestService.URL).path(Uris.ALARMS).body(newAlarm).post().build();

        AlarmsWrapper wrapp = new RestBuilder<AlarmsWrapper>(RestService.URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        boolean found = false;
        int i = 0;
        while (!found && i < wrapp.getAlarms().size()) {
            if (wrapp.getAlarms().get(i).getName().equals(newAlarm.getName())) {
                found = true;
            }
            else {
                i++;
            }
        }

        new RestBuilder<Object>(RestService.URL).path(Uris.ALARMS).path("/" + String.valueOf(wrapp.getAlarms().get(i).getId())).delete().build();

        wrapp = new RestBuilder<AlarmsWrapper>(RestService.URL).path(Uris.ALARMS).clazz(AlarmsWrapper.class).get().build();
        found = false;
        i = 0;
        while (!found && i < wrapp.getAlarms().size()) {
            if (wrapp.getAlarms().get(i).getName().equals(newAlarm.getName())) {
                found = true;
            }
            else {
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
