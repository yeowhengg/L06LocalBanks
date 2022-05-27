package sg.edu.rp.c346.id20042741.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView getDBSTV;
    TextView getOCBCTV;
    TextView getUOBTV;
    String getTVValue;

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
            Intent changeLang = new Intent(MainActivity.this, ChangeLangActivity.class);
            changeLang.putExtra("dbslang","星展银行");
            changeLang.putExtra("ocbclang","华侨银行");
            changeLang.putExtra("uoblang","大华银行");
            finish();
            startActivity(changeLang);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().equals("Website")){
            if(getDBSTV.is)

        }
        return super.onContextItemSelected(item);
    }
}