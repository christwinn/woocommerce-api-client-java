/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import uk.co.twinn.api.woocommerce.builders.CoreParameterCollector;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {

    public static final String DEFAULT_LOCATION = "/.woocommerce-api/config.json";
    private static final String DEFAULT_API = "/wp-json/wc/v3/";
    private static String website;
    private static String api;
    private static String key;
    private static String secret;

    private static Boolean debug;


    /**
     * <p>
     * Static values
     * so, we set the configuration once, or not at all!
     * </p><p>
     * <h2>RIOT ACT NOTICE</h2>
     * <p>with the key and secret great damage can be visited upon your site<br/>
     * ALWAYS, ALWAYS ensure it is kept private!<br/>
     * NEVER, NEVER share it<br/>
     * </p><hr><p>
     * If the Configuration is never called it is the same as new Configuration() below.<br>
     * </p><p>
     * <i>-rely upon a file being in the userhome under ~/.woocommerce-api/config.json</i><br>
     * new Configuration();
     * </p><p>
     * <i>-set the values by literal strings</i><br>
     * new Configuration("myWebsite", "apiPath", "mykey", "mysecret");
     * </p><p>
     * <i>-as above but use the preset api of /wp-json/wc/v3/ but then this could change in future releases</i><br>
     * new Configuration("myWebsite", "mykey", "mysecret");
     * </p><p>
     * <i>-read a file somewhere else</i><br>
     * new Configuration("/literal/path/to/a/file/named/config.json");
     * </p><p>
     * <i>-use a builder to set the values</i><br>
     * new Configuration().Builder()<br>
     *  .website("myWebsite")<br>
     *  .api("apiPath")<br>
     *  .key("key")<br>
     *  .secret("secret")<br>
     *  .build();<br>
     *  </p>
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
    private Configuration(Builder builder){
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
            fromFile();
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

    public void setDebug(boolean debug) {
        Configuration.debug = debug;
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
                Logger.getLogger(CoreParameterCollector.class.getName()).log(Level.SEVERE, pathToConfigDOTjson, fileNotFoundException);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        Logger.getLogger(CoreParameterCollector.class.getName()).log(Level.SEVERE, "is.close()", e);
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

        public void build(){
            new Configuration(this);
        }

    }

}
