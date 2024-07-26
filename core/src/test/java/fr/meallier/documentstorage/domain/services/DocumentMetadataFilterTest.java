package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.core.Metadata;
import fr.meallier.documentstorage.domain.services.metadata.filter.DocumentSizeFilter;
import fr.meallier.documentstorage.domain.services.metadata.filtering.MetadataProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DocumentMetadataFilterTest {

    @Autowired
    MetadataProcessor metadataProcessor;

    @Test
    void DocumentSizeFilterTest() {
        byte[] myData = UUID.randomUUID().toString().getBytes();

        Map<String, Metadata> metadatas = metadataProcessor.applyFilters(myData);

        assertNotNull(metadatas);
        assertEquals(2, metadatas.size());
        assertEquals(36, Integer.valueOf(metadatas.get(DocumentSizeFilter.KEY).values()));
    }

}