package controllers;

import daos.core.TextilePrintingDao;
import entities.core.TextilePrinting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import wrappers.TextilePrintingWrapper;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TextilePrintingController {

    @Autowired
    private TextilePrintingDao textilePrintingsDao;

    @Autowired
    public void setTextilePrintingDao(TextilePrintingDao textilePrintingsDao) {
        this.textilePrintingsDao = textilePrintingsDao;
    }

    public List<TextilePrintingWrapper> getAll() {
        List<TextilePrinting> TextilePrintingList = textilePrintingsDao.findAll();
        return textilePrintingListToTextilePrintingWrapperList(TextilePrintingList);
    }

    private List<TextilePrintingWrapper> textilePrintingListToTextilePrintingWrapperList(List<TextilePrinting> textilePrintingList) {
        List<TextilePrintingWrapper> textilePrintingWrapperList = new ArrayList<TextilePrintingWrapper>();
        for (TextilePrinting textilePrinting : textilePrintingList) {
            textilePrintingWrapperList.add(new TextilePrintingWrapper(textilePrinting));
        }
        return textilePrintingWrapperList;
    }

    public void removeTextilePrinting(long id) {
        TextilePrinting textilePrinting = textilePrintingsDao.findOne(id);
        textilePrintingsDao.delete(textilePrinting);
    }

    public void addTextilePrinting(TextilePrintingWrapper textilePrintingWrapper) {
        TextilePrinting textilePrinting = textilePrintingsDao.findOne(textilePrintingWrapper.getId());
        if (textilePrinting == null) {
            TextilePrinting newTextilePrinting = new TextilePrinting(textilePrintingWrapper.getId(), textilePrintingWrapper.getReference(),
                    textilePrintingWrapper.getRetailPrice(), textilePrintingWrapper.getDescription(), textilePrintingWrapper.getType());
            this.textilePrintingsDao.save(newTextilePrinting);
        }

    }

    public void updateTextilePrinting(TextilePrintingWrapper textilePrintingWrapper) {
        TextilePrinting textilePrinting = textilePrintingsDao.findOne(textilePrintingWrapper.getId());
        if (textilePrinting != null) {
            textilePrinting.setDescription(textilePrintingWrapper.getDescription());
            textilePrinting.setReference(textilePrintingWrapper.getReference());
            textilePrinting.setRetailPrice(textilePrintingWrapper.getRetailPrice());
            textilePrinting.setType(textilePrintingWrapper.getType());

            this.textilePrintingsDao.save(textilePrinting);
        }
    }

}
