package br.edu.infnet.lucasribeiroapi.model.loader;

import br.edu.infnet.lucasribeiroapi.model.domain.Conta;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

public class ContaLoader {
    public static List<Conta> loadFromXml(String filePath) {
        List<Conta> contas = new ArrayList<>();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Conta");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Conta conta = new Conta();
                    conta.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    conta.setNumero(element.getElementsByTagName("numero").item(0).getTextContent());
                    conta.setTipo(element.getElementsByTagName("tipo").item(0).getTextContent());
                    conta.setAtivo(Boolean.parseBoolean(element.getElementsByTagName("ativo").item(0).getTextContent()));
                    contas.add(conta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contas;
    }
}