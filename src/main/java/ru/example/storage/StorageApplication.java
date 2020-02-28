package ru.example.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.example.storage.service.StorageServiceImpl;
import ru.example.storage.to.Storage;
import ru.example.storage.util.FIleUtils;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class StorageApplication {
    public static void main(String[] args) throws JAXBException, IOException {
        if (args.length == 0) {
            System.out.println("Not specified file path");
            return;
        }

        ConfigurableApplicationContext springContext = SpringApplication.run(StorageApplication.class, args);

        String xmlData = FIleUtils.readFile(args[0], StandardCharsets.UTF_8);

        StringReader reader = new StringReader(xmlData);

        JAXBContext context = JAXBContext.newInstance(Storage.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Storage storage = (Storage) unmarshaller.unmarshal(reader);
        springContext.getBean(StorageServiceImpl.class).save(storage);


    }
}
