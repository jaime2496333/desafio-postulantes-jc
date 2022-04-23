package com.fapro.exam.scraping.service;

import com.fapro.exam.scraping.model.insituciones_financiera;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class ServiceUrlImpl implements ServiceUrl{

    @Value("${sitioweb}")
    String url;
    
    @Override
    public Set<insituciones_financiera> getInformation() {

        //Using a set here to only store unique elements
        Set<insituciones_financiera> responseInst = new HashSet<>();
        
                extraerInformacion(responseInst, url );

        return responseInst;
    }

    @Override
    public Set<insituciones_financiera> getInformationByNo(String v_no) {

        //Using a set here to only store unique elements
        Set<insituciones_financiera> responseInst = new HashSet<>();

        extraerInformacionByNo(responseInst, url, v_no );

        return responseInst;
    }



    private void extraerInformacion(Set<insituciones_financiera> responseInst, String url){

        int item_institucion;

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        // Set to headless mode: must be set to headless mode
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--whitelisted-ips=''");
        //chromeOptions.addArguments("--disable-gpu");
        // Set browser window size
        chromeOptions.addArguments("--window-size=800,600");
        // Equivalent to creating a virtual browser
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(url);
        //driver.get(url);
        //List<String> searchResults = new ArrayList<>();

        try {



            List<WebElement> tabla = driver.findElements(By.tagName("tbody"));
            //recorro la tabla para encontrar las filas
            for(WebElement row: tabla) {
                List<WebElement> filas = row.findElements(By.tagName("tr"));



                for(WebElement tr: filas) {

                    List<WebElement> columnas = tr.findElements(By.tagName("td"));

                    //recorro las columnas
                    //inicializo contador para objeto
                    item_institucion=0;
                    //inicializo el objeto
                    insituciones_financiera responseInstitucion = new insituciones_financiera();

                    for(WebElement td: columnas) {

                        if (item_institucion > 6) {
                            item_institucion = 0;
                        }

                        switch (item_institucion) {

                            case 0:
                                responseInstitucion.setNo(Integer.parseInt(td.getText()));
                                break;
                            case 1:
                                responseInstitucion.setRazon_social(td.getText());
                                break;
                            case 2:
                                responseInstitucion.setPais(td.getText());
                                break;
                            case 3:
                                responseInstitucion.setDatos_inscripcion(td.getText());
                                break;
                            case 4:
                                responseInstitucion.setVigencia(td.getText());
                                break;
                            case 5:
                                responseInstitucion.setDatos_ultima_actualizacion(td.getText());
                                break;
                            case 6:
                                responseInstitucion.setEstado_actualizado(td.getText());
                                break;

                        }

                        if (item_institucion == 6) {
                            responseInst.add(responseInstitucion);
                        }

                        item_institucion += 1;

                        //System.out.println( td.getText());

                    }

                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    private void extraerInformacionByNo(Set<insituciones_financiera> responseInst, String url, String no){

        int item_institucion;

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        // Set to headless mode: must be set to headless mode
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--whitelisted-ips=''");
        //chromeOptions.addArguments("--disable-gpu");
        // Set browser window size
        chromeOptions.addArguments("--window-size=800,600");
        // Equivalent to creating a virtual browser
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(url);
        //driver.get(url);
        //List<String> searchResults = new ArrayList<>();

        try {



            List<WebElement> tabla = driver.findElements(By.tagName("tbody"));
            //recorro la tabla para encontrar las filas
            for(WebElement row: tabla) {
                List<WebElement> filas = row.findElements(By.tagName("tr"));



                for(WebElement tr: filas) {

                    List<WebElement> columnas = tr.findElements(By.tagName("td"));

                    //recorro las columnas
                    //inicializo contador para objeto
                    item_institucion=0;
                    //inicializo el objeto
                    insituciones_financiera responseInstitucion = new insituciones_financiera();

                    for(WebElement td: columnas) {

                        if (item_institucion > 6) {
                            item_institucion = 0;
                        }

                        switch (item_institucion) {

                            case 0:
                                responseInstitucion.setNo(Integer.parseInt(td.getText()));
                                break;
                            case 1:
                                responseInstitucion.setRazon_social(td.getText());
                                break;
                            case 2:
                                responseInstitucion.setPais(td.getText());
                                break;
                            case 3:
                                responseInstitucion.setDatos_inscripcion(td.getText());
                                break;
                            case 4:
                                responseInstitucion.setVigencia(td.getText());
                                break;
                            case 5:
                                responseInstitucion.setDatos_ultima_actualizacion(td.getText());
                                break;
                            case 6:
                                responseInstitucion.setEstado_actualizado(td.getText());
                                break;

                        }

                        if (item_institucion == 6 && responseInstitucion.getNo().equals(Integer.parseInt(no))) {
                            responseInst.add(responseInstitucion);
                        }

                        item_institucion += 1;

                        //System.out.println( td.getText());

                    }

                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }



}
