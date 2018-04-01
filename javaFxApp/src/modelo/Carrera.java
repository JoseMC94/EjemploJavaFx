package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Carrera {

    private IntegerProperty codigoCarrera;
    private StringProperty nombreCarrera;
    private IntegerProperty cantidadAsignaturas;

    public Carrera(Integer codigoCarrera, String nombreCarrera, Integer cantidadAsignaturas){
        this.codigoCarrera = new SimpleIntegerProperty(codigoCarrera);
        this.nombreCarrera = new SimpleStringProperty(nombreCarrera);
        this.cantidadAsignaturas = new SimpleIntegerProperty(cantidadAsignaturas);
    }

    public int getCodigoCarrera() {
        return codigoCarrera.get();
    }

    public IntegerProperty codigoCarreraProperty() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera.set(codigoCarrera);
    }

    public String getNombreCarrera() {
        return nombreCarrera.get();
    }

    public StringProperty nombreCarreraProperty() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera.set(nombreCarrera);
    }

    public int getCantidadAsignaturas() {
        return cantidadAsignaturas.get();
    }

    public IntegerProperty cantidadAsignaturasProperty() {
        return cantidadAsignaturas;
    }

    public void setCantidadAsignaturas(int cantidadAsignaturas) {
        this.cantidadAsignaturas.set(cantidadAsignaturas);
    }


    public static void llenarInformacion(Connection connection, ObservableList<Carrera> lista){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "SELECT CarreraId, "
                            + "nombre_carrera, "
                            + "cantidad_clases "
                            + "FROM carrera"
            );
            while (resultado.next()){
                lista.add(
                        new Carrera(
                                resultado.getInt("CarreraId"),
                                resultado.getString("nombre_carrera"),
                                resultado.getInt("cantidad_clases")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return nombreCarrera.get() + " ("+cantidadAsignaturas.get()+")";
    }
}
