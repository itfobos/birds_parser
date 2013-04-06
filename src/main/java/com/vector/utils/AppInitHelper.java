package com.vector.utils;

import com.vector.FileParserApp;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;

public class AppInitHelper {
    private static AbstractApplicationContext applicationContext;

    public static void initApp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(
                "/META-INF/application.xml");

        applicationContext.registerShutdownHook();

        initLog4j();
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
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
        Document document = loader.parse(FileParserApp.class
                .getResourceAsStream("/META-INF/log4j.xml"));

        DOMConfigurator.configure(document.getDocumentElement());
    }
}
