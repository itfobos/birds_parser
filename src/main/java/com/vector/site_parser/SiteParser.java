package com.vector.site_parser;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SiteParser {
    private static final Logger logger = LoggerFactory.getLogger(SiteParser.class);

    private static final String URL_TEMPLATE = "http://www.birding.in/checklists/sibley-monroe_checklist_%s.htm";

    public void parse() {
        for (int i = 1; i <= 18; i++) {
            String pageUrl = String.format(URL_TEMPLATE, StringUtils.leftPad(String.valueOf(i), 2, '0'));

            parseUrl(pageUrl);

            break;//TODO: remove it
        }
    }

    private void parseUrl(String pageUrl) {
        logger.debug("Requesting URL: {}", pageUrl);
        Document doc = null;
        try {
            doc = Jsoup.connect(pageUrl).get();
        } catch (IOException e) {
            logger.error("Can't get URL: {}", pageUrl, e);
            new RuntimeException(e);
        }

        Elements orderElements = doc.select("p.ordhed");

        for (Element element : orderElements) {

        }

        //TODO:
    }

    private void parseOrder(Element orderElement) {
        String content = orderElement.text();
    }

    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.birding.in/checklists/sibley-monroe_checklist_01.htm").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements orderHeads = doc.select("p.ordhed");

        logger.debug("Elems: {}", orderHeads.size());
    }
}
