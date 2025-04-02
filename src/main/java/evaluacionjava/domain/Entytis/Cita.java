package evaluacionjava.domain.Entytis;

public class Cita {
    private int cita_id;
    private int paciente_id;
    private int medico_id;
    private String fecha_hora;
    private String estado;
    public Cita(int cita_id, int paciente_id, int medico_id, String fecha_hora, String estado) {
        this.cita_id = cita_id;
        this.paciente_id = paciente_id;
        this.medico_id = medico_id;
        this.fecha_hora = fecha_hora;
        this.estado = estado;
    }
    public int getCita_id() {
        return cita_id;
    }
    public void setCita_id(int cita_id) {
        this.cita_id = cita_id;
    }
    public int getPaciente_id() {
        return paciente_id;
    }
    public void setPaciente_id(int paciente_id) {
        this.paciente_id = paciente_id;
    }
    public int getMedico_id() {
        return medico_id;
    }
    public void setMedico_id(int medico_id) {
        this.medico_id = medico_id;
    }
    public String getFecha_hora() {
        return fecha_hora;
    }
    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
