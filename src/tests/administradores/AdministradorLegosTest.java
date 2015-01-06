package tests.administradores;

import administradores.AdministradorLegos;
import iadministradores.IAdministradorLegos;
import modelos.LegosEntity;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.SessionUtil;

import java.util.Collection;

public class AdministradorLegosTest {
    private static Session sesion;
    private static IAdministradorLegos administradorLegos;

    @BeforeClass
    public static void inicializacion() {
        sesion = SessionUtil.getSession();
        administradorLegos = new AdministradorLegos(sesion);
    }

    @Test
    public void testAgregar() {
        LegosEntity entidad = new LegosEntity();
        entidad.setMac("COM10");
        entidad.setIdTipoLego(3);

        int nuevoId = administradorLegos.agregar(entidad);

        Assert.assertNotEquals(nuevoId, 0);
    }

    @Test
    public void testEliminar() {
        int id = 4;
        administradorLegos.eliminar(id);

        LegosEntity entidad = administradorLegos.buscarPorId(id);
        Assert.assertNull(entidad);
    }

    @Test
    public void testBuscarPorId() {
        int id = 3;

        LegosEntity entidad = administradorLegos.buscarPorId(id);
        Assert.assertNotNull(entidad);
    }

    @Test
    public void testLista() {
        Collection<LegosEntity> lista = administradorLegos.lista();
        assert lista.size() > 0;
    }

    @Test
    public void testListaPorTipo() {
        int idTipo = 3;
        Collection<LegosEntity> lista = administradorLegos.listaPorTipo(idTipo);
        assert lista.size() == 1;
    }
}