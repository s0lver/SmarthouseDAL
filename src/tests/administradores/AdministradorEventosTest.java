package tests.administradores;

import administradores.AdministradorEventos;
import modelos.EventosEntity;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.SessionUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.function.Consumer;

public class AdministradorEventosTest {
    private static Session sesion;
    private static AdministradorEventos administradorEventos;

    @BeforeClass
    public static void inicializacion() {
        sesion = SessionUtil.getSession();
        administradorEventos = new AdministradorEventos(sesion);
    }

    @Test
    public void testAgregar() {
        Timestamp ts = new Timestamp(new Date().getTime());

        EventosEntity evento = new EventosEntity();
        evento.setTimestamp(ts);
        evento.setIdLegoRecurso(4);
        evento.setSentido((byte)0);

        int nuevoId = administradorEventos.agregar(evento);
        Assert.assertNotEquals(0, nuevoId);
    }

    @Test
    public void testBuscarPorId() {
        int id = 2;
        EventosEntity entidad =  administradorEventos.buscarPorId(id);

        Assert.assertNotNull(entidad);
    }

    @Test
    public void testListaPorLego() {
        int idLego = 4;

        Collection<EventosEntity> eventos =
                administradorEventos.listaPorLego(idLego);

        eventos.forEach(new Consumer<EventosEntity>() {
            @Override
            public void accept(EventosEntity eventosEntity) {
                System.out.println(eventosEntity.getLegosrecursosByIdLegoRecurso().getRecursosByIdRecurso().getDescripcion());
            }
        });

        assert eventos.size() > 0;
    }

    @Test
    public void testLista() {
        int idLego = 4;

        Collection<EventosEntity> eventos = administradorEventos.lista();

        eventos.forEach(new Consumer<EventosEntity>() {
            @Override
            public void accept(EventosEntity eventosEntity) {
                System.out.println(eventosEntity.getLegosrecursosByIdLegoRecurso().getRecursosByIdRecurso().getDescripcion());
            }
        });

        assert eventos.size() > 0;

    }

    @Test
    public void testlistaPorLegoConFechas() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaInicio = sdf.parse("2014/12/11");
        Date tmpFechaFin = sdf.parse("2014/12/11");
        Date fechaFin = new Date(tmpFechaFin.getTime() + (23 * 60 * 60 * 1000) + (59 * 60 * 1000));


        int idLego = 4;

        Collection<EventosEntity> eventos =
                administradorEventos.listaPorLegoConFechas(idLego, fechaInicio, fechaFin);

        eventos.forEach(new Consumer<EventosEntity>() {
            @Override
            public void accept(EventosEntity eventosEntity) {
                System.out.println(eventosEntity.getLegosrecursosByIdLegoRecurso().getRecursosByIdRecurso().getDescripcion());
            }
        });

        assert eventos.size() > 0;
    }

    @Test
    public void testlistaPorTipoLegoConFechas() throws ParseException {
        int idTipoLego = 2;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaInicio = sdf.parse("2014/12/01");
        Date fechaFin = sdf.parse("2014/12/31");

        Collection<EventosEntity> eventos =
                administradorEventos.listaPorTipoLegoConFechas(idTipoLego, fechaInicio, fechaFin);

        eventos.forEach(new Consumer<EventosEntity>() {
            @Override
            public void accept(EventosEntity eventosEntity) {
                System.out.println(eventosEntity.getLegosrecursosByIdLegoRecurso().getRecursosByIdRecurso().getDescripcion());
            }
        });
        assert eventos.size() > 0;
    }
}
