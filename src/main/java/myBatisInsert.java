import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class myBatisInsert {

	public static void main(String args[]) throws IOException {

		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();

		insertStudentData(session);
		session.commit();
		session.close();
	}

	private static void insertStudentData(SqlSession session) {
		// Create a new student object
		Student student = new Student("Arun", "ECE", 70, 984767222, "aman89@gmail.com");

		// Insert student data
		session.insert("Student.insert", student);
		System.out.println("record inserted successfully");
	}
}