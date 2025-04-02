package evaluacionjava.domain.Repository;

import java.util.List;
import evaluacionjava.domain.Entytis.Especialidad;

public interface EspecialidadRepository {
    void save(Especialidad especialidad);
    Especialidad searchById(int id);
    List<Especialidad> listAll();
    void update(Especialidad especialidad);
    void delete(int id);
}
