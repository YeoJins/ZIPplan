// Source code is decompiled from a .class file using FernFlower decompiler.
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
   public DBUtil() {
   }

   public static Connection getConnection() throws SQLException {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException var3) {
         var3.printStackTrace();
      }

      String var0 = "jdbc:mysql://localhost:3306/zipplan?serverTimezone=Asia/Seoul";
      String var1 = "root";
      String var2 = "1340";
      return DriverManager.getConnection(var0, var1, var2);
   }
}
