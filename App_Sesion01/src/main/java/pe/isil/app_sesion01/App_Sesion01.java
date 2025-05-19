package pe.isil.app_sesion01;

import java.sql.SQLException;
import java.util.List;
import pe.isil.app_sesion01.beans.Curso;
import pe.isil.app_sesion01.dao.AD_curso;
import view.frmCurso;

/**
 *
 * @author sebas
 */
public class App_Sesion01 {

    public static void main(String[] args) throws SQLException {
        frmCurso form = new frmCurso();
        form.setVisible(true);
        
        
        AD_curso ad_Curso = new AD_curso(); //Permite llamar a la funcion de obtener un curso por id
        Curso curso = new Curso(); // crear un objeto para guardar el resultado de la funcion obtener curso por id del ADO
        curso = ad_Curso.getCursoById(1);
        
        //Validacion
        if(curso.getId() != null){
            System.out.println(curso.getNombre());
            
            List<Curso> cursos = ad_Curso.getCursos();
            System.out.println(cursos.get(0).getDescripcion());
        }else{
            System.out.println("No se obtuvieron los datos");
        }
        
        //insert
        Curso curso_insertar = new Curso();
        curso_insertar.setCreditos(3);
        curso_insertar.setNombre("Analisis y diseño I");
        curso_insertar.setDescripcion("Analisis y diseño I");
        curso_insertar.setHorario("viernes 7 a 9pm");
        curso_insertar.setHoras(3);
        curso_insertar.setNrc("1200");
        curso_insertar.setProfesor("Fercho");
        
        
        if(ad_Curso.addCurso(curso_insertar) == true){
            System.out.println("Curso registrado exitosamente");
        }
        
        //update
        Curso curso_actualizar = new Curso();
        curso_actualizar.setCreditos(3);
        curso_actualizar.setNombre("Analisis y diseño I");
        curso_actualizar.setDescripcion("Analisis y diseño con JavaScript");
        curso_actualizar.setHorario("viernes 7 a 9pm");
        curso_actualizar.setHoras(3);
        curso_actualizar.setNrc("1212");
        curso_actualizar.setProfesor("Fercho");
        curso_actualizar.setId(2);
        
        if(ad_Curso.updateCurso(curso_actualizar) == true){
            System.out.println("Curso actualizado exitosamente");
        }
        
        //DELETE
        if(ad_Curso.deleteCurso(1) == true){
            System.out.println("Curso eliminado exitosamente");
        }
        
    }
}
