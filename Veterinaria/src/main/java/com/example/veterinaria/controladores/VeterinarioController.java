package com.example.veterinaria.controladores;

import com.example.veterinaria.modelos.Veterinario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioController {

    Connection conn;

    public VeterinarioController()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria?user=root&password=123456");
    }

    public List<Veterinario> listaVeterinarios()  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Veterinarios");
        List<Veterinario> listaVeterinarios = new ArrayList<Veterinario>();
        while(resultados.next()) {
            Integer idVet = resultados.getInt("id");
            String nombre =  resultados.getString("nombre");
            String telefono = resultados.getString("telefono");
            String especialidad = resultados.getString("especialidad");

//Tomar en cuenta que el constructor llevaba null como ultimo parametro
            Veterinario veterinario = new Veterinario(idVet, nombre,  telefono, especialidad);
            listaVeterinarios.add(veterinario);
        }
        return listaVeterinarios;
    }



    public Veterinario buscarPorID(int id)  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Veterinarios where id = "+id);
        Veterinario veterinario=null;
        if(resultados.next()) {
            Integer idVet = resultados.getInt("id");
            String nombre =  resultados.getString("nombre");
            String telefono = resultados.getString("telefono");
            String especialidad = resultados.getString("especialidad");

            veterinario = new Veterinario(id,nombre,telefono,especialidad);

        }
        return veterinario;
    }




}
