package fr.meallier.documentstorage.domain.core;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public record Metadata(String key, String values, ZonedDateTime creationDate) {

    public static String separator = " ";
    public Metadata(String key, String values) {
        this(key,values,ZonedDateTime.now(ZoneId.systemDefault()));
    }

    public static Metadata build(String key, String values) {
        return new Metadata(key,values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(key, metadata.key) && Objects.equals(values, metadata.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, values);
    }
}
