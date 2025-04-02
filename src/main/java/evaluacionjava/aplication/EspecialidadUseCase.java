package evaluacionjava.aplication;

import java.util.List;
import evaluacionjava.domain.Entytis.Especialidad;
import evaluacionjava.domain.Repository.EspecialidadRepository;

public class EspecialidadUseCase {
       private final EspecialidadRepository repository;

    public EspecialidadUseCase(EspecialidadRepository repository){
        this.repository = repository;
    }
    public void registerespecialidad(int id, String nombre){
        Especialidad especialidad = new Especialidad(id,nombre);
        repository.save(especialidad);
    }
    public Especialidad getespecialidad(int cita_id) {
        return repository.searchById(cita_id);
    }
    public List<Especialidad> listespecialidad() {
        return repository.listAll();
    }
    public void updateespecialidad(int id, String nombre){
        Especialidad especialidad = new Especialidad(id,nombre);
        repository.update(especialidad);
    }
    public void deleteespecialidad(int cita_id) {
        repository.delete(cita_id);
    } 
}
