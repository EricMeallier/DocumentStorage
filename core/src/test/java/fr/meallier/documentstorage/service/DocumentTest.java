package fr.meallier.documentstorage.service;

import fr.meallier.documentstorage.domain.services.metadata.filtering.MetadataProcessorConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;

@SpringBootTest
@EnableConfigurationProperties({MetadataProcessorConfig.class})
class DocumentTest {


    static final private Logger LOGGER = LoggerFactory.getLogger(Class.class.getName());

    @Autowired
    DocumentManagement documentManagement;

    @Test
    void storeFile() throws Exception {
        File file = new File("/tmp/testfile");

        byte[] data = "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.".getBytes();

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(data);
        }

        documentManagement.storeFile(file);

        String result = documentManagement.exportAll();
        LOGGER.info(result);
    }
}