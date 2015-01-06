package tests.administradores;

import TiposRecurso.TiposRecurso;
import administradores.AdministradorLegosRecursos;
import iadministradores.IAdministradorLegosRecursos;
import modelos.LegosrecursosEntity;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.SessionUtil;

public class AdministradorLegosRecursosTest {
    private static Session sesion;
    private static IAdministradorLegosRecursos administradorLegosRecursos;
    @BeforeClass
    public static void inicializacion() {
        sesion = SessionUtil.getSession();
        administradorLegosRecursos = new AdministradorLegosRecursos(sesion);
    }

    @Test
    public void testAgregar() {
        LegosrecursosEntity entidad = new LegosrecursosEntity();
        entidad.setIdLego(3);
        entidad.setIdRecurso(TiposRecurso.PUERTA.getIdentificador());

        int nuevoId = administradorLegosRecursos.agregar(entidad);

        Assert.assertNotEquals(nuevoId, 0);
    }

    @Test
    public void testEliminar() {
        int id = 2;
        administradorLegosRecursos.eliminar(id);

        LegosrecursosEntity entidad = administradorLegosRecursos.buscarPorId(id);

        Assert.assertNull(entidad);
    }

    @Test
    public void testBuscarPorId() {
        int id = 3;

        LegosrecursosEntity entidad = administradorLegosRecursos.buscarPorId(id);
        Assert.assertNotNull(entidad);
    }
}
