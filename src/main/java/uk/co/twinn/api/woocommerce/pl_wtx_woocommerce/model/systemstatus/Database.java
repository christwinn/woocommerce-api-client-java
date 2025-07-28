/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.systemstatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Database {

    private String wcDatabaseVersion; //	string	WC database version.read-only
    private String databasePrefix; //	string	Database prefix.read-only
    private String maxmindGeoIpDatabase; //	string	MaxMind GeoIP database.read-only
    //private HashMap<String, HashMap<String, DatabaseTable>> databaseTables; //	array	Database tables.read-only
    private DatabaseTables databaseTables;

    private DatabaseSize databaseSize; //undocumented

    public Database(){}

    @JsonProperty("wc_database_version")
    public String getWcDatabaseVersion() {
        return wcDatabaseVersion;
    }

    @JsonProperty("wc_database_version")
    public void setWcDatabaseVersion(String wcDatabaseVersion) {
        this.wcDatabaseVersion = wcDatabaseVersion;
    }

    @JsonProperty("database_prefix")
    public String getDatabasePrefix() {
        return databasePrefix;
    }

    @JsonProperty("database_prefix")
    public void setDatabasePrefix(String databasePrefix) {
        this.databasePrefix = databasePrefix;
    }

    @JsonProperty("maxmind_geoip_database")
    public String getMaxmindGeoIpDatabase() {
        return maxmindGeoIpDatabase;
    }

    @JsonProperty("maxmind_geoip_database")
    public void setMaxmindGeoIpDatabase(String maxmindGeoIpDatabase) {
        this.maxmindGeoIpDatabase = maxmindGeoIpDatabase;
    }

    /*@JsonProperty("database_tables")
    public HashMap<String, HashMap<String, DatabaseTable>> getDatabaseTables() {
        return databaseTables;
    }

    @JsonProperty("database_tables")
    public void setDatabaseTables(HashMap<String, HashMap<String, DatabaseTable>> databaseTables) {
        this.databaseTables = databaseTables;
    }*/

    @JsonProperty("database_tables")
    public DatabaseTables getDatabaseTables() {
        return databaseTables;
    }

    @JsonProperty("database_tables")
    public void setDatabaseTables(DatabaseTables databaseTables) {
        this.databaseTables = databaseTables;
    }

    @JsonProperty("database_size")
    public DatabaseSize getDatabaseSize() {
        return databaseSize;
    }

    @JsonProperty("database_size")
    public void setDatabaseSize(DatabaseSize databaseSize) {
        this.databaseSize = databaseSize;
    }
}
