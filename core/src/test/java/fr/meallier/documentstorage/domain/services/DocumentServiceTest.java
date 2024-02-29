package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.core.Metadata;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentServiceTest {

    DocumentService documentService;

    @Test
    void storeDataAndRetrieve() {
        byte[] myData = UUID.randomUUID().toString().getBytes();
        UUID documentId = documentService.storeData(myData);

        byte[] retrieveDataData = documentService.getData(documentId);
        assertArrayEquals(myData,retrieveDataData);
    }

    @Test
    void addMetadataAndRetrieve() {
        byte[] myData = UUID.randomUUID().toString().getBytes();
        UUID documentId = documentService.storeData(myData);

        //  add Metadata first time when it's empty
        Metadata metadata = new Metadata("name","NoNameFile");
        Map<String, Metadata> metadatas = new HashMap<>();
        metadatas.put(metadata.key(), metadata);
        documentService.addMetadata(documentId,metadatas);
        Map<String, Metadata> retrievedMD = documentService.getMetadata(documentId);
        assertNotNull(retrievedMD);
        assertEquals(1 , retrievedMD.size());
        assertEquals("NoNameFile",retrievedMD.get("name").values());

        // add Metadata second time and check
        metadata = new Metadata("author","JohnDoe");
        metadatas = new HashMap<>();
        metadatas.put(metadata.key(), metadata);
        documentService.addMetadata(documentId,metadatas);
        retrievedMD = documentService.getMetadata(documentId);
        assertNotNull(retrievedMD);
        assertEquals(2 , retrievedMD.size());
        assertEquals("NoNameFile",retrievedMD.get("name").values());
        assertEquals("JohnDoe",retrievedMD.get("author").values());
    }

    @Test
    void setMetadataAndRetrieve() {
        byte[] myData = UUID.randomUUID().toString().getBytes();
        UUID documentId = documentService.storeData(myData);

        //  set Metadata first time when it's empty
        Metadata metadata = new Metadata("name","NoNameFile");
        Map<String, Metadata> metadatas = new HashMap<>();
        metadatas.put(metadata.key(), metadata);
        documentService.setMetadata(documentId,metadatas);
        Map<String, Metadata> retrievedMD = documentService.getMetadata(documentId);
        assertNotNull(retrievedMD);
        assertEquals(1 , retrievedMD.size());
        assertEquals("NoNameFile",retrievedMD.get("name").values());

        //  set Metadata second time and check
        metadata = new Metadata("author","JohnDoe");
        metadatas = new HashMap<>();
        metadatas.put(metadata.key(), metadata);
        documentService.addMetadata(documentId,metadatas);
        retrievedMD = documentService.getMetadata(documentId);
        assertNotNull(retrievedMD);
        assertEquals(1 , retrievedMD.size());
        assertEquals("JohnDoe",retrievedMD.get("author").values());
    }

    @Test
    void search() {
        // Add first document with name metadata
        byte[] myData = UUID.randomUUID().toString().getBytes();
        Map<String, Metadata> metadatas = new HashMap<>();
        Metadata metadata = new Metadata("name","NoNameFile");
        metadatas.put(metadata.key(), metadata);
        UUID documentId1 = documentService.storeData(myData,metadatas);

        // Add second document with name metadata
        myData = UUID.randomUUID().toString().getBytes();
        metadatas = new HashMap<>();
        metadata = new Metadata("name","FileWithAName");
        metadatas.put(metadata.key(), metadata);
        UUID documentId2 = documentService.storeData(myData,metadatas);

        // retrieve one of these 2 files
        metadatas = new HashMap<>();
        metadata = new Metadata("name","FileWithAName");
        metadatas.put(metadata.key(), metadata);
        List<UUID> retrieved = documentService.search(metadatas);

        assertEquals(1,retrieved.size());
        assertEquals(documentId1.toString(),retrieved.get(0).toString());

        // retrieve none file
        metadatas = new HashMap<>();
        metadata = new Metadata("name","NoneFile");
        metadatas.put(metadata.key(), metadata);
        retrieved = documentService.search(metadatas);

        assertEquals(0,retrieved.size());
    }
}