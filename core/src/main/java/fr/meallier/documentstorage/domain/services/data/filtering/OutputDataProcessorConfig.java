package fr.meallier.documentstorage.domain.services.data.filtering;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
@ConfigurationProperties(prefix = "document.data.output.processor")
public record OutputDataProcessorConfig(List<String> filterNames) implements  DataProcessorConfig {}
