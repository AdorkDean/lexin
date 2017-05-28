
//    UtilXMLParser.java

package com.caipiao.pay.mobao;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;
import java.util.Map;

public class UtilXMLParser
{

	public UtilXMLParser()
	{
	}

	public static void parse(String xmlData, Map resultMap)
		throws Exception
	{
		Document doc = DocumentHelper.parseText(xmlData);
		Element root = doc.getRootElement();
		parseNode(root, resultMap);
	}

	private static void parseNode(Element node, Map resultMap)
	{
		List attList = node.attributes();
		List eleList = node.elements();
		for (int i = 0; i < attList.size(); i++)
		{
			Attribute att = (Attribute)attList.get(i);
			resultMap.put(att.getPath(), att.getText().trim());
		}

		resultMap.put(node.getPath(), node.getTextTrim());
		for (int i = 0; i < eleList.size(); i++)
			parseNode((Element)eleList.get(i), resultMap);

	}
}
