package evaluacionjava.aplication;

import java.util.List;
import evaluacionjava.domain.Entytis.Paciente;
import evaluacionjava.domain.Repository.PacienteRepository;

public class PacienteUseCase {
    private final PacienteRepository repository;

    public PacienteUseCase(PacienteRepository repository){
        this.repository = repository;
    }
    public void registerPaciente(int id, String nombre, String apellido, String fecha_nacimiento, String direccion, String telefono,String email){
        Paciente paciente = new Paciente(id,nombre,apellido,fecha_nacimiento,direccion,telefono,email);
        repository.save(paciente);
    }
    public Paciente getPacientes(int id) {
        return repository.searchById(id);
    }
    public List<Paciente> listCita() {
        return repository.listAll();
    }
    public void updatePaciente(int id, String nombre, String apellido, String fecha_nacimiento, String direccion, String telefono,String email){
        Paciente paciente = new Paciente(id,nombre,apellido,fecha_nacimiento,direccion,telefono,email);
        repository.update(paciente);
    }
    public void deleteCita(int id) {
        repository.delete(id);
    } 
}
