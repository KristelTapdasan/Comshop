package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Karystel
 */
public class dbConnector {
    
    private Connection connect;
    
    public dbConnector(){
        try{
            connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/computershop", "root", "");
        }catch(SQLException ex){
            System.out.println("Can't connect to database: "+ex.getMessage());
                    
        }
    }
     
    //Function to retrieve data
        public ResultSet getData(String sql) throws SQLException{
            Statement stmt = connect.createStatement();
            ResultSet rst = stmt.executeQuery(sql);
            return rst;
        }
        
        //Function to save data
    public boolean insertData(String sql){
     try{
        PreparedStatement pst = connect.prepareStatement(sql);
        pst.executeUpdate();
        System.out.println("Inserted Successfully!");
        pst.close();
        return true;
        }catch(SQLException ex){
        System.out.println("Connection Error:"+ex);
        return false;
        
    }
    }
public boolean isEmailExists(String email) {
    String query = "SELECT COUNT(*) FROM tbl_user WHERE u_email = ?";
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computershop", "root", "");
         PreparedStatement pst = con.prepareStatement(query)) {
        
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0;  // If count > 0, email exists
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return false; // Default return if error occurs
}
    public boolean isUsernameExists(String username) {
    String query = "SELECT COUNT(*) FROM tbl_user WHERE u_username = ?";
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computershop", "root", "");
         PreparedStatement pst = con.prepareStatement(query)) {
        
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0;  // If count > 0, email exists
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return false; // Default return if error occurs
}
    public boolean isPCExists(String pcNumber) {
    boolean exists = false;
    String query = "SELECT * FROM tbl_user WHERE u_pcnumber = ?";
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computershop", "root", "");
        PreparedStatement pst = con.prepareStatement(query)) {
            
        pst.setString(1, pcNumber);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            exists = true; // PC number already exists
        }
        
        rs.close();
        pst.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return exists;
}



 public int insertUser(String username, String em, String type, String pc, String pass) {
    String query = "INSERT INTO tbl_user (u_username, u_email, u_usertype, u_pcnumber, u_password, u_status) VALUES (?, ?, ?, ?, ?, 'Pending')";
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computershop", "root", "");
         PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        
        pst.setString(1, username);
        pst.setString(2, em);
        pst.setString(3, type);
        pst.setString(4, pc);
        pst.setString(5, pass);
      
        
        int rowsInserted = pst.executeUpdate(); // Execute the insert query
        return rowsInserted; // Return the number of rows inserted
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return 0; // Return 0 if insertion fails
}
 public int insertUser(String fname, String lname, String email, String contact, String type, String username, String hashedPassword) {
    int rowsInserted = 0;
    String query = "INSERT INTO tbl_user (u_username, u_email, u_usertype, u_pcnumber, u_password, u_status) VALUES (?, ?, ?, ?, ?, 'Pending')";
    
    try {
        PreparedStatement pst = this.connect.prepareStatement(query); // Ensure `conn` is a valid connection
        pst.setString(1, fname);
        pst.setString(2, lname);
        pst.setString(3, email);
        pst.setString(4, contact);
        pst.setString(5, type);
        pst.setString(6, username);
        pst.setString(7, hashedPassword);
        
        rowsInserted = pst.executeUpdate(); // Executes the query and returns affected rows
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return rowsInserted; // Return the number of inserted rows (should be 1 if successful)

 }
 
public void closeConnection() {
    try {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   
    
}
