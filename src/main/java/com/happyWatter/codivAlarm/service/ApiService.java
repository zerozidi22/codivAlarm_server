package com.happyWatter.codivAlarm.service;

import com.happyWatter.codivAlarm.entity.ApiCodivData;
import com.happyWatter.codivAlarm.repository.CodivDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

    @Autowired
    public CodivDataRepository codivDataRepository;

    public List<ApiCodivData> getCodivDataFromServer() throws Exception {

        String ServiceKey = "5JvFLv3oF5idcPNnPGNAsvl6d%2B8JEsE7FPweOD3NA55%2B3M8RuO9dJpBhQTVzIcWxUGhXv4h1kVDgd3D%2F%2BFaqcA%3D%3D";

        String yestDate = LocalDateTime.now().minusDays(0).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String yestYestDate = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /*URL*/
        urlBuilder.append("?" + "ServiceKey=" + ServiceKey); /*Service Key*/
        urlBuilder.append("&" + "pageNo=" + "1");
        urlBuilder.append("&" + "numOfRows=" + "10");
        urlBuilder.append("&" + "startCreateDt=" + yestYestDate);
        urlBuilder.append("&" + "endCreateDt=" + yestDate);


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder  = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("item");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<ApiCodivData> apiDataList = new ArrayList<>();

        for(int temp =0; temp<nodeList.getLength(); temp++) {
            Node nNode = nodeList.item(temp);
            if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                Element element = (Element) nNode;


                String createDt = getTagValue("createDt", element);
                int idx = createDt.indexOf(".");
                String parseedCreateDt = createDt.substring(0, idx);

                boolean isDuplicated = apiDataList.stream().anyMatch(o -> o.getCreateDt().equals(LocalDateTime.parse(parseedCreateDt, formatter).format(DateTimeFormatter.ofPattern("yyyyMMdd"))));
                if(!isDuplicated){
                    ApiCodivData apiData = new ApiCodivData();
                    apiData.setCreateDt(LocalDateTime.parse(parseedCreateDt, formatter).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                    apiData.setDecideCnt(Long.parseLong(getTagValue("decideCnt", element)));
                    apiDataList.add(apiData);
                }

            }
        }

        return apiDataList;

    }

    public Long getCodivDate(){
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String yest = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        ApiCodivData nowData =  codivDataRepository.findByCreateDt(now);
        ApiCodivData yestData =  codivDataRepository.findByCreateDt(yest);

        return nowData.getDecideCnt() - yestData.getDecideCnt();


    }

    private static String getTagValue(String tag, Element ele) {

        NodeList nodeList = ele.getElementsByTagName(tag).item(0).getChildNodes();

        Node nValue = (Node) nodeList.item(0);

        if(nValue == null) {

            return null;

        }

        return nValue.getNodeValue();

    }

}
