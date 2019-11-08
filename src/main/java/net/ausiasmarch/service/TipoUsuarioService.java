/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.bean.TipoUsuarioBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.TipoUsuarioDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class TipoUsuarioService implements ServiceInterface {

    HttpServletRequest oRequest = null;

    public TipoUsuarioService(HttpServletRequest oRequest) {
        this.oRequest = oRequest;
    }

    @Override
    public String get() throws Exception {
        ConnectionInterface oConnectionImplementation = null;
        Connection oConnection = null;
        try {
            oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
            oConnection = oConnectionImplementation.newConnection();
            int id = Integer.parseInt(oRequest.getParameter("id"));
            
            TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
            TipoUsuarioBean oTipoUsuarioBean = oTipoUsuarioDao.get(id);
            
            Gson oGson = GsonFactory.getGson();
            String strJson = oGson.toJson(oTipoUsuarioBean);
            return "{\"status\":200,\"message\":" + strJson + "}";
        } catch (Exception ex) {
            String msg = this.getClass().getName() + " get method ";
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oConnectionImplementation != null) {
                oConnectionImplementation.disposeConnection();
            }
        }
    }

    @Override
    public String getPage() throws Exception {
        ConnectionInterface oConnectionImplementation = null;
        Connection oConnection = null;
        try {
            oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
            oConnection = oConnectionImplementation.newConnection();
            int iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
            int iPage = Integer.parseInt(oRequest.getParameter("page"));
            List<String> orden = null;
            if (oRequest.getParameter("order") != null) {
                orden = Arrays.asList(oRequest.getParameter("order").split("\\s*,\\s*"));
            }
            TipoUsuarioDao oUsuarioDao = new TipoUsuarioDao(oConnection);
            ArrayList alTipoUsuarioBean = oUsuarioDao.getPage(iPage, iRpp, orden);
            Gson oGson = GsonFactory.getGson();
            String strJson = oGson.toJson(alTipoUsuarioBean);
            return "{\"status\":200,\"message\":" + strJson + "}";
        } catch (Exception ex) {
            String msg = this.getClass().getName() + " get method ";
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oConnectionImplementation != null) {
                oConnectionImplementation.disposeConnection();
            }
        }
    }

    @Override
    public String getCount() throws Exception {
        ConnectionInterface oConnectionImplementation = null;
        Connection oConnection = null;
        try {
            oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
            oConnection = oConnectionImplementation.newConnection();
            ResponseBean oResponseBean;
            Gson oGson = GsonFactory.getGson();
            TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
            Integer iCount = oTipoUsuarioDao.getCount();
            if (iCount < 0) {
                oResponseBean = new ResponseBean(500, iCount.toString());
            } else {
                oResponseBean = new ResponseBean(200, iCount.toString());
            }
            return oGson.toJson(oResponseBean);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + " get method ";
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oConnectionImplementation != null) {
                oConnectionImplementation.disposeConnection();
            }
        }
    }

    @Override
    public String update() throws Exception {
        HttpSession oSession = oRequest.getSession();
        ResponseBean oResponseBean;
        Gson oGson = GsonFactory.getGson();
        if (oSession.getAttribute("usuario") != null) {
            ConnectionInterface oConnectionImplementation = null;
            Connection oConnection = null;
            try {
                oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
                oConnection = oConnectionImplementation.newConnection();
                TipoUsuarioBean oTipoUsuarioBean = new TipoUsuarioBean();
                String data = oRequest.getParameter("data");
                oTipoUsuarioBean = oGson.fromJson(data, TipoUsuarioBean.class);
                TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
                if (oTipoUsuarioDao.update(oTipoUsuarioBean) == 0) {
                    oResponseBean = new ResponseBean(500, "KO");
                } else {
                    oResponseBean = new ResponseBean(200, "OK");
                }
                return oGson.toJson(oResponseBean);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + " get method ";
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oConnectionImplementation != null) {
                    oConnectionImplementation.disposeConnection();
                }
            }
        } else {
            oResponseBean = new ResponseBean(401, "Error: No session");
            return oGson.toJson(oResponseBean);
        }

    }

    @Override
    public String insert() throws Exception {
     //   HttpSession oSession = oRequest.getSession();
        ResponseBean oResponseBean;
        Gson oGson = GsonFactory.getGson();
     //   if (oSession.getAttribute("usuario") != null) {
            ConnectionInterface oConnectionImplementation = null;
            Connection oConnection = null;
            try {
                oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
                oConnection = oConnectionImplementation.newConnection();
                TipoUsuarioBean oTipoUsuarioBean = oGson.fromJson(oRequest.getParameter("data"), TipoUsuarioBean.class);
                TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
                if (oTipoUsuarioDao.insert(oTipoUsuarioBean) == 0) {
                    oResponseBean = new ResponseBean(500, "KO");
                } else {
                    oResponseBean = new ResponseBean(200, "OK");
                };
                return oGson.toJson(oResponseBean);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + " get method ";
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oConnectionImplementation != null) {
                    oConnectionImplementation.disposeConnection();
                }
          //  }
        } /*else {
            oResponseBean = new ResponseBean(401, "Error: No session");
            return oGson.toJson(oResponseBean);
        }*/
    }

    @Override
    public String remove() throws Exception {
        HttpSession oSession = oRequest.getSession();
        ResponseBean oResponseBean;
        Gson oGson = GsonFactory.getGson();
        if (oSession.getAttribute("usuario") != null) {
            ConnectionInterface oConnectionImplementation = null;
            Connection oConnection = null;
            try {
                oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
                oConnection = oConnectionImplementation.newConnection();
                TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
                int id = Integer.parseInt(oRequest.getParameter("id"));
                if (oTipoUsuarioDao.remove(id) == 0) {
                    oResponseBean = new ResponseBean(500, "KO");
                } else {
                    oResponseBean = new ResponseBean(200, "OK");
                }
                return oGson.toJson(oResponseBean);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + " get method ";
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oConnectionImplementation != null) {
                    oConnectionImplementation.disposeConnection();
                }
            }
        } else {
            oResponseBean = new ResponseBean(401, "Error: No session");
            return oGson.toJson(oResponseBean);
        }
    }

   
}