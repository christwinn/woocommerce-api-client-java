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
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.TaxRate;
import uk.co.twinn.pl_wtx_woocommerce.model.ISO3166;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.TAXES;

public class TaxRateBuilder extends ApiRequest {

    protected final TaxRate taxRate = new TaxRate();

    private boolean force;

    public TaxRateBuilder(){}

    private TaxRateBuilder(Creator<?> creator){

        taxRate.setCountry(creator.country);
        taxRate.setState(creator.state);

        taxRate.setPostcodes(creator.postcodes);
        taxRate.setCities(creator.cities);
        taxRate.setRate(creator.rate);
        taxRate.setName(creator.name);
        taxRate.setPriority(creator.priority);
        taxRate.setCompound(creator.compound);
        taxRate.setShipping(creator.shipping);
        taxRate.setOrder(creator.order);
        taxRate.setTaxClass(creator.taxClass);

    }

    private TaxRateBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        taxRate.setId(updater.id);

    }

    private TaxRateBuilder(Deleter<?> deleter){

        taxRate.setId(deleter.id);
        force = deleter.force;

    }

    public String endPoint(){

        return TAXES +
            (taxRate.getId() != null && taxRate.getId() != 0
                ? ("/" + taxRate.getId())
                : ""
            );

    }

    public String toJson(){

        try {

            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(taxRate);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        private ISO3166.CountryEnum country; //	string	Country ISO 3166 code. See ISO 3166 Codes (Countries) for more details
        private String state; //	string	State code.
        //postcode	string	Postcode/ZIP, it doesn't support multiple values. Deprecated as of WooCommerce 5.3, postcodes should be used instead.
        //city	string	City name, it doesn't support multiple values. Deprecated as of WooCommerce 5.3, postcodes should be used instead.
        private List<String> postcodes; //	string[]	Postcodes/ZIPs. Introduced in WooCommerce 5.3.
        private List<String> cities; //	string[]	City names. Introduced in WooCommerce 5.3.
        private String rate; //	string	Tax rate.
        private String name; //	string	Tax rate name.
        private Integer priority; //	integer	Tax priority. Only 1 matching rate per priority will be used. To define multiple tax rates for a single area you need to specify a different priority per rate. Default is 1.
        private Boolean compound; //	boolean	Whether or not this is a compound tax rate. Compound rates are applied on top of other tax rates. Default is false.
        private Boolean shipping; //	boolean	Whether or not this tax rate also gets applied to shipping. Default is true.
        private Integer order; //	integer	Indicates the order that will appear in queries.
        private String taxClass; //	string	Tax class. Default is standard.

        T self() {
            return (T) this;
        }

        /**
         *
         * @param country Country ISO 3166 code.
         *                See ISO 3166 Codes (Countries) for more details
         *                <a href="https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes">https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes</a>
         * @return T
         */
        public T setCountry(String country) {
            return setCountry(ISO3166.CountryEnum.fromValue(country));
        }

        public T setCountry(ISO3166.CountryEnum country) {
            this.country = country;
            return self();
        }

        /**
         *
         * @param state State code.
         * @return T
         */
        public T setState(String state) {
            this.state = state;
            return self();
        }

        /**
         *
         * @param postcodes Postcodes/ZIPs.
         *                  Introduced in WooCommerce 5.3.
         * @return T
         */
        public T setPostcodes(List<String> postcodes) {
            this.postcodes = postcodes;
            return self();
        }

        /**
         *
         * @param cities City names.
         *               Introduced in WooCommerce 5.3.
         * @return T
         */
        public T setCities(List<String> cities) {
            this.cities = cities;
            return self();
        }

        /**
         *
         * @param rate Tax rate.
         * @return T
         */
        public T setRate(String rate) {
            this.rate = rate;
            return self();
        }

        /**
         *
         * @param name Tax rate name.
         * @return T
         */
        public T setName(String name) {
            this.name = name;
            return self();
        }

        /**
         *
         * @param priority Tax priority.
         *                 Only 1 matching rate per priority will be used.
         *                 To define multiple tax rates for a single area
         *                 you need to specify a different priority per rate.
         *                 Default is 1.
         * @return T
         */
        public T setPriority(Integer priority) {
            this.priority = priority;
            return self();
        }

        /**
         *
         * @param compound Whether or not this is a compound tax rate.
         *                 Compound rates are applied on top of other tax rates.
         *                 Default is false.
         * @return T
         */
        public T setCompound(Boolean compound) {
            this.compound = compound;
            return self();
        }

        /**
         *
         * @param shipping Whether or not this tax rate also gets applied to shipping.
         *                 Default is true.
         * @return T
         */
        public T setShipping(Boolean shipping) {
            this.shipping = shipping;
            return self();
        }

        /**
         *
         * @param order Indicates the order that will appear in queries.
         * @return T
         */
        public T setOrder(Integer order) {
            this.order = order;
            return self();
        }

        /**
         *
         * @param taxClass Tax class.
         *                 Default is standard.
         * @return T
         */
        public T setTaxClass(String taxClass) {
            this.taxClass = taxClass;
            return self();
        }


        protected TaxRateBuilder build(){
            return new TaxRateBuilder(this);
        }


        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<TaxRate> getResponse(){

            TaxRateBuilder create = build();
            //make the call
            return new Created<>(
                new Rest().create(create.endPoint(), create.toJson(), new TypeReference<TaxRate>(){})
            );

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private final int id;

        public Updater(int taxRateId){
            this.id = taxRateId;
        }

        protected TaxRateBuilder build(){
            return new TaxRateBuilder(this);
        }

        @Override
        public Updated<TaxRate> getResponse(){
            if (id > 0){

                TaxRateBuilder create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<TaxRate>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReader.ReaderCore<T>{

        public Reader(int taxRateId){
            super(taxRateId);
        }

        public Read<TaxRate> getResponse(){
            return (Read<TaxRate>)super.getResponse(TAXES, new TypeReference<TaxRate>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleter.DeleterCore<T>{

        public Deleter(int taxRateId, boolean force){
            super(taxRateId, force);
        }

        protected TaxRateBuilder build(){
            return new TaxRateBuilder(this);
        }

        public Deleted<TaxRate> getResponse(){
            return (Deleted<TaxRate>)super.getResponse(TAXES, new TypeReference<TaxRate>() {});

        }

    }

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<T>{

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().taxRate);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().taxRate);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().taxRate);
            return self();
        }

        public Batched<TaxRate> getResponse(){

            return (Batched<TaxRate>) super.getResponse(TAXES, batch, new TypeReference<Batch<TaxRate>>(){});

        }

    }

    public static class ListAll<T extends ListAll<T>> extends Seek.SearchCorePaging<T>{

        T self() {
            return (T) this;
        }

        /**
         *
         * @param taxClass Retrieve only tax rates of this Tax class.
         * @return T
         */
        public T setClass(String taxClass){
            addNameValuePair("class", taxClass);
            return self();
        }

        public T setOrderby(String orderby) {
            addNameValuePair("orderby", orderby);
            return self();
        }

        public T setOffset(int offset) {
            addNameValuePair("offset", offset);
            return self();
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Listed<TaxRate> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    TAXES,
                    build(),
                    new TypeReference<List<TaxRate>>(){}
                )
            );


        }

    }

}
