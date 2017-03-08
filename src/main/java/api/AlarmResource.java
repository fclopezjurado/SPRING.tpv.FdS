package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controllers.AlarmController;

@RestController
@RequestMapping(Uris.VERSION + Uris.ALARMS)
public class AlarmResource {
    
    private AlarmController alarmController;
    
    @Autowired
    public void setAdminController(AlarmController alarmController) {
        this.alarmController = alarmController;
    }
    
}
