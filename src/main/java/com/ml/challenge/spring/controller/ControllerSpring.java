package com.ml.challenge.spring.controller;

import com.ml.challenge.spring.repository.IpTorRepository;
import com.ml.challenge.spring.model.IpTor;
import com.ml.challenge.spring.scraping.Scraping_all_IPSTor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class ControllerSpring {

    @Autowired
    @Qualifier("ScrapingTOR")
    Scraping_all_IPSTor scrapingAllIpsTor;
    @Autowired
    private IpTorRepository ipTorRepository;

    @GetMapping(value = "/listadoips", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<String> ListadoIps() {
        ArrayList<String> ips = scrapingAllIpsTor.ObtenerIPsFuenteExterna();
        return ips;
    }

    @PostMapping(value = "/agregarip")
    @ResponseBody
    public ResponseEntity AgregarIP(@RequestBody String ip) {
        System.out.println("Saving ip information");

        IpTor ipTor = new IpTor();
        ipTor.setIp(ip);

        try {
            ipTorRepository.save(ipTor);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/listadoips/filtrado", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<String> ListadoIpsFiltrado() {

        ArrayList<String> ips = scrapingAllIpsTor.ObtenerIPsFuenteExterna();
        IpTor ipTor = new IpTor();
        ipTor.filtrarIps(ips, ipTorRepository);

        return ips;
    }
}
