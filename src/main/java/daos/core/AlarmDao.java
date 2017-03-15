package daos.core;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, Integer> {

    Alarm findById(int id);
    
    @Transactional
    Long deleteById(int id);

}
