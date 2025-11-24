package fr.meallier.documentstorage.domain.core.data.storage;

import java.util.UUID;

public interface DataStorage {

    UUID storeData(byte[] data);

    byte[] getData(UUID documentId);
}