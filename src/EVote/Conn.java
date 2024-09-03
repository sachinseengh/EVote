package EVote;
import java.sql.*;
public class Conn {
    Connection c;
    Statement s;
        
    public Conn(){
        try{
       
       c= DriverManager.getConnection("jdbc:mysql://localhost/evote","root","");
       s=c.createStatement();

  
    }catch(Exception e){
        System.out.println(e);
    }
    }
    
    public static void main(String args[]) {
        new Conn();
    }
}
