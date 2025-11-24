package fr.meallier.documentstorage.domain.core.metadata.filtering;

import fr.meallier.documentstorage.domain.core.metadata.filtering.MetadataFilter;
import fr.meallier.documentstorage.domain.model.Metadata;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component("DocumentCreationDateFilter")
public class DocumentCreationDateFilter implements MetadataFilter {

    public static String KEY = "DocumentCreationDate";
    @Override
    public void doFilter(byte [] data, Map<String, Metadata> metadatas) {
        metadatas.put(KEY, new Metadata(KEY, ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_DATE_TIME)));
    }
}
