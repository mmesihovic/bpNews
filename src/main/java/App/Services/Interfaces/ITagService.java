package App.Services.Interfaces;

import App.Entities.Tags;

import java.util.List;

/**
 * Interface defining methods for manipulating Tags table in Oracle database.
 *
 * @author  Mirza
 * @since   1.0
 */


public interface ITagService {
    List<Tags> getAllTags();
    Tags findByName(String name);
}
