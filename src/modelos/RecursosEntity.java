package modelos;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafael on 09/12/2014.
 */
@Entity
@Table(name = "recursos", schema = "", catalog = "smarthouse")
public class RecursosEntity {
    private Integer id;
    private String descripcion;
    private Byte conHorario;
    private Integer idTipoLego;
    private Collection<LegosrecursosEntity> legosrecursosesById;
    private TiposlegoEntity tiposlegoByIdTipoLego;

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
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "conHorario")
    public Byte getConHorario() {
        return conHorario;
    }

    public void setConHorario(Byte conHorario) {
        this.conHorario = conHorario;
    }

    @Basic
    @Column(name = "idTipoLego")
    public Integer getIdTipoLego() {
        return idTipoLego;
    }

    public void setIdTipoLego(Integer idTipoLego) {
        this.idTipoLego = idTipoLego;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecursosEntity that = (RecursosEntity) o;

        if (conHorario != null ? !conHorario.equals(that.conHorario) : that.conHorario != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idTipoLego != null ? !idTipoLego.equals(that.idTipoLego) : that.idTipoLego != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (conHorario != null ? conHorario.hashCode() : 0);
        result = 31 * result + (idTipoLego != null ? idTipoLego.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "recursosByIdRecurso")
    public Collection<LegosrecursosEntity> getLegosrecursosesById() {
        return legosrecursosesById;
    }

    public void setLegosrecursosesById(Collection<LegosrecursosEntity> legosrecursosesById) {
        this.legosrecursosesById = legosrecursosesById;
    }

    @ManyToOne
    @JoinColumn(name = "idTipoLego", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TiposlegoEntity getTiposlegoByIdTipoLego() {
        return tiposlegoByIdTipoLego;
    }

    public void setTiposlegoByIdTipoLego(TiposlegoEntity tiposlegoByIdTipoLego) {
        this.tiposlegoByIdTipoLego = tiposlegoByIdTipoLego;
    }
}
