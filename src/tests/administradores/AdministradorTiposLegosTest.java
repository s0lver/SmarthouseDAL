package tests.administradores;

import administradores.AdministradorTiposLego;
import iadministradores.IAdministradorTiposLego;
import modelos.TiposlegoEntity;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.SessionUtil;

import java.util.Collection;
import java.util.function.Consumer;

public class AdministradorTiposLegosTest {
    private static Session sesion;
    private static IAdministradorTiposLego administradorTiposLegos;

    @BeforeClass
    public static void inicializacion() {
        sesion = SessionUtil.getSession();
        administradorTiposLegos = new AdministradorTiposLego(sesion);
    }

    @Test
    public void testAgregar() {
        TiposlegoEntity entidad = new TiposlegoEntity();
        entidad.setDescripcion("Controlador aromatizante");
        int nuevoId = administradorTiposLegos.agregar(entidad);
        Assert.assertNotEquals(nuevoId, 0);
    }

    @Test
    public void testEliminar() {
        int id = 5;
        administradorTiposLegos.eliminar(id);

        TiposlegoEntity entidad = administradorTiposLegos.buscarPorId(id);
        Assert.assertNull(entidad);
    }

    @Test
    public void testBuscarPorId() {
        int id = 1;

        TiposlegoEntity tipoLego = administradorTiposLegos.buscarPorId(id);
        Assert.assertNotNull(tipoLego);
    }

    @Test
    public void testLista() {
        Collection<TiposlegoEntity> lista = administradorTiposLegos.lista();

        lista.forEach(new Consumer<TiposlegoEntity>() {
            @Override
            public void accept(TiposlegoEntity tipoLego) {
                System.out.println(
                        String.format("id: %d, descripción: %s",
                                tipoLego.getId(),
                                tipoLego.getDescripcion()));
            }
        });

        assert lista.size() > 0;
    }
}
