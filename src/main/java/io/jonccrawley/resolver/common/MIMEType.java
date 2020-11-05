package io.jonccrawley.resolver.common;

import io.jonccrawley.resolver.serialization.Deserializer;
import io.jonccrawley.resolver.serialization.Serializer;
import io.jonccrawley.resolver.serialization.XMLSerializer;
import io.jonccrawley.resolver.serialization.JSONSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum MIMEType {
    JSON(JSONSerializer.class,JSONSerializer.class,true,true,"application/json", "text/json","*","*/*"),
    XML(XMLSerializer.class,XMLSerializer.class,true,true,"application/xml", "text/xml"),
    FORM(JSONSerializer.class,null,true,false,"application/x-www-form-urlencoded");

    private final Class<? extends Serializer> serializerClass;
    private final Class<? extends Deserializer> deserializerClass;
    private final String[] mimeTypes;
    private final boolean validRequestType;
    private final boolean validResponseType;

    MIMEType(Class<? extends Deserializer> deserializerClass, Class<? extends Serializer> serializerClass, boolean validRequestType, boolean validResponseType, String ... mimeTypes) {
        this.deserializerClass = deserializerClass;
        this.serializerClass = serializerClass;
        this.mimeTypes = mimeTypes;
        this.validRequestType = validRequestType;
        this.validResponseType = validResponseType;
    }

    public static MIMEType getType(String mimeType) {
        for(MIMEType currentMimeType  : MIMEType.class.getEnumConstants()) {
            for(String type : currentMimeType.getMimeTypes()) {
                if (type.equals(mimeType)) {
                    return currentMimeType;
                }
            }
        }

        return null;
    }

    @Override
    public String toString(){
        return mimeTypes[0];
    }

    public Serializer getSerializer() throws IllegalAccessException, InstantiationException {
        return serializerClass != null ?  serializerClass.newInstance() : null;
    }

    public Deserializer getDeserializer() throws IllegalAccessException, InstantiationException {
        return deserializerClass != null ?  deserializerClass.newInstance() : null;
    }

    private String[] getMimeTypes() {
        return mimeTypes;
    }

    public static MIMEType determineMimeTypeForResponse(final String accepts) {
        Map<Double, List<String>> requestedAcceptsTypes = AcceptsTypeProcessor.process(accepts);

        for (Map.Entry<Double, List<String>> entry : requestedAcceptsTypes.entrySet()) {
            List<String> mimeTypes = entry.getValue();
            for (String type : mimeTypes) {
                MIMEType correspondingMimeType = getType(type);
                if (correspondingMimeType != null  && correspondingMimeType.validResponseType) {
                    return correspondingMimeType;
                }
            }
        }
        return null;
    }

    public static List<String> getAggregatedMimeTypesForResponse(){
        List<String> aggregatedMimeTypes = new ArrayList<>();
        for(MIMEType  mimeType : MIMEType.class.getEnumConstants()) {
            if(mimeType.validResponseType) {
                for(String mimeTypeString : mimeType.getMimeTypes()) {
                    aggregatedMimeTypes.add(mimeTypeString);
                }
            }
        }
        return aggregatedMimeTypes;
    }

}
