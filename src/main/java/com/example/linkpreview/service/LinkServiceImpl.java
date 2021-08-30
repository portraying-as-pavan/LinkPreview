package com.example.linkpreview.service;

import com.example.linkpreview.Entity.Link;

import com.example.linkpreview.repository.LinkRepository;
import com.google.common.net.InternetDomainName;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository linkRepository;

    private final Logger logger= LogManager.getLogger(getClass());

    @Override
    public void addLink(Link link) {
        linkRepository.save(link);
    }

    @Override
    public Link getLink(int id) {
        return linkRepository.findById(id).get();
    }

    @Override
    public Link extractData(String url) throws IOException{
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }

        Document document = Jsoup.connect(url).get();
        String title = getMetaDataFromUrl(document, "meta[name=title]");
        String desc = getMetaDataFromUrl(document, "meta[name=description]");
        String ogUrl = StringUtils.defaultIfBlank(getMetaDataFromUrl(document, "meta[property=og:url]"), url);
        String ogTitle = getMetaDataFromUrl(document, "meta[property=og:title]");
        String ogDesc = getMetaDataFromUrl(document, "meta[property=og:description]");
        String ogImage = getMetaDataFromUrl(document, "meta[property=og:image]");

        String domain = ogUrl;
        try {
            domain = InternetDomainName.from(new URL(ogUrl).getHost()).topPrivateDomain().toString();
        }  catch (IOException e) {
            logger.warn("Unable to connect to extract domain name from : {}", url);
            throw e;
          //
        }
        return new Link(domain, url, StringUtils.defaultIfBlank(ogTitle, title), StringUtils.defaultIfBlank(ogDesc, desc), ogImage);
    }

    @Override
    public String getMetaDataFromUrl(Document document, String query) {
        Element elm = document.select(query).first();
        if (elm != null) {
            return elm.attr("content");
        }
        return "";
    }
}
