package com.example.medicalcenterapp.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalcenterapp.R;
import com.example.medicalcenterapp.adapters.SymptomsAdapter;
import com.example.medicalcenterapp.models.SymptomsModal;
import com.example.medicalcenterapp.utilities.RecyclerItemTouchHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class SymptomsCheckActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private ArrayList<SymptomsModal> symptomsList = new ArrayList<>();
    private SymptomsModal symptomsModal;
    private SymptomsAdapter symptomsAdapter;
    private CoordinatorLayout coordinatorLayout;
    private ArrayList<String> sympList = new ArrayList<>();
    Button submit;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_check);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Symptom Selection");

                actionBar.setDefaultDisplayHomeAsUpEnabled(true);

        }

        RecyclerView recyclerViewSymp = findViewById(R.id.recyclerViewSimptom);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        submit = findViewById(R.id.submitSymptoms);
        Button noSymptom = findViewById(R.id.noSymptomsFound);
        submit.setAlpha(0.5f);
        submit.setClickable(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSymp.setLayoutManager(layoutManager);
        recyclerViewSymp.setHasFixedSize(true);
        recyclerViewSymp.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Set<String> sympSet = new TreeSet<>();
        sympSet.addAll(Arrays.asList(SymptomsModal.CANCER));
        sympSet.addAll(Arrays.asList(SymptomsModal.DIABETES));
        sympSet.addAll(Arrays.asList(SymptomsModal.HIV));
        sympSet.addAll(Arrays.asList(SymptomsModal.KIDNEY));
        sympSet.addAll(Arrays.asList(SymptomsModal.OBESITY));
        sympSet.addAll(Arrays.asList(SymptomsModal.EPILEPSY));
        sympSet.addAll(Arrays.asList(SymptomsModal.TYPHOID));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerViewSymp);

       for(String sets: sympSet){
           symptomsList.add(new SymptomsModal(sets));
       }
       symptomsAdapter = new SymptomsAdapter(this, symptomsList);
       recyclerViewSymp.setAdapter(symptomsAdapter);

       noSymptom.setOnClickListener((v)->{
          startActivity(new Intent(this, RegretsActivity.class)
                  .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
       });

    }
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof SymptomsAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name =SymptomsAdapter.sympFilter.get(viewHolder.getAdapterPosition()).getSymptomTitle();
           // String sport = teamAdapter.extra;


            // backup of removed item for undo purpose
            final SymptomsModal selectedItem = symptomsList.get(viewHolder.getAdapterPosition());
            final int selectedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            symptomsAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " selected symptom!", Snackbar.LENGTH_LONG);

            snackbar.setAction("UNDO", view -> {

                // undo is selected, restore the deleted item
                symptomsAdapter.restoreItem(selectedItem, selectedIndex);
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();

            snackbar.addCallback(new Snackbar.Callback() {

                @Override
                public void onDismissed(Snackbar snackbar, int event) {


                    if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {


                       sympList.add(name);
                       if (4 < sympList.size()){
                           submit.setClickable(true);
                           submit.setAlpha(1);
                           submit.setOnClickListener((v)->{
                               //send data to class that will calculate the weight and give an accurate disease
                               Intent intent = new Intent(SymptomsCheckActivity.this, ResponseActivity.class);
                               intent.putStringArrayListExtra("symptoms",sympList);
                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               startActivity(intent);
                           });

                       }

                    }

                }


            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.symptom_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                symptomsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                symptomsAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
