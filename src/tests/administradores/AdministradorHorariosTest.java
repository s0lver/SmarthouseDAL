package tests.administradores;

import administradores.AdministradorHorarios;
import iadministradores.IAdministradorHorarios;
import modelos.HorariosEntity;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.SessionUtil;

import java.sql.Time;
import java.util.Collection;

public class AdministradorHorariosTest {
    private static Session sesion;
    private static IAdministradorHorarios administradorHorarios;

    @BeforeClass
    public static void inicializacion() {
        sesion = SessionUtil.getSession();
        administradorHorarios = new AdministradorHorarios(sesion);
    }

    @Test
    public void testAgregar() {
        /*HorariosEntity horario = new HorariosEntity();
        horario.setHoraInicio(new Time(10, 0, 0));
        horario.setHoraFin(new Time(15, 0, 0));

        horario.setIdLegoRecurso(13);

        int nuevoId = administradorHorarios.agregar(horario);

        Assert.assertNotEquals(0, nuevoId);*/
        HorariosEntity horario = new HorariosEntity();
        horario.setId(2);
        horario.setHoraInicio(new Time(10, 0, 0));
        horario.setHoraFin(new Time(15, 0, 0));
        int nuevoId = administradorHorarios.agregar(horario);

        Assert.assertNotEquals(0, nuevoId);
    }

    @Test
    public void testEliminar() {
        int idHorario = 11;
        administradorHorarios.eliminar(idHorario);

        HorariosEntity horario = administradorHorarios.buscarPorId(idHorario);

        Assert.assertNull(horario);
    }

    @Test
    public void testbuscarPorId() {
        int idHorario = 12;
        HorariosEntity horario = administradorHorarios.buscarPorId(12);

        Assert.assertNotNull(horario);
    }

    @Test
    public void testBuscarPorIdLego() {
        int idLego = 3;
        Collection<HorariosEntity> horarios = administradorHorarios.buscarPorIdLego(idLego);

        assert horarios.size() != 0;
    }
}
