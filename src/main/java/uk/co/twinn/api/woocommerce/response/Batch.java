/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Batch<T> {

    private List<T> create = new ArrayList<>();
    private List<T> update = new ArrayList<>();
    private List<T> delete = new ArrayList<>();

    @JsonIgnore
    public boolean isEmpty(){

        return create.isEmpty() && update.isEmpty() && delete.isEmpty();

    }

    @JsonIgnore
    public int getRecordCount(){

        return create.size() + update.size() + delete.size();

    }

    public Batch(){

    }

    public void setCreate(List<T> create){

        this.create = create;

    }

    public void setUpdate(List<T> update){

        this.update = update;
    }

    public void setDelete(List<T> delete){

        this.delete = delete;

    }

    @JsonProperty("create")
    public List<T> getCreated(){
        return create;
    }
    @JsonProperty("update")
    public List<T> getUpdated(){
        return update;
    }
    @JsonProperty("delete")
    public List<T> getDeleted(){
        return delete;
    }

    public void addCreate(T create){
        this.create.add(create);
    }

    public void addUpdate(T update){
        this.update.add(update);
    }

    public void addDelete(T delete){
        this.delete.add(delete);
    }

}
