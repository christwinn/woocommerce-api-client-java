/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.data.Continent;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.data.Country;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.data.Currency;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class DataBuilder {


    //<editor-fold defaultstate="collapsed" desc="Reader Continent">
    public static class ReadContinent extends CoreReader.ReaderCoreStringKey<Continent>{

        public ReadContinent(String continentCode){
            super(continentCode);
        }

        public Read<Continent> getResponse(){
            return super.getResponse(DATA_CONTINENTS, new TypeReference<Continent>() {});
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Reader Country">
    public static class ReadCountry extends CoreReader.ReaderCoreStringKey<Country>{

        public ReadCountry(String countryCode){
            super(countryCode);
        }

        public Read<Country> getResponse(){
            return super.getResponse(DATA_COUNTRIES, new TypeReference<Country>() {});
        }

    }
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="Reader Currency">
    public static class ReadCurrency extends CoreReader.ReaderCoreStringKey<Currency>{

        public ReadCurrency(String currencyCode){
            super(currencyCode);
        }

        public Read<Currency> getResponse(){
            return super.getResponse(DATA_CURRENCIES, new TypeReference<Currency>() {});
        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Continents Listing Builder">
    /**
     *
     * List All Continents
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/#list-all-continents">https://woocommerce.github.io/woocommerce-rest-api-docs/#list-all-continents</a>
     *
     * @param <T>
     */
    public static class ListAllContinents<T> extends CoreList<Continent, T>{

        public ListAllContinents(){}

        public Listed<Continent> getResponse(){

            return super.getResponse(
                DATA_CONTINENTS,
                "",
                new TypeReference<List<Continent>>() {}
            );

        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Countries Listing Builder">
    /**
     *
     * List All Countries
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/#list-all-countries">https://woocommerce.github.io/woocommerce-rest-api-docs/#list-all-countries</a>
     *
     * @param <T>
     */
    public static class ListAllCountries<T> extends CoreList<Country, T>{

        public ListAllCountries(){}

        public Listed<Country> getResponse(){

            return super.getResponse(
                DATA_COUNTRIES,
                "",
                new TypeReference<List<Country>>() {}
            );

        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Currencies Listing Builder">
    /**
     *
     * List All Currencies
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/#list-all-currencies">https://woocommerce.github.io/woocommerce-rest-api-docs/#list-all-currencies</a>
     *
     * @param <T>
     */
    public static class ListAllCurrencies<T> extends CoreList<Currency, T>{

        public ListAllCurrencies(){}

        public Listed<Currency> getResponse(){

            return super.getResponse(
                DATA_CURRENCIES,
                "",
                new TypeReference<List<Currency>>() {}
            );

        }

    }
    //</editor-fold>

}
