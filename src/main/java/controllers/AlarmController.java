package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AlarmDao;
import entities.core.Alarm;
import wrappers.AlarmWrapper;
import wrappers.AlarmsWrapper;

@Controller
public class AlarmController {

    @Autowired
    private AlarmDao alarmDao;

    @Autowired
    public void setAlarmDao(AlarmDao alarmDao) {
        this.alarmDao = alarmDao;
    }

    public AlarmsWrapper getAll() {
        return new AlarmsWrapper(this.alarmDao.findAll());
    }

    public void addNewAlarm(AlarmWrapper alarm) {
        Alarm alarmEntity = new Alarm(alarm.getName(), alarm.getProductsList(), alarm.getType(), alarm.getNumProducts());
        alarmDao.save(alarmEntity);
    }

    public void editAlarm(AlarmWrapper alarmWrapper) {
        Alarm alarm = alarmDao.findById(alarmWrapper.getId());
        alarm.setName(alarmWrapper.getName());
        alarm.setType(alarmWrapper.getType());
        alarm.setArticleList(alarmWrapper.getProductsList());
        alarm.setValue(alarmWrapper.getNumProducts());
        alarmDao.save(alarm);
    }

}
