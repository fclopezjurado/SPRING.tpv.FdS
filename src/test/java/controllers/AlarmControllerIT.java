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
import wrappers.AlarmWrapper;
import wrappers.ArticleWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class AlarmControllerIT {

    @Autowired
    private AlarmController alarmController;

    @Autowired
    private ArticleController articleController;

    @Test
    public void testGetAlarm() {
        assertEquals(3, alarmController.getAll().getAlarms().size());
    }

    @Test
    public void testEditAlarm() {
        AlarmWrapper alarmWrapper = new AlarmWrapper(1, "Alarma modificada", new ArrayList<ArticleWrapper>(), AlarmType.CRITICAL, 1);
        alarmController.editAlarm(alarmWrapper);
        Alarm alarm = alarmController.getAll().getAlarms().get(0);
        assertEquals("Alarma modificada", alarm.getName());
        assertEquals(AlarmType.CRITICAL, alarm.getType());
        // assertEquals(0, alarm.getArticleList().size());
        assertEquals(1, alarm.getValue());
    }

    @Test
    public void testCreateAlarm() {
        AlarmWrapper alarmWrapper = new AlarmWrapper("nuevaAlarma", AlarmType.CRITICAL, new ArrayList<ArticleWrapper>(), 1);
        alarmController.addNewAlarm(alarmWrapper);
        Alarm alarm = alarmController.getAll().getAlarms().get(alarmController.getAll().getAlarms().size() - 1);
        assertEquals("nuevaAlarma", alarm.getName());
        assertEquals(AlarmType.CRITICAL, alarm.getType());
        assertEquals(0, alarm.getArticleList().size());
        assertNotEquals(0, alarm.getId());
        assertEquals(1, alarm.getValue());
    }

    @Test
    public void testDeleteAlarm() {
        AlarmWrapper alarmWrapper = new AlarmWrapper("nuevaAlarma", AlarmType.CRITICAL, new ArrayList<ArticleWrapper>(), 1);
        alarmController.addNewAlarm(alarmWrapper);
        Alarm alarm = alarmController.getAll().getAlarms().get(alarmController.getAll().getAlarms().size() - 1);
        alarmController.removeAlarm(alarm.getId());
        alarm = alarmController.getAll().getAlarms().get(alarmController.getAll().getAlarms().size() - 1);
        assertNotEquals(1, alarm.getId());
    }

    @Test
    public void testSendAlarm() {
        int stock = articleController.getAll().get(2).getStock();
        articleController.updateStock(articleController.getAll().get(2), 0);
        assertEquals(true, alarmController.checkAlarmArticle(articleController.getAll().get(2)));
        articleController.updateStock(articleController.getAll().get(2), stock);
    }

    @Test
    public void testNoSendAlarm() {
        int stock = articleController.getAll().get(2).getStock();
        articleController.updateStock(articleController.getAll().get(2), 10);
        assertEquals(false, alarmController.checkAlarmArticle(articleController.getAll().get(2)));
        articleController.updateStock(articleController.getAll().get(2), stock);
    }

}
