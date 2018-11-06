package App.Services.Implementations;

import App.Entities.Tags;
import App.Repositories.ITagRepository;
import App.Services.Interfaces.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * CategoryService encapsulates the state information needed for get and find Tags.
 * Uses ITagRepository.
 * Implements ITagService
 * @see ITagRepository
 * @see ITagService
 *
 * @author  Mirza
 * @since   1.0
 */

@Service
public class TagService implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    /**
     * Returns list of all tags from Tags table in Oracle database BP20
     *
     * @return      list of all tags from Oracle database BP20
     */

    @Override
    public List<Tags> getAllTags() {
        return (List<Tags>) tagRepository.findAll();
    }

    /**
     * Returns one tag with specified name
     *
     * @param name      used for finding tag with specific name
     * @return          only one Tag find by name
     */

    @Override
    public Tags findByName(String name) { return tagRepository.findByName(name); }
}
