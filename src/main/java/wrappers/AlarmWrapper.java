package wrappers;

import java.util.List;

import entities.alarm.Alarm;

public class AlarmWrapper {

    private List<Alarm> alarms;

    public AlarmWrapper() {
    }

    public AlarmWrapper(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    @Override
    public String toString() {
        return "AlarmWrapper [alarms=" + alarms + "]";
    }

}
