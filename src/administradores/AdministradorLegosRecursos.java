package administradores;

import iadministradores.IAdministradorHorarios;
import iadministradores.IAdministradorLegos;
import iadministradores.IAdministradorLegosRecursos;
import iadministradores.IAdministradorRecursos;
import modelos.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Time;
import java.util.Collection;
import java.util.List;

public class AdministradorLegosRecursos implements IAdministradorLegosRecursos{
    private Session sesion;

    public AdministradorLegosRecursos(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int agregar(LegosrecursosEntity legorecurso) {
        validarExisteLego(legorecurso);
        validarExisteRecurso(legorecurso);
        validarExisteAsociacionEntreLegoYRecurso(legorecurso);

        Transaction tx = sesion.beginTransaction();

        sesion.saveOrUpdate(legorecurso);

        tx.commit();

        return legorecurso.getId();
    }

    @Override
    public void eliminar(int id) {
        LegosrecursosEntity entidad = buscarPorId(id);

        validarExisteRegistro(entidad, id);

        Transaction tx = sesion.beginTransaction();

        sesion.delete(entidad);

        tx.commit();
    }

    @Override
    public LegosrecursosEntity buscarPorId(int id) {
        LegosrecursosEntity entidad = (LegosrecursosEntity)
                sesion.get(LegosrecursosEntity.class, id);

        return entidad;
    }

    @Override
    public LegosrecursosEntity buscarPorLegoYRecurso(int idLego, int idRecurso) {
        String hql = "from LegosrecursosEntity where idLego = :idLego and idRecurso = :idRecurso";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idLego", idLego);
        consulta.setInteger("idRecurso", idRecurso);

        List resultados = consulta.list();

        if (resultados.size() == 0)
            return null;
        else
        return
                (LegosrecursosEntity) resultados.get(0);
    }

    @Override
    public void crearAsociacionesNuevoLego(LegosEntity lego) {
        int idTipoLego = lego.getIdTipoLego();

        IAdministradorRecursos administradorRecursos;
        administradorRecursos = new AdministradorRecursos(sesion);

        IAdministradorHorarios administradorHorarios;
        administradorHorarios = new AdministradorHorarios(sesion);

        Collection<RecursosEntity> recursos = administradorRecursos.buscarPorTipoLego(idTipoLego);

        Time horaCero = new Time(0, 0, 0);
        for (RecursosEntity recurso : recursos) {
            LegosrecursosEntity lr = new LegosrecursosEntity();
            lr.setIdRecurso(recurso.getId());
            lr.setIdLego(lego.getId());

            int idLegoRecurso = agregar(lr);

            HorariosEntity horario = new HorariosEntity();
            horario.setIdLegoRecurso(idLegoRecurso);
            horario.setHoraInicio(horaCero);
            horario.setHoraFin(horaCero);

            administradorHorarios.agregar(horario);
        }

    }

    private void validarExisteAsociacionEntreLegoYRecurso(LegosrecursosEntity legorecurso) {
        IAdministradorLegos administradorLegos = new AdministradorLegos(sesion);
        IAdministradorRecursos administradorRecursos = new AdministradorRecursos(sesion);

        LegosEntity lego = administradorLegos.buscarPorId(legorecurso.getIdLego());
        RecursosEntity recurso = administradorRecursos.buscarPorId(legorecurso.getIdRecurso());

        if (recurso.getIdTipoLego() != lego.getIdTipoLego()){
            throw new RuntimeException(String.format(
                    "No se pueda agregar el legorecurso, no existe asociación entre el tipo del lego %s y el tipo de recurso %s",
                    lego.getTiposlegoByIdTipoLego().getDescripcion(),
                    recurso.getDescripcion()
            ));
        }
    }

    private void validarExisteRecurso(LegosrecursosEntity legorecurso) {
        IAdministradorRecursos administradorRecursos = new AdministradorRecursos(sesion);

        RecursosEntity recurso = administradorRecursos.buscarPorId(legorecurso.getIdRecurso());

        if (null == recurso) {
            throw new RuntimeException(
                    String.format("No se pueda agregar el legorecurso, el registro" +
                                    " de %s con el id %d no existe",
                            RecursosEntity.class.getSimpleName(),
                            legorecurso.getIdRecurso()));
        }
    }

    private void validarExisteLego(LegosrecursosEntity legorecurso) {
        IAdministradorLegos administradorLegos = new AdministradorLegos(sesion);

        LegosEntity lego = administradorLegos.buscarPorId(legorecurso.getIdLego());

        if (null == lego){
            throw new RuntimeException(
                    String.format("No se pueda agregar el legorecurso, el registro" +
                                    " de %s con el id %d no existe",
                            LegosEntity.class.getSimpleName(),
                            legorecurso.getIdLego()));
        }
    }

    private void validarExisteRegistro(LegosrecursosEntity registro, int id) {
        if (registro == null) {
            throw new RuntimeException(
                    String.format("El registro de %s con el id %d no existe",
                            LegosrecursosEntity.class.getSimpleName(), id)
            );
        }
    }
}
