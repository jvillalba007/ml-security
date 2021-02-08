package com.ml.challenge.spring.scraping;

import com.ml.challenge.spring.scraping.websites.DanMeUk;
import com.ml.challenge.spring.scraping.websites.FuenteExterna;
import com.ml.challenge.spring.scraping.websites.TorStatusBlutMagieDe;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service("ScrapingTOR")
public class Scraping_all_IPSTor {

    public ArrayList<FuenteExterna> sitiosArrayList;

    public Scraping_all_IPSTor() {
        sitiosArrayList = new ArrayList<FuenteExterna>();
        sitiosArrayList.add(new DanMeUk());
        sitiosArrayList.add(new TorStatusBlutMagieDe());
    }

    public static int getStatusConnectionCode(String url) {

        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    public static Document getHtmlDocument(String url) {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }
        return doc;
    }

    public ArrayList<String> ObtenerIPsFuenteExterna() {
        ArrayList<String> listadoIps = new ArrayList<String>();

        for (FuenteExterna fuenteExterna : sitiosArrayList) {
            fuenteExterna.agregarIPsFuenteExterna(listadoIps);
        }
        return listadoIps;
    }
}