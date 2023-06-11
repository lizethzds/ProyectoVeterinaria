package com.example.veterinaria.controladores;

import com.example.veterinaria.modelos.Mascota;
import com.example.veterinaria.modelos.Propietario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MascotasController {

    Connection conn;

    public MascotasController()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria?user=root&password=123456");
    }

    public List<Mascota> listaMascotas()  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Mascotas");
        List<Mascota> listaMascotas = new ArrayList<Mascota>();
        while(resultados.next()) {
            Integer idMascota = resultados.getInt("id");
            String nombreMascota =  resultados.getString("nombre");
            String especie = resultados.getString("especie");
            String raza = resultados.getString("raza");
            Integer edad = resultados.getInt("edad");
            Integer prop_id = resultados.getInt("dueño_id");

//Tomar en cuenta que el constructor llevaba null como ultimo parametro
            Mascota mascota = new Mascota(idMascota, nombreMascota,  especie, raza,edad,prop_id);
            listaMascotas.add(mascota);
        }
        return listaMascotas;
    }


    //Busqueda de Mascota por ID
    public Mascota buscarPorID(int idMascota)  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Mascotas where id = "+idMascota);
        Mascota mascota=null;
        if(resultados.next()) {
            Integer id = resultados.getInt("id");
            String nombreMascota =  resultados.getString("nombre");
            String especie = resultados.getString("especie");
            String raza = resultados.getString("raza");
            Integer edad = resultados.getInt("edad");
            Integer prop_id = resultados.getInt("dueño_id");

            mascota = new Mascota(id,nombreMascota,especie,raza,edad,prop_id);

        }
        return mascota;
    }


    //Agregar registro de mascota
    public boolean agregarMascota(Mascota mascota) {
        if (validarMascota(mascota)) {
            try {

                String valores = String.format("%d, %s,%s,%s", mascota.getId(),mascota.getEspecie(),mascota.getRaza(),mascota.getEdad(),mascota.getPropietario_id());
                String instSQL = "insert into Mascotas (id,nombre,especie,raza,edad,dueño_id) values (" + valores + ")";
                Statement inst = conn.createStatement();
                inst.executeUpdate(instSQL);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else
            return false;
    }


    //Validacion de mascotas

    private boolean validarMascota(Mascota mascota) {
        boolean correcto = true;
        if (mascota == null)
            correcto = false;
        else if (mascota.getNombre().isBlank())
            correcto = false;
        else {
            try {
                String instSQL = String.format("select id from Mascotas where id = '%s'",
                        mascota.getId());
                Statement inst = conn.createStatement();
                ResultSet resultado = inst.executeQuery(instSQL);
                correcto = !resultado.next();
            } catch (Exception e) {
                return false;
            }
        }
        return  correcto;
    }

    //Modificar registro de Dueño
    public boolean modificarMascota(Mascota mascota) {
        try {
            String valores = String.format("%d, %s,%s,%s", mascota.getId(),mascota.getEspecie(),mascota.getRaza(),mascota.getEdad(),mascota.getPropietario_id());
            String instSQL = String.format("update  Mascotas set %s where id = %d", valores, mascota.getId());
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Eliminar registro de Dueño
    public boolean eliminarMascota(int id) {
        try {
            String instSQL = "delete from Mascotas where id = "+id;
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
