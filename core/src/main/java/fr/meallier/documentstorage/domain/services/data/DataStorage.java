package fr.meallier.documentstorage.domain.services.data;

import fr.meallier.documentstorage.domain.core.Metadata;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DataStorage {

    UUID storeData(byte[] data);

    byte[] getData(UUID documentId);
}