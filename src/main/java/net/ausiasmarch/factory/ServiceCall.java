package net.ausiasmarch.factory;

import javax.servlet.http.HttpServletRequest;
import net.ausiasmarch.service.CarritoService;
import net.ausiasmarch.service.CompraService;
import net.ausiasmarch.service.ProductoService;
import net.ausiasmarch.service.TipoUsuarioService;
import net.ausiasmarch.service.UsuarioService;
import net.ausiasmarch.service.FacturaService;

public class ServiceCall {

    HttpServletRequest oRequest;

    public static String executeService(HttpServletRequest oRequest) throws Exception {
        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        String strResult = null;
        if (ob.equalsIgnoreCase("usuario")) {
            UsuarioService oUsuarioService = new UsuarioService(oRequest);
            switch (op) {
                case "login":
                    strResult = oUsuarioService.login();
                    break;
                case "check":
                    strResult = oUsuarioService.check();
                    break;
                case "logout":
                    strResult = oUsuarioService.logout();
                    break;
                case "get":
                    strResult = oUsuarioService.get();
                    break;
                case "getcount":
                    strResult = oUsuarioService.getCount();
                    break;
                case "insert":
                    strResult = oUsuarioService.insert();
                    break;
                case "update":
                    strResult = oUsuarioService.update();
                    break;
                case "remove":
                    strResult = oUsuarioService.remove();
                    break;
                case "getpage":
                    strResult = oUsuarioService.getPage();
                    break;
            }
        } else if (ob.equalsIgnoreCase("producto")) {
            ProductoService oProductoService = new ProductoService(oRequest);
            switch (op) {
                case "get":
                    strResult = oProductoService.get();
                    break;
                case "update":
                    strResult = oProductoService.update();
                    break;
                case "remove":
                    strResult = oProductoService.remove();
                    break;
                case "insert":
                    strResult = oProductoService.insert();
                    break;
                case "getpage":
                    strResult = oProductoService.getPage();
                    break;
                case "getcount":
                    strResult = oProductoService.getCount();
                    break;
            }

        } else if (ob.equalsIgnoreCase("carrito")) {
            CarritoService oCarritoService = new CarritoService(oRequest);
            switch (op) {
                case "add":
                    strResult = oCarritoService.add();
                    break;
                case "list":
                    strResult = oCarritoService.list();
                    break;
                case "remove":
                    strResult = oCarritoService.remove();
                    break;
                case "empty":
                    strResult = oCarritoService.empty();
                    break;
            } 
        } else if (ob.equalsIgnoreCase("factura")) {
            FacturaService oFacturaService = new FacturaService(oRequest);
            switch (op) {
                case "get":
                    strResult = oFacturaService.get();
                    break;
                case "update":
                    strResult = oFacturaService.update();
                    break;
                case "remove":
                    strResult = oFacturaService.remove();
                    break;
                case "insert":
                    strResult = oFacturaService.insert();
                    break;
                case "getpage":
                    strResult = oFacturaService.getPage();
                    break;
                case "getcount":
                    strResult = oFacturaService.getCount();
                    break;
            }
        } else if(ob.equalsIgnoreCase("compra")) {
            CompraService oCompraService = new CompraService(oRequest);
            switch (op) {
                case "get":
                    strResult = oCompraService.get();
                    break;
                case "update":
                    strResult = oCompraService.update();
                    break;
                case "remove":
                    strResult = oCompraService.remove();
                    break;
                case "insert":
                    strResult = oCompraService.insert();
                    break;
                case "getpage":
                    strResult = oCompraService.getPage();
                    break;
                case "getcount":
                    strResult = oCompraService.getCount();
                    break;
            }
        } else if (ob.equalsIgnoreCase("tipo_usuario")) {
            TipoUsuarioService oTipoUsuarioService = new TipoUsuarioService(oRequest);
            switch (op) {
                case "get":
                    strResult = oTipoUsuarioService.get();
                    break;
                case "getcount":
                    strResult = oTipoUsuarioService.getCount();
                    break;
                case "getpage":
                    strResult = oTipoUsuarioService.getPage();
                    break;
                case "update":
                    strResult = oTipoUsuarioService.update();
                    break;
                case "remove":
                    strResult = oTipoUsuarioService.remove();
                    break;
                case "insert":
                    strResult = oTipoUsuarioService.insert();
                    break;
             
            }
        }
        return strResult;
    }
}
