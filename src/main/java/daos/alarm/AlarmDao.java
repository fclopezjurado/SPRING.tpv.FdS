package daos.alarm;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.alarm.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, Integer> {

}
