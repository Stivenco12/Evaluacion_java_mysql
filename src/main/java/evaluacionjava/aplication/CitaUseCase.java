package evaluacionjava.aplication;

import java.util.List;
import evaluacionjava.domain.Entytis.Cita;
import evaluacionjava.domain.Repository.CitaRepository;

public class CitaUseCase {
    private final CitaRepository repository;

    public CitaUseCase(CitaRepository repository){
        this.repository = repository;
    }
    public void registerCita(int cita_id, int paciente_id, int medico_id, String fecha_hora, String estado){
        Cita cita = new Cita(cita_id,paciente_id,medico_id,fecha_hora,estado);
        repository.save(cita);
    }
    public Cita getCita(int cita_id) {
        return repository.searchById(cita_id);
    }
    public List<Cita> listCita() {
        return repository.listAll();
    }
    public void updateCita(int cita_id, int paciente_id, int medico_id, String fecha_hora, String estado){
        Cita cita = new Cita(cita_id,paciente_id,medico_id,fecha_hora,estado);
        repository.update(cita);
    }
    public void deleteCita(int cita_id) {
        repository.delete(cita_id);
    } 
    public void updatecanselar(int cita_id, int paciente_id, int medico_id, String fecha_hora, String estado){
        Cita cita = new Cita(cita_id,paciente_id,medico_id,fecha_hora,estado);
        repository.update(cita);
    }
    public void deleteCita(int canselar, String estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCita'");
    }
}
