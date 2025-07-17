/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.core.deserialisers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import uk.co.twinn.pl_wtx_woocommerce.model.PaymentGatewaySetting;
import uk.co.twinn.pl_wtx_woocommerce.model.PaymentGatewaySettings;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://www.baeldung.com/jackson-deserialization">https://www.baeldung.com/jackson-deserialization</a>
 * PaymentGatewaySettings is a list of settings, not defined as a list therefore we need to do some legwork
 **/
public class JsonPaymentGatewaySettings extends StdDeserializer<PaymentGatewaySettings> {

    private final HashMap<String, PaymentGatewaySetting> keys = new LinkedHashMap<>();

    protected JsonPaymentGatewaySettings(){
        this(null);
    }

    protected JsonPaymentGatewaySettings(Class<?> vc) {
        super(vc);
    }

    @Override
    public PaymentGatewaySettings deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        getAllKeysUsingJsonNodeFields(node, true, "");

        return new PaymentGatewaySettings(keys);

    }

    private void getAllKeysUsingJsonNodeFields(JsonNode jsonNode, boolean isRoot, String root) {

        if (jsonNode.isObject()) {

            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            fields.forEachRemaining(field -> {
                if (isRoot) {
                    keys.put(field.getKey(), new PaymentGatewaySetting());
                    getAllKeysUsingJsonNodeFields((JsonNode) field.getValue(), false, field.getKey());
                }else {
                    switch (field.getKey()){
                        case "id":          keys.get(root).setId(field.getValue().toString()); break;
                        case "label":       keys.get(root).setLabel(field.getValue().toString()); break;
                        case "description": keys.get(root).setDescription(field.getValue().toString()); break;
                        case "type":        keys.get(root).setType(field.getValue().toString()); break;
                        //could be string , could be array
                        case "value":       keys.get(root).setValue(
                                                getListOfStrings(field.getValue())
                                            ); break;
                        //could be string , could be array
                        case "default":     keys.get(root).setDefaultValue(
                                                getListOfStrings(field.getValue())
                                            ); break;
                        case "tip":         keys.get(root).setTip(field.getValue().toString()); break;
                        case "placeholder": keys.get(root).setPlaceholder(field.getValue().toString()); break;
                        //list namevaluepair
                        case "options":     getOptionsJsonNodeFields((JsonNode) field.getValue(),false, root); break;
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

    private void getOptionsJsonNodeFields(JsonNode jsonNode, boolean isRoot, String root) {

        if (jsonNode.isObject()) {
            List<NameValuePair> nvp = new ArrayList<>();
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            fields.forEachRemaining(field -> {
                nvp.add(new BasicNameValuePair(field.getKey(), field.getValue().toString()));
            });
            keys.get(root).setOptions(nvp);
        }

    }

    private Object getListOfStrings(JsonNode value){

        if (value.isArray()){
            List<String> strings = new ArrayList<>();
            ArrayNode arrayField = (ArrayNode) value;
            arrayField.forEach(node -> {
                strings.add(node.asText());
            });
            return strings;
        }else {
            return value.asText();
        }
    }

}
