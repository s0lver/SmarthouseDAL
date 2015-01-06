package iadministradores;

import modelos.TiposlegoEntity;

import java.util.Collection;

public interface IAdministradorTiposLego {

    public int agregar(TiposlegoEntity tipoLego);

    public void eliminar(int id);

    public TiposlegoEntity buscarPorId(int id);

    public Collection<TiposlegoEntity> lista();
}
