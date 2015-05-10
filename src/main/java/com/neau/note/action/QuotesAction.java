package com.neau.note.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.pojo.Quotes;
import com.neau.note.service.QuotesService;

@Controller("quotesAction")
@Scope("prototype")
public class QuotesAction extends BaseAction{


	private static final long serialVersionUID = 1L;

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private Quotes quote;
	
	private List<Quotes> quotes;
	
	private String author;
	
	private String content;
	
	private String type;
	
	private String result;
	
	@Resource
	QuotesService quotesService;
	
	public String execute(){
		quote = quotesService.getQuote();
		return SUCCESS;
	}
	
	public String getOneQuote(){
		quote = quotesService.getQuote();
		logger.info("index...");
		return SUCCESS;
	}

	public String updateQuotes(){
		Quotes quote = new Quotes();
		quote.setAuthor(author);
		quote.setContent(content);
		quote.setType(type);
		result = quotesService.updateQuotes(quote);
		return SUCCESS;
	}
	

	
	public String getAllQuotes(){
		quotes = quotesService.getAllQuotes();
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Quotes getQuote() {
		return quote;
	}

	public List<Quotes> getQuotes() {
		return quotes;
	}
	

}
