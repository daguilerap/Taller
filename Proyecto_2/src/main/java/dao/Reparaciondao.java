package dao;

import conexion.Conexion;
import net.sf.jasperreports.engine.*;
import vo.ReparacionVo;
import vo.trabajadorVo;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reparaciondao {


    private PreparedStatement preparedStatment;


    public static ArrayList<ReparacionVo> obtenDatos() {

        Conexion conex = new Conexion();
        ArrayList<ReparacionVo> miLista = new ArrayList<>();
        ReparacionVo repa;

        try {
            SimpleDateFormat date;
            date = new SimpleDateFormat("dd-MM-yyyy");
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reparacion ");


            while (rs.next()) {
                repa = new ReparacionVo();
                repa.setId(rs.getInt("Id"));
                repa.setId_tra(rs.getInt("id_trab"));
                repa.setTiporep(rs.getString("tipo_rep"));
                repa.setDescripcion(rs.getString("Descripcion"));
                repa.setHoras(rs.getDouble("Horas"));
                repa.setPrecio(rs.getDouble("Precio"));
                repa.setfInicio(date.format(rs.getDate("Fecha_ini")));
                //repa.setfFin(date.format(rs.getDate("Fechafin")));

                if((rs.getDate("Fecha_fin")==null)) {
                    repa.setfFin("NULL");
                }else {
                    repa.setfFin(date.format(rs.getDate("Fecha_fin")));
                }
                repa.setPrecio_tot(rs.getDouble("Precio_tot"));
                miLista.add(repa);
            }
            rs.close();
            st.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
        return miLista;
    }

    public static ArrayList<trabajadorVo> obtenDatostra() {

        Conexion conex = new Conexion();
        ArrayList<trabajadorVo> miLista = new ArrayList<>();
        trabajadorVo trab;

        try {
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM trabajador ");


            while (rs.next()) {
                trab = new trabajadorVo();
                trab.setId(rs.getInt("Id_trab"));
                trab.setNombre(rs.getString("Nombre"));
                trab.setApellido(rs.getString("Apellidos"));
                trab.setDni(rs.getString("DNI"));
                trab.setDireccion(rs.getString("Direccion"));
                trab.setTelefono(rs.getString("Telefono"));
                miLista.add(trab);

            }
            rs.close();
            st.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
        return miLista;
    }

    public void insertar(ReparacionVo repa) {

        try {
            Conexion conex = new Conexion();
            String sql="INSERT INTO reparacion (id_trab,tipo_rep,Descripcion,Horas,Precio,Fecha_ini,Fecha_fin,Precio_tot) VALUES(?,?,?,?,?,?,?,?)";
            preparedStatment =conex.getConnection().prepareStatement(sql);

            preparedStatment.setInt(1, repa.getId_tra());
            preparedStatment.setString(2, repa.getTiporep());
            preparedStatment.setString(3, repa.getDescripcion());
            preparedStatment.setDouble(4, repa.getHoras());
            preparedStatment.setDouble(5, repa.getPrecio());
            preparedStatment.setString(6, repa.getfInicio());
            preparedStatment.setString(7, repa.getfFin());
            preparedStatment.setDouble(8, repa.getPrecio_tot());

            preparedStatment.executeUpdate();

            preparedStatment.close();

            conex.desconectar();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Numero repetido");
            JOptionPane.showMessageDialog(null, "Error al insertar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }
    public void insertartrab(trabajadorVo repa) {

        try {
            Conexion conex = new Conexion();
            String sql="INSERT INTO trabajador (Nombre,Apellidos,DNI,Direccion,Telefono) VALUES(?,?,?,?,?)";
            preparedStatment =conex.getConnection().prepareStatement(sql);

            preparedStatment.setString(1, repa.getNombre());
            preparedStatment.setString(2, repa.getApellido());
            preparedStatment.setString(3, repa.getDni());
            preparedStatment.setString(4, repa.getDireccion());
            preparedStatment.setString(5, repa.getTelefono());


            preparedStatment.executeUpdate();

            preparedStatment.close();

            conex.desconectar();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Numero repetido");
            JOptionPane.showMessageDialog(null, "Error al insertar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }


    public static ReparacionVo Consulta(int id) {

        Conexion conex = new Conexion();
        
        ReparacionVo repa = null;
        SimpleDateFormat date;
        try{
        date = new SimpleDateFormat("dd-MM-yyyy");
        Statement st = conex.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM reparacion where Id="+(id+""));


        while (rs.next()) {
            repa = new ReparacionVo();
            repa.setId(rs.getInt("Id"));
            repa.setId_tra(rs.getInt("Id_trab"));
            repa.setDescripcion(rs.getString("Descripcion"));
            repa.setTiporep(rs.getString("tipo_rep"));
            repa.setHoras(rs.getDouble("Horas"));
            repa.setPrecio(rs.getDouble("Precio"));
            repa.setfInicio(date.format(rs.getDate("Fecha_ini")));
            if((rs.getDate("Fecha_fin")==null)) {
                repa.setfFin("");
            }else {
                repa.setfFin(date.format(rs.getDate("Fecha_fin")));
            }
            repa.setPrecio_tot(rs.getDouble("Precio_tot"));
         

        }
        rs.close();
        st.close();
        conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
        return repa;

    }


    public static trabajadorVo Consultatra(int id) {

        Conexion conex = new Conexion();

        trabajadorVo repa = null;

        try{

            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM trabajador where id_trab="+(id+""));


            while (rs.next()) {
                repa = new trabajadorVo();
                repa.setId(rs.getInt("id_trab"));
                repa.setNombre(rs.getString("Nombre"));
                repa.setApellido(rs.getString("Apellidos"));
                repa.setDni(rs.getString("DNI"));
                repa.setDireccion(rs.getString("Direccion"));
                repa.setTelefono(rs.getString("Telefono"));



            }
            rs.close();
            st.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
        return repa;

    }

    public void modifica(ReparacionVo repa) {

        try {
            Conexion conex = new Conexion();
            String sql="Update reparacion set id_trab=?,tipo_rep=?,Descripcion=?,Horas=?,Precio=?,Fecha_ini=?,Fecha_fin=?,Precio_tot=? where Id =?";


            preparedStatment =conex.getConnection().prepareStatement(sql);

            preparedStatment.setInt(1,repa.getId_tra());
            preparedStatment.setString(2, repa.getTiporep());
            preparedStatment.setString(3, repa.getDescripcion());
            preparedStatment.setDouble(4, repa.getHoras());
            preparedStatment.setDouble(5, repa.getPrecio());
            preparedStatment.setString(6, repa.getfInicio());
            preparedStatment.setString(7, repa.getfFin());
            preparedStatment.setDouble(8, repa.getPrecio_tot());
            preparedStatment.setDouble(9, repa.getId());

            preparedStatment.executeUpdate();

            preparedStatment.close();

            conex.desconectar();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public void modificatra(trabajadorVo repa) {

        try {
            Conexion conex = new Conexion();
            String sql="Update trabajador set Nombre=?,Apellidos=?,DNI=?,Direccion=?,Telefono=? where id_trab =?";


            preparedStatment =conex.getConnection().prepareStatement(sql);

            preparedStatment.setString(1, repa.getNombre());
            preparedStatment.setString(2, repa.getApellido());
            preparedStatment.setString(3, repa.getDni());
            preparedStatment.setString(4, repa.getDireccion());
            preparedStatment.setString(5, repa.getTelefono());
            preparedStatment.setDouble(6, repa.getId());

            preparedStatment.executeUpdate();

            preparedStatment.close();

            conex.desconectar();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public void elimina(int id) {


        try {
            Conexion conex = new Conexion();
            String sql="delete from reparacion where Id="+id;

            preparedStatment =conex.getConnection().prepareStatement(sql);


            preparedStatment.executeUpdate();

            preparedStatment.close();

            conex.desconectar();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al borrar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public void eliminatra(int id) {


        try {
            Conexion conex = new Conexion();
            String sql="delete from trabajador where id_trab="+id;

            preparedStatment =conex.getConnection().prepareStatement(sql);


            preparedStatment.executeUpdate();

            preparedStatment.close();

            conex.desconectar();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al borrar", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public void reportereparaciones() {

        JasperReport archivo;
        Conexion conex = new Conexion();

        String path = "reparaciones.jrxml";
        // String path2 = "C:\\Users\\User-01\\JaspersoftWorkspace\\MyReports\\ReporteVisor.jasper";

        try
        {
            archivo = JasperCompileManager.compileReport(path);

           // Map parametros = new HashMap(); //definimos objeto Map que contiene el nombre de cada parámetro que usará y el valor del parámetro
           // parametros.put("DNI", Dni); //si tuviésemos más de un parámetro, se definiría como éste

            JasperPrint prin = JasperFillManager.fillReport(archivo,null,conex.getConnection());

            JasperExportManager.exportReportToPdfFile(prin,"reparaciones.pdf");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "reparaciones.pdf");
        }
        catch (JRException | IOException e)
        {
            e.printStackTrace();
        }
    }
    public void reporteTrabjadores() {

        JasperReport archivo;
        Conexion conex = new Conexion();

        String path = "trabajadores.jrxml";
        // String path2 = "C:\\Users\\User-01\\JaspersoftWorkspace\\MyReports\\ReporteVisor.jasper";

        try
        {
            archivo = JasperCompileManager.compileReport(path);

            // Map parametros = new HashMap(); //definimos objeto Map que contiene el nombre de cada parámetro que usará y el valor del parámetro
            // parametros.put("DNI", Dni); //si tuviésemos más de un parámetro, se definiría como éste

            JasperPrint prin = JasperFillManager.fillReport(archivo,null,conex.getConnection());

            JasperExportManager.exportReportToPdfFile(prin,"trabajadores.pdf");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "trabajadores.pdf");
        }
        catch (JRException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public void reporteDni(String Dni) {

        JasperReport archivo;
        Conexion conex = new Conexion();

        String path = "dnireporte.jrxml";
        // String path2 = "C:\\Users\\User-01\\JaspersoftWorkspace\\MyReports\\ReporteVisor.jasper";

        try
        {
            archivo = JasperCompileManager.compileReport(path);

            Map parametros = new HashMap(); //definimos objeto Map que contiene el nombre de cada parámetro que usará y el valor del parámetro
            parametros.put("DNI", Dni); //si tuviésemos más de un parámetro, se definiría como éste

            JasperPrint prin = JasperFillManager.fillReport(archivo,parametros,conex.getConnection());

            JasperExportManager.exportReportToPdfFile(prin,"reportedni.pdf");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "reportedni.pdf");
        }
        catch (JRException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public void repotefechas(String ini, String fin) {

        JasperReport archivo;
        Conexion conex = new Conexion();

        String path = "Fechas.jrxml";
        // String path2 = "C:\\Users\\User-01\\JaspersoftWorkspace\\MyReports\\ReporteVisor.jasper";

        try
        {
            archivo = JasperCompileManager.compileReport(path);

             Map parametros = new HashMap(); //definimos objeto Map que contiene el nombre de cada parámetro que usará y el valor del parámetro
             parametros.put("fechaini", ini); //si tuviésemos más de un parámetro, se definiría como éste
             parametros.put("fechafin", fin);

            JasperPrint prin = JasperFillManager.fillReport(archivo,parametros,conex.getConnection());

            JasperExportManager.exportReportToPdfFile(prin,"Fechas.pdf");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "Fechas.pdf");
        }
        catch (JRException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public void reporteoper(String id) {

        JasperReport archivo;
        Conexion conex = new Conexion();

        String path = "reparacioportraba.jrxml";
        // String path2 = "C:\\Users\\User-01\\JaspersoftWorkspace\\MyReports\\ReporteVisor.jasper";

        try
        {
            archivo = JasperCompileManager.compileReport(path);

            Map parametros = new HashMap(); //definimos objeto Map que contiene el nombre de cada parámetro que usará y el valor del parámetro
            parametros.put("ID", id); //si tuviésemos más de un parámetro, se definiría como éste


            JasperPrint prin = JasperFillManager.fillReport(archivo,parametros,conex.getConnection());

            JasperExportManager.exportReportToPdfFile(prin,"reporteportrab.pdf");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "reporteportrab.pdf");
        }
        catch (JRException | IOException e)
        {
            e.printStackTrace();
        }
    }

}
