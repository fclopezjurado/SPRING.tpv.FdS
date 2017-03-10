package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.alarm.AlarmDao;
import entities.alarm.Alarm;
import wrappers.AlarmWrapper;
import wrappers.AlarmsWrapper;

@Controller
public class AlarmController {

    @Autowired
    private AlarmDao alarmDao;
    
    @Autowired
    private MockArticlesDao articlesDao;
    
    @Autowired
    public void setAlarmDao(AlarmDao alarmDao) {
        this.alarmDao = alarmDao;
    }
    
    @Autowired
    public void setArticlesDao(MockArticlesDao articlesDao){
        this.articlesDao = articlesDao;
    }

    public AlarmsWrapper getAll() {
        return new AlarmsWrapper(this.alarmDao.findAll());
    }

    public void addNewAlarm(AlarmWrapper alarm) {
        Alarm alarmEntity = new Alarm(alarm.getName(), alarm.getProducts());
        alarmDao.save(alarmEntity);
    }
    
}
