package ru.adventurers.pildarok;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import ru.adventurers.pildarok.Utils.DialogUtils;
import ru.adventurers.pildarok.base.BaseActivity;
import ru.adventurers.pildarok.listeners.OnDialogOk;
import ru.adventurers.pildarok.models.Error;
import ru.adventurers.pildarok.models.Ticket;

public class TicketCardActivity extends BaseActivity implements TicketCardMVPView {
    ActionBar actionBar;
    String code;
    @Inject
    TicketPresenter presenter;
    TextView text_name, text_code,text_email, text_phone;
    Button button_enter;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_ticket_card);

        actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent=getIntent();
        code=intent.getStringExtra("code");
        text_name=(TextView)findViewById(R.id.text_name);
        text_code=(TextView)findViewById(R.id.text_code);
        text_phone=(TextView)findViewById(R.id.text_phone);
        text_email=(TextView)findViewById(R.id.text_email) ;
        button_enter=(Button) findViewById(R.id.button_enter);
        imageView=(ImageView)findViewById(R.id.image_code);

        presenter.attachView(this);
        presenter.getTicket(code);
    }

    @Override
    public void onError(Error error) {

    }

    @Override
    public void onProgress() {

    }
    public void openPhoneActivity(String phone ){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phone));
        startActivity(intent);
    }
    @Override
    public void onSuccess(final Ticket ticket) {
        if(ticket!=null){
            text_name.setText(ticket.getName());
            text_code.setText(ticket.getSha256());
            text_email.setText(ticket.getEmail());
            text_phone.setText(ticket.getPhone());
            text_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openPhoneActivity(ticket.getPhone());
                }
            });
            imageView.setImageDrawable(getResources().getDrawable(ticket.getCategoryImage()));
            if(ticket.isEnter()){
                //imageView.setVisibility(View.INVISIBLE);
                button_enter.setEnabled(false);
                button_enter.setText("Вход выполнен");
                button_enter.setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));
            }else{
                button_enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       presenter.setTicket(ticket);

                    }
                });
            }
        }else{
            OnDialogOk listener=new OnDialogOk() {
                @Override
                public void onOk() {
                    finish();
                }
            };
            DialogUtils.showAlertDialog(TicketCardActivity.this, "Билет не найден", listener);
        }

    }

    @Override
    public void onSuccessQuery(List<Ticket> list) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){ //кнопка назад
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_save: //кнопка сохранить


                break;
        }
        return true;
    }
}
