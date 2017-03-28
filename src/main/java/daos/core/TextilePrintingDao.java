package daos.core;

import entities.core.TextilePrinting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TextilePrintingDao extends JpaRepository<TextilePrinting, Long> , TextilePritingFExtended{
    public List<TextilePrinting> findAll();
    public TextilePrinting findById(long id);
}
