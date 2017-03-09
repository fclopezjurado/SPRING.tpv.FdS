package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.AlarmController;
import wrappers.AlarmWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.ALARMS)
public class AlarmResource {
    
    private AlarmController alarmController;
    
    @Autowired
    public void setAdminController(AlarmController alarmController) {
        this.alarmController = alarmController;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public AlarmWrapper getAll() {
        return alarmController.getAll();
    }
    
}
