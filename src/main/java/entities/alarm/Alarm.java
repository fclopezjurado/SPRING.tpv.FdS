package entities.alarm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import entities.core.Article;

@Entity
public class Alarm {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Article> articleList;

    @Column(nullable = false)
    private AlarmType type;

    @Column(nullable = false)
    private int value;
    
    public Alarm() {
        this.articleList = new ArrayList<>();
    }

    public Alarm(int id, String name, List<Article> articleList, AlarmType type, int value) {
        super();
        this.id = id;
        this.name = name;
        this.articleList = articleList;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public AlarmType getType() {
        return type;
    }

    public void setType(AlarmType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alarm other = (Alarm) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Alarm [id=" + id + ", name=" + name + ", articleList=" + articleList + ", type=" + type + "]";
    }

}
