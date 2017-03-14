package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AlarmDao;
import daos.core.ArticleDao;
import entities.core.Alarm;
import entities.core.Article;
import wrappers.AlarmWrapper;
import wrappers.AlarmsWrapper;



@Controller
public class AlarmController {

    @Autowired
    private AlarmDao alarmDao;
    
    @Autowired
    private ArticleDao articlesDao;
    
    @Autowired
    public void setAlarmDao(AlarmDao alarmDao) {
        this.alarmDao = alarmDao;
    }
    
    @Autowired
    public void setArticlesDao(ArticleDao articlesDao){
        this.articlesDao = articlesDao;
    }

    public AlarmsWrapper getAll() {
        /*List<Alarm> alarms = this.alarmDao.findAll();
        List<AlarmsWrapper> alarmsWrapper = new ArrayList<>();
        for(Alarm alarm : alarms) {
            alarmsWrapper.add(new AlarmWrapper(alarm.getId(), alarm.getName(), alarm.getType(), alarm.getArticleList(), alarm.getValue()));
        }*/
        return new AlarmsWrapper(this.alarmDao.findAll());
    }

    public void addNewAlarm(AlarmWrapper alarm) {
        List<Article> articles = new ArrayList<Article>();
        for(String nameProduct : alarm.getProducts()){
            articles.add(articlesDao.findByDescription(nameProduct));
        }
        Alarm alarmEntity = new Alarm(alarm.getName(), articles, alarm.getType(), alarm.getNumProducts());
        alarmDao.save(alarmEntity);
    }
    
    public void editAlarm(AlarmWrapper alarmWrapper) {
       Alarm alarm = alarmDao.findById(alarmWrapper.getId());
       alarm.setName(alarmWrapper.getName());
       alarm.setType(alarmWrapper.getType());
       alarm.setValue(alarmWrapper.getNumProducts());
       alarmDao.save(alarm);
    }
    
}
