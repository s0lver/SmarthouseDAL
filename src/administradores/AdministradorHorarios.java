package administradores;

import iadministradores.IAdministradorHorarios;
import iadministradores.IAdministradorLegosRecursos;
import modelos.HorariosEntity;
import modelos.LegosrecursosEntity;
import modelos.RecursosEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class AdministradorHorarios implements IAdministradorHorarios{
    private Session sesion;

    public AdministradorHorarios(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int agregar(HorariosEntity horario) {
        validarExisteAsociacionLegoRecurso(horario);

        horario = corregirHorarioExistente(horario);

        Transaction tx = sesion.beginTransaction();

        sesion.saveOrUpdate(horario);

        tx.commit();

        return horario.getId();
    }

    @Override
    public void eliminar(int id) {
        validarExisteRegistro(id);

        HorariosEntity horario = buscarPorId(id);

        Transaction tx = sesion.beginTransaction();

        sesion.delete(horario);

        tx.commit();
    }

    @Override
    public HorariosEntity buscarPorId(int id) {
        Transaction tx = sesion.beginTransaction();

        HorariosEntity entidad = (HorariosEntity)
                sesion.get(HorariosEntity.class, id);

        tx.commit();

        return entidad;

    }

    @Override
    public HorariosEntity buscarPorIdLegoIdRecurso(int idLego, int idRecurso) {
        String hql = "from HorariosEntity where legosrecursosByIdLegoRecurso.idLego = :idLego and " +
                " legosrecursosByIdLegoRecurso.idRecurso = :idRecurso";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idLego", idLego);
        consulta.setInteger("idRecurso", idRecurso);
        List resultados = consulta.list();
        return (HorariosEntity) resultados.get(0);
    }

    @Override
    public Collection<HorariosEntity> buscarPorIdLego(int idLego) {
        String hql = "from HorariosEntity where legosrecursosByIdLegoRecurso.idLego = :idLego";
        Query consulta = sesion.createQuery(hql);
        consulta.setInteger("idLego", idLego);

        List resultados = consulta.list();
        return resultados;
    }

    private void validarExisteAsociacionLegoRecurso(HorariosEntity horario) {
        IAdministradorLegosRecursos administradorLegosRecursos;
        administradorLegosRecursos = new AdministradorLegosRecursos(sesion);

        LegosrecursosEntity asociacion;
        asociacion = administradorLegosRecursos.buscarPorId(horario.getIdLegoRecurso());

        if (null == asociacion) {
            throw new RuntimeException(String.format(
                    "No se pueda agregar el horario, no existe un registro de asociación %s con el id %d",
                    LegosrecursosEntity.class.getSimpleName(),
                    horario.getIdLegoRecurso()
            ));
        }
    }

    private void validarExisteRegistro(int idHorario) {
        HorariosEntity horario = buscarPorId(idHorario);

        if (null == horario) {
            throw new RuntimeException(
                    String.format("No se pueda eliminar el horario, el registro" +
                                    " de %s con el id %d no existe",
                            HorariosEntity.class.getSimpleName(),
                            horario.getId()));
        }
    }

    private HorariosEntity corregirHorarioExistente(HorariosEntity horario) {
        IAdministradorLegosRecursos administradorLegosRecursos;
        administradorLegosRecursos = new AdministradorLegosRecursos(sesion);

        LegosrecursosEntity legoRecurso;
        legoRecurso = administradorLegosRecursos.buscarPorId(horario.getIdLegoRecurso());

        if (legoRecurso.getHorariosesById() != null && legoRecurso.getHorariosesById().size() != 0) {
            HorariosEntity horarioExistente = legoRecurso.getHorariosesById().iterator().next();
            copiarEn(horario, horarioExistente);
            return horarioExistente;
        }else {
            return horario;
        }
    }

    private void copiarEn(HorariosEntity horario, HorariosEntity horarioExistente) {
        horarioExistente.setHoraInicio(horario.getHoraInicio());
        horarioExistente.setHoraFin(horario.getHoraFin());
    }
}
