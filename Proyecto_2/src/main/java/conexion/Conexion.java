/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;

/**
 *
 * @author David
 */
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class Conexion {
        static String bd = "taller";
        static String login = "root";
        static String password = "";
        static String url = "jdbc:mariadb://localhost:3307/"+bd;

        Connection conn = null;

        /** Constructor de DbConnection */
        public Conexion() {
            try{
                //obtenemos el driver de para mysql
                Class.forName("org.mariadb.jdbc.Driver");
                //obtenemos la conexi?n
                conn = DriverManager.getConnection(url,login,password);

                if (conn!=null){
                    System.out.println("Conexión a base de datos "+bd+" OK");
                }
            }
            catch(SQLException e){
                System.out.println(e);
            }catch(ClassNotFoundException e){
                System.out.println(e);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        /**Permite retornar la conexi?n*/
        public Connection getConnection(){
            return conn;
        }

        public void desconectar(){
            conn = null;
        }


    }

