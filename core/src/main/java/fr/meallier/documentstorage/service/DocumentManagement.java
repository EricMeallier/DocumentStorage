package fr.meallier.documentstorage.service;

import fr.meallier.documentstorage.domain.core.Metadata;
import fr.meallier.documentstorage.domain.services.DocumentService;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.UUID;

@Component
public class DocumentManagement {

    private final DocumentService documentService;

    public DocumentManagement(DocumentService documentServiceInMemory) {
        this.documentService = documentServiceInMemory;
    }

    public UUID storeFile(File file) throws Exception {

        byte[] bytes = null;

        DataInputStream reader = new DataInputStream(new FileInputStream(file));
        int nBytesToRead = reader.available();
        if(nBytesToRead > 0) {
            bytes = new byte[nBytesToRead];
            int red = reader.read(bytes);
            if (red != nBytesToRead)
                throw new Exception("File not read entirely");
        }

        UUID id = documentService.storeData(bytes);
        documentService.addMetadata(id,new Metadata("Name", file.getName()));
        return id;
    }

    public byte[] getFile(UUID id) {
        return documentService.getData(id);
    }

    public Collection<Metadata> getMetadata(UUID id) {
        return documentService.getMetadata(id).values();
    }

    public void addMetadata(UUID id, Metadata metadata) {
        documentService.addMetadata(id,metadata);
    }

    public void addMetadata(UUID id, Collection<Metadata> metadatas) {
        for (Metadata metadata: metadatas) {
            documentService.addMetadata(id,metadata);
        }
    }

    public String exportAll() {
        return documentService.exportAll();
    }
}
