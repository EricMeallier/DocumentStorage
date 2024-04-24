package fr.meallier.documentstorage.domain.services.filter;

import fr.meallier.documentstorage.domain.core.Metadata;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("DocumentSizeFilter")
public class DocumentSizeFilter implements MetadataFilter{

    public static String KEY = "DocumentSize";
    @Override
    public void doFilter(byte [] data, Map<String, Metadata> metadatas) {
        metadatas.put(KEY, new Metadata(KEY,String.valueOf(data.length)));
    }
}
