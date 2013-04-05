package com.vector;

import com.vector.site_parser.FileParser;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        initLog4j();
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "/META-INF/application.xml");

        applicationContext.registerShutdownHook();

        FileParser fileParser = applicationContext.getBean(FileParser.class);
//        fileParser.parse("D:\\Docs\\Sonya_parser\\Sources\\birds_parser\\src\\main\\resources\\data\\birds_short.txt");
        fileParser.parse("D:\\Sources\\birds_parser\\src\\main\\resources\\data\\birds.txt");

        logger.debug("Finished");
    }


    private static void initLog4j() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // then we have to create document-loader:
        DocumentBuilder loader = factory.newDocumentBuilder();

        loader.setEntityResolver(new EntityResolver() {
            public InputSource resolveEntity(String publicId, String systemId)
                    throws SAXException, IOException {
                // Check for dtd ref
                if (systemId.endsWith("org/apache/log4j/xml/log4j.dtd")) {
                    // return the dtd from classpath
                    return new InputSource(getClass().getClassLoader()
                            .getResourceAsStream(
                                    "org/apache/log4j/xml/log4j.dtd"));
                }

                // Resume normal flow
                return null;
            }
        });

        // loading a DOM-tree...
        Document document = loader.parse(App.class
                .getResourceAsStream("/META-INF/log4j.xml"));

        DOMConfigurator.configure(document.getDocumentElement());
    }
}
