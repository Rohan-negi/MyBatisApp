import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class myBatisUpdate {

	public static void main(String args[]) throws IOException {

		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();

		updateStudentDetails(session);
		verifyUpdatedInfo(session);
		session.commit();
		session.close();
	}

	private static void updateStudentDetails(SqlSession session) {
		// select a particular student using id
		Student student = (Student) session.selectOne("Student.getById", 2);
		System.out.println("Current details of the student are");
		System.out.println(student.toString());

		// Set new values to the mail and phone number of the student
		student.setEmail("arun867@gmail.com");
		student.setPhone(999999009);

		// Update the student record
		session.update("Student.update", student);
		System.out.println("Record updated successfully");
	}

	private static void verifyUpdatedInfo(SqlSession session) {
		Student student = session.selectOne("Student.getById", 1);
		System.out.println("Details of the student after update operation");
		System.out.println(student.toString());
	}
}