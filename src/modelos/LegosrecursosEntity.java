package modelos;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafael on 09/12/2014.
 */
@Entity
@Table(name = "legosrecursos", schema = "", catalog = "smarthouse")
public class LegosrecursosEntity {
    private Integer id;
    private Integer idLego;
    private Integer idRecurso;
    private Collection<EventosEntity> eventosesById;
    private Collection<HorariosEntity> horariosesById;
    private LegosEntity legosByIdLego;
    private RecursosEntity recursosByIdRecurso;

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
    @Column(name = "idLego")
    public Integer getIdLego() {
        return idLego;
    }

    public void setIdLego(Integer idLego) {
        this.idLego = idLego;
    }

    @Basic
    @Column(name = "idRecurso")
    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegosrecursosEntity that = (LegosrecursosEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idLego != null ? !idLego.equals(that.idLego) : that.idLego != null) return false;
        if (idRecurso != null ? !idRecurso.equals(that.idRecurso) : that.idRecurso != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idLego != null ? idLego.hashCode() : 0);
        result = 31 * result + (idRecurso != null ? idRecurso.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "legosrecursosByIdLegoRecurso")
    public Collection<EventosEntity> getEventosesById() {
        return eventosesById;
    }

    public void setEventosesById(Collection<EventosEntity> eventosesById) {
        this.eventosesById = eventosesById;
    }

    @OneToMany(mappedBy = "legosrecursosByIdLegoRecurso")
    public Collection<HorariosEntity> getHorariosesById() {
        return horariosesById;
    }

    public void setHorariosesById(Collection<HorariosEntity> horariosesById) {
        this.horariosesById = horariosesById;
    }

    @ManyToOne
    @JoinColumn(name = "idLego", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public LegosEntity getLegosByIdLego() {
        return legosByIdLego;
    }

    public void setLegosByIdLego(LegosEntity legosByIdLego) {
        this.legosByIdLego = legosByIdLego;
    }

    @ManyToOne
    @JoinColumn(name = "idRecurso", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public RecursosEntity getRecursosByIdRecurso() {
        return recursosByIdRecurso;
    }

    public void setRecursosByIdRecurso(RecursosEntity recursosByIdRecurso) {
        this.recursosByIdRecurso = recursosByIdRecurso;
    }
}
