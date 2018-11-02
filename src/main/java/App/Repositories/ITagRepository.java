package App.Repositories;

import App.Entities.Tags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepository extends CrudRepository<Tags, Long> {
    Tags findByName(String name);
}
