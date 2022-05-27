package sg.edu.rp.c346.id20042741.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView getDBSTV;
    TextView getOCBCTV;
    TextView getUOBTV;
    String getTVValue;
    String bank_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDBSTV = findViewById(R.id.tvDBS);
        getOCBCTV = findViewById(R.id.tvOCBC);
        getUOBTV = findViewById(R.id.tvUOB);

        registerForContextMenu(getDBSTV);
        registerForContextMenu(getOCBCTV);
        registerForContextMenu(getUOBTV);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);

        menu.setGroupVisible(R.id.langGroup, false);
        getTVValue = v == getDBSTV ? getString(R.string.eng_dbs) : v == getOCBCTV ? getString(R.string.eng_ocbc) : v == getUOBTV ? getString(R.string.eng_uob) : "";
        bank_num = v == getDBSTV ? getString(R.string.dbs_phone) : v == getOCBCTV ? getString(R.string.ocbc_phone) : v == getUOBTV ? getString(R.string.uob_phone) : "";

        menu.setHeaderTitle("To find out more:");

        if (!getDBSTV.getText().toString().equals("DBS")) {
            menu.setGroupVisible(R.id.engBankGroup, false);
            menu.setGroupVisible(R.id.chiBankGroup, true);
        } else {
            menu.setGroupVisible(R.id.engBankGroup, true);
            menu.setGroupVisible(R.id.chiBankGroup, false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.setGroupVisible(R.id.engBankGroup, false);
        menu.setGroupVisible(R.id.chiBankGroup, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.chiLang) {
            getDBSTV.setText(getString(R.string.chi_dbs));
            getOCBCTV.setText(getString(R.string.chi_ocbc));
            getUOBTV.setText(getString(R.string.chi_uob));
            return true;
        }
        if (id == R.id.engLang) {
            getDBSTV.setText(getString(R.string.eng_dbs));
            getOCBCTV.setText(getString(R.string.eng_ocbc));
            getUOBTV.setText(getString(R.string.eng_uob));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String uri = String.format("https://www.%s.com", getTVValue);
        String bank_no = String.format("tel:+%s", bank_num);

        if (item.getTitle().equals(getString(R.string.website)) || item.getTitle().equals(getString(R.string.chi_web))) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
            return true;
        } else if (item.getTitle().toString().equalsIgnoreCase(getString(R.string.contact_the_bank)) || item.getTitle().toString().equalsIgnoreCase(getString(R.string.chi_contact))) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(bank_no));
            startActivity(intent);
            return true;
        }

        //0xff000000 = black color
        //0xffff0000 = red color
        if (item.getTitle().toString().equalsIgnoreCase(getString(R.string.eng_fav)) || item.getTitle().toString().equalsIgnoreCase(getString(R.string.chi_fav))) {
            if ((getTVValue.equalsIgnoreCase(getString(R.string.eng_dbs)) || getTVValue.equalsIgnoreCase(getString(R.string.chi_dbs))) && getDBSTV.getCurrentTextColor() == 0xff000000) { //0xffff0000
                //Toast.makeText(MainActivity.this, String.format("%d", getDBSTV.getCurrentTextColor()),Toast.LENGTH_SHORT).show();
                getDBSTV.setTextColor(Color.RED);
                return true;
            } else if ((getTVValue.equalsIgnoreCase(getString(R.string.eng_ocbc)) || getTVValue.equalsIgnoreCase(getString(R.string.chi_ocbc))) && getOCBCTV.getCurrentTextColor() == 0xff000000) {
                getOCBCTV.setTextColor(Color.RED);
                return true;
            } else if ((getTVValue.equalsIgnoreCase(getString(R.string.eng_uob)) || getTVValue.equalsIgnoreCase(getString(R.string.chi_uob))) && getUOBTV.getCurrentTextColor() == 0xff000000) {
                getUOBTV.setTextColor(Color.RED);
                Toast.makeText(MainActivity.this, getTVValue, Toast.LENGTH_SHORT).show();
                return true;
            } else {
                if ((getTVValue.equalsIgnoreCase(getString(R.string.eng_dbs)) || getTVValue.equalsIgnoreCase(getString(R.string.chi_dbs))) && getDBSTV.getCurrentTextColor() == 0xffff0000) { //0xffff0000
                    //Toast.makeText(MainActivity.this, String.format("%d", getDBSTV.getCurrentTextColor()),Toast.LENGTH_SHORT).show();
                    getDBSTV.setTextColor(Color.BLACK);
                    return true;
                } else if ((getTVValue.equalsIgnoreCase(getString(R.string.eng_ocbc)) || getTVValue.equalsIgnoreCase(getString(R.string.chi_ocbc))) && getOCBCTV.getCurrentTextColor() == 0xffff0000) {
                    getOCBCTV.setTextColor(Color.BLACK);
                    return true;
                } else if ((getTVValue.equalsIgnoreCase(getString(R.string.eng_uob)) || getTVValue.equalsIgnoreCase(getString(R.string.chi_uob))) && getUOBTV.getCurrentTextColor() == 0xffff0000) {
                    getUOBTV.setTextColor(Color.BLACK);
                    return true;
                }
            }
        }
            return super.onContextItemSelected(item);
        }
    }
