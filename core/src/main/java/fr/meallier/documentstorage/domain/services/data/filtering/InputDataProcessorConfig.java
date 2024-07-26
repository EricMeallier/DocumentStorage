package fr.meallier.documentstorage.domain.services.data.filtering;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "document.data.input.processor")
public record InputDataProcessorConfig(List<String> filterNames) implements DataProcessorConfig {}
