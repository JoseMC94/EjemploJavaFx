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

public class CentroEstudio {

    private IntegerProperty codigoCentro;
    private StringProperty nombreCentro;

    public CentroEstudio(Integer codigoCentro, String nombreCentro){
        this.codigoCentro = new SimpleIntegerProperty(codigoCentro);
        this.nombreCentro = new SimpleStringProperty(nombreCentro);
    }

    public int getCodigoCentro() {
        return codigoCentro.get();
    }

    public IntegerProperty codigoCentroProperty() {
        return codigoCentro;
    }

    public void setCodigoCentro(int codigoCentro) {
        this.codigoCentro.set(codigoCentro);
    }

    public String getNombreCentro() {
        return nombreCentro.get();
    }

    public StringProperty nombreCentroProperty() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro.set(nombreCentro);
    }
    public static void llenarInformacion(Connection connection, ObservableList<CentroEstudio> lista){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "SELECT CentrosEstudiosId, nombre_centro FROM centrosestudios"
            );
            while (resultado.next()){
                lista.add(
                        new CentroEstudio(
                                resultado.getInt("CentrosEstudiosId"),
                                resultado.getString("nombre_centro")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return nombreCentro.get();
    }
}
