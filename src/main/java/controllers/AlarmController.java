package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AlarmDao;
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
    
}
