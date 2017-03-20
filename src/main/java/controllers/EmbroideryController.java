package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.EmbroideryDao;
import entities.core.Embroidery;
import wrappers.EmbroideryWrapper;

@Controller
public class EmbroideryController {

    @Autowired
    private EmbroideryDao embroideryDao;
    
    @Autowired
    public void setEmbroideryDao(EmbroideryDao embroideryDao) {
        this.embroideryDao = embroideryDao;
    }
    
    public List<EmbroideryWrapper> getAll() {
        List<Embroidery> embroideryList = embroideryDao.findAll();
        return embroideryListToEmbroideryWrapperList(embroideryList);
    }

    
    private List<EmbroideryWrapper> embroideryListToEmbroideryWrapperList(List<Embroidery> embroideryList) {
        List<EmbroideryWrapper> embroideryWrapperList = new ArrayList<EmbroideryWrapper>();
        for(Embroidery embroidery : embroideryList) {
            embroideryWrapperList.add(new EmbroideryWrapper(embroidery));
        }
        return embroideryWrapperList;
    }
    
    public void removeEmbroidery(long id) {
        Embroidery embroidery = embroideryDao.findOne(id);
        embroideryDao.delete(embroidery);
}

}
