package com.project.library.converters.date;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;

public class StringToLocalDateDeserializer extends StdDeserializer<LocalDate> {
    public StringToLocalDateDeserializer() {
        this(null);
    }

    public StringToLocalDateDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

        return LocalDate.parse(p.getText());
    }
}
