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
import wrappers.ArticleWrapper;

@Controller
public class AlarmController {

    @Autowired
    private AlarmDao alarmDao;
    
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    public void setAlarmDao(AlarmDao alarmDao) {
        this.alarmDao = alarmDao;
    }

    public AlarmsWrapper getAll() {
        return new AlarmsWrapper(this.alarmDao.findAll());
    }

    public void addNewAlarm(AlarmWrapper alarm) {
        List<Article> articles = convertArticleWrapperListToArticleList(alarm.getProductsList());
        Alarm alarmEntity = new Alarm(alarm.getName(), articles, alarm.getType(), alarm.getNumProducts());
        alarmDao.save(alarmEntity);
    }

    public void editAlarm(AlarmWrapper alarmWrapper) {
        Alarm alarm = alarmDao.findById(alarmWrapper.getId());
        List<Article> articlesList = convertArticleWrapperListToArticleList(alarmWrapper.getProductsList());
        alarm.setName(alarmWrapper.getName());
        alarm.setType(alarmWrapper.getType());
        alarm.setArticleList(articlesList);
        alarm.setValue(alarmWrapper.getNumProducts());
        alarmDao.save(alarm);
    }

    public void removeAlarm(int id) {
        alarmDao.deleteById(id);
    }
    
    protected boolean checkAlarmArticle(ArticleWrapper article) {
        boolean result = false;
        List<Alarm> listAlarm = alarmDao.findByArticleListContaining(article.getId());
        int i = 0;
        while(!result && i < listAlarm.size()){
            result = article.getStock() <= listAlarm.get(i).getValue();
            i++;
        }
        return result;
    }
    
    private List<Article> convertArticleWrapperListToArticleList(List<ArticleWrapper> articleWrapperList) {
        List<Article> articlesList = new ArrayList<Article>();
        for(ArticleWrapper articleWrapper : articleWrapperList) {
            articlesList.add(articleDao.findById(articleWrapper.getId()));
        }
        return articlesList;
    }

}
