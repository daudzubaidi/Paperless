package com.example.daz.latihan4;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by daz on 3/24/2016.
 */
public class DisplayFragment extends Fragment {

    ArrayList<DATA> todos = new ArrayList<DATA>();

    View rootView;

    private static final String url = "jdbc:mysql://85.10.205.173:3306/daudaz";
    private static final String user = "daudaz";
    private static final String pass = "7206361";

    int a=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_display, container, false);

        new getData().execute();

        return rootView;
    }


    private void tabel() {
        int row = a;
        int coly = 8;

        String field[] = {"No Surat", "Perihal", "Status", "Tag", "Catatan", "Tanggal Masuk", "Tanggal Keluar", "Asal Surat"};
        TableLayout tl = (TableLayout) rootView.findViewById(R.id.tlGridTable);
            for (int awal = -1; awal < row; awal++) {
            final TableRow tr = new TableRow(getActivity());
            tr.setClickable(true);

            tr.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    TableRow t = (TableRow) view;

                    TextView a1 = (TextView) t.getChildAt(0);
                    TextView a2 = (TextView) t.getChildAt(1);
                    TextView a3 = (TextView) t.getChildAt(2);
                    TextView a4 = (TextView) t.getChildAt(3);
                    TextView a5 = (TextView) t.getChildAt(4);
                    TextView a6 = (TextView) t.getChildAt(5);
                    TextView a7 = (TextView) t.getChildAt(6);
                    TextView a8 = (TextView) t.getChildAt(7);

                    String a11 = a1.getText().toString();
                    String a12 = a2.getText().toString();
                    String a15 = a5.getText().toString();
                    String a16 = a6.getText().toString();
                    String a17 = a7.getText().toString();
                    String a18 = a8.getText().toString();

                    Toast.makeText(getActivity(), "" + a11, Toast.LENGTH_LONG).show();


                    String no = a11;
                    String perihal = a12;
                    String asal = a18;
                    String catatan = a15;
                    String tglmsk = a16;
                    String tglbrs = a17;
                    String TabOfnotext = ((MainActivity) getActivity()).getFragment_no();

                    DataFragment fragmentB = (DataFragment) getActivity()
                            .getSupportFragmentManager()
                            .findFragmentByTag(TabOfnotext);
                    fragmentB.b_updateText(no,tglmsk,perihal,tglbrs,asal,catatan);
                    tr.setBackgroundColor(Color.RED);
                    Handler handler;
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tr.setBackgroundColor(Color.BLACK);
                        }
                    }, 1000);
                }
            });

            tr.setBackgroundColor(Color.BLACK);
               for (int kolom = 0; kolom < coly; kolom++) {
                TextView tv = new TextView(getActivity());
                TableRow.LayoutParams params = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                // menentukan title
                if (awal == -1) {
                    tv.setTextColor(Color.BLACK);
                    tv.setText(field[kolom]);
                    params.setMargins(3, 3, 3, 3);
                    tv.setTextSize(14);

                } else {
                    tv.setTextColor(Color.WHITE);
                    // menentukan value
                    if (kolom == 0) {
                        params.setMargins(0, 2, 2, 2);
                        tv.setText(todos.get(awal).getNo_surat());
                   } else if (kolom == 1) {
                        params.setMargins(0, 2, 2, 2);
                        tv.setText(todos.get(awal).getPerihal());
                    }else if (kolom == 2) {
                        params.setMargins(0, 2, 2, 2);
                        tv.setText(todos.get(awal).getKeterangan());
                    }else if (kolom == 3) {
                        params.setMargins(0, 2, 2, 2);
                        tv.setText(todos.get(awal).getStatus_surat());
                    }else if (kolom == 4) {
                        params.setMargins(0, 2, 2, 2);
                        tv.setText(todos.get(awal).getAsal());
                    }else if (kolom == 5) {
                        params.setMargins(0, 2, 2, 2);
                        tv.setText(todos.get(awal).getTglmasuk());
                    }else if (kolom == 6) {
                        params.setMargins(0, 2, 2, 2);
                        tv.setText(todos.get(awal).getTglkeluar());
                    }else if (kolom == 7) {
                        params.setMargins(0, 2, 2, 2);
                        tv.setText(todos.get(awal).getTag());
                    }
                }
                tv.setBackgroundColor(Color.GRAY);
                   tv.setPadding(5, 5, 5, 5);
                tr.addView(tv, params);
            }
            tl.addView(tr);
        }
    }


    private class getData extends AsyncTask<Void, Void, ArrayList<DATA>> {
        ProgressDialog pDialog;

        protected void onPreExecute() {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Harap Tunggu. . ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }


        protected ArrayList<DATA> doInBackground(Void... params) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();


                ResultSet rs = st.executeQuery("select * from tdatasurat where status_surat='Belum' order by tdatasurat.no_surat");

                 while (rs.next()) {
                    DATA datatabel = new DATA();
                    datatabel.setNo_surat(rs.getString(2));
                    datatabel.setPerihal(rs.getString(3));
                     datatabel.setAsal(rs.getString(4));
                     datatabel.setKeterangan(rs.getString(5));
                     datatabel.setStatus_surat(rs.getString(6));
                     datatabel.setTglmasuk(rs.getString(7));
                     datatabel.setTglkeluar(rs.getString(8));
                    datatabel.setTag(rs.getString(9));
                    todos.add(datatabel);

                }

                rs.close();
                con.close();
                st.close();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            return todos;

        }

        protected void onPostExecute(ArrayList<DATA> dta) {
            pDialog.dismiss();
            Toast.makeText(getActivity(), "Terdapat " + dta.size()+" Surat", Toast.LENGTH_LONG).show();

            a=dta.size();
            tabel();
        }

    }
}



