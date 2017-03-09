package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.alarm.AlarmDao;
import entities.alarm.Alarm;
import wrappers.AlarmWrapper;

@Controller
public class AlarmController {

    @Autowired
    private AlarmDao alarmDao;
    
    @Autowired
    public void setAlarmDao(AlarmDao alarmDao) {
        this.alarmDao = alarmDao;
    }

    public AlarmWrapper getAll() {
        return new AlarmWrapper(this.alarmDao.findAll());
    }
    
}
