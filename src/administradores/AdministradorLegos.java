package administradores;

import iadministradores.IAdministradorLegos;
import iadministradores.IAdministradorLegosRecursos;
import modelos.LegosEntity;
import modelos.TiposlegoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class AdministradorLegos implements IAdministradorLegos {
    private Session sesion;

    public AdministradorLegos(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int agregar(LegosEntity lego) {
        validarExisteTipoLego(lego);
        validarMacDuplicada(lego);

        Transaction tx = sesion.beginTransaction();

        sesion.saveOrUpdate(lego);

        tx.commit();

        IAdministradorLegosRecursos administradorLegosRecursos;
        administradorLegosRecursos = new AdministradorLegosRecursos(sesion);

        administradorLegosRecursos.crearAsociacionesNuevoLego(lego);


        return lego.getId();
    }

    @Override
    public void eliminar(int id) {
        LegosEntity entidad = buscarPorId(id);

        if (entidad == null) {
            throw new RuntimeException(
                    String.format("El registro de %s con el id %d no existe",
                            LegosEntity.class.getClass().getName(), id)
            );
        }

        Transaction tx = sesion.beginTransaction();

        sesion.delete(entidad);

        tx.commit();
    }

    @Override
    public LegosEntity buscarPorId(int id) {
        LegosEntity entidad = (LegosEntity)
                sesion.get(LegosEntity.class, id);

        return entidad;
    }

    @Override
    public LegosEntity buscarPorMac(String mac) {
        String hql = "from LegosEntity where mac = :mac";
        Query consulta = sesion.createQuery(hql);
        consulta.setString("mac", mac);

        List resultados = consulta.list();

        if (resultados.size() == 0)
            return null;
        else
            return (LegosEntity)consulta.list().get(0);
    }

    @Override
    public Collection<LegosEntity> lista() {
        String hql = "from LegosEntity";
        Query consulta = sesion.createQuery(hql);
        List resultado = consulta.list();

        return resultado;
    }

    @Override
    public Collection<LegosEntity> listaPorTipo(int idTipoLego) {
        String hql = "from LegosEntity where idTipoLego = :idTipoLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idTipoLego", idTipoLego);

        List resultados = consulta.list();

        return resultados;
    }

    private void validarExisteTipoLego(LegosEntity lego){
        TiposlegoEntity tipoLego = buscarTipoLego(lego);
        if (null == tipoLego) {
            throw new RuntimeException(
                    String.format("No se pueda agregar el lego, el registro" +
                                    " de %s con el id %d no existe",
                            TiposlegoEntity.class.getSimpleName(),
                            lego.getIdTipoLego()));
        }
    }

    private void validarMacDuplicada(LegosEntity lego) {
        LegosEntity entidad = buscarPorMac(lego.getMac());
        if (null != entidad) {
            throw new RuntimeException(
                    String.format("No se pueda agregar el lego, ya hay un " +
                                    "registro de %s con la mac %s registrada",
                            LegosEntity.class.getClass().getName(),
                            lego.getMac()));
        }

    }

    private TiposlegoEntity buscarTipoLego(LegosEntity lego){
        AdministradorTiposLego administradorTiposLegos
                = new AdministradorTiposLego(sesion);

        TiposlegoEntity tipoLego
                = administradorTiposLegos.buscarPorId(lego.getIdTipoLego());

        return tipoLego;
    }
}

