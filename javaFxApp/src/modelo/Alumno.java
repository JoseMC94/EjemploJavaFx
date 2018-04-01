package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.*;

public class Alumno {

    private IntegerProperty codigoAlumno;
    private StringProperty nombre;
    private StringProperty apellido;
    private IntegerProperty edad;
    private StringProperty genero;
    private Date fechaIngreso;
    private CentroEstudio centroEstudio;
    private Carrera carrera;

    public Alumno(Integer codigoAlumno, String nombre, String apellido, Integer edad, String genero, Date fechaIngreso, CentroEstudio centroEstudio, Carrera carrera){
        this.codigoAlumno = new SimpleIntegerProperty(codigoAlumno);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.edad = new SimpleIntegerProperty(edad);
        this.genero = new SimpleStringProperty(genero);
        this.fechaIngreso = fechaIngreso;
        this.centroEstudio = centroEstudio;
        this.carrera = carrera;
    }

    public int getCodigoAlumno() {
        return codigoAlumno.get();
    }

    public IntegerProperty codigoAlumnoProperty() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(int codigoAlumno) {
        this.codigoAlumno.set(codigoAlumno);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public int getEdad() {
        return edad.get();
    }

    public IntegerProperty edadProperty() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }

    public String getGenero() {
        return genero.get();
    }

    public StringProperty generoProperty() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public CentroEstudio getCentroEstudio() {
        return centroEstudio;
    }

    public void setCentroEstudio(CentroEstudio centroEstudio) {
        this.centroEstudio = centroEstudio;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    public static void llenarInformacionAlumnos(Connection connection,  ObservableList<Alumno> lista){
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery(
                    "SELECT A.AlumnoId, " +
                            "A.nombre, " +
                            "A.apellido, " +
                            "A.edad, " +
                            "A.genero, " +
                            "A.fechaIngreso, " +
                            "A.CentrosEstudiosId, " +
                            "A.CarreraId, " +
                            "B.nombre_carrera, " +
                            "B.cantidad_clases, " +
                            "C.nombre_centro " +
                            "FROM alumno A " +
                            "INNER JOIN carrera B " +
                            "ON (A.CarreraId = B.CarreraId) " +
                            "INNER JOIN centrosestudios C " +
                            "ON (A.CentrosEstudiosId = C.CentrosEstudiosId)"
            );
            while(resultado.next()){
                lista.add(
                        new Alumno(
                                resultado.getInt("AlumnoId"),
                                resultado.getString("nombre"),
                                resultado.getString("apellido"),
                                resultado.getInt("edad"),
                                resultado.getString("genero"),
                                resultado.getDate("fechaIngreso"),
                                new CentroEstudio(resultado.getInt("CentrosEstudiosId"),
                                        resultado.getString("nombre_centro")
                                ),
                                new Carrera(resultado.getInt("CarreraId"),
                                        resultado.getString("nombre_carrera"),
                                        resultado.getInt("cantidad_clases"))
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int guardarRegistro(Connection connection){
        try {
            //Evitar inyeccion SQL.
            PreparedStatement instruccion =
                    connection.prepareStatement("INSERT INTO alumno (nombre, apellido, edad, genero, "
                            + "fechaIngreso, CarreraId, CentrosEstudiosId) "+
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, apellido.get());
            instruccion.setInt(3, edad.get());
            instruccion.setString(4, genero.get());
            instruccion.setDate(5, fechaIngreso);
            instruccion.setInt(6, carrera.getCodigoCarrera());
            instruccion.setInt(7, centroEstudio.getCodigoCentro());
            return instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int actualizarRegistro(Connection connection){
        try {
            PreparedStatement instruccion =
                    connection.prepareStatement(
                            "UPDATE alumno "+
                                    " SET 	nombre = ?,  "+
                                    " apellido = ?,  "+
                                    " edad = ?, "+
                                    " genero = ?,  "+
                                    " fechaIngreso = ?, "+
                                    " CarreraId = ?,  "+
                                    " CentrosEstudiosId = ?  "+
                                    " WHERE AlumnoId = ?"
                    );
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, apellido.get());
            instruccion.setInt(3, edad.get());
            instruccion.setString(4, genero.get());
            instruccion.setDate(5, fechaIngreso);
            instruccion.setInt(6, carrera.getCodigoCarrera());
            instruccion.setInt(7, centroEstudio.getCodigoCentro());
            instruccion.setInt(8, codigoAlumno.get());
            return instruccion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int eliminarRegistro(Connection connection){
        try {
            PreparedStatement instruccion = connection.prepareStatement(
                    "DELETE FROM alumno "+
                            "WHERE AlumnoId = ?"
            );
            instruccion.setInt(1, codigoAlumno.get());
            return instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


}
