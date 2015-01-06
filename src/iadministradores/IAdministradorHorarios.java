package iadministradores;

import modelos.HorariosEntity;

import java.util.Collection;

public interface IAdministradorHorarios {
    public int agregar(HorariosEntity horario);

    public void eliminar(int id);

    public HorariosEntity buscarPorId(int id);

    public HorariosEntity buscarPorIdLegoIdRecurso(int idLego, int idRecurso);

    public Collection<HorariosEntity> buscarPorIdLego(int idLego);
}
