package com.example.demo.config;




import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfig {

    @Bean
    public DataModel getMySQLDataModel(){
        MysqlDataSource dataSource=new MysqlDataSource();

        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setDatabaseName("shopping");
        return new MySQLJDBCDataModel(dataSource,"goods_rate","userId","gId","rate","time");
    }

}
