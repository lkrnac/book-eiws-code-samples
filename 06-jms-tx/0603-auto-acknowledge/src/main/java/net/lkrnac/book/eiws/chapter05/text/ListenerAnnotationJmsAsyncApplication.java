package net.lkrnac.book.eiws.chapter05.text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ListenerAnnotationJmsAsyncApplication {
  public static void main(String[] args) throws Exception {
    Class.forName("org.h2.Driver");
    Connection conn = DriverManager.getConnection("jdbc:h2:./test", "sa", "");

    Statement statement = conn.createStatement();
    statement.execute("drop table text_table");
    statement.execute("create table text_table (TEXT varchar(30))");

    statement.close();

    PreparedStatement pStatement = conn.prepareStatement("");
    pStatement.execute();
    pStatement.close();
    conn.close();

    SpringApplication.run(ListenerAnnotationJmsAsyncApplication.class, args);
  }
}
