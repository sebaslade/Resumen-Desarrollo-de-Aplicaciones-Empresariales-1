package pe.isil.marte.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.isil.marte.beans.Curso;


public class AD_Curso {
    private PreparedStatement pst = null; //objeto que permite ejecutar una sentencia SQL con o sin parametros
    private ResultSet rst; //conjunto de datos, equivalente al resultado de un SELECT, filas.
    
    //SELECT por ID, con parametros
    //Funcion que obtenga el curso por su ID
    public Curso getCursoById(Integer id) throws SQLException
    {
        Curso curso = new Curso(); //un curso en especifico
        Connection Conexion = null; //creo el objecto conexion        
        try {
            //1. Abrir o crear conexion
            Conexion = ConnectionPool.getInstance().getConnection();            
            if(Conexion != null)
            {
                //2. Crear la sentencia SQL
                String SQL = "SELECT * FROM curso WHERE id = ?";
                pst = Conexion.prepareStatement(SQL);
                //3. Agregar los parametros
                pst.setInt(1, id);
                //4. Ejecutar la sentencia SQL
                rst = pst.executeQuery();
                //5. Asignar los valores obtenidos al objeto
                while(rst.next())
                {
                    curso.setCreditos(rst.getInt("creditos"));
                    curso.setDescripcion(rst.getString("descripcion"));
                    curso.setHorario(rst.getString("horario"));
                    curso.setHoras(rst.getInt("horas"));
                    curso.setId(rst.getInt("id"));
                    curso.setNombre(rst.getString("nombre"));
                    curso.setNrc(rst.getString("nrc"));
                    curso.setProfesor(rst.getString("profesor"));
                }
            }
            else
            {
                System.out.println("Error en la conexion a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return curso;
    }
    
    //SELECT *, todos los registros sin parametros
    public List<Curso> getCursos() throws SQLException
    {
        List<Curso> cursos = new ArrayList<>();
        Curso curso; //un curso en especifico
        Connection Conexion = null; //creo el objecto conexion        
        try {
            //1. Abrir o crear conexion
            Conexion = ConnectionPool.getInstance().getConnection();            
            if(Conexion != null)
            {
                //2. Crear la sentencia SQL
                String SQL = "SELECT * FROM curso";
                pst = Conexion.prepareStatement(SQL);
                //3. Ejecutar la sentencia SQL
                rst = pst.executeQuery();
                //4. Asignar los valores obtenidos al objeto
                while(rst.next())
                {
                    curso = new Curso();
                    curso.setCreditos(rst.getInt("creditos"));
                    curso.setDescripcion(rst.getString("descripcion"));
                    curso.setHorario(rst.getString("horario"));
                    curso.setHoras(rst.getInt("horas"));
                    curso.setId(rst.getInt("id"));
                    curso.setNombre(rst.getString("nombre"));
                    curso.setNrc(rst.getString("nrc"));
                    curso.setProfesor(rst.getString("profesor"));
                    
                    //5. Agregar el curso al listado de cursos
                    cursos.add(curso);
                }
            }
            else
            {
                System.out.println("Error en la conexion a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return cursos;
    }
    
    
    //INSERTAR UN NUEVO CURSO
    public boolean addCurso(Curso curso) throws SQLException
    {
        Connection Conexion = null; //creo el objecto conexion        
        try {
            //1. Abrir o crear conexion
            Conexion = ConnectionPool.getInstance().getConnection();            
            if(Conexion != null)
            {
                //2. Crear la sentencia SQL
                String SQL = "INSERT INTO curso(nombre, descripcion, nrc, profesor, creditos, horas, horario)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?)";
                pst = Conexion.prepareStatement(SQL);
                pst.setString(1,  curso.getNombre());
                pst.setString(2, curso.getDescripcion());
                pst.setString(3, curso.getNrc());
                pst.setString(4, curso.getProfesor());
                pst.setInt(5, curso.getCreditos());
                pst.setInt(6, curso.getHoras());
                pst.setString(7, curso.getHorario());                
                //3. Ejecutar la sentencia SQL
                /* if (pst.executeUpdate() > 0) {
                    return true;
                }
                else
                {
                    return false;
                }
                */                
                int res = 0;
                res = pst.executeUpdate();
                if (res > 0) {
                    return true;
                } else {
                    return false;
                }                
            }
            else
            {
                System.out.println("Error en la conexion a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return false;
    }
    
    //ACTUALIZAR UN CURSO
    public boolean updateCurso(Curso curso) throws SQLException
    {
        Connection Conexion = null; //creo el objecto conexion        
        try {
            //1. Abrir o crear conexion
            Conexion = ConnectionPool.getInstance().getConnection();            
            if(Conexion != null)
            {
                //2. Crear la sentencia SQL
                String SQL = "UPDATE curso SET nombre = ?, descripcion = ?, nrc = ?, profesor = ?, creditos = ?, horas = ?, horario = ?"
                        + " WHERE id = ?";
                pst = Conexion.prepareStatement(SQL);
                pst.setString(1,  curso.getNombre());
                pst.setString(2, curso.getDescripcion());
                pst.setString(3, curso.getNrc());
                pst.setString(4, curso.getProfesor());
                pst.setInt(5, curso.getCreditos());
                pst.setInt(6, curso.getHoras());
                pst.setString(7, curso.getHorario());
                pst.setInt(8, curso.getId());
                //3. Ejecutar la sentencia SQL
                /* if (pst.executeUpdate() > 0) {
                    return true;
                }
                else
                {
                    return false;
                }
                */                
                int res = 0;
                res = pst.executeUpdate();
                if (res > 0) {
                    return true;
                } else {
                    return false;
                }                
            }
            else
            {
                System.out.println("Error en la conexion a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return false;
    }
    
    //ELIMINAR UN CURSO
    public boolean deleteCurso(int id) throws SQLException
    {
        Connection Conexion = null; //creo el objecto conexion        
        try {
            //1. Abrir o crear conexion
            Conexion = ConnectionPool.getInstance().getConnection();            
            if(Conexion != null)
            {
                //2. Crear la sentencia SQL
                String SQL = "DELETE FROM curso WHERE id = ?";
                pst = Conexion.prepareStatement(SQL);
                pst.setInt(1, id);
                //3. Ejecutar la sentencia SQL              
                int res = 0;
                res = pst.executeUpdate();
                if (res > 0) {
                    return true;
                } else {
                    return false;
                }                
            }
            else
            {
                System.out.println("Error en la conexion a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeConnection(Conexion);
        }
        return false;
    }
}
