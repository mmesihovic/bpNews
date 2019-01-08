package App.Repositories;

import App.Entities.FajloviDelila;
import App.Entities.FajloviSanja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IFajloviDelila extends CrudRepository<FajloviDelila, Long> {
    @Transactional
    IFajloviDelila findByName(String name);
}
