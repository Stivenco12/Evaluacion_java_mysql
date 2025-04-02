package evaluacionjava.aplication;

import java.util.List;
import evaluacionjava.domain.Entytis.Medico;
import evaluacionjava.domain.Repository.MedicoRepository;

public class MedicoUseCase {
    private final MedicoRepository repository;

    public MedicoUseCase(MedicoRepository repository){
        this.repository = repository;
    }
    public void registerPaciente(int id, String nombre, int especialidad_id, String horario_inicio, String horario_final){
        Medico medico = new Medico(id,nombre,especialidad_id,horario_inicio,horario_final);
        repository.save(medico);
    }
    public Medico getPacientes(int id) {
        return repository.searchById(id);
    }
    public List<Medico> listCita() {
        return repository.listAll();
    }
    public void updatePaciente(int id, String nombre, int especialidad_id, String horario_inicio, String horario_final){
        Medico medico = new Medico(id,nombre,especialidad_id,horario_inicio,horario_final);
        repository.update(medico);
    }
    public void deleteCita(int id) {
        repository.delete(id);
    } 
}
