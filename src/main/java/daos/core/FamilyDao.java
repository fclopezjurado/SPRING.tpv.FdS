package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Family;

public interface FamilyDao extends JpaRepository<Family, Long> {

    public Family findByName(String name);

    public List<Family> findAll();
}
