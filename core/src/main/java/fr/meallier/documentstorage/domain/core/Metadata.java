package fr.meallier.documentstorage.domain.core;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public record Metadata(String key, String values, ZonedDateTime creationDate) {
    public Metadata(String key, String values) {
        this(key,values,ZonedDateTime.now(ZoneId.systemDefault()));
    }
}
