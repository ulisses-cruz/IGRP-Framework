package nosi.core.webapp.databse.helpers;


import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import nosi.core.config.Config;
import nosi.core.webapp.Core;
import nosi.core.webapp.databse.helpers.DatabaseMetadaHelper.Column;
import nosi.core.webapp.databse.helpers.ResultSet.Record;
import nosi.core.webapp.helpers.DateHelper;


/**
 * Emanuel
 * 21 Dec 2017
 */
public abstract class QueryHelper implements QueryInterface{

	protected String sql = "";
	protected String schemaName;
	protected String tableName;
	protected List<DatabaseMetadaHelper.Column> columnsValue;
	protected String connectionName;
	protected boolean isWhere = false;
	
	public QueryHelper(String connectionName) {
		this.columnsValue = new ArrayList<>();
		this.connectionName = Core.isNotNull(connectionName)?connectionName:Config.getBaseConnection();
	}	
	
	public QueryInterface where(String condition) {
		this.sql += " WHERE "+condition;
		return this;
	}
	
	protected QueryInterface filterWhere(String condition) {
		this.sql += condition;
		return this;
	}
	
	public QueryInterface where() {
		this.sql += " WHERE 1=1 ";
		return this;
	}
	
	public QueryInterface addLong(String columnName,Long value) {
		this.addColumn(columnName, value, Long.class);
		return this;
	}

	public QueryInterface addDouble(String columnName,Double value) {
		this.addColumn(columnName, value, Double.class);
		return this;
	}

	public QueryInterface addFloat(String columnName,Float value) {
		this.addColumn(columnName, value, Float.class);
		return this;
	}

	public QueryInterface addShort(String columnName,Short value) {
		this.addColumn(columnName, value, Short.class);
		return this;
	}

	public QueryInterface addDate(String columnName,String value,String format) {
		this.addColumn(columnName, value, java.sql.Date.class,format);
		return this;
	}
	
	public QueryInterface addDate(String columnName,String value) {
		return addDate(columnName, value, "yyyy-mm-dd");
	}
	
	public QueryInterface addDate(String columnName,java.sql.Date value) {
		return addDate(columnName, value, "yyyy-mm-dd");
	}
	
	public QueryInterface addDate(String columnName,java.sql.Date value,String format) {
		this.addColumn(columnName, value, java.sql.Date.class,format);
		return this;
	}
	
	public QueryInterface add(Column col,Object value) {
		this.addColumn(col.getName(), value,col.getType());
		return this;
	}
	
	public QueryInterface addString(String columnName,String value) {
		this.addColumn(columnName, value, String.class);
		return this;
	}
	
	public QueryInterface addInt(String columnName,Integer value) {
		this.addColumn(columnName, value, Integer.class);
		return this;
	}

	public QueryInterface addBinaryStream(String columnName,FileInputStream value) {
		this.addColumn(columnName, value, FileInputStream.class);
		return this;
	}
	public QueryInterface addBinaryStream(String columnName,InputStream value) {
		this.addColumn(columnName, value, InputStream.class);
		return this;
	}

    public QueryInterface addObject(String columnName,Object value) {
        this.addColumn(columnName, value, Object.class);
        return this;
    }
    
    public QueryInterface addTimestamp(String columnName,Timestamp value) {
        this.addColumn(columnName, value, Timestamp.class);
        return this;
    }

    public QueryInterface addArray(String columnName,Array value) {
        this.addColumn(columnName, value, Array.class);
        return this;
    }

    public QueryInterface addAsciiStream(String columnName,InputStream value) {
        this.addColumn(columnName, value, InputStream.class);
        return this;
    }

    public QueryInterface addClob(String columnName,Clob value) {
        this.addColumn(columnName, value, Clob.class);
        return this;
    } 

    public QueryInterface addBlob(String columnName,Blob value) {
        this.addColumn(columnName, value, Blob.class);
        return this;
    } 

    public QueryInterface addByte(String columnName,byte[] value) {
        this.addColumn(columnName, value, Byte[].class);
        return this;
    }  
    
    public QueryInterface addByte(String columnName,byte value) {
        this.addColumn(columnName, value, Byte.class);
        return this;
    }  
    
    public QueryInterface addBoolean(String columnName,boolean value) {
        this.addColumn(columnName, value, Boolean.class);
        return this;
    }  
    
    public QueryInterface addBigDecimal(String columnName,BigDecimal value) {
        this.addColumn(columnName, value, BigDecimal.class);
        return this;
    }
    
    public QueryInterface addTime(String columnName,Time value) {
        this.addColumn(columnName, value, Time.class);
        return this;
    }
    
	protected void addColumn(String name,Object value,Object type) {
		this.addColumn(name, value, type, null);
	}
	
