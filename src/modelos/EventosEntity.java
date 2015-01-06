package modelos;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Rafael on 09/12/2014.
 */
@Entity
@Table(name = "eventos", schema = "", catalog = "smarthouse")
public class EventosEntity {
    private Integer id;
    private Integer idLegoRecurso;
    private Timestamp timestamp;
    private Byte sentido;
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
    @Column(name = "idLegoRecurso")
    public Integer getIdLegoRecurso() {
        return idLegoRecurso;
    }

    public void setIdLegoRecurso(Integer idLegoRecurso) {
        this.idLegoRecurso = idLegoRecurso;
    }

    @Basic
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "sentido")
    public Byte getSentido() {
        return sentido;
    }

    public void setSentido(Byte sentido) {
        this.sentido = sentido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventosEntity that = (EventosEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idLegoRecurso != null ? !idLegoRecurso.equals(that.idLegoRecurso) : that.idLegoRecurso != null)
            return false;
        if (sentido != null ? !sentido.equals(that.sentido) : that.sentido != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idLegoRecurso != null ? idLegoRecurso.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (sentido != null ? sentido.hashCode() : 0);
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
