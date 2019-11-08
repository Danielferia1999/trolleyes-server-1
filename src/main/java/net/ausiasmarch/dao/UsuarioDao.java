package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.UsuarioBean;

public class UsuarioDao implements DaoInterface {

    Connection oConnection = null;

    public UsuarioDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    @Override
    public UsuarioBean get(int id) throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT * FROM usuario WHERE id=?";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oPreparedStatement.setInt(1, id);
        oResultSet = oPreparedStatement.executeQuery();
        UsuarioBean oUsuarioBean;
        if (oResultSet.next()) {
            oUsuarioBean = new UsuarioBean();
            oUsuarioBean.setId(oResultSet.getInt("id"));
            oUsuarioBean.setDni(oResultSet.getString("dni"));
            oUsuarioBean.setNombre(oResultSet.getString("nombre"));
            oUsuarioBean.setApellido1(oResultSet.getString("apellido1"));
            oUsuarioBean.setApellido2(oResultSet.getString("apellido2"));
            oUsuarioBean.setEmail(oResultSet.getString("email"));
            oUsuarioBean.setLogin(oResultSet.getString("login"));
            oUsuarioBean.setPassword(oResultSet.getString("password"));
        } else {
            oUsuarioBean = null;
        }
        return oUsuarioBean;
    }

    @Override
    public int getCount() throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT count(*) FROM usuario";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oResultSet = oPreparedStatement.executeQuery();
        if (oResultSet.next()) {
            return oResultSet.getInt(1);
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(BeanInterface oPostBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement = null;
        String strSQL = "UPDATE usuario SET dni = ?, nombre = ?, apellido1 = ?, apellido2=?, email=?, login=?, password=? WHERE id = ?";
        int iResult;
        oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        UsuarioBean oUsuarioBean = (UsuarioBean) oPostBeanParam;
        oPreparedStatement.setString(1, oUsuarioBean.getDni());
        oPreparedStatement.setString(2, oUsuarioBean.getNombre());
        oPreparedStatement.setString(3, oUsuarioBean.getApellido1());
        oPreparedStatement.setString(4, oUsuarioBean.getApellido2());
        oPreparedStatement.setString(5, oUsuarioBean.getEmail());
        oPreparedStatement.setString(6, oUsuarioBean.getLogin());
        oPreparedStatement.setString(7, oUsuarioBean.getPassword());

        iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public Integer insert(BeanInterface oPostBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement;
        String strsql = "INSERT INTO usuario (dni,nombre,apellido1,apellido2,email,login,password) VALUES(?,?,?,?,?,?,?)";
        oPreparedStatement = oConnection.prepareStatement(strsql);
        UsuarioBean oUsuarioBean = (UsuarioBean) oPostBeanParam;
        oPreparedStatement.setString(1, oUsuarioBean.getDni());
        oPreparedStatement.setString(2, oUsuarioBean.getNombre());
        oPreparedStatement.setString(3, oUsuarioBean.getApellido1());
        oPreparedStatement.setString(4, oUsuarioBean.getApellido2());
        oPreparedStatement.setString(5, oUsuarioBean.getEmail());
        oPreparedStatement.setString(6, oUsuarioBean.getLogin());
        oPreparedStatement.setString(7, oUsuarioBean.getPassword());
        int iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public Integer remove(int id) throws SQLException {
        PreparedStatement oPreparedStament = null;
        oPreparedStament = oConnection.prepareStatement("DELETE FROM usuario WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
        oPreparedStament.setInt(1, id);
        int iResult = oPreparedStament.executeUpdate();
        return iResult;
    }

    @Override
    public ArrayList<UsuarioBean> getPage(int page, int limit, List<String> orden) throws SQLException {

        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        int offset;

        if (page == 1) {
            offset = 0;
        } else {
            offset = (limit * page) - limit;
        }

        if (orden == null) {
            oPreparedStatement = oConnection.prepareStatement("SELECT * FROM usuario LIMIT ? OFFSET ?");
            oPreparedStatement.setInt(1, limit);
            oPreparedStatement.setInt(2, offset);
        } else {
            String sqlQuery = "SELECT * FROM usuario ";
            sqlQuery += "ORDER BY ";
            for (int i = 1; i <= orden.size(); i++) {
                if (orden.get((i - 1)).equalsIgnoreCase("asc")) {
                    sqlQuery += "ASC ";
                } else if (orden.get((i - 1)).equalsIgnoreCase("desc")) {
                    sqlQuery += "DESC ";
                } else {
                    sqlQuery += "? ";
                }
            }
            sqlQuery += "LIMIT ? OFFSET ?";
            oPreparedStatement = oConnection.prepareStatement(sqlQuery);
            for (int i = 1; i < orden.size(); i++) {
                if (orden.get((i - 1)).equalsIgnoreCase("id")) {
                    oPreparedStatement.setInt(i, 1);
                } else if (orden.get((i - 1)).equalsIgnoreCase("dni")) {
                    oPreparedStatement.setInt(i, 2);
                } else if (orden.get((i - 1)).equalsIgnoreCase("nombre")) {
                    oPreparedStatement.setInt(i, 3);
                } else if (orden.get((i - 1)).equalsIgnoreCase("apellido1")) {
                    oPreparedStatement.setInt(i, 4);
                } else if (orden.get((i - 1)).equalsIgnoreCase("apellido2")) {
                    oPreparedStatement.setInt(i, 5);
                } else if (orden.get((i - 1)).equalsIgnoreCase("email")) {
                    oPreparedStatement.setInt(i, 6);
                }

            }
            oPreparedStatement.setInt((orden.size()), limit);
            oPreparedStatement.setInt((orden.size() + 1), offset);
        }

        oResultSet = oPreparedStatement.executeQuery();

        ArrayList<UsuarioBean> oUsuarioBeanList = new ArrayList<>();
        while (oResultSet.next()) {
            UsuarioBean oUsuarioBean = new UsuarioBean();
            oUsuarioBean.setId(oResultSet.getInt("id"));
            oUsuarioBean.setDni(oResultSet.getString("dni"));
            oUsuarioBean.setNombre(oResultSet.getString("nombre"));
            oUsuarioBean.setApellido1(oResultSet.getString("apellido1"));
            oUsuarioBean.setApellido2(oResultSet.getString("apellido2"));
            oUsuarioBean.setEmail(oResultSet.getString("email"));

            oUsuarioBeanList.add(oUsuarioBean);
        }

        return oUsuarioBeanList;
    }


}
