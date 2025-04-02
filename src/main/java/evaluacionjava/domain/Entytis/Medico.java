package evaluacionjava.domain.Entytis;

public class Medico {
    private int id;
    private String nombre;
    private int especialidad_id;
    private String horario_inicio;
    private String horario_final;
    public Medico(int id, String nombre, int especialidad_id, String horario_inicio, String horario_final) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad_id = especialidad_id;
        this.horario_inicio = horario_inicio;
        this.horario_final = horario_final;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEspecialidad_id() {
        return especialidad_id;
    }
    public void setEspecialidad_id(int especialidad_id) {
        this.especialidad_id = especialidad_id;
    }
    public String getHorario_inicio() {
        return horario_inicio;
    }
    public void setHorario_inicio(String horario_inicio) {
        this.horario_inicio = horario_inicio;
    }
    public String getHorario_final() {
        return horario_final;
    }
    public void setHorario_final(String horario_final) {
        this.horario_final = horario_final;
    }
}
