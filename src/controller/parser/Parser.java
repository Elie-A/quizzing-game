package controller.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import controller.miscutilities.MiscUtilities;
import model.Choice;
import model.Question;

public class Parser implements IParser{

	@Override
	public List<Question> getQuestionsFromXML(String filePath) throws Exception {
		List<Question> list = new ArrayList<Question>();
		Choice[] choices = new Choice[]{};
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(new File(filePath));
		document.getDocumentElement().normalize();

		NodeList nList = document.getElementsByTagName("Question");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Question question = new Question();
				Element eElement = (Element) node;
				question.setId(eElement.getAttribute("id"));
				question.setValue(eElement.getElementsByTagName("Value").item(0).getTextContent());
				question.setAnswer(eElement.getElementsByTagName("Answer").item(0).getTextContent());
				NodeList nList2 = eElement.getElementsByTagName("Choice");
				for (int temp2 = 0; temp2 < nList2.getLength(); temp2++) {
					Node node2 = nList2.item(temp2);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Choice choice = new Choice();
						Element eElement2 = (Element) node2;
						choice.setId(eElement2.getAttribute("id"));
						choice.setValue(eElement2.getTextContent());
						choices = MiscUtilities.addToChoiceArray(choices.length, choices, choice);
					}
					question.setChoices(choices);
				}
				choices = MiscUtilities.clearChoiceArray(choices);
				list.add(question);
			}
		}
		return list;
	}

	@Override
	public int totalQuestionFromXml(List<Question> listOfQuestions) throws Exception {
		return listOfQuestions.size();
	}
	
	@Override
	public void saveToFile(String filePath, String info) throws Exception {
		FileWriter fileWriter = new FileWriter(new File(filePath + ".txt"));
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(info);
		bufferedWriter.close();
		fileWriter.close();
	}

}
