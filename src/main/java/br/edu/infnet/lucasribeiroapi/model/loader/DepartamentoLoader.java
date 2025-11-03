package br.edu.infnet.lucasribeiroapi.model.loader;

import br.edu.infnet.lucasribeiroapi.model.domain.Departamento;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

public class DepartamentoLoader {
    public static List<Departamento> loadFromXml(String filePath) {
        List<Departamento> departamentos = new ArrayList<>();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Departamento");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Departamento departamento = new Departamento();
                    departamento.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    departamento.setDescricao(element.getElementsByTagName("descricao").item(0).getTextContent());
                    departamento.setAtivo(Boolean.parseBoolean(element.getElementsByTagName("ativo").item(0).getTextContent()));
                    departamentos.add(departamento);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departamentos;
    }
}