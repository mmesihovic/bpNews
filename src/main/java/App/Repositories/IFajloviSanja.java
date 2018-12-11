package App.Repositories;

import App.Entities.FajloviSanja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IFajloviSanja extends CrudRepository<FajloviSanja, Long> {
    @Transactional
    IFajloviSanja findByName(String name);
}
