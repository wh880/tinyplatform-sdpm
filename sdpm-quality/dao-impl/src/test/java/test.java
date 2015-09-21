/*import org.apache.commons.dbcp.BasicDataSource;
import org.tinygroup.jdbctemplatedslsession.SimpleDslSession;
import org.tinygroup.sdpm.quality.dao.impl.BugDaoImpl;

import junit.framework.TestCase;

public class test extends TestCase {

	BugDaoImpl bugdaoimpl = new BugDaoImpl();	
	
	public void setUp(){
		BasicDataSource source =new BasicDataSource();
		source.setDriverClassName("com.mysql.jdbc.Driver");
		source.setUrl("jdbc:mysql://127.0.0.1/hsjry?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8");
		source.setUsername("root");
		source.setPassword("123456");
		bugdaoimpl.setDslSession(new SimpleDslSession(source));
	}
}*/
