package com.example.daz.latihan4;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import android.content.Intent;

/**
 * Created by daz on 3/24/2016.
 */
public class DataFragment extends Fragment {

    private EditText text_tglMasuk;
    private EditText text_tglBerakhir;



    EditText text_no,text_asal;
    EditText text_catatan,text_perihal;

    ArrayList<String> todosinstruksi = new ArrayList<String>();

    ArrayList<String> todosditeruskan = new ArrayList<String>();

    private static final String url = "jdbc:mysql://85.10.205.173:3306/daudaz";
    private static final String user = "daudaz";
    private static final String pass = "7206361";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_data, container, false);

        text_no = (EditText)rootView.findViewById(R.id.text_nomor);
        text_asal = (EditText)rootView.findViewById(R.id.text_asal);
        text_catatan = (EditText)rootView.findViewById(R.id.text_catatan);
        text_perihal = (EditText)rootView.findViewById(R.id.text_perihal);
        final CheckBox cek_hadir = (CheckBox)rootView.findViewById(R.id.check_hadir);
        final CheckBox cek_hadirbersama = (CheckBox)rootView.findViewById(R.id.check_HadirBersama);
        final CheckBox cek_kebinamargaan = (CheckBox)rootView.findViewById(R.id.check_kebinamargaan);
        final CheckBox cek_informasi = (CheckBox)rootView.findViewById(R.id.check_KoordinasiInformasi);
        final CheckBox cek_laporan = (CheckBox)rootView.findViewById(R.id.check_Laporan);
        final CheckBox cek_pengairan = (CheckBox)rootView.findViewById(R.id.check_pengairan);
        final CheckBox cek_pengendalian = (CheckBox)rootView.findViewById(R.id.check_pengendalian);
        final CheckBox cek_perencanaan = (CheckBox)rootView.findViewById(R.id.check_perencanaan);
        final CheckBox cek_pju = (CheckBox)rootView.findViewById(R.id.check_PJU);
        final CheckBox cek_sekertaris = (CheckBox)rootView.findViewById(R.id.check_sekertaris);
        final CheckBox cek_subbag = (CheckBox)rootView.findViewById(R.id.check_subbag);
        final CheckBox cek_tanggapsaran = (CheckBox)rootView.findViewById(R.id.check_TanggapanSaran);
        final CheckBox cek_tindaklanjut = (CheckBox)rootView.findViewById(R.id.check_TindakLanjut);
        final CheckBox cek_tugaskanstaff = (CheckBox)rootView.findViewById(R.id.check_TugaskanStaff);
        final CheckBox cek_untukbahan = (CheckBox)rootView.findViewById(R.id.check_UntukBahan);
        final CheckBox cek_upt = (CheckBox)rootView.findViewById(R.id.check_UPT);
        final RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioG);

        String myTag = getTag();
        ((MainActivity)getActivity()).setFragment_no(myTag);


        text_tglMasuk = (EditText) rootView.findViewById(R.id.text_tglMasuk);
        text_tglBerakhir = (EditText) rootView.findViewById(R.id.text_tglBerakhir);

        Button btn = (Button)rootView.findViewById(R.id.buttondisposisi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no =  text_no.getText().toString();
              //  Toast.makeText(getActivity(),"Test Click : "+no,Toast.LENGTH_SHORT).show();

                //Check box Diteruskan
                if (cek_sekertaris.isChecked()) {
                    String a = cek_sekertaris.getText().toString();
                    todosditeruskan.add(a);
                } ;
                if (cek_perencanaan.isChecked()) {
                    String a1 = cek_perencanaan.getText().toString();
                    todosditeruskan.add(a1);
                } ;
                if (cek_pengendalian.isChecked()) {
                    String a2 = cek_pengendalian.getText().toString();
                    todosditeruskan.add(a2);
                } ;
                if (cek_kebinamargaan.isChecked()) {
                    String a3 = cek_kebinamargaan.getText().toString();
                    todosditeruskan.add(a3);
                } ;
                if (cek_pengairan.isChecked()) {
                    String a4 = cek_pengairan.getText().toString();
                    todosditeruskan.add(a4);
                } ;
                if (cek_pju.isChecked()) {
                    String a5 = cek_pju.getText().toString();
                    todosditeruskan.add(a5);
                } ;
                if (cek_subbag.isChecked()) {
                    String a6 = cek_subbag.getText().toString();
                    todosditeruskan.add(a6);
                } ;
                if (cek_upt.isChecked()) {
                    String a7 = cek_upt.getText().toString();
                    todosditeruskan.add(a7);
                } ;

                //Check box intruksi
                if (cek_hadir.isChecked()) {
                    String b = cek_hadir.getText().toString();
                    todosinstruksi.add(b);
                } ;
                if (cek_hadirbersama.isChecked()) {
                    String b1 = cek_hadirbersama.getText().toString();
                    todosinstruksi.add(b1);
                };
                if (cek_informasi.isChecked()){
                    String b2 = cek_informasi.getText().toString();
                    todosinstruksi.add(b2);
                };
                if (cek_tugaskanstaff.isChecked()){
                    String b3 = cek_tugaskanstaff.getText().toString();
                    todosinstruksi.add(b3);
                };
                if (cek_tindaklanjut.isChecked()){
                    String b4 = cek_tindaklanjut.getText().toString();
                    todosinstruksi.add(b4);
                };
                if (cek_untukbahan.isChecked()){
                    String b5 = cek_untukbahan.getText().toString();
                    todosinstruksi.add(b5);
                };
                if (cek_tanggapsaran.isChecked()){
                    String b6 = cek_tanggapsaran.getText().toString();
                    todosinstruksi.add(b6);
                };
                if (cek_informasi.isChecked()){
                    String b7 = cek_informasi.getText().toString();
                    todosinstruksi.add(b7);
                };
                if (cek_laporan.isChecked()){
                    String b8 = cek_laporan.getText().toString();
                    todosinstruksi.add(b8);
                };



                String Diteruskan="";
                String Instruksi="";
                for(int i=0;i<todosinstruksi.size();i++){
                    Instruksi = Instruksi +todosinstruksi.get(i).toString()+ ", ";
                }
                for(int i=0;i<todosditeruskan.size();i++){
                    Diteruskan = Diteruskan +todosditeruskan.get(i).toString()+ ", ";
                }

                //Informasi

                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) rootView.findViewById(selectedId);
                String Informasi = radioButton.getText().toString();
                Toast.makeText(getActivity(),Informasi, Toast.LENGTH_SHORT).show();



                Toast.makeText(getActivity(), "Diteruskan : " + Diteruskan, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Instruksi : " + Instruksi, Toast.LENGTH_SHORT).show();

               new getData(no,Diteruskan,Instruksi,Informasi).execute();
                todosinstruksi.clear();
                todosditeruskan.clear();

                startActivity(new Intent(getActivity(), MainActivity.class));
             }
        });


        return rootView;

    }

    public void b_updateText(String no, String tglmsk, String hal, String tglbrs, String asal, String ctn ){
        text_no.setText(no);
        text_tglMasuk.setText(tglmsk);
        text_perihal.setText(hal);
        text_tglBerakhir.setText(tglbrs);
        text_asal.setText(asal);
        text_catatan.setText(ctn);

    }


    private class getData extends AsyncTask<Void, Void, ArrayList<String>> {
        ProgressDialog pDialog;
        String no,ins,dit,info;
        int hasil = 0;

        public getData(String no, String dit, String ins, String info) {
            this.no = no;
            this.dit = dit;
            this.ins = ins;
            this.info = info;
        }

        protected void onPreExecute() {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Harap Tunggu. . ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            // Show progressbar
            pDialog.show();

        }

    

        protected ArrayList<String> doInBackground(Void... params) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();

                String query="insert into tdisposisi(tdisposisi.id_surat,tdisposisi.diteruskan,tdisposisi.instruksi,tdisposisi.kategori) values('"+no+"','"+dit+"','"+ins+"','"+info+"')";


                hasil = st.executeUpdate(query);



                con.close();
                st.close();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();

                String queryup="UPDATE tdatasurat SET tdatasurat.status_surat = 'Selesai' WHERE tdatasurat.no_surat = '"+no+"'";

                hasil = st.executeUpdate(queryup);

                con.close();
                st.close();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            return todosinstruksi;

        }

        protected void onPostExecute(ArrayList<String> dta) {
            pDialog.dismiss();

            Toast.makeText(getActivity(), "Didisposisikan " + hasil + " Surat", Toast.LENGTH_LONG).show();

        }

    }



}
