package com.example.veterinaria.controladores;

import com.example.veterinaria.modelos.Propietario;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PropietarioController {

    Connection conn;

    public PropietarioController()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria?user=root&password=123456");
    }

    public List<Propietario> listaPropietarios()  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Dueños");
        List<Propietario> listaPropietarios = new ArrayList<Propietario>();
        while(resultados.next()) {
            Integer idPropietario = resultados.getInt("id");
            String nombreCompleto =  resultados.getString("nombre");
            String direccion = resultados.getString("direccion");
            String telefono = resultados.getString("telefono");

//Tomar en cuenta que el constructor llevaba null como ultimo parametro
            Propietario propietario = new Propietario(idPropietario, nombreCompleto,  direccion, telefono);
            listaPropietarios.add(propietario);
        }
        return listaPropietarios;
    }


    //Busqueda de veterinario por ID
    public Propietario buscarPorID(int idPropietario)  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Dueños where id = "+idPropietario);
        Propietario propietario=null;
        if(resultados.next()) {
            Integer id = resultados.getInt("id");
            String nombreCompleto =  resultados.getString("nombre");
            String direccion = resultados.getString("direccion");
            String telefono = resultados.getString("telefono");

            propietario = new Propietario(id,nombreCompleto,direccion,telefono);

        }
        return propietario;
    }


    //Agregar registro de veterinario
    public boolean agregarPropietario(Propietario propietario) {
        if (validarPropietario(propietario)) {
            try {

                String valores = String.format("%d, %s,%s,%s", propietario.getIdPropietario(),propietario.getNombreCompleto(),propietario.getDireccion(),propietario.getTelefono());
                String instSQL = "insert into Dueños (id,nombre,telefono,especialidad) values (" + valores + ")";
                Statement inst = conn.createStatement();
                inst.executeUpdate(instSQL);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else
            return false;
    }


    //Validacion de veterinario

    private boolean validarPropietario(Propietario propietario) {
        boolean correcto = true;
        if (propietario == null)
            correcto = false;
        else if (propietario.getNombreCompleto().isBlank())
            correcto = false;
        else {
            try {
                String instSQL = String.format("select id from Dueños where id = '%s'",
                        propietario.getIdPropietario());
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
    public boolean modificarPropietario(Propietario propietario) {
        try {

            String valores = String.format("%d, %s,%s,%s", propietario.getIdPropietario(),propietario.getNombreCompleto(),propietario.getDireccion(),propietario.getTelefono());
            String instSQL = String.format("update  Dueños set %s where id = %d", valores, propietario.getIdPropietario());
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Eliminar registro de Dueño
    public boolean eliminarPropietario(int id) {
        try {
            String instSQL = "delete from Veterinarios where id = "+id;
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
