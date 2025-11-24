package fr.meallier.documentstorage.domain.core.data.filtering;

public interface DataFilter {

    byte[] doFilter(byte[] data);
}
