/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.TaxClass;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.TAX_CLASSES;

public class TaxClassBuilder extends ApiRequest {

    protected final TaxClass taxClass = new TaxClass();

    private boolean force;

    public TaxClassBuilder(){}

    private TaxClassBuilder(Creator<?> creator){

        taxClass.setName(creator.name);

    }

    public String endPoint(){

        return TAX_CLASSES +
            (taxClass.getSlug() != null && !taxClass.getSlug().isEmpty()
                ? ("/" + taxClass.getSlug())
                : ""
            );

    }

    public String toJson(){

        try {

            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(taxClass);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        private final String name; //	string	Tax rate name.

        public Creator(String name){
            this.name = name;
        }

        protected TaxClassBuilder build(){
            return new TaxClassBuilder(this);
        }

        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<TaxClass> getResponse(){

            TaxClassBuilder create = build();
            //make the call
            return new Created<>(
                new Rest().create(create.endPoint(), create.toJson(), new TypeReference<TaxClass>(){})
            );

        }

    }

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleter.DeleterCoreStringKey<T>{

        public Deleter(String taxClassSlug, boolean force){
            super(taxClassSlug, force);
        }

        @SuppressWarnings("unchecked")
        public Deleted<TaxClass> getResponse(){
            return (Deleted<TaxClass>)super.getResponse(TAX_CLASSES, new TypeReference<TaxClass>() {});

        }

    }

    public static class ListAll<T extends ListAll<T>>{

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Listed<TaxClass> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    TAX_CLASSES,
                    "",
                    new TypeReference<List<TaxClass>>(){}
                )
            );


        }

    }
}
