package com.example.Banka.service;

import com.example.Banka.model.AnalitikaIzvoda;
import com.example.Banka.repository.AnalitikaIzvodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalitikaIzvodaService {

    @Autowired
    private AnalitikaIzvodaRepository analitikaIzvodaRepository;

    @Value("${mt102.pathToFile}")
    private String pathToFile102;

    @Value("${mt103.pathToFile}")
    private String pathToFile103;



    public AnalitikaIzvoda saveAnalitika(AnalitikaIzvoda analitikaIzvoda){
        return analitikaIzvodaRepository.save(analitikaIzvoda);
    }

    public List<AnalitikaIzvoda> findAll(){
        return analitikaIzvodaRepository.findAll();
    }

    public Map<Integer, AnalitikaIzvoda> saveToFile103(Map<Integer, AnalitikaIzvoda> analitika) {

        Map<Integer, AnalitikaIzvoda> ret = new HashMap<>();

        try {
            Path path = Paths.get(pathToFile103);
            System.out.println(path.toFile().getAbsolutePath());
            List<String> lines = new ArrayList<String>();

            for (AnalitikaIzvoda analitikaIzvodaDTO : analitika.values()) {
                String line = analitikaIzvodaDTO.getBrojStavke() + ";" + analitikaIzvodaDTO.getDuznik() + ";" + analitikaIzvodaDTO.getRacunDuznika() + ";" + analitikaIzvodaDTO.getRacunPrimaoca() + ";" + analitikaIzvodaDTO.getIznos() + ";" + analitikaIzvodaDTO.getHitno();
                lines.add(line);
                ret.put(analitikaIzvodaDTO.getBrojStavke(), analitikaIzvodaDTO);

            }

            Files.write(path, lines, Charset.forName("UTF-8"));
            System.out.println(" 1 JEL STIGLO DOVDE?");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    public Map<Integer, AnalitikaIzvoda> saveToFile102(Map<Integer, AnalitikaIzvoda> analitika,Integer brojStavke) {
        Map<Integer, AnalitikaIzvoda> ret = new HashMap<>();

        try {
            Path path = Paths.get(pathToFile102);
            System.out.println(path.toFile().getAbsolutePath());
            List<String> lines = new ArrayList<String>();

            for (AnalitikaIzvoda analitikaizvoda102 : analitika.values()) {
                String line = brojStavke + ";" + analitikaizvoda102.getDuznik() + ";" + analitikaizvoda102.getRacunDuznika() + ";" + analitikaizvoda102.getRacunPrimaoca() + ";" + analitikaizvoda102.getIznos() + ";" + analitikaizvoda102.getHitno() + ";" + analitikaizvoda102.getVremePrenosa();
                lines.add(line);
                ret.put(analitikaizvoda102.getBrojStavke(), analitikaizvoda102);

            }

            Files.write(path, lines, Charset.forName("UTF-8"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }


    public AnalitikaIzvoda makeAnalitikaIzvodaPrimaoca(AnalitikaIzvoda uplatioca){
        AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
        analitikaIzvoda.setPozivNaBroj(uplatioca.getPozivNaBroj());
        analitikaIzvoda.setIznos(uplatioca.getIznos());
        analitikaIzvoda.setRacunPrimaoca(uplatioca.getRacunPrimaoca());
        analitikaIzvoda.setRacunDuznika(uplatioca.getRacunDuznika());
        analitikaIzvoda.setModel(uplatioca.getModel());
        analitikaIzvoda.setHitno(uplatioca.getHitno());
        analitikaIzvoda.setSvrhaPlacanja(uplatioca.getSvrhaPlacanja());
        analitikaIzvoda.setVremePrenosa(uplatioca.getVremePrenosa());
        analitikaIzvoda.setBrojStavke(uplatioca.getBrojStavke());
        analitikaIzvoda.setDuznik(uplatioca.getDuznik());
        analitikaIzvoda.setPrimalac(uplatioca.getPrimalac());
        analitikaIzvoda.setVrstaPlacanja(uplatioca.getVrstaPlacanja());
        return analitikaIzvoda;



    }


}
