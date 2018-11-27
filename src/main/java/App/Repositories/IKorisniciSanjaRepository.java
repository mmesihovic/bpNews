package App.Repositories;

import App.Entities.KorisniciSanja;
import org.springframework.data.repository.CrudRepository;

public interface IKorisniciSanjaRepository extends CrudRepository<KorisniciSanja, Long> {
    KorisniciSanja findByMail(String mail);
}
