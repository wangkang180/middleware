package com.kang;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class XpathDemo1 {
    private static Document doc;
    private static XPath xpath;

    public static void main(String[] args) throws Exception {
        init();

        //从北京机场出发的所有航班
        getAttrEles();

        //b.到港时间早于10:00的所有航班
        getAttr();

        //System.out.println(doc.getDocumentElement().getChildNodes().getLength());
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                System.out.print(nodeList.item(i).getNodeName() + " ");
            }
        }
    }

    // 初始化Document、XPath对象
    public static void init() throws Exception {
        // 创建Document对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new FileInputStream(new File("F:\\homework5\\FlightINFO.xml")));

        // 创建XPath对象
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

    //
    //从北京机场出发的所有航班
    public static void getAttrEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//Airlines/AirlinesTime[LeaveAirport='北京首都国际机场']/FlightCode", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
            System.out.println();
        }
        System.out.println("-----------------------------");
    }


    //b.到港时间早于10:00的所有航班
    public static void getAttr() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//Airlines/AirlinesTime[number(translate(substring(ArriveTime,1,5),\":\",\".\"))<10.00]/FlightCode", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.print(nodeList.item(i).getNodeName() + "-->"
                        + nodeList.item(i).getTextContent() + " ");
                System.out.println();
        }
        System.out.println();
    }
}
