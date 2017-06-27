package ru.adventurers.pildarok;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.adventurers.pildarok.Utils.DialogUtils;
import ru.adventurers.pildarok.base.BaseActivity;
import ru.adventurers.pildarok.listeners.OnDialogOkCancel;
import ru.adventurers.pildarok.models.Error;
import ru.adventurers.pildarok.models.Ticket;

public class TicketsListActivity extends BaseActivity implements TicketListMVPView{
    ItemAdapter itemAdapter;
    ActionBar actionBar;
    List<Ticket> ticketList;
    @Inject
    TicketListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setContentView(R.layout.activity_tickets_list);

        presenter.attachView(this);

        actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setTitle(title);

        }

        Button scanButton=(Button)findViewById(R.id.button_scan);
        ListView listView=(ListView)findViewById(R.id.listView);
        RelativeLayout empty_view=(RelativeLayout)findViewById(R.id.empty_view);
         final SearchView searchView=(SearchView) findViewById(R.id.searchView);
         final TextView text_empty=(TextView) findViewById(R.id.text_empty);
        ticketList =new ArrayList<>();
        itemAdapter=new ItemAdapter(this);
        listView.setAdapter(itemAdapter);
        listView.setEmptyView(empty_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openGood(ticketList.get(position).getSha256());
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(TicketsListActivity.this)
                        .setCaptureActivity(AnyOrientationCaptureActivity.class)
                        .initiateScan();
            }
        });

        presenter.getTickets();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length()>2) {
                    text_empty.setText(null);
                    text_empty.setVisibility(View.INVISIBLE);
                    presenter.getTickets(query);
                }else{
                    //recyclerView.setVisibility(View.GONE);
                    text_empty.setVisibility(View.VISIBLE);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query.length()>2) {
                    text_empty.setVisibility(View.INVISIBLE);
                    presenter.getTickets(query);
                    //recyclerView.setVisibility(View.VISIBLE);
                }else{
                    //recyclerView.setVisibility(View.GONE);
                    text_empty.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        /*ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(searchView!=null){
                    EditText et = (EditText) searchView.findViewById(R.id.search_src_text);

                    //Clear the text from EditText view
                    et.setText("");
                    //Clear query
                    searchView.setQuery("", false);
                    //Collapse the action view
                    searchView.onActionViewCollapsed();
                    //Collapse the search widget
                   presenter.getTickets();
                }

            }
        });*/
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                presenter.getTickets();
                return false;
            }
        });
    }

    @Override
    public void onError(Error error) {

    }

    @Override
    public void onProgress() {

    }

    @Override
    public void onSuccess(List<Ticket> list) {
        ticketList=list;
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessQuery(List<Ticket> list) {

    }

    private void openGood(String code){
        Intent intent=new Intent(this,TicketCardActivity.class);
        intent.putExtra("code",code);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.save, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){ //кнопка назад
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_save: //кнопка сохранить
                OnDialogOkCancel onDialogOkCancel=new OnDialogOkCancel() {
                    @Override
                    public void onOk() {
                        finish();
                    }

                    @Override
                    public void onCancel() {

                    }
                };
                DialogUtils.showAlertDialog2(TicketsListActivity.this,"Подтвердите действие",onDialogOkCancel);

                break;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("TicketsListActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

            } else {
                Log.d("TicketsListActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                openGood(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private class ItemAdapter extends BaseAdapter {
        Context context;
        public ItemAdapter(Context context){
            this.context=context;
        }
        @Override
        public int getCount() {
            return ticketList.size();
        }

        @Override
        public Object getItem(int position) {
            return ticketList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_item, null);
            final Ticket item= ticketList.get(position);
            TextView text_caption=(TextView)convertView.findViewById(R.id.text_name);
            TextView text_code=(TextView) convertView.findViewById(R.id.text_code);
            ImageView icon_enter=(ImageView) convertView.findViewById(R.id.icon_enter);

            text_caption.setText(item.getName());
            text_code.setText(item.getPhone());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGood(item.getSha256());
                }
            });
            if(item.isEnter()){
                icon_enter.setVisibility(View.VISIBLE);
            }else{
                icon_enter.setVisibility(View.INVISIBLE);
            }
            return convertView;
        }
    }
}
