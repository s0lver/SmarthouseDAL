package administradores;

import iadministradores.IAdministradorTiposLego;
import modelos.TiposlegoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class AdministradorTiposLego implements IAdministradorTiposLego {
    private Session sesion;

    public AdministradorTiposLego(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int agregar(TiposlegoEntity tipoLego) {
        Transaction tx = sesion.beginTransaction();
        sesion.saveOrUpdate(tipoLego);

        tx.commit();

        return tipoLego.getId();
    }

    @Override
    public void eliminar(int id) {
        TiposlegoEntity entidad = buscarPorId(id);
        if (entidad == null) {
            throw new RuntimeException(
                    String.format("El registro de %s con el id %d no existe",
                            TiposlegoEntity.class.getSimpleName(), id));
        }

        Transaction tx = sesion.beginTransaction();

        sesion.delete(entidad);

        tx.commit();
    }

    @Override
    public TiposlegoEntity buscarPorId(int id) {
        TiposlegoEntity entidad = (TiposlegoEntity)
                sesion.get(TiposlegoEntity.class, id);

        return entidad;
    }

    @Override
    public Collection<TiposlegoEntity> lista() {
        String hql = "from TiposlegoEntity";
        Query consulta = sesion.createQuery(hql);

        List resultados = consulta.list();

        return resultados;
    }
}
