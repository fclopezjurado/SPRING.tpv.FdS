package controllers;

import daos.core.EmbroideryDao;
import entities.core.Embroidery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import wrappers.EmbroideryWrapper;

import java.util.ArrayList;
import java.util.List;

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
        for (Embroidery embroidery : embroideryList) {
            embroideryWrapperList.add(new EmbroideryWrapper(embroidery));
        }
        return embroideryWrapperList;
    }

    public void removeEmbroidery(long id) {
        Embroidery embroidery = embroideryDao.findOne(id);
        embroideryDao.delete(embroidery);
    }

    public void addEmbroidery(EmbroideryWrapper embroideryWrapper) {
        Embroidery embroidery = embroideryDao.findOne(embroideryWrapper.getId());
        if (embroidery == null) {
            Embroidery newEmbroidery = new Embroidery(embroideryWrapper.getId(), embroideryWrapper.getReference(),
                    embroideryWrapper.getRetailPrice(), embroideryWrapper.getDescription(), embroideryWrapper.getStitches(),
                    embroideryWrapper.getColors(), embroideryWrapper.getSquareMillimeters());
            this.embroideryDao.save(newEmbroidery);
        }

    }

    public void updateEmbroidery(EmbroideryWrapper embroideryWrapper) {
        Embroidery embroidery = embroideryDao.findOne(embroideryWrapper.getId());
        if (embroidery != null) {
            embroidery.setDescription(embroideryWrapper.getDescription());
            embroidery.setReference(embroideryWrapper.getReference());
            embroidery.setRetailPrice(embroideryWrapper.getRetailPrice());
            
            /*TODO */
            /*embroidery.setStitches(embroideryWrapper.getStitches());
            embroidery.setSquareMillimeters(embroideryWrapper.getSquareMillimeters());
            embroidery.setColors(embroideryWrapper.getColors());*/

            this.embroideryDao.save(embroidery);
        }
    }
    public EmbroideryWrapper getEmbroidery(long id) {
        Embroidery embroidery = embroideryDao.findById(id);
        EmbroideryWrapper wrapper = new EmbroideryWrapper(embroidery);
        return wrapper;
        
    }

}
