package com.example.veterinaria.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.controlsfx.tools.Borders;


public class MenuPrincipal extends BorderPane {

    Button btnDuenos, btnCitas, btnMascotas, btnVeterinarios;
    TilePane panelBotones;

    BorderPane contenedorPrincipal;

   Label lbTitulo;


    public MenuPrincipal(){
        inicializarComponentes();
    }

    private void inicializarComponentes() {

        Button btnCitas = new Button("Citas");
        Button btnDuenos = new Button("Pacientes ");
        Button btnVeterinarios = new Button("Veterinarios");
        Button btnMascotas = new Button("Mascotas");
        Label lbTitulo = new Label("Seleccione una opción");
        Button btnPropietarios = new Button("Dueños");

        btnVeterinarios.setOnAction(evt->{
            abrirVentanaVet();
        });

        lbTitulo.setAlignment(Pos.CENTER);

        TilePane panelBotones = new TilePane();

        panelBotones.setHgap(3);
        panelBotones.setPrefColumns(4);
        panelBotones.setAlignment(Pos.CENTER);



        panelBotones.getChildren().addAll(btnCitas,btnMascotas,btnVeterinarios,btnPropietarios);
        contenedorPrincipal.getChildren().addAll(panelBotones);

        contenedorPrincipal.setCenter(panelBotones);
        contenedorPrincipal.setTop(lbTitulo);



    }


    //Generar eventos de ventanas

    public void abrirVentanaVet(){
        VentanaVeterinarios ventanaVet = new VentanaVeterinarios();
        ventanaVet.show();

    }

}
