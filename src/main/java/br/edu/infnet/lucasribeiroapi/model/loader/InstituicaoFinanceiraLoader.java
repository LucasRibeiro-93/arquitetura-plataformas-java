package br.edu.infnet.lucasribeiroapi.model.loader;

import br.edu.infnet.lucasribeiroapi.model.domain.InstituicaoFinanceira;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

public class InstituicaoFinanceiraLoader {
    public static List<InstituicaoFinanceira> loadFromXml(String filePath) {
        List<InstituicaoFinanceira> instituicoes = new ArrayList<>();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("InstituicaoFinanceira");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
                    instituicao.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
                    instituicao.setCnpj(element.getElementsByTagName("cnpj").item(0).getTextContent());
                    instituicao.setCodigoBanco(Integer.parseInt(element.getElementsByTagName("codigoBanco").item(0).getTextContent()));
                    instituicao.setAtivo(Boolean.parseBoolean(element.getElementsByTagName("ativo").item(0).getTextContent()));
                    instituicoes.add(instituicao);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instituicoes;
    }
}