package evaluacionjava.domain.Repository;

import java.util.List;
import evaluacionjava.domain.Entytis.Medico;

public interface MedicoRepository {
    void save(Medico medico);
    Medico searchById(int id);
    List<Medico> listAll();
    void update(Medico medico);
    void delete(int id);
} 