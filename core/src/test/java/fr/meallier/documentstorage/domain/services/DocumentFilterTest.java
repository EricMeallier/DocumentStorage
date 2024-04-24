package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.core.Metadata;
import fr.meallier.documentstorage.domain.services.filter.DocumentSizeFilter;
import fr.meallier.documentstorage.domain.services.filter.MetadataFilter;
import fr.meallier.documentstorage.domain.services.filter.MetadataProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentFilterTest {

    @Test
    void DocumentSizeFilterTest() {
        byte[] myData = UUID.randomUUID().toString().getBytes();

        List<MetadataFilter> filters = Arrays.asList( new MetadataFilter[] {
                new DocumentSizeFilter()
        });

        MetadataProcessor processor = new MetadataProcessor(filters);

        Map<String, Metadata> metadatas = processor.applyFilters(myData);

        assertNotNull(metadatas);
        assertEquals(1,metadatas.size());
        assertEquals(36,Integer.valueOf(metadatas.get(DocumentSizeFilter.KEY).values()));
    }

}