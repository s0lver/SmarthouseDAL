package modelos;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafael on 09/12/2014.
 */
@Entity
@Table(name = "legos", schema = "", catalog = "smarthouse")
public class LegosEntity {
    private Integer id;
    private String mac;
    private Integer idTipoLego;
    private TiposlegoEntity tiposlegoByIdTipoLego;
    private Collection<LegosrecursosEntity> legosrecursosesById;

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
    @Column(name = "mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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

        LegosEntity that = (LegosEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idTipoLego != null ? !idTipoLego.equals(that.idTipoLego) : that.idTipoLego != null) return false;
        if (mac != null ? !mac.equals(that.mac) : that.mac != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mac != null ? mac.hashCode() : 0);
        result = 31 * result + (idTipoLego != null ? idTipoLego.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idTipoLego", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TiposlegoEntity getTiposlegoByIdTipoLego() {
        return tiposlegoByIdTipoLego;
    }

    public void setTiposlegoByIdTipoLego(TiposlegoEntity tiposlegoByIdTipoLego) {
        this.tiposlegoByIdTipoLego = tiposlegoByIdTipoLego;
    }

    @OneToMany(mappedBy = "legosByIdLego")
    public Collection<LegosrecursosEntity> getLegosrecursosesById() {
        return legosrecursosesById;
    }

    public void setLegosrecursosesById(Collection<LegosrecursosEntity> legosrecursosesById) {
        this.legosrecursosesById = legosrecursosesById;
    }
}
