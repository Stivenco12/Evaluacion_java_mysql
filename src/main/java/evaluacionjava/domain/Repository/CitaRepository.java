package evaluacionjava.domain.Repository;

import java.util.List;

import evaluacionjava.domain.Entytis.Cita;

public interface CitaRepository {
    void save(Cita cita);
    Cita searchById(int cita_id);
    List<Cita> listAll();
    void update(Cita cita);
    void delete(int cita_id);
    void cancelar(Cita cita);
} 
