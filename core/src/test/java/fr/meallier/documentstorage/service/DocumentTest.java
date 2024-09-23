package fr.meallier.documentstorage.service;

import fr.meallier.documentstorage.domain.core.Metadata;
import fr.meallier.documentstorage.domain.services.metadata.filtering.MetadataProcessorConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;


@SpringBootTest
@EnableConfigurationProperties({MetadataProcessorConfig.class})
class DocumentTest {


    static final private Logger LOGGER = LoggerFactory.getLogger(Class.class.getName());

    @Autowired
    DocumentManagement documentManagement;

    private final String dataContaint = "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.";

    @Test
    void storeFile() throws Exception {
        File file = new File("/tmp/testfile");

        byte[] data = dataContaint.getBytes(StandardCharsets.UTF_8);


        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(data);
        }

        UUID id = documentManagement.storeFile(file);

        byte[] resultData = documentManagement.getFile(id);
        documentManagement.addMetadata(id,Metadata.build("Author", "EricMeallier"));
        Assertions.assertArrayEquals(Arrays.copyOfRange(dataContaint.getBytes(),4,dataContaint.getBytes().length), resultData);
        String resultString = new String(resultData,StandardCharsets.UTF_8);
        Assertions.assertEquals(dataContaint.substring(4), resultString);

        Collection<Metadata> resultMetadata = documentManagement.getMetadata(id);
        Metadata metadata = new Metadata("DocumentSize",String.valueOf(data.length));
        Assertions.assertTrue(resultMetadata.contains(metadata));

        Assertions.assertEquals(4,documentManagement.getMetadata(id).size());
    }
}