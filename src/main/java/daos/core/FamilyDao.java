package daos.core;

import entities.core.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyDao extends JpaRepository<Family, Long> {

    public Family findByNameIgnoreCase(String name);

    public List<Family> findAll();
}
