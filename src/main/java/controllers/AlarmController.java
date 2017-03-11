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
    
}
