package com.holamundoandroid.npedrosapena.saludopersonalizado;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SaludoPersonalizadoActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo_personalizado);//llamada a la ventana primera

        //definimos un boton
        Button btnBoton = (Button)findViewById(R.id.b_saludo);//no hace falta hacer new porque
                                                              //ya lo tenemos creado en el xml
                                                              //se castea para pasar el objeto view a button

        btnBoton.setOnClickListener(new View.OnClickListener()//añadimos el listener
        {
            @Override
            public void onClick(View v)//view v representa al boton sobre el que hacemos click
            {
                //definimos un btn para poder acceder a las propiedades setText etc..
                //al cual le pasamos el view (objeto) recogido
               Button labelDelBoton= (Button) findViewById(R.id.b_saludo);

     
               EditText textoDelEditText = (EditText)findViewById(R.id.entrada);


                TextView textoEscribir= (TextView) findViewById(R.id.saludo);

                RadioButton seleccionado=(RadioButton)findViewById(R.id.rdsr);

                if(textoDelEditText.getText().toString().equals(""))
                {
                  showAlert();
                  showToast();

                }else {

                    if (seleccionado.isChecked()) {
                        textoEscribir.setText("Hola Sr.: " + textoDelEditText.getText().toString());
                        labelDelBoton.setText("le hiciste click + Sr");
                    } else {
                        textoEscribir.setText("Hola Sra.: " + textoDelEditText.getText().toString());
                        labelDelBoton.setText("le hiciste click + Sra");
                    }
                }

            }
        });


       final CheckBox mostrarHora= (CheckBox)findViewById(R.id.checkbox);

        mostrarHora.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePicker time=(TimePicker)findViewById(R.id.timePicker);
                DatePicker fecha=(DatePicker)findViewById(R.id.datePicker);

                if(mostrarHora.isChecked())
                {
                    time.setVisibility(View.VISIBLE);
                    fecha.setVisibility(View.VISIBLE);
                }else
                {
                    time.setVisibility(View.GONE);
                    fecha.setVisibility(View.GONE);
                }
            }
        });


        /*************************************/
        /*           ZONA EXAMEN            */
        /***********************************/


        //ESTA COMENTADO PARA NO INFLUIR EN EL PROGRAMA


    /*    RadioGroup rg= (RadioGroup)findViewById(R.id.RadioGroupDespedida);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton radioExamen=(RadioButton)findViewById(R.id.saludoDespedida);//obtengo el objeto radioButon

                if(radioExamen.isChecked()==true)
                {
                    Toast.makeText(getApplicationContext(), radioExamen.getText().toString(), Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(getApplicationContext(), ((RadioButton)findViewById(R.id.despedidaDesdepida)).getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

        /*************************************/
        /*          ZONA EXAMEN  2          */
        /***********************************/

        Spinner spinnerExamen= (Spinner)findViewById(R.id.SpinnerDespedida);//instanciamos el objeto
        List<String> lista=new ArrayList<String>();//creamos una lista para los elementos que le agregaremos

        lista.add("Hola");
        lista.add("Adios");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExamen.setAdapter(adaptador);

        spinnerExamen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(parent.getContext(), "Seleccionado: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });





    }

    //muestra un mensaje con boton
    protected void showAlert()
    {
        String texto= getResources().getString(R.string.noName);//de nuestros recursos recogemos el string
        AlertDialog.Builder alarma= new AlertDialog.Builder(this);
        alarma.setMessage(texto);
        //alarma.setPositiveButton("aceptar",null);
        alarma.setNeutralButton("aceptar neutral",null);
        alarma.show();
    }

    //muestra mensaje clasico por pantalla android
    protected void showToast()
    {
        Context context= getApplicationContext();

        AlertDialog.Builder textoToast = new AlertDialog.Builder(this);

        int duracion= Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,R.string.noName,duracion);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.saludo_personalizado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
