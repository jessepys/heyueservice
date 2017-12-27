package com.heyue.service.framework.util;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DBUtil {
	public static final String IGNORE_INDEX_MERCHANT_ID = "ignore index(idx_merchant_id)";
	
	public static final String TABLE_FASTPAY_PAID = "t_fastpay_paid";
	public static final String TABLE_FASTPAY = "t_fastpay";
	
	public static int extractIntegerData(ResultSet rs)
			throws SQLException {
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return 0;
		}
	}
	
	public static Map<String, Object> generateResultMap(ResultSet rs) throws SQLException {
		Map<String, Object> result = new HashMap<String, Object>();
		if (rs != null) {
			ResultSetMetaData rsMeta = rs.getMetaData();
			if (rsMeta != null) {
				for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
					String label = rsMeta.getColumnLabel(i);
					result.put(label, rs.getString(label));
				}
			}
		}
			
		return result;
	}
	
	public static String getString(ResultSet rs, String[] columnLabels, String columnLabel) throws SQLException {
		if (Arrays.binarySearch(columnLabels, columnLabel) >= 0) return rs.getString(columnLabel);
		else return null;
	}
	
	public static BigDecimal getBigDecimal(ResultSet rs, String[] columnLabels, String columnLabel) throws SQLException {
		if (Arrays.binarySearch(columnLabels, columnLabel) >= 0) return rs.getBigDecimal(columnLabel);
		else return null;
	}
	
	public static Timestamp getTimestamp(ResultSet rs, String[] columnLabels, String columnLabel) throws SQLException {
		if (Arrays.binarySearch(columnLabels, columnLabel) >= 0) return rs.getTimestamp(columnLabel);
		else return null;
	}
	
	public static Integer getInteger(ResultSet rs, String[] columnLabels, String columnLabel) throws SQLException {
		if (Arrays.binarySearch(columnLabels, columnLabel) >= 0) return rs.getInt(columnLabel);
		else return null;
	}
	
	public static int getInt(ResultSet rs, String[] columnLabels, String columnLabel) throws SQLException {
		Integer result = getInteger(rs, columnLabels, columnLabel);
		return result == null ? 0 : result;
	}
	
	public static Boolean getBoolean(ResultSet rs, String[] columnLabels, String columnLabel) throws SQLException {
		if (Arrays.binarySearch(columnLabels, columnLabel) >= 0) return rs.getBoolean(columnLabel);
		else return null;
	}
	
	public static boolean getBool(ResultSet rs, String[] columnLabels, String columnLabel) throws SQLException {
		Boolean result = getBoolean(rs, columnLabels, columnLabel);
		return result == null ? false : result;
	}
	
	public static Long getLong(ResultSet rs, String[] columnLabels, String columnLabel) throws SQLException {
		Long res = null;
		if (Arrays.binarySearch(columnLabels, columnLabel) >= 0) 
			res = rs.getLong(columnLabel);
		return res == null ? 0 : res;
	}
}
