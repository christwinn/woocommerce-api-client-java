/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.core.deserialisers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import uk.co.twinn.pl_wtx_woocommerce.model.Link;
import uk.co.twinn.pl_wtx_woocommerce.model.MappedSetting;

import java.io.IOException;
import java.util.*;

public class JsonMappedLinks  extends StdDeserializer<HashMap<String, Link>> {

    private final HashMap<String, Link> keys = new LinkedHashMap<>();

    protected JsonMappedLinks(){
        this(null);
    }

    protected JsonMappedLinks(Class<?> vc) {
        super(vc);
    }

    @Override
    public HashMap<String, Link> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        getAllKeysUsingJsonNodeFields(node, true, "");

        return keys;

    }

    private void getAllKeysUsingJsonNodeFields(JsonNode jsonNode, boolean isRoot, String root) {

        if (jsonNode.isObject()) {

            Set<Map.Entry<String, JsonNode>> fields = jsonNode.properties();
            fields.iterator().forEachRemaining(field -> {
                if (isRoot) {
                    keys.put(field.getKey(), new Link());
                    getAllKeysUsingJsonNodeFields((JsonNode) field.getValue(), false, field.getKey());
                }else {
                    switch (field.getKey()){
                        case "href":        keys.get(root).setHref(field.getValue().toString()); break;
                        //there is more to get
                        default:            break;
                    }
                    //
                }
            });

        } else if (jsonNode.isArray()) {

            ArrayNode arrayField = (ArrayNode) jsonNode;
            arrayField.forEach(node -> {
                getAllKeysUsingJsonNodeFields(node, false, root);
            });

        }
    }


}
