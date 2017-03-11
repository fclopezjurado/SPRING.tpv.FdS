package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, Integer> {

}
