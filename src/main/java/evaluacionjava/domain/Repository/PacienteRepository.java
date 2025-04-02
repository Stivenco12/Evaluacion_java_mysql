package evaluacionjava.domain.Repository;

import java.util.List;
import evaluacionjava.domain.Entytis.Paciente;

public interface PacienteRepository {
    void save(Paciente paciente);
    Paciente searchById(int id);
    List<Paciente> listAll();
    void update(Paciente paciente);
    void delete(int id);
}
