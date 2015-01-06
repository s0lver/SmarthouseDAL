package iadministradores;

import modelos.EventosEntity;

import java.util.Collection;
import java.util.Date;

public interface IAdministradorEventos {
    public int agregar(EventosEntity evento);

    public EventosEntity buscarPorId(int id);

    public Collection<EventosEntity> lista();

    public Collection<EventosEntity> listaConFechas(Date fechaInicio, Date fechaFin);

    public Collection<EventosEntity> listaPorLego(int idLego);

    public Collection<EventosEntity> listaPorLegoConFechas(int idLego, Date fechaInicio, Date fechaFin);

    public Collection<EventosEntity> listaPorTipoLego(int idTipoLego);

    public Collection<EventosEntity> listaPorTipoLegoConFechas(int idTipoLego, Date fechaInicio, Date fechaFin);
}