package xyz.juehen.jichu.util;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DBHelper {

    private static final String url = "jdbc:mysql://localhost:3306/glory";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "root";
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    private void getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE,e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
    }

    private void closeAll(){
        try {
            if(rs!=null)
                rs.close();
            if(pst!=null)
                pst.close();
            if(connection!=null)
                connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
    }

    public boolean executeUpdate(String sql,Object[] params){
        try {
            getConnection();
            pst=connection.prepareStatement(sql);//装载sql语句
            if(params!=null && params.length > 0)
                for(int i=0;i<params.length;i++)
                    pst.setObject(i+1, params[i]);
            return pst.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
            return false;
        }finally{
            closeAll();
        }
    }

    public <T> List<T> executeQuery(String sql,Object[] params,Class<T> cls) throws Exception{
        List<T> data= new ArrayList<>();
        try {
            getConnection();
            pst=connection.prepareStatement(sql);//装载sql语句
            if(params!=null && params.length > 0)
                for(int i=0;i<params.length;i++)
                    pst.setObject(i+1, params[i]);
            rs=pst.executeQuery();
            //把查询出来的记录封装成对应的实体类对象
            ResultSetMetaData rsd=rs.getMetaData(); //获得列对象,通过此对象可以得到表的结构，包括，列名，列的个数，列的数据类型
            while(rs.next()){
                T m=cls.newInstance();
                for(int i=0;i<rsd.getColumnCount();i++){
                    String col_name=rsd.getColumnName(i+1); //获得列名
                    Object value=rs.getObject(col_name); //获得列所的对应值
                    Field field=cls.getDeclaredField(col_name);
                    field.setAccessible(true); //给私有属性设置可访问权
                    field.set(m, value); //给对象的私有属性赋值
                }
                data.add(m);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }finally{
            closeAll();
        }
        return data;
    }
}
