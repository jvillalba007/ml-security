package com.ml.challenge.spring.scraping.websites;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static com.ml.challenge.spring.scraping.Scraping_all_IPSTor.getHtmlDocument;
import static com.ml.challenge.spring.scraping.Scraping_all_IPSTor.getStatusConnectionCode;

public class DanMeUk implements FuenteExterna {

    String sitio = "https://xamurai007.github.io/TorNodes/index.html";

    @Override
    public void agregarIPsFuenteExterna(ArrayList<String> listadoIps) {
        try {
            // Compruebo si me da un 200 al hacer la petición
            if (getStatusConnectionCode(sitio) == 200) {

                // Obtengo el HTML de la web en un objeto Document
                Document document = getHtmlDocument(sitio);

                // Busco del div del listado
                Elements entradas = document.select("div.article.box");

                String listado = entradas.toString();
                int comienzo_lista = listado.indexOf("<!-- __BEGIN_TOR_NODE_LIST__ //-->");
                int final_lista = listado.indexOf("<!-- __END_TOR_NODE_LIST__ //-->");
                listado = listado.substring(comienzo_lista, final_lista);
                listado = listado.substring(listado.indexOf(">") + 1);
                String[] split_listado = listado.split("<br>");

                int indice;
                int longitud = split_listado.length - 1;
                for (indice = 0; indice < longitud; indice++) {
                    String ip = split_listado[indice].substring(1, split_listado[indice].indexOf("|"));
                    listadoIps.add(ip);
                }

            }
        } catch (Exception ex) {
            System.out.println("No se pudo obtener las IPS desde " + sitio);
        }
    }
}
