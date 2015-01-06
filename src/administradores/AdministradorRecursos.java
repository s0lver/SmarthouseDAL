package administradores;

import iadministradores.IAdministradorRecursos;
import modelos.LegosEntity;
import modelos.RecursosEntity;
import modelos.TiposlegoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class AdministradorRecursos implements IAdministradorRecursos {
    private Session sesion;

    public AdministradorRecursos(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int agregar(RecursosEntity recurso) {
        validarExisteTipoLego(recurso);

        Transaction tx = sesion.beginTransaction();

        sesion.saveOrUpdate(recurso);

        tx.commit();

        return recurso.getId();
    }

    @Override
    public void eliminar(int id) {
        RecursosEntity entidad = buscarPorId(id);

        if (entidad == null) {
            throw new RuntimeException(
                    String.format("El registro de %s con el id %d no existe",
                            RecursosEntity.class.getSimpleName(), id)
            );
        }

        Transaction tx = sesion.beginTransaction();

        sesion.delete(entidad);

        tx.commit();
    }

    @Override
    public RecursosEntity buscarPorId(int id) {
        RecursosEntity entidad = (RecursosEntity)
                sesion.get(RecursosEntity.class, id);

        return entidad;
    }

    @Override
    public Collection<RecursosEntity> buscarPorTipoLego(int idTipoLego) {
        String hql = "from RecursosEntity where idTipoLego = :idTipoLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idTipoLego", idTipoLego);

        List resultados = consulta.list();

        return resultados;
    }

    private void validarExisteTipoLego(RecursosEntity recurso) {
        AdministradorTiposLego administradorTiposLego = new AdministradorTiposLego(sesion);
        TiposlegoEntity tipoLego = administradorTiposLego.buscarPorId(recurso.getIdTipoLego());
        if (null == tipoLego) {
            throw new RuntimeException(
                    String.format("No se pueda agregar el lego, el registro" +
                                    " de %s con el id %d no existe",
                            TiposlegoEntity.class.getSimpleName(),
                            recurso.getIdTipoLego()));
        }
    }
}
