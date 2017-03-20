package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.TextilePrintingDao;
import entities.core.TextilePrinting;
import wrappers.TextilePrintingWrapper;

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
    
    private List<TextilePrintingWrapper> textilePrintingListToTextilePrintingWrapperList(List<TextilePrinting>  textilePrintingList) {
        List<TextilePrintingWrapper> textilePrintingWrapperList = new ArrayList<TextilePrintingWrapper>();
        for(TextilePrinting textilePrinting : textilePrintingList) {
            textilePrintingWrapperList.add(new TextilePrintingWrapper(textilePrinting));
        }
        return textilePrintingWrapperList;
    }



}
