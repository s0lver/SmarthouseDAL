package modelos;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafael on 09/12/2014.
 */
@Entity
@Table(name = "tiposlego", schema = "", catalog = "smarthouse")
public class TiposlegoEntity {
    private Integer id;
    private String descripcion;
    private Collection<LegosEntity> legosesById;
    private Collection<RecursosEntity> recursosesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TiposlegoEntity that = (TiposlegoEntity) o;

        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tiposlegoByIdTipoLego")
    public Collection<LegosEntity> getLegosesById() {
        return legosesById;
    }

    public void setLegosesById(Collection<LegosEntity> legosesById) {
        this.legosesById = legosesById;
    }

    @OneToMany(mappedBy = "tiposlegoByIdTipoLego")
    public Collection<RecursosEntity> getRecursosesById() {
        return recursosesById;
    }

    public void setRecursosesById(Collection<RecursosEntity> recursosesById) {
        this.recursosesById = recursosesById;
    }
}
