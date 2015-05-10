package com.neau.note.dao;

import java.util.List;
import java.util.Map;

import com.neau.note.pojo.Quotes;

public interface QuotesDao {

	public int updateQutoes(Quotes quote);
	
	public List<Quotes> getAllQuotes();
	
	public String deleteQuotes(int id);
}
