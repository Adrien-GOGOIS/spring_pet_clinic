package com.petclinic.invoice;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoiceDeserializer extends JsonDeserializer<Invoice> {

    @Override
    public Invoice deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate date = LocalDate.parse(node.get("date").asText(), formatter);
        String ownerName = node.get("owner").get("firstName").asText();
        String visitPurpose = node.get("purpose").asText();
        String petType = node.get("pet").get("type").asText();
        String petName = node.get("pet").get("name").asText();
        return new Invoice(date, visitPurpose, ownerName, petType, petName, 0d);
    }
}
