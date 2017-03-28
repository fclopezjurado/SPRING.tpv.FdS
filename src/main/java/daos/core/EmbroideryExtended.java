package daos.core;

import java.util.List;

import entities.core.Embroidery;
import wrappers.EmbroideryFilterWrapper;

public interface EmbroideryExtended {
    public List<Embroidery> findEmbroideryByFilter(EmbroideryFilterWrapper embroidery);
}
