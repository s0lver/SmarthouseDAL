package iadministradores;

import modelos.RecursosEntity;

import java.util.Collection;

public interface IAdministradorRecursos {
    public int agregar(RecursosEntity recurso);

    public void eliminar(int id);

    public RecursosEntity buscarPorId(int id);

    public Collection<RecursosEntity> buscarPorTipoLego(int idTipoLego);
}
