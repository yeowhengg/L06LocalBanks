package sg.edu.rp.c346.id20042741.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.setGroupVisible(R.id.langGroup,false);
        getTVValue = v == getDBSTV ? "DBS" : v == getOCBCTV ? "OCBC" : v == getUOBTV ? "UOB" : "";
        bank_num = v == getDBSTV ? "18001111111" : v == getOCBCTV ? "18003633333" : v == getUOBTV ? "18002222121" : "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.setGroupVisible(R.id.bankGroup, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.chiLang){
            getDBSTV.setText("星展银行");
            getOCBCTV.setText("华侨银行");
            getUOBTV.setText("大华银行");
        }
        if (id == R.id.engLang) {
            getDBSTV.setText("DBS");
            getOCBCTV.setText("OCBC");
            getUOBTV.setText("UOB");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        String uri = String.format("https://www.%s.com",getTVValue);
        String bank_no = String.format("tel:+%s",bank_num);

        if(item.getTitle().equals("Website")){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }
        else if (item.getTitle().toString().equalsIgnoreCase("Contact the bank")){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(bank_no));
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }
}