package com.boozdb.api.model.links;

// todo serialize
public class Link {

    public String key;
    public String value;

    public Link(Builder builder) {
    }

    public static final class Builder {
        public String key;
        public String value;

        private Builder() {
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Link build() {
            return new Link(this);
        }
    }
}
