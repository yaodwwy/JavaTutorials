package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Created by Adam Yao on 2017/6/11
 */
public class XmlTest {
    /* 17. XML
                SAX: 在读取文档提取相应的标记事件(元素起始、元素结束、文档起始)
                DOM: 在内存中构造与文档中元素相应的树，可以遍历、搜索、修改
                DTD: 验证文档是否正确
                JAXP: 用于XML处理的Java API
                Castor: 开源项目，用于Java对象与XML映射
            */

    // 从对象中生成XML
    private final static String FILENAME = "serial.xml";

    public static void main(String[] args) throws Exception {
        String a = "hard work and best callback";
        new XmlTest().write(a);
        new XmlTest().dump();
        // XSLT转换XML
        // XSLT可以用来对输出格式进行各种控制
        Transformer tx = TransformerFactory.newInstance().newTransformer(new StreamSource("people.xml"));
        tx.transform(new StreamSource("people.xml"), new StreamResult("people.html"));

    }

    public void write(Object obj) throws IOException {
        XMLEncoder os = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILENAME)));
        os.writeObject(obj);
        os.close();
    }

    public void dump() throws IOException {
        XMLDecoder out = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILENAME)));
        System.out.println(out.readObject());
        out.close();
    }

    /*serial.xml格式内容如下:
    <? xml version = "1.0"
    encoding="UTF-8"?>
            <java version="1.6.0_02"class="java.beans.XMLDecoder">
    <string> hard work
    and best
    callback</string>
            </java>
    控制台输出
    hard work
    and best
    callback*/
    // 用SAX解析XML - 主要用于查找关键元素，不用全文遍历
    public XmlTest() throws SAXException, IOException, ParserConfigurationException {
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        parser.setContentHandler(new PeopleHandler());
        parser.parse("C:\\StudySource\\javacooksrc2\\xml\\people.xml");
    }

    private static class XmlDocument {
        public static XmlDocument createXmlDocument(String uri, boolean b) {
            return null;
        }
    }

    class PeopleHandler extends DefaultHandler {
        boolean parent = false;
        boolean kids = false;

        public void startElement(String nsURI, String localName, String rawName, Attributes attr) throws SAXException {
            System.out.println("startElement: " + localName + "," + rawName);
            if (rawName.equalsIgnoreCase("name"))
                parent = true;
            if (rawName.equalsIgnoreCase("children"))
                kids = true;
        }

        public void characters(char[] ch, int start, int length) {
            if (parent) {
                System.out.println("Parent: " + new String(ch, start, length));
                parent = false;
            } else if (kids) {
                System.out.println("Children: " + new String(ch, start, length));
                kids = false;
            }
        }

        public PeopleHandler() throws SAXException {
            super();
        }
    }

    public static void test() throws IOException, SAXException, ParserConfigurationException {
        // DOM解析XML - 遍历整个树
        String uri = "file:" + new File("C:\\StudySource\\javacooksrc2\\xml\\people.xml").getAbsolutePath();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(uri);
        NodeList nodes = document.getChildNodes();
        for (
                int i = 0; i < nodes.getLength(); i++)

        {
            Node n = nodes.item(i);
            switch (n.getNodeType()) {
                case Node.ELEMENT_NODE:
                    // todo
                    break;
                case Node.TEXT_NODE:
                    // todo
                    break;
            }
        }

        // 使用DTD或者XSD验证
        //定义好DTD或XSD文件
        XmlDocument doc = XmlDocument.createXmlDocument(uri, true);

        // 用DOM生成XML
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = fact.newDocumentBuilder();
        Document document1 = parser.newDocument();
        Node root = document.createElement("Poem");
        document1.appendChild(root);
        Node stanza = document.createElement("Stanza");
        root.appendChild(stanza);
        Node line = document.createElement("Line");
        stanza.appendChild(line);
        line.appendChild(document1.createTextNode("Once, upon a midnight dreary"));
        line = document1.createElement("Line");
        stanza.appendChild(line);
        line.appendChild(document1.createTextNode("While I pondered, weak and weary"));
    }
}
