package pl.wtx.woocommerce.api.client.model;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import pl.wtx.woocommerce.api.client.invoker.JSON;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Link {

    public static final String SERIALIZED_NAME_HREF = "href";
    @SerializedName(SERIALIZED_NAME_HREF)
    @javax.annotation.Nullable
    private String href;

    public Link(){}

    public Link href(@javax.annotation.Nullable String href) {
        this.href = href;
        return this;
    }

    /**
     * id.
     * @return id
     */
    @javax.annotation.Nullable
    public String getHref() {
        return href;
    }

    public void setHref(@javax.annotation.Nullable String href) {
        this.href = href;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Link link = (Link) o;
        return Objects.equals(this.href, link.href) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(href);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("class Link {\n")
            .append("    href: ").append(toIndentedString(href))
            .append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     *
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }


    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    static {
        // a set of all properties/fields (JSON key names)
        openapiFields = new HashSet<String>();
        openapiFields.add("href");

        // a set of required properties/fields (JSON key names)
        openapiRequiredFields = new HashSet<String>();
    }

    /**
     * Validates the JSON Element and throws an exception if issues found
     *
     * @param jsonElement JSON Element
     * @throws IOException if the JSON Element is invalid with respect to Billing
     */
    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
        if (jsonElement == null) {
            if (!Link.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
                throw new IllegalArgumentException(String.format("The required field(s) %s in Link is not found in the empty JSON string", Link.openapiRequiredFields.toString()));
            }
        }

        System.out.println(jsonElement.getAsJsonArray());

        // jsonElement.getAsJsonArray().getAsJsonObject()
        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonArray().getAsJsonObject().entrySet();
        // check to see if the JSON string contains additional fields
        for (Map.Entry<String, JsonElement> entry : entries) {
            if (!Brand.openapiFields.contains(entry.getKey())) {
                throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Link` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
            }
        }

        JsonObject jsonObj = jsonElement.getAsJsonObject();
        for (Map.Entry entry : jsonObj.entrySet()){
            String key = (String)entry.getKey();
            if ((jsonObj.get(key) != null && !jsonObj.get(key).isJsonNull()) && !jsonObj.get(key).isJsonPrimitive()) {
                throw new IllegalArgumentException(
                    String.format("Expected the field `%s` to be a primitive type in the JSON string but got `%s`", key, jsonObj.get(key).toString()));
            }
        }

    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!Link.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'Billing' and its subtypes
            }
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<Link> thisAdapter
                = gson.getDelegateAdapter(this, TypeToken.get(Link.class));

            return (TypeAdapter<T>) new TypeAdapter<Link>() {
                @Override
                public void write(JsonWriter out, Link value) throws IOException {
                    JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
                    elementAdapter.write(out, obj);
                }

                @Override
                public Link read(JsonReader in) throws IOException {
                    JsonElement jsonElement = elementAdapter.read(in);
                    validateJsonElement(jsonElement);
                    return thisAdapter.fromJsonTree(jsonElement);
                }

            }.nullSafe();
        }
    }

    /**
     * Create an instance of Brand given a JSON string
     *
     * @param jsonString JSON string
     * @return An instance of Billing
     * @throws IOException if the JSON string is invalid with respect to Billing
     */
    public static Link fromJson(String jsonString) throws IOException {
        return JSON.getGson().fromJson(jsonString, Link.class);
    }

    /**
     * Convert an instance of Billing to an JSON string
     *
     * @return JSON string
     */
    public String toJson() {
        return JSON.getGson().toJson(this);
    }

}
