package administradores;

import iadministradores.IAdministradorEventos;
import modelos.EventosEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AdministradorEventos implements IAdministradorEventos {
    private Session sesion;

    public AdministradorEventos(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int agregar(EventosEntity evento) {
        Transaction tx = sesion.beginTransaction();

        sesion.saveOrUpdate(evento);

        tx.commit();

        return evento.getId();
    }

    @Override
    public EventosEntity buscarPorId(int id) {
        EventosEntity entidad = (EventosEntity) sesion.get(EventosEntity.class, id);
        return entidad;
    }

    @Override
    public Collection<EventosEntity> listaPorLego(int idLego) {
        String hql = "from EventosEntity where legosrecursosByIdLegoRecurso.idLego = :idLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idLego", idLego);

        return consulta.list();
    }

    @Override
    public Collection<EventosEntity> listaPorLegoConFechas(int idLego, Date fechaInicio, Date fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            return listaPorLegoConAmbasFechas(idLego, fechaInicio, fechaFin);
        }else if (fechaInicio == null && fechaFin != null) {
            return listaPorLegoConFechaFin(idLego, fechaFin);
        }else if (fechaInicio != null && fechaFin == null) {
            return listaPorLegoConFechaInicio(idLego, fechaInicio);
        }else {
            return listaPorLego(idLego);
        }
    }

    private Collection<EventosEntity> listaPorLegoConAmbasFechas(int idLego, Date fechaInicio, Date fechaFin) {
        String hql = "from EventosEntity where timestamp >= :fechaInicio and timestamp <= :fechaFin and legosrecursosByIdLegoRecurso.idLego = :idLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idLego", idLego);
        consulta.setTimestamp("fechaInicio", fechaInicio);
        consulta.setTimestamp("fechaFin", fechaFin);

        return consulta.list();
    }

    private Collection<EventosEntity> listaPorLegoConFechaInicio(int idLego, Date fechaInicio) {
        String hql = "from EventosEntity where timestamp >= :fechaInicio and legosrecursosByIdLegoRecurso.idLego = :idLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idLego", idLego);
        consulta.setTimestamp("fechaInicio", fechaInicio);

        return consulta.list();
    }

    private Collection<EventosEntity> listaPorLegoConFechaFin(int idLego, Date fechaFin) {
        String hql = "from EventosEntity where timestamp <= :fechaFin and legosrecursosByIdLegoRecurso.idLego = :idLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idLego", idLego);
        consulta.setTimestamp("fechaFin", fechaFin);

        return consulta.list();
    }

    @Override
    public Collection<EventosEntity> listaPorTipoLego(int idTipoLego) {
        String hql = "from EventosEntity where legosrecursosByIdLegoRecurso.legosByIdLego.idTipoLego = :idTipoLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idTipoLego", idTipoLego);

        return consulta.list();
    }

    @Override
    public Collection<EventosEntity> listaPorTipoLegoConFechas(int idTipoLego, Date fechaInicio, Date fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            return listaPorTipoLegoConAmbasFechas(idTipoLego, fechaInicio, fechaFin);
        }else if (fechaInicio == null && fechaFin != null) {
            return listaPorTipoLegoConFechaFin(idTipoLego, fechaFin);
        }else if (fechaInicio != null && fechaFin == null) {
            return listaPorTipoLegoConFechaInicio(idTipoLego, fechaInicio);
        }else {
            return listaPorTipoLego(idTipoLego);
        }
    }

    private Collection<EventosEntity> listaPorTipoLegoConFechaInicio(int idTipoLego, Date fechaInicio) {
        String hql = "from EventosEntity where timestamp >= :fechaInicio and legosrecursosByIdLegoRecurso.legosByIdLego.idTipoLego = :idTipoLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idTipoLego", idTipoLego);
        consulta.setTimestamp("fechaInicio", fechaInicio);

        return consulta.list();
    }

    private Collection<EventosEntity> listaPorTipoLegoConFechaFin(int idTipoLego, Date fechaFin) {
        String hql = "from EventosEntity where timestamp <= :fechaFin and legosrecursosByIdLegoRecurso.legosByIdLego.idTipoLego = :idTipoLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idTipoLego", idTipoLego);
        consulta.setTimestamp("fechaFin", fechaFin);

        return consulta.list();
    }

    private Collection<EventosEntity> listaPorTipoLegoConAmbasFechas(int idTipoLego, Date fechaInicio, Date fechaFin) {
        String hql = "from EventosEntity where timestamp >= :fechaInicio and timestamp <= :fechaFin and legosrecursosByIdLegoRecurso.legosByIdLego.idTipoLego = :idTipoLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idTipoLego", idTipoLego);
        consulta.setTimestamp("fechaInicio", fechaInicio);
        consulta.setTimestamp("fechaFin", fechaFin);

        return consulta.list();
    }

    @Override
    public Collection<EventosEntity> lista() {
        String hql = "from EventosEntity";
        Query consulta = sesion.createQuery(hql);

        return consulta.list();
    }

    @Override
    public Collection<EventosEntity> listaConFechas(Date fechaInicio, Date fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            return listaConAmbasFechas(fechaInicio, fechaFin);
        }else if (fechaInicio == null && fechaFin != null) {
            return listaConFechaFin(fechaFin);
        }else if (fechaInicio != null && fechaFin == null) {
            return listaConFechaInicio(fechaInicio);
        }else {
            return lista();
        }
    }

    private Collection<EventosEntity> listaConFechaInicio(Date fechaInicio) {
        String hql = "from EventosEntity where timestamp >= :fechaInicio";
        Query consulta = sesion.createQuery(hql);
        consulta.setTimestamp("fechaInicio", fechaInicio);

        return consulta.list();
    }

    private Collection<EventosEntity> listaConFechaFin(Date fechaFin) {
        String hql = "from EventosEntity where timestamp <= :fechaFin";
        Query consulta = sesion.createQuery(hql);
        consulta.setTimestamp("fechaFin", fechaFin);

        return consulta.list();
    }

    private Collection<EventosEntity> listaConAmbasFechas(Date fechaInicio, Date fechaFin) {
        String hql = "from EventosEntity where timestamp >= :fechaInicio and timestamp <= :fechaFin";
        Query consulta = sesion.createQuery(hql);
        consulta.setTimestamp("fechaInicio", fechaInicio);
        consulta.setTimestamp("fechaFin", fechaFin);

        return consulta.list();
    }
}
