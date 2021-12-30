insert into banka(pib, adresa,sifra_banke,naziv) values ('12345' ,'Novi Sad' ,'12345' ,'OTP Banka');
insert into banka(pib, adresa,sifra_banke,naziv) values ('1223' ,'Beograd' ,'1223' ,'UniCredit Banka');
insert into banka(pib, adresa,sifra_banke,naziv) values ('1333' ,'Beograd' ,'1333' ,'Societa Generale');


insert into klijent(adresa,broj_licne_karte,datum_rodjenja,ime,jmbg) values ('Temisvarska 21a Zrenjanin','111','1983-11-12','Jovan Markovic','111');
insert into klijent(adresa,broj_licne_karte,datum_rodjenja,ime,jmbg) values ('Kralja Aleksandra 43 Zrenjanin','222','1992-05-12','Petar Zdravkovic','222');
insert into klijent(adresa,broj_licne_karte,datum_rodjenja,ime,jmbg) values ('Kralja Petra I 31 Vrsac','333','1994-08-12','Miodrag Bankic','333');


insert into racun(broj_racuna,datum_otvaranja,banka_id,klijent_id) values ('9874-0000-6000-4200','2020-05-10',1,1);
insert into racun(broj_racuna,datum_otvaranja,banka_id,klijent_id) values ('4400-9871-6520-2100','2021-07-10',2,2);
insert into racun(broj_racuna,datum_otvaranja,banka_id,klijent_id) values ('4400-9871-5400-1200','2021-10-10',2,3);


insert into dnevno_stanje(broj_izvoda, datum_izvoda, novo_stanje, prethodno_stanje, promet_na_teret, prometukorist, rezervisano, version, racun_privatnih_lica_id)
values (900, '2021-12-28', -10000, -10000, 0, 1000, 0, 1, 1);


insert into dnevno_stanje( broj_izvoda, datum_izvoda, novo_stanje, prethodno_stanje, promet_na_teret, prometukorist, rezervisano, version, racun_privatnih_lica_id)
values (570, '2021-12-28', 10000, 10000, 0, 10000, 0, 1, 3);




insert into analitika_izvoda(broj_stavke, duznik, hitno, iznos, model, poziv_na_broj, primalac, racun_duznika, racun_primaoca, svrha_placanja, vreme_prenosa, vrsta_placanja, dnevno_stanje_id) values
    (900, 'Jovan Markovic', false, 10000, '98', 'transakcija građana', 'primalac opisan po računu', '9874-0000-6000-4200', '4400-9871-5400-1200', 'slanje novca osiguranju', '2021-12-28', 'transkacija po nalogu građana', 1);

insert into analitika_izvoda(broj_stavke, duznik, hitno, iznos, model, poziv_na_broj, primalac, racun_duznika, racun_primaoca, svrha_placanja, vreme_prenosa, vrsta_placanja, dnevno_stanje_id) values
    (900, 'Jovan Markovic', false, 10000, '98', 'transakcija građana', 'primalac opisan po računu', '9874-0000-6000-4200', '4400-9871-5400-1200', 'slanje novca osiguranju', '2021-12-28', 'transkacija po nalogu građana', 2);