package dev.el_nico.dam2_ad_p4.xpath;

import java.util.Objects;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import dev.el_nico.dam2_ad_p4.comun.Pedir;

public class MainXpath 
{
    private static Database db;
    private static XPathQueryService xpath;

/* LA MOVIDA QUE ME FUNCIONÓ A MI :)
 * ResourceIterator ri = xpath.query("/productos/produc[precio=50]/stock_actual/text()").getIterator();
 * while (ri.hasMoreResources()) {
 *     XMLResource res = ((XMLResource) ri.nextResource());
 *     System.out.println(res.getContent());
 * }
 */

    @SuppressWarnings("deprecation")
    public static void main( String[] args ) throws Exception
    {
        db = (Database) Class.forName("org.exist.xmldb.DatabaseImpl").newInstance();
        Collection col = Objects.requireNonNull(db.getCollection("exist://localhost:6969/exist/xmlrpc/apps", "admin", ""));
        xpath = Objects.requireNonNull((XPathQueryService) col.getService(XPathQueryService.SERVICE_NAME, null));
    
        int orden = 0;
        do {
            imprMenu();
            orden = Pedir.orden(10);
            acatar(orden);
        } while (orden != 0);
    }

    // Imprime el menú
    private static void imprMenu() {
        System.out.println(
                  "\nÓrdenes válidas:\n================\n"
                + "0 -> Salir.\n"
                + "1 -> Obtener los nodos denominación y precio de todos los productos.\n"
                + "2 -> Obtener los nodos de los productos que sean placas base.\n"
                + "3 -> Obtener los nodos de los productos con precio mayor de 60€ y de la zona 20.\n"
                + "4 -> Obtener el número de productos que sean memorias y de la zona 10.\n"
                + "5 -> Obtener la media de precio de los micros.\n"
                + "6 -> Obtener los datos de los productos cuyo stock mínimo sea mayor que su stock actual.\n"
                + "7 -> Obtener el nombre y el precio de aquellos productos cuyo stock mínimo sea mayor que su stock actual y que sean de la zona 40.\n"
                + "8 -> Obtener el producto más caro.\n"
                + "9 -> Obtener el producto más barato de la zona 20.\n"
                + "10 -> Obtener el producto más caro de la zona 10.\n"
        );
    }

    // llama a la funcion que corresponda
    private static void acatar(int orden) throws XMLDBException {
        switch (orden) {
            case 0: return;
            case 1: nodosDenominacionYPrecio(); break;
          /*  case 2: nodosDeProductosPlacasBase(); break;
            case 3: nodosDeProds60EurosYZona20(); break;
            case 4: numProdsMemoriaYZona10(); break;
            case 5: mediaPreciosMicroprocesadores(); break;
            case 6: datosProdsStockMinMayorQueStockActual(); break;
            case 7: datosStockMinMayorQueStockActualYZona40(); break;
            case 8: productoMasCaro(); break;
            case 9: productoMasBaratoZona20(); break;
            case 10: productoMasCaroZona10(); break;*/
        }
    }

    private static void nodosDenominacionYPrecio() throws XMLDBException {
        ResourceIterator ri = xpath.query("/productos/produc/*[self::denominacion or self::precio]").getIterator();
        while (ri.hasMoreResources()) {
            XMLResource res = ((XMLResource) ri.nextResource());
            System.out.println(res.getContent());
        }
    }

}
