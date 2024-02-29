package fr.meallier.documentstorage.domain.core;

import java.util.*;

public class Document {

    UUID documentID;
    byte[] data;
    Map<String, Metadata> metadatas;

    public Document(UUID documentID, byte[] data, Map<String, Metadata> metadatas) {
        if (documentID == null) {
            documentID = UUID.randomUUID();
        } else {
            this.documentID = documentID;
        }
        this.data = data;
        if (metadatas == null) {
            metadatas = new HashMap<>();
        } else {
            this.metadatas = metadatas;
        }
    }

    public UUID getDocumentID() {
        return documentID;
    }

    public void setDocumentID(UUID documentID) {
        this.documentID = documentID;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Map<String, Metadata> getMetadatas() {
        return metadatas;
    }

    public void setMetadatas(Map<String, Metadata> metadatas) {
        this.metadatas = metadatas;
    }
}
