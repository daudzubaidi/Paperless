package com.example.daz.latihan4;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Fragment dispF = new DisplayFragment();
    Fragment datF = new DataFragment();


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    String Fragment_no;

    public String getFragment_no() {
        return Fragment_no;
    }

    public void setFragment_no(String fragment_no) {
        Fragment_no = fragment_no;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailClient = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(mailClient);

            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.paperless2);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.help_menu);
            TextView t = (TextView)dialog.findViewById(R.id.textView12);
            t.setText("Aplikasi ini dirancang untuk membantu pimpinan perusahaan dalam mendisposisikan surat. Data surat di dapat dari aplikasi server yang sebelumnya telah melakukan input data surat yang masuk / baru di terima, Lalu admin atau petugas pengelola data surat akan memasukan index surat ke server database agar bisa dilakukan tindak lanjut oleh pimpinan.\n" +
                    "Fungsi utama dari aplikasi ini hanya memberikan rujukan kepada admin (pegawai) pengelola surat untuk meneruskan surat tersebut kepada bagian yang harus menerima surat tersebut. Antinya dari server admin secara otomatis rujukan tersebut akan di filter lalu di kirimkan melalu E-mail serta SMS untuk bidang yang dituju.");

            TextView t1 = (TextView)dialog.findViewById(R.id.textView15);
            t1.setText("1. Buka Aplikasi\n" +
                    "2. Lihat Data Surat Belum Didisposisikan Pada Tab Lihat Data\n" +
                    "3. Click Data Dalam Table\n" +
                    "4. Pilih Tab Disposisi Data\n" +
                    "5. Checklist Data Diteruskan Kepada\n" +
                    "6. Checklist Data Instruksi / Informasi\n" +
                    "7. Setelah Data Diteruskan Tekan Tombol Disposisi\n" +
                    "8. Lihat Data Tabel Data Terupdate Ke Server");

            dialog.show();
                dialog.setTitle("HELP");
                dialog.setCancelable(true);
                //there are a lot of settings, for dialog, check them all out!

                //set up text
                TextView text = (TextView) dialog.findViewById(R.id.textView13);
              //  text.setText(R.string.lots_of_text);
            text.getText();

                //set up image view
                ImageView img = (ImageView) dialog.findViewById(R.id.imageView);
                img.setImageResource(R.mipmap.paperless196);

                //set up button
                Button button = (Button) dialog.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //now that the dialog is set up, it's time to show it




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 1) {

             return dispF;
            } else {

             return datF;
            }

        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DISPOSISI DATA";
                case 1:
                    return "LIHAT DATA";
            }
            return null;
        }
    }

}


