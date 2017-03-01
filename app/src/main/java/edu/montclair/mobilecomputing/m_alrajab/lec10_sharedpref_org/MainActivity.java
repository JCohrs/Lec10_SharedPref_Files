package edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity
        implements TitlesFragment.OnFragmentInteractionListener{
    FragmentTransaction transaction;

    @BindView(R.id.btn_addanote_ma)  Button btn;
    @BindView(R.id.textview_ma1)  TextView tv;

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);
        ButterKnife.bind(this);

        btn.setOnClickListener(new Lstnr());


        transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,new TitlesFragment());
        transaction.commit();

        if(findViewById(R.id.fragment_container_details)!=null){
            transaction=getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container_details,new DetailsFragment());
            transaction.commit();
        }

    }


    class Lstnr implements View.OnClickListener{
        @Override
        public void onClick(final View view) {

            View viewGrp=getLayoutInflater().inflate(R.layout.costum_dialog_layout,
                    (ViewGroup) findViewById(R.id.activity_main), false);

            final EditText noteTitle=(EditText)viewGrp.findViewById(R.id.dialog_title_et);
            final EditText noteBody=(EditText)viewGrp.findViewById(R.id.dialog_body_et);
            AlertDialog.Builder alertBuilder=new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Take a note").setView(viewGrp)

            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    tv.setText(noteTitle.getText());

                    try{
                        FileOutputStream outputStream=openFileOutput(noteTitle.getText().toString().replace(" ",""),MODE_PRIVATE);
                        outputStream.write(noteBody.getText().toString().getBytes());
                        outputStream.close();
                        Snackbar.make(view,"File Saved",Snackbar.LENGTH_INDEFINITE).show();
                    }catch (Exception e){
                        Log.e("ERROR",e.getMessage());
                    }
                }
            });
            alertBuilder.show();
        }
    }

    @Override
    public void onFragmentInteraction(String uri) {
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,new TitlesFragment());
        transaction.commit();
        if(findViewById(R.id.fragment_container_details)!=null){
            transaction=getSupportFragmentManager().beginTransaction();
            DetailsFragment df=new DetailsFragment();
            Bundle b=new Bundle();  b.putString("KEY",uri);
            df.setArguments(b);
            transaction.add(R.id.fragment_container_details,df);
            transaction.commit();
        }else{
            Intent i=new Intent(MainActivity.this,Main2Activity.class);
            i.putExtra("MSG",uri);
            startActivity(i);
        }
    }
}
