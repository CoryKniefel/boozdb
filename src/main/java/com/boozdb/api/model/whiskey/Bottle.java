package com.boozdb.api.model.whiskey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

@JsonDeserialize(builder = Bottle.Builder.class)
@JsonSerialize(using = Bottle.Builder.class)
public class Bottle
{
    public final String item;
    public final String category;
    public final String subCategory;
    public final String description;
    public final int age;
    public final int id;
    public final double size;
    public final double price;
    public final double proof;

    public Bottle(Builder builder)
    {
        item = builder.item;
        category = builder.category;
        subCategory = builder.subCategory;
        description = builder.description;
        age = builder.age;
        id = builder.id;
        size = builder.size;
        price = builder.price;
        proof = builder.proof;

    }

    public static final class Builder extends JsonSerializer<Bottle> {
        @JsonProperty
        private String item;
        @JsonProperty
        private String category;
        @JsonProperty
        private String subCategory;
        @JsonProperty
        private String description;
        @JsonProperty
        private int age;
        @JsonProperty
        private int id;
        @JsonProperty
        private double size;
        @JsonProperty
        private double price;
        @JsonProperty
        private double proof;

        public Builder() {
        }

        public Builder item(String item) {
            this.item = item;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder subCategory(String subCategory) {
            this.subCategory = subCategory;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder size(double size) {
            this.size = size;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder proof(double proof) {
            this.proof = proof;
            return this;
        }

        @Override
        public void serialize(Bottle bottle, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", bottle.id);
            jsonGenerator.writeStringField("description", bottle.description);
            jsonGenerator.writeStringField("item", bottle.item);
            jsonGenerator.writeStringField("category", bottle.category);
            jsonGenerator.writeStringField("subCategory", bottle.subCategory);
            jsonGenerator.writeNumberField("age", bottle.age);
            jsonGenerator.writeNumberField("size", bottle.size);
            jsonGenerator.writeNumberField("proof", bottle.proof);
            jsonGenerator.writeNumberField("price", bottle.price);
            jsonGenerator.writeEndObject();
        }

        public Bottle build() {
            return new Bottle(this);
        }
    }
}
