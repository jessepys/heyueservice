package com.heyue.service.framework.dao;


import com.heyue.service.framework.DataSourceManager;
import com.heyue.service.framework.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.MappedSuperclass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

@MappedSuperclass
public class BaseDao {
    private Logger logger = LoggerFactory.getLogger(BaseDao.class);

	@Autowired
	private DataSourceManager dataSourceManager;
	
	protected String getSortString(Pageable page) {
		StringBuffer sortBuffer = new StringBuffer("");
		Sort sort = page.getSort();
		if(sort!=null){
			Iterator<Order> orders = sort.iterator();
			int i=1;
			while(orders.hasNext()){
				Order order = orders.next();
				if(i==1){
					sortBuffer.append(" order by "+order.getProperty()+" "+order.getDirection().name());
				}
				else{
					sortBuffer.append(" ,"+order.getProperty()+" "+order.getDirection().name());
				}
			}
		}
		return sortBuffer.toString();
	}

	protected static RowMapper<Map<String, Object>> VK_ROW_MAPPER = new RowMapper<Map<String, Object>>() {
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int arg1)
					throws SQLException {
				return DBUtil.generateResultMap(rs);
			}
	};
		
	protected static ResultSetExtractor<Integer> COUNT_RESULT_SET_EXTRACTOR = new ResultSetExtractor<Integer>() {
		public Integer extractData(ResultSet rs)
				throws SQLException, DataAccessException {
			return DBUtil.extractIntegerData(rs);
		}
	};

    public JdbcTemplate getOdsTemplate() {
        return dataSourceManager.getOdsJdbcTemplate();
    }

	public JdbcTemplate getOltpTemplate() {
		return dataSourceManager.getOdsJdbcTemplate();
	}
}
