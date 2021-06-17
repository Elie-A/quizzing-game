package controller.parser;

import java.util.List;

import model.Question;

public interface IParser {
	
	public List<Question> getQuestionsFromXML(String filePath) throws Exception;
	
	public int totalQuestionFromXml(List<Question> listOfQuestions) throws Exception;
	
	public void saveToFile(String filePath, String info) throws Exception;
}
