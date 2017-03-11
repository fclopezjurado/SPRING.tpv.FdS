package entities.alarm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.core.Alarm;
import entities.core.AlarmType;
import entities.core.Article;

public class AlarmTest {

    private Alarm alarm;

    @Before
    public void init() {
        alarm = new Alarm();
    }

    @Test
    public void testSetId() {
        alarm.setId(2);
        assertEquals(2, alarm.getId());
    }

    @Test
    public void testSetName() {
        alarm.setName("Alarma test");
        assertEquals("Alarma test", alarm.getName());
        alarm.setName("Alarma testÑ2342");
        assertEquals("Alarma testÑ2342", alarm.getName());
        alarm.setName("Alarma testÑ2342_324325@");
        assertEquals("Alarma testÑ2342_324325@", alarm.getName());
    }

    @Test
    public void testSetArticleList() {
        List<Article> articles = new ArrayList<Article>();
        for (int i = 0; i < 4; i++) {
            Article a = new Article();
            articles.add(a);
        }
        alarm.setArticleList(articles);
        assertNotNull(alarm.getArticleList());
        assertEquals(4, alarm.getArticleList().size());
    }
    
    @Test
    public void testSetValue() {
        alarm.setValue(2);
        assertEquals(2, alarm.getValue());
    }

    @Test
    public void testSetType() {
        alarm.setType(AlarmType.CRITICAL);
        assertEquals(AlarmType.CRITICAL, alarm.getType());
        alarm.setType(AlarmType.WARNING);
        assertEquals(AlarmType.WARNING, alarm.getType());
    }

    @Test
    public void testHashCode() {
        alarm.setId(53);
        assertEquals(53, alarm.hashCode());
    }

}
