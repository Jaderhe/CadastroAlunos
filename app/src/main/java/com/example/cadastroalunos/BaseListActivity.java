package com.example.cadastroalunos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.util.Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseListActivity extends AppCompatActivity {

    private final int activityListXmlId;
    private final int titleXmlId;
    private RecyclerView.Adapter adapter;
    private final int recyclerViewListXmlId;
    private RecyclerView recyclerView;
    private LinearLayout linearList;
    private final int linearListXmlId;
    private final String modelName;
    private final Class<? extends BaseActivity> newModelActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(titleXmlId);
        setContentView(activityListXmlId);
        linearList = findViewById(linearListXmlId);

        fillList();
    }

    abstract RecyclerView.Adapter createAdapter();


    public void fillList() {
        recyclerView = findViewById(recyclerViewListXmlId);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = createAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_add:
                openCreateForm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openCreateForm() {
        Intent intent = new Intent(this, newModelActivity);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(linearList, modelName + " salvo com sucesso!", 1);
        }
        fillList();
    }
}
