package com.ml.challenge.spring.model;

import com.ml.challenge.spring.repository.IpTorRepository;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "tor")
public class IpTor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ArrayList<String> filtrarIps(ArrayList<String> ips, IpTorRepository ipTorRepository) {
        var it = ipTorRepository.findAll();
        ArrayList<IpTor> ips_filtrar = new ArrayList<IpTor>();
        it.forEach(e -> ips_filtrar.add(e));

        for (IpTor ipfiltrar : ips_filtrar) {
            ips.remove(ipfiltrar.getIp());
        }

        return ips;
    }
}
