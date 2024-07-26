package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.services.data.filtering.InputDataProcessor;
import fr.meallier.documentstorage.domain.services.data.filtering.OutputDataProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DocumentDataFilterTest {

    @Autowired
    InputDataProcessor inputDataProcessor;

    @Autowired
    OutputDataProcessor outputDataProcessor;

    @Test
    void InputDataFilterTest() {
        byte[] myData = UUID.randomUUID().toString().getBytes();

        byte[] filteredData = inputDataProcessor.applyFilters(myData);

        assertEquals(myData.length-2,filteredData.length);
    }
    @Test
    void OutputDataFilterTest() {
        byte[] myData = UUID.randomUUID().toString().getBytes();

        byte[] filteredData = outputDataProcessor.applyFilters(myData);

        assertEquals(myData.length-2,filteredData.length);
    }

}