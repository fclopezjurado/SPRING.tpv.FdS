package daos.core;

import entities.core.TextilePrinting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextilePrintingDao extends JpaRepository<TextilePrinting, Long> {
    TextilePrinting findById(long id);

}
