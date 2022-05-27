package sg.edu.rp.c346.id20042741.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ChangeLangActivity extends AppCompatActivity {

    TextView getDBSTV;
    TextView getOCBCTV;
    TextView getUOBTV;

    String langDBS;
    String langOCBC;
    String langUOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_lang);

        getDBSTV = findViewById(R.id.tvDBS);
        getOCBCTV = findViewById(R.id.tvOCBC);
        getUOBTV = findViewById(R.id.tvUOB);

        Intent getValues = getIntent();
        langDBS = getValues.getStringExtra("dbslang");
        langOCBC = getValues.getStringExtra("ocbclang");
        langUOB = getValues.getStringExtra("uoblang");

        getDBSTV.setText(langDBS);
        getOCBCTV.setText(langOCBC);
        getUOBTV.setText(langUOB);

        registerForContextMenu(getDBSTV);
        registerForContextMenu(getOCBCTV);
        registerForContextMenu(getUOBTV);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.setGroupVisible(R.id.langGroup,false);
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
        if(id == R.id.engLang){
            Intent changeLang = new Intent(ChangeLangActivity.this, MainActivity.class);
            changeLang.putExtra("dbslang","DBS");
            changeLang.putExtra("ocbclang","OCBC");
            changeLang.putExtra("uoblang","UOB");
            finish();
            startActivity(changeLang);
        }
        return super.onOptionsItemSelected(item);
    }
}