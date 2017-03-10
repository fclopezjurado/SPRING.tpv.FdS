package wrappers;

import java.util.List;

import entities.alarm.Alarm;

public class AlarmsWrapper {

    private List<Alarm> alarms;

    public AlarmsWrapper() {
    }

    public AlarmsWrapper(List<Alarm> alarms) {
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
