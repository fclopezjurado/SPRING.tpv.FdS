package daos.core;

import java.util.List;

import entities.core.TextilePrinting;
import wrappers.TextilePritingFilterWrapper;

public interface TextilePritingFExtended {
    public List<TextilePrinting> findArticlesByFilter(TextilePritingFilterWrapper textilePriting);
}
