package com.example.linkpreview.service;

import com.example.linkpreview.Entity.Link;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface LinkService {

   void addLink(Link link);
   Link getLink(int id);
   Link extractData(String url) throws IOException;
   String getMetaDataFromUrl(Document document,String query);
}
