package App.Repositories;

import App.Entities.FajloviTin;
import App.Entities.FajloviSanja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IFajloviDelila extends CrudRepository<FajloviTin, Long> {
    @Transactional
    IFajloviTin findByName(String name);
}
