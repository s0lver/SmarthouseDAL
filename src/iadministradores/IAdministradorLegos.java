package iadministradores;

import modelos.LegosEntity;

import java.util.Collection;

public interface IAdministradorLegos {
    public int agregar(LegosEntity lego);

    public void eliminar(int id);

    public LegosEntity buscarPorId(int id);

    public LegosEntity buscarPorMac(String mac);

    public Collection<LegosEntity> lista();

    public Collection<LegosEntity> listaPorTipo(int idTipoLego);
}
