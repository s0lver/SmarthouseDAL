package iadministradores;

import modelos.LegosEntity;
import modelos.LegosrecursosEntity;

public interface IAdministradorLegosRecursos {
    public int agregar(LegosrecursosEntity legorecurso);

    public void eliminar(int id);

    public LegosrecursosEntity buscarPorId(int id);

    public LegosrecursosEntity buscarPorLegoYRecurso(int idLego, int idRecurso);

    public void crearAsociacionesNuevoLego(LegosEntity lego);
}
