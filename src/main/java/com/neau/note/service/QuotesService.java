package com.neau.note.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.neau.note.dao.impl.QuotesDaoImpl;
import com.neau.note.pojo.Quotes;
import com.neau.note.utils.Content;

@Service("quotesService")
public class QuotesService {
	
	@Resource
	QuotesDaoImpl quotesDaoImpl;
	
	public static List<Quotes> quotesAll;
	
	public String updateQuotes(Quotes quote){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Content.result, Content.failure);
		int id = quotesDaoImpl.updateQutoes(quote);
		if( id > 0 ){
			map.put(Content.result, Content.success);
			map.put(Content.response, id);
		}
		return JSONObject.fromObject(map).toString();
	}

	public List<Quotes> getAllQuotes() {
		List<Quotes> quotes = new ArrayList<Quotes>();
		quotes = quotesDaoImpl.getAllQuotes();
		if( quotesAll == null ){
			quotesAll = quotes;
		}
		return quotes;
	}

	public Quotes getQuote() {
		if( quotesAll == null ){
			quotesAll = getAllQuotes();
		}
		int length = quotesAll.size();
		int id = ((int)( Math.random()*length))%length;
		return quotesAll.get(id);
	}

}
