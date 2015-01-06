package tests.administradores;

import administradores.AdministradorRecursos;
import iadministradores.IAdministradorRecursos;
import modelos.RecursosEntity;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.SessionUtil;

public class AdministradorRecursosTest {
    private static Session sesion;
    private static IAdministradorRecursos administradorRecursos;

    @BeforeClass
    public static void inicializacion() {
        sesion = SessionUtil.getSession();
        administradorRecursos = new AdministradorRecursos(sesion);
    }

    @Test
    public void testAgregar() {
        RecursosEntity entidad = new RecursosEntity();
        entidad.setDescripcion("Aromatizante");
        entidad.setConHorario((byte)1);
        entidad.setIdTipoLego(6);
        int nuevoId = administradorRecursos.agregar(entidad);

        Assert.assertNotEquals(nuevoId, 0);
    }

    @Test
    public void testEliminar() {
        int id = 7;
        administradorRecursos.eliminar(id);
        RecursosEntity entidad = administradorRecursos.buscarPorId(id);
        Assert.assertNull(entidad);
    }

    @Test
    public void testBuscarPorId() {
        int id = 1;

        RecursosEntity tipoLego = administradorRecursos.buscarPorId(id);
        Assert.assertNotNull(tipoLego);
    }
}
