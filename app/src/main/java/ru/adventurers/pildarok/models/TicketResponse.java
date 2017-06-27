package ru.adventurers.pildarok.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Дмитрий on 09.02.2016.
 */
public class TicketResponse  {
    @Expose
    Data data;

    public TicketResponse() {
    }
    public List<Ticket> getTickets(){
        return data.getTickets();
    }
    public class Data {
        @Expose
        List<Ticket> objects;
        public Data(){
            objects=new ArrayList<>();
        }

        public Data(List<Ticket> objects) {
            if(objects!=null){
                this.objects = objects;
            }else{
                this.objects=new ArrayList<>();
            }

        }

        public List<Ticket> getTickets() {
            return objects;
        }
    }

}
