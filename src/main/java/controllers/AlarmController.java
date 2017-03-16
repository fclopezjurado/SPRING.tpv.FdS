package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AlarmDao;
import entities.core.Alarm;
import entities.core.Article;
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

    public void removeAlarm(int id) {
        alarmDao.deleteById(id);
    }
    
    protected boolean checkAlarmArticle(Article article) {
        boolean result = false;
        List<Alarm> listAlarm = alarmDao.findByArticleListContaining(article.getId());
        int i = 0;
        while(!result && i < listAlarm.size()){
            result = article.getStock() <= listAlarm.get(i).getValue();
            i++;
        }
        return result;
    }

}
