package com.neau.note.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.neau.note.dao.QuotesDao;
import com.neau.note.pojo.Quotes;

@Repository("quotesDaoImpl")
public class QuotesDaoImpl implements QuotesDao {

	@Resource
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int updateQutoes(Quotes quote) {
		int id = 0;
		try {
			sqlSessionTemplate.insert("Quotes.updateQuotes", quote);
			id = quote.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Quotes> getAllQuotes() {
		List<Quotes> quotes = null;
		try{
			quotes = sqlSessionTemplate.selectList("Quotes.getAllQuotes");
		} catch( Exception e ){
			e.printStackTrace();
		}
		return quotes;
	}

	@Override
	public String deleteQuotes(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
