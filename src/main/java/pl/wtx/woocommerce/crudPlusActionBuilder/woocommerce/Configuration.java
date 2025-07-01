/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.ParameterCollector;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {

    public static String DEFAULT_LOCATION = "/.woocommerce-api/config.json";
    private static String DEFAULT_API = "/wp-json/wc/v3/";
    private static String website;
    private static String api;
    private static String key;
    private static String secret;

    private static Boolean debug;


    /**
     * Static values
     * so, we set the configuration once, or not at all!
     *
     * RIOT ACT NOTICE
     * with the key and secret great damage can be visited upon your site
     * ALWAYS, ALWAYS ensure it is kept private!
     * NEVER, NEVER share it
     *
     * if we do none it is akin to new Configuration() below, the first call reads the file
     *
     * -rely upon a file being in the userhome under ~/.woocommerce-api/config.json
     * new Configuration();
     *
     * -set the values by literal strings
     * new Configuration("myWebsite", "apiPath", "mykey", "mysecret");
     *
     * -as above but use the preset api of /wp-json/wc/v3/ but then this could change in future releases
     * new Configuration("myWebsite", "mykey", "mysecret");
     *
     * -read a file somewhere else
     * new Configuration("/literal/path/to/a/file/named/config.json");
     *
     * -use a builder to set the values
     * new Configuration().Builder()
     *  .website("myWebsite")
     *  .api("apiPath")
     *  .key("key")
     *  .secret("secret")
     *  .build();
     *
     */
    public Configuration(){

        //fromFile();

    }
    /**
     *  new Configuration("/literal/path/to/a/file/named/config.json")
     *  */
    public Configuration(String pathToFileNamed_configDOTjson){

        fromFile(pathToFileNamed_configDOTjson);

    }

    public Configuration(String website, String key, String secret){

        this(website, DEFAULT_API, key, secret, false);

    }

    public Configuration(String website, String key, String secret, boolean debug){
        this(website, DEFAULT_API, key, secret, debug);
    }

    public Configuration(String website, String api, String key, String secret){
        this(website, DEFAULT_API, key, secret, false);
    }

    public Configuration(String website, String api, String key, String secret, boolean debug){
        Configuration.website = website;
        Configuration.api = api;
        Configuration.key = key;
        Configuration.secret = secret;
        Configuration.debug = debug;
    }
    public Configuration(Builder builder){

        this(builder.website, builder.api, builder.key, builder.secret, builder.debug);

    }

    public static String getWebsite() {
        if (website == null){
            fromFile();
        }
        return website;
    }

    public static String getApi() {
        if (api == null){
            api = DEFAULT_API;
        }
        return api;
    }

    public static String getKey() {
        if (key == null){
            fromFile();
        }
        return key;
    }
    public static String getSecret() {
        if (secret == null){
            fromFile();
        }
        return secret;
    }
    public static boolean isDebug() {
        if (debug == null){
            debug = false;
        }
        return debug;
    }

    public void setWebsite(String website) {

        Configuration.website = website;

    }

    public void setApi(String api) {
        Configuration.api = api;
    }

    public void setKey(String key) {
        Configuration.key = key;
    }

    public void setSecret(String secret) {
        Configuration.secret = secret;
    }

    /* We will look in ~/.woocommerce/config.json */
    private static void fromFile(){
        fromFile(System.getProperty("user.home")  + DEFAULT_LOCATION);
    }
    /* We will look in ~/.woocommerce/config.json */
    private static void fromFile(String pathToConfigDOTjson){

        File f = new File(pathToConfigDOTjson);
        if (f.exists() && f.isFile()){

            InputStream is = null;
            try {
                is = new FileInputStream(f);

                String config = readFromInputStream(is);

                Configuration conf = getObjectMapper().readValue(config, new TypeReference<Configuration>(){});

            }catch (FileNotFoundException fileNotFoundException){
                Logger.getLogger(ParameterCollector.class.getName()).log(Level.SEVERE, pathToConfigDOTjson, fileNotFoundException);
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        Logger.getLogger(ParameterCollector.class.getName()).log(Level.SEVERE, "is.close()", e);
                    }
                }
            }

        }
    }

    /*https://www.baeldung.com/reading-file-in-java*/
    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private static ObjectMapper getObjectMapper(){

        ObjectMapper objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            //.setDateFormat(new RFC3339DateFormat())
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        return objectMapper;

    }



    public static class Builder{

        private String website;
        private String api;
        private String key;
        private String secret;
        private boolean debug;

        /**
         * @param website
         * do not include https://
         * MUST be https: for the authentication to work
         */
        public Builder website(String website){
            this.website = website;
            return this;
        }
        /**
         * @param api
         * the endpoint for the WooCommerce API
         * e.g: /wp-json/wc/v3/
         */
        public Builder api(String api){
            this.api = api;
            return this;
        }

        public Builder key(String key){
            this.key = key;
            return this;
        }
        public Builder secret(String secret){
            this.secret = secret;
            return this;
        }

        public Builder debug(boolean debug){
            this.debug = debug;
            return this;
        }

        public Configuration build(){
            return new Configuration(this);
        }

    }

}
