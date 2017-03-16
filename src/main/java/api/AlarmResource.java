package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.InvalidAlarmTypeException;
import api.exceptions.InvalidNewAlarmException;
import controllers.AlarmController;
import entities.core.AlarmType;
import wrappers.AlarmWrapper;
import wrappers.AlarmsWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.ALARMS)
public class AlarmResource {

    private AlarmController alarmController;

    @Autowired
    public void setAdminController(AlarmController alarmController) {
        this.alarmController = alarmController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public AlarmsWrapper getAll() {
        return alarmController.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createAlarm(@RequestBody AlarmWrapper alarm) throws InvalidNewAlarmException {
        validNewAlarm(alarm);
        alarmController.addNewAlarm(alarm);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void editAlarm(@RequestBody AlarmWrapper alarm) throws InvalidAlarmTypeException {
        validType(alarm.getType());
        alarmController.editAlarm(alarm);
    }

    private void validNewAlarm(AlarmWrapper alarm) throws InvalidNewAlarmException {
        if (alarm.getName().isEmpty() || alarm.getProductsList().isEmpty() || alarm.getType() == null || alarm.getNumProducts() == 0) {
            throw new InvalidNewAlarmException();
        }
    }

    private void validType(AlarmType type) throws InvalidAlarmTypeException {
        if (type != AlarmType.WARNING && type != AlarmType.CRITICAL) {
            throw new InvalidAlarmTypeException();
        }
    }
}
