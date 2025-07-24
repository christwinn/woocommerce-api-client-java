/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.DataBuilder;
import uk.co.twinn.api.woocommerce.builders.ReportBuilder;

public class Data {

    protected Data(){}

    public static DataBuilder.ReadContinent readContinent(String continentCode){

        return new DataBuilder.ReadContinent(continentCode);

    }

    public static DataBuilder.ReadCountry readCountry(String countryCode){

        return new DataBuilder.ReadCountry(countryCode);

    }

    public static DataBuilder.ReadCurrency readCurrency(String currencyCode){

        return new DataBuilder.ReadCurrency(currencyCode);

    }

    public static DataBuilder.ListAllContinents<?> listAllContinents(){

        return new DataBuilder.ListAllContinents<>();

    }

    public static DataBuilder.ListAllCountries<?> listAllCountries(){

        return new DataBuilder.ListAllCountries<>();

    }

    public static DataBuilder.ListAllCurrencies<?> listAllCurrencies(){

        return new DataBuilder.ListAllCurrencies<>();

    }

}
