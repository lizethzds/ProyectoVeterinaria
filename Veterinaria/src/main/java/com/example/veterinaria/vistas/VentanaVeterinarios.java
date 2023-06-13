package com.example.veterinaria.vistas;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VentanaVeterinarios extends Stage {

    Label etiqueta;

    BorderPane contenedorVeterinarios;
    public VentanaVeterinarios(){
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        etiqueta = new Label("HOla");

        contenedorVeterinarios.setTop(etiqueta);
    }
}
