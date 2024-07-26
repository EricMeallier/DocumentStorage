package fr.meallier.documentstorage.domain.services.data.filter;

import fr.meallier.documentstorage.domain.services.data.filtering.DataFilter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("DataRemoveHeaderFilter")
public class DataRemoveHeaderFilter implements DataFilter {
    @Override
    public byte[] doFilter(byte[] data) {
        return Arrays.copyOfRange(data,2,data.length);
    }
}
