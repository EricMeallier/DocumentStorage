package fr.meallier.documentstorage.domain.services.filter;

import fr.meallier.documentstorage.domain.core.Metadata;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component("DocumentCreationDateFilter")
public class DocumentCreationDateFilter implements MetadataFilter{

    public static String KEY = "DocumentCreationDate";
    @Override
    public void doFilter(byte [] data, Map<String, Metadata> metadatas) {
        metadatas.put(KEY, new Metadata(KEY, ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_DATE_TIME)));
    }
}
