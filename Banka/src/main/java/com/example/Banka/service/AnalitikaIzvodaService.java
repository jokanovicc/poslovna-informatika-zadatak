package com.example.Banka.service;

import com.example.Banka.model.AnalitikaIzvoda;
import com.example.Banka.model.Klijent;
import com.example.Banka.model.Racun;
import com.example.Banka.repository.AnalitikaIzvodaRepository;
import com.example.Banka.web.dto.IzvodDTO;
import com.example.Banka.web.dto.NalogZaPrenosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

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
                String line = analitikaIzvodaDTO.getBrojStavke() + ";" + analitikaIzvodaDTO.getDuznik() + ";" + analitikaIzvodaDTO.getRacunDuznika() + ";" + analitikaIzvodaDTO.getRacunPrimaoca() + ";" + analitikaIzvodaDTO.getIznos() + ";" + analitikaIzvodaDTO.getSvrhaPlacanja() + ";RSD;" + analitikaIzvodaDTO.getVremePrenosa();
                lines.add(line);
                ret.put(analitikaIzvodaDTO.getBrojStavke(), analitikaIzvodaDTO);

            }

            Files.write(path, lines, Charset.forName("UTF-8"));

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
                String line = brojStavke + ";" + analitikaizvoda102.getDuznik() + ";" + analitikaizvoda102.getRacunDuznika() + ";" + analitikaizvoda102.getRacunPrimaoca() + ";" + analitikaizvoda102.getIznos() + ";" + "RSD" + ";" + analitikaizvoda102.getHitno() + ";" + analitikaizvoda102.getVremePrenosa();
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


    public void dtoToModel(NalogZaPrenosDTO nalogZaPrenosDTO, AnalitikaIzvoda analitikaIzvoda){
        Random r = new Random();
        int low = 10;
        int high = 1000;
        int result = r.nextInt(high-low) + low;
        analitikaIzvoda.setBrojStavke(result);
        analitikaIzvoda.setDuznik(nalogZaPrenosDTO.getUplatilac());
        analitikaIzvoda.setPrimalac("primalac opisan po računu");
        analitikaIzvoda.setHitno(nalogZaPrenosDTO.isHitno());
        analitikaIzvoda.setModel(98);
        analitikaIzvoda.setPozivNaBroj("transakcija građana");
        analitikaIzvoda.setIznos(nalogZaPrenosDTO.getIznos());
        analitikaIzvoda.setVrstaPlacanja("transkacija po nalogu građana");
        analitikaIzvoda.setRacunDuznika(nalogZaPrenosDTO.getBrojRacunaUplatioca());
        analitikaIzvoda.setRacunPrimaoca(nalogZaPrenosDTO.getBrojRacunaPrimaoca());
        analitikaIzvoda.setVremePrenosa(LocalDate.now());
        analitikaIzvoda.setSvrhaPlacanja(nalogZaPrenosDTO.getSvrhaPlacanja());

    }


    public void caseRTGS(AnalitikaIzvoda analitikaIzvoda){
        System.out.println("-------REALTIME MT102-----------");
        Map<Integer, AnalitikaIzvoda> ret = new HashMap<>();
        List<AnalitikaIzvoda> analitike = findAll();
        ret.put(analitikaIzvoda.getBrojStavke(),analitikaIzvoda);
        saveToFile103(ret);
    }


    public void caseClearing(AnalitikaIzvoda analitikaIzvoda){
        Random r = new Random();
        int low = 10;
        int high = 1000;
        int result = r.nextInt(high-low) + low;
        Map<Integer, AnalitikaIzvoda> ret2 = new HashMap<>();
        List<AnalitikaIzvoda> analitike2 = findAll();
        for(AnalitikaIzvoda analitikaIzvoda1: analitike2){
            if(analitikaIzvoda1.getHitno()==false || analitikaIzvoda1.getIznos() < 300000 && analitikaIzvoda1.getVremePrenosa() == analitikaIzvoda.getVremePrenosa())
                ret2.put(analitikaIzvoda1.getBrojStavke(),analitikaIzvoda1);

        }
        ret2.put(analitikaIzvoda.getBrojStavke(),analitikaIzvoda);
        saveToFile102(ret2,result);

    }



}
