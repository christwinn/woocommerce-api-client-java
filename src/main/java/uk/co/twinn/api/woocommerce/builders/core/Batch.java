/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Batch<T> {

    private List<T> create = new ArrayList<>();
    private List<T> update = new ArrayList<>();

    //we go in as a list of integers,
    private List<Integer> delete = new ArrayList<>();

    @JsonIgnore
    public boolean isEmpty(){

        return create.isEmpty() && update.isEmpty() && delete.isEmpty();

    }

    public boolean empty(){

        create.clear();
        update.clear();
        delete.clear();

        return isEmpty();

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

    public void setDelete(List<Integer> delete){

        this.delete = delete;

    }

    public void addCreate(T create){
        this.create.add(create);
    }

    public void addUpdate(T update){
        this.update.add(update);
    }

    public void addDelete(Integer delete){
        this.delete.add(delete);
    }

    public List<T> getCreate(){
        return create;
    }

    public List<T> getUpdate(){
        return update;
    }

    public List<Integer> getDelete(){
        return delete;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
