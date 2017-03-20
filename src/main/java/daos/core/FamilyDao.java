package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.core.Family;

public interface FamilyDao extends JpaRepository<Family, Long>{
     public Family findById(Long id);
}
