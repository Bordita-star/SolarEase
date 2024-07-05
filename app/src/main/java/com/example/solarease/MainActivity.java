package com.example.solarease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private EditText Latitud;

    private EditText Longitud;

    private EditText Area;

    private SeekBar InclinacionSeebar;

    private TextView Inclinacion;

    private Button calcularButton;

    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Latitud= findViewById(R.id.Latitud);
        Longitud= findViewById(R.id.Longitud);
        Area = findViewById(R.id.Area);
        InclinacionSeebar = findViewById(R.id.seekBar);
        Inclinacion = findViewById(R.id.inclinacion);
        calcularButton = findViewById(R.id.button);
        resultado = findViewById(R.id.resultado);


        InclinacionSeebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Inclinacion.setText("Inclinacion de paneles"+ progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int validacionResultado = validateInputFields(Latitud, Longitud, Area);

                switch (validacionResultado) {
                    //Tipos de datos.
                    case 0:

                        double latitud = Double.parseDouble(Latitud.getText().toString());
                        double longitud = Double.parseDouble(Longitud.getText().toString());
                        double area = Double.parseDouble(Area.getText().toString());
                        int inclinacion = InclinacionSeebar.getProgress();


                        double produccionEnergia = calcularProduccionEnergia(latitud, longitud, area, inclinacion);

                        resultado.setText("ProduccionEnergia:" + produccionEnergia + "KW/H");
                        break;

                    case 1:
                        resultado.setText("Por favor, complete todos los campos 😒😒😒");
                        break;

                }
            }

        });

    }
    private int validateInputFields(EditText latitudeEditText, EditText longitudeEditText, EditText areaEditText) {
        if(!latitudeEditText.getText().toString().isEmpty()&&
                !longitudeEditText.getText().toString().isEmpty()&&
                !areaEditText.getText().toString().isEmpty()){
            return 0;
        }else{
            return 1;
        }
    }

    private double calcularProduccionEnergia(double latitud, double longitud, double area, int inclinacion) {
      double latitudRadio = Math.toRadians(latitud);
      double longitudRadio = Math.toRadians(longitud);
      double areaRadio = Math.toRadians(area);
      int diaAño= LocalDate.now().getDayOfYear();

      double constanteSolar = 0.1367;
      double anguloIncidencia = 1;
      double radiacion= constanteSolar * Math.cos(anguloIncidencia) * (1+0.33+Math.toRadians(360 *diaAño/360.0));

      double areaPanel1 = area /10000.0;
      double eficiaPanel= 0.16;
      double factorPerdida= 0.9;

      double produccionEnergia =areaPanel1 * radiacion * eficiaPanel*factorPerdida;

      return produccionEnergia;

    }
    private double calcularProduccionEnergia(double latitud, double longitud,int area, int inclinacion) {
        double areaText = (double) Math.round(area);
        return calcularProduccionEnergia(latitud, longitud, area, inclinacion);

    }


};




