package com.company.persistence;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.*;

@Repository //==@Component
public class BookDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// insert
	public boolean insert(BookDTO insertDTO) {
		String sql = "insert into bookTBL values(?,?,?,?)";
		int result = jdbcTemplate.update(sql,insertDTO.getCode(),insertDTO.getTitle(),insertDTO.getWriter(),insertDTO.getPrice());
		return result > 0 ? true:false;
	}

	// delete
	public boolean delete(String code) {
			String sql = "delete from bookTBL where code=?";
			int result = jdbcTemplate.update(sql,code);
			return result > 0 ? true:false;
	}

	// update
	public boolean update(String code, int price) {
			String sql = "update bookTBL set price=? where code=?";
			int result = jdbcTemplate.update(sql,price,code);
			return result > 0 ? true:false;
	}

	// select
	public List<BookDTO> list() {
			String sql = "select * from bookTBL";
			return jdbcTemplate.query(sql, new BookRowMapper());
	}
	
	//검색
	public List<BookDTO> search(String criteria, String keyword){
			String sql = "select * from bookTBL where "+criteria+"=?";   
			return jdbcTemplate.query(sql, new BookRowMapper(),keyword); 
	}
	
}
