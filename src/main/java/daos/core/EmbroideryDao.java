package daos.core;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Embroidery;

public interface EmbroideryDao extends JpaRepository<Embroidery, Long>, EmbroideryExtended {
    
    public List<Embroidery> findAll();
    public Embroidery findById(Long id);

}
