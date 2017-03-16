package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.core.Alarm;
import entities.core.AlarmType;
import entities.core.Article;
import wrappers.AlarmWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class AlarmControllerTest {

    @Autowired
    AlarmController alarmController;

    @Test
    public void testGetAlarm() {
        assertEquals(2, alarmController.getAll().getAlarms().size());
    }

    @Test
    public void testEditAlarm() {
        AlarmWrapper alarmWrapper = new AlarmWrapper(1, "Alarma modificada", new ArrayList<Article>(), AlarmType.CRITICAL, 1);
        alarmController.editAlarm(alarmWrapper);
        Alarm alarm = alarmController.getAll().getAlarms().get(0);
        assertEquals("Alarma modificada", alarm.getName());
        assertEquals(AlarmType.CRITICAL, alarm.getType());
        //assertEquals(0, alarm.getArticleList().size());
        assertEquals(1, alarm.getValue());
    }

}
