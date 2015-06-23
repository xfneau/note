package com.neau.note.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.neau.note.dao.SynchronizationDataDao;
import com.neau.note.pojo.Note;
import com.neau.note.pojo.Sms;

@Repository("synchronizationDao")
public class SynchronizationDaoImpl implements SynchronizationDataDao {

	@Resource
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int backups(List<Note> list) {
		int id=0;
		try{
			id = sqlSessionTemplate.insert("Note.insertNoteByUserId", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Note> recover(int userid) {
		List<Note> list = new ArrayList<Note>();
		try{
			list = sqlSessionTemplate.selectList("Note.getNoteByUserId", userid);
		}catch(Exception e){
			sqlSessionTemplate.rollback(true);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void remove(int userid) {
		try{
			sqlSessionTemplate.delete("Note.remove", userid);
		}catch(Exception e){
			sqlSessionTemplate.rollback(true);
			e.printStackTrace();
		}
	}

	@Override
	public void _backUps(List<Sms> list) {
		try{
			System.out.println(list);
			if( list != null ){
				for( Sms s:list ){
					if( _getTest(s).size() < 1 ){
						sqlSessionTemplate.insert("Note.insertTest", s);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public List<Sms> _getTest(Sms s) {
		List<Sms> list = new ArrayList<Sms>();
		try{
			list = sqlSessionTemplate.selectList("Note.getTest", s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void _backUps(String list) {
		try{
			sqlSessionTemplate.insert("Note.insertTestList", list);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