	protected void addColumn(String name,Object value,Object type,String format) {
		Column c = new Column();
		c.setName(name);
		c.setDefaultValue(value);
		c.setType(type);
		c.setFormat(format);
		c.setAfterWhere(isWhere);
		this.columnsValue.add(c);
	}
	
	
	public String getSql() {
		if(this instanceof QueryInsert) {
			this.sql = this.getSqlInsert(this.getSchemaName(),this.getColumnsValue(), this.getTableName()) + this.sql;
		}
		else if(this instanceof QueryUpdate) {
			this.sql = this.getSqlUpdate(this.getSchemaName(),this.getColumnsValue(), this.getTableName()) + this.sql;
		}
		else if(this instanceof QueryDelete) {
			this.sql = this.getSqlDelete(this.getSchemaName(), this.getTableName()) + this.sql;
		}
		return sql;
	}

	public void setSql(String sql) {
		this.sql += sql;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public List<DatabaseMetadaHelper.Column> getColumnsValue() {
		return columnsValue;
	}

	public void setColumnsValue(List<DatabaseMetadaHelper.Column> columnsValue) {
		this.columnsValue = columnsValue;
	}

	public String getSqlInsert(String schemaName, List<DatabaseMetadaHelper.Column> colmns, String tableName) {
		tableName = (schemaName!=null && !schemaName.equals(""))?schemaName+"."+tableName:tableName;//Adiciona schema
		String inserts = "";
		String values = "";
		for(DatabaseMetadaHelper.Column col:colmns) {
			if(!col.isAutoIncrement()) {
				inserts += col.getName().toLowerCase()+",";
				values += ":"+col.getName().toLowerCase()+",";
			}
		}	
		inserts = inserts.substring(0, inserts.length()-1);
		values = values.substring(0, values.length()-1);
		return "INSERT INTO "+tableName+" ("+inserts+") VALUES ("+values+")";
	}
	

	public String getSqlUpdate(String schemaName, List<DatabaseMetadaHelper.Column> colmns, String tableName) {
		tableName = (schemaName!=null && !schemaName.equals(""))?schemaName+"."+tableName:tableName;//Adiciona schema
		String updates = "";
		for(DatabaseMetadaHelper.Column col:colmns) {
			if(!col.isAutoIncrement() && !col.isAfterWhere()) {
				updates += col.getName().toLowerCase()+"=:"+col.getName().toLowerCase()+",";
			}
		}	
		updates = updates.substring(0, updates.length()-1);
		return "UPDATE "+tableName +" SET "+updates;
	}
	
	public String getSqlDelete(String schemaName, String tableName) {
		tableName = (schemaName!=null && !schemaName.equals(""))?schemaName+"."+tableName:tableName;//Adiciona schema
		return "DELETE FROM "+tableName;
	}
	
	
	public void setParameter(Query query, Object value, Column col) {
		if(col.getType().equals(java.lang.Integer.class)) {
			query.setParameter(col.getName(),Integer.parseInt(value.toString()));
		}else if(col.getType().equals(java.lang.Double.class)){
			query.setParameter(col.getName(), Double.parseDouble(value.toString()));
		}else if(col.getType().equals(java.lang.Float.class)){
			query.setParameter(col.getName(), Float.parseFloat(value.toString()));
		}else if(col.getType().equals(java.lang.Character.class)){
			query.setParameter(col.getName(), (Character)value);
		}else if(col.getType().equals(java.lang.Long.class)){
			query.setParameter(col.getName(), (Long)value);
		}else if(col.getType().equals(java.lang.Short.class)){
			query.setParameter(col.getName(), (Short)value);
		}else if(col.getType().equals(java.sql.Date.class)){
			if((value instanceof String) && Core.isNotNull(value))
				query.setParameter(col.getName(),Core.ToDate(value.toString(), col.getFormat()));
			else
				query.setParameter(col.getName(),value);
		}else if(col.getType().equals(java.lang.String.class) || col.getType().equals(java.lang.Character.class) && Core.isNotNull(value)){
			query.setParameter(col.getName(),value.toString());
		}else {
			query.setParameter(col.getName(),value);
		}
	}

	public void setParameter(NamedParameterStatement query, Object value, Column col) throws SQLException {
		
		if(col.getType().equals(java.lang.Integer.class)) {
			query.setInt(col.getName(),Integer.parseInt(value.toString()));
		}
		if(col.getType().equals(java.lang.Integer.class)) {
			query.setInt(col.getName(),Integer.parseInt(value.toString()));
		}else if(col.getType().equals(java.lang.Double.class)){
			query.setDouble(col.getName(), Double.parseDouble(value.toString()));
		}else if(col.getType().equals(java.lang.Float.class)){
			query.setFloat(col.getName(), Float.parseFloat(value.toString()));
		}else if(col.getType().equals(java.lang.Long.class)){
			query.setLong(col.getName(), (Long)value);
		}else if(col.getType().equals(java.lang.Short.class)){
			query.setShort(col.getName(), (Short)value);
		}else if(col.getType().equals(java.sql.Date.class) && Core.isNotNull(value)){
			query.setDate(col.getName(),DateHelper.formatDate(value.toString(), col.getFormat()));
		}else if(col.getType().equals(java.lang.String.class) || col.getType().equals(java.lang.Character.class) && Core.isNotNull(value)){
			query.setString(col.getName(),value.toString());
		}else {
			query.setObject(col.getName(), value);
		}
	}
	
//	@Override
//	public ResultSet execute() {
//		ResultSet r = new ResultSet();
//		String sql = this.getSql();
//		if(this.getConnectionName()!=null) {
//			EntityManagerFactory entityManagerFactory = HibernateUtils.getSessionFactory(this.getConnectionName());
//			EntityManager em = entityManagerFactory.createEntityManager();			
//			if(this instanceof QueryInsert) {				
//				if(retuerningKeys!=null) {
//					sql += " returning "+ String.join(",",(String[])retuerningKeys.toArray());	
//				}else {
//					sql += " returning *";
//				}
//				Query q = em.createNativeQuery(sql,Tuple.class);
//				try {
//					this.setParameters(q);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				r.setTuple((Tuple) q.getSingleResult());
//			}			
//			else{
//				Query q = em.createNativeQuery(sql);
//				try {
//					this.setParameters(q);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				em.getTransaction().begin();
//				q.executeUpdate();
//				em.getTransaction().commit();
//			}
//			em.close();
//		}
//		return r;
//	}
	
	
	//
//	private void setParameters(Query q) throws SQLException {
//		for(DatabaseMetadaHelper.Column col:this.getColumnsValue()) {	
//			 if(col.getDefaultValue()!=null) {
//				 this.setParameter(q,col.getDefaultValue(),col);					
//			 }else {
//				 q.setParameter(col.getName(), null);
//			 }
//		}
//	}
	
	@Override
	public ResultSet execute() {
		ResultSet r = new ResultSet();
		Connection conn =nosi.core.config.Connection.getConnection(this.getConnectionName());
		if(conn!=null) {
			if(this instanceof QueryInsert) {
				try {
					NamedParameterStatement q = new NamedParameterStatement(conn ,this.getSql(),Statement.RETURN_GENERATED_KEYS);
					this.setParameters(q);	
					Core.log("SQL:"+q.getSql());
					r.setSql(q.getSql());
					r.setKeyValue(q.executeInsert(this.tableName));
				} catch (SQLException e) {
					r.setError(e.getMessage());
					Core.log(e.getMessage());
					e.printStackTrace();
				}
			}else {
				try {
					NamedParameterStatement q = new NamedParameterStatement(conn, this.getSql());
					this.setParameters(q);
					r.setSql(q.getSql());
					Core.log("SQL:"+q.getSql());
					r.setKeyValue( q.executeUpdate());
				} catch (SQLException e) {
					r.setError(e.getMessage());
					Core.log(e.getMessage());
				}
			}
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(conn!=null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return r;
	}
	
	private void setParameters(NamedParameterStatement q) throws SQLException {
		for(DatabaseMetadaHelper.Column col:this.getColumnsValue()) {	
			 if(col.getDefaultValue()!=null) {
				 this.setParameter(q,col.getDefaultValue(),col);					
			 }else {
				 q.setObject(col.getName(), null);
			 }
		}
	}



	public List<Tuple> getResultList() {
		throw new UnsupportedOperationException();
	}
	
	public Tuple getSigleResult() {
		throw new UnsupportedOperationException();
	}
	
	public TypedQuery<?> getSingleResult(){
		throw new UnsupportedOperationException();
	}
	
	public <T> List<T> getResultList(Class<T> type){
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhere(String name, String operator, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhere(String name, String operator, String[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhere(String name, String operator, Integer value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhere(String name, String operator, Float value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhere(String name, String operator, Double value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhere(String name, String operator, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhere(String name, String operator, String[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhere(String name, String operator, Integer value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhere(String name, String operator, Float value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhere(String name, String operator, Double value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface having(String name, String operator, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orderBy(String[]... orderByNames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryInterface groupBy(String... groupByNames) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface between(String name, Object value1, Object value2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface notBetween(String name, Object value, Object value2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryInterface exists(String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface innerJoin(String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface leftJoin(String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface rightJoin( String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface outerJoin( String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface selfJoin( String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface union() {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface unionAll() {
		throw new UnsupportedOperationException();
	}
	@Override
	public QueryInterface andWhereNotNull(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhereIsNull(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhereNotNull(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhereIsNull(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhere(String name, String operator, Integer[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhere(String name, String operator, Double[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface andWhere(String name, String operator, Float[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhere(String name, String operator, Integer[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhere(String name, String operator, Double[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface orWhere(String name, String operator, Float[] values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface having(String name, String operator, Integer value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface having(String name, String operator, Double value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface having(String name, String operator, Float value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface from(String tables) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface select(String collumns) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface innerJoin(String table1, String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface leftJoin(String table1, String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface rightJoin(String table1, String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface outerJoin(String table1, String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryInterface selfJoin(String table1, String table2, String key1, String key2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Record getRecordList() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Record getSigleRescord() {
		throw new UnsupportedOperationException();
	}
}
