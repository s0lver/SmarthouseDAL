package modelos;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Rafael on 09/12/2014.
 */
@Entity
@Table(name = "horarios", schema = "", catalog = "smarthouse")
public class HorariosEntity {
    private Integer id;
    private Time horaInicio;
    private Time horaFin;
    private Integer idLegoRecurso;
    private LegosrecursosEntity legosrecursosByIdLegoRecurso;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "horaInicio")
    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Basic
    @Column(name = "horaFin")
    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    @Basic
    @Column(name = "idLegoRecurso")
    public Integer getIdLegoRecurso() {
        return idLegoRecurso;
    }

    public void setIdLegoRecurso(Integer idLegoRecurso) {
        this.idLegoRecurso = idLegoRecurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorariosEntity that = (HorariosEntity) o;

        if (horaFin != null ? !horaFin.equals(that.horaFin) : that.horaFin != null) return false;
        if (horaInicio != null ? !horaInicio.equals(that.horaInicio) : that.horaInicio != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idLegoRecurso != null ? !idLegoRecurso.equals(that.idLegoRecurso) : that.idLegoRecurso != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (horaInicio != null ? horaInicio.hashCode() : 0);
        result = 31 * result + (horaFin != null ? horaFin.hashCode() : 0);
        result = 31 * result + (idLegoRecurso != null ? idLegoRecurso.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idLegoRecurso", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public LegosrecursosEntity getLegosrecursosByIdLegoRecurso() {
        return legosrecursosByIdLegoRecurso;
    }

    public void setLegosrecursosByIdLegoRecurso(LegosrecursosEntity legosrecursosByIdLegoRecurso) {
        this.legosrecursosByIdLegoRecurso = legosrecursosByIdLegoRecurso;
    }
}
