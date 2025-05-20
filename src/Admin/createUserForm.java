/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import computershop.loginForm;
import config.dbConnector;
import config.passwordHasher;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Karystel
 */
public class createUserForm extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard
     */
    public createUserForm() {
        initComponents();
    }
    
     public String destination =""; 
    File selectedFile;
    public String oldpath;
    public String path;
    
    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/userimages", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
    
    public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
    ImageIcon MyImage = null;
        if(ImagePath !=null){
            MyImage = new ImageIcon(ImagePath);
        }else{
            MyImage = new ImageIcon(pic);
        }
        
    int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    return image;
}
      public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
    
      }
    public static String em, user;
    
    public boolean duplicateCheck(){
        
        dbConnector dbc = new dbConnector();
        
        try{
        String query = "SELECT * FROM tbl_user  WHERE u_username = '" +username.getText()+ "' OR u_email = '" +email.getText()+ "'";
            ResultSet resultSet = dbc.getData(query);
            
            if(resultSet.next()){
                em = resultSet.getString("u_email");
                if(em.equals(email.getText())){
                    JOptionPane.showMessageDialog(null, "Email is Already Used!");
                    email.setText("");
                }
                user = resultSet.getString("u_username");
                if(user.equals(username.getText())){
                    JOptionPane.showMessageDialog(null, "Username is Already Used!");
                    username.setText("");
                }
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println(""+ex);
            return false;
        }
        
    }
    public boolean updateCheck(){
        dbConnector dbc = new dbConnector();    
        try{
        String query = "SELECT * FROM tbl_user  WHERE (u_username = '" +username.getText()+ "' OR u_email = '" +email.getText()+ "') AND u_id != '"+uid.getText()+"'";
            ResultSet resultSet = dbc.getData(query);
            
            if(resultSet.next()){
                em = resultSet.getString("u_email");
                if(em.equals(email.getText())){
                    JOptionPane.showMessageDialog(null, "Email is Already Used!");
                    email.setText("");
                }
                user= resultSet.getString("u_username");
                if(user.equals(username.getText())){
                    JOptionPane.showMessageDialog(null, "Username is Already Used!");
                    username.setText("");
                }
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println(""+ex);
            return false;
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Container = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        remove = new javax.swing.JButton();
        select = new javax.swing.JButton();
        cpas = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pw = new javax.swing.JPasswordField();
        ans = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        sq = new javax.swing.JComboBox<>();
        clear = new javax.swing.JButton();
        add = new javax.swing.JButton();
        us = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        uid = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cn = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ut = new javax.swing.JComboBox<>();
        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Container.setBackground(new java.awt.Color(255, 255, 255));
        Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        jPanel1.setLayout(null);
        jPanel1.add(image);
        image.setBounds(11, 16, 270, 270);

        Container.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 290, 300));

        remove.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        remove.setText("REMOVE");
        remove.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 0)));
        remove.setEnabled(false);
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        Container.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 520, 130, 50));

        select.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        select.setText("SELECT");
        select.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 0)));
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        Container.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 520, 130, 50));

        cpas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cpas.setText("Show password");
        cpas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpasActionPerformed(evt);
            }
        });
        Container.add(cpas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 650, -1, 20));

        jLabel18.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel18.setText("QUESTION:");
        Container.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, 130, 70));

        jLabel17.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel17.setText("ANSWER:");
        Container.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 590, 130, 70));

        pw.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        Container.add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, 210, 30));

        ans.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ans.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        ans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ansActionPerformed(evt);
            }
        });
        Container.add(ans, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 610, 210, 30));

        jLabel15.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel15.setText("USER STATUS:");
        Container.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 130, 70));

        refresh.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        refresh.setText("REFRESH");
        refresh.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 0)));
        Container.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 130, 50));

        cancel.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        cancel.setText("CANCEL");
        cancel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 0)));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        Container.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 140, 50));

        update.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        update.setText("UPDATE");
        update.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 0)));
        update.setEnabled(false);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        Container.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 140, 50));

        delete.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        delete.setText("DELETE");
        delete.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 0)));
        Container.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 130, 50));

        sq.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is your favorite food?", "What is your mother's maiden name?", "What was the name of your first school?", "What is the name of your first pet?", "What city were you born in?" }));
        sq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        Container.add(sq, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 570, 210, 30));

        clear.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        clear.setText("CLEAR");
        clear.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 0)));
        Container.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 130, 50));

        add.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        add.setText("ADD");
        add.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 0)));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        Container.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 130, 50));

        us.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        us.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));
        us.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        Container.add(us, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 210, 30));

        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel14.setText("PASSWORD:");
        Container.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 510, 130, 70));

        uid.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        uid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        uid.setEnabled(false);
        uid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidActionPerformed(evt);
            }
        });
        Container.add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 100, 30));

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel11.setText("USER ID:");
        Container.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 100, 30));

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel7.setText("USERNAME:");
        Container.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 100, 30));

        username.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        Container.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 210, 30));

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel9.setText("EMAIL:");
        Container.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 120, 50));

        email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        Container.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 210, 30));

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel5.setText("USER TYPE:");
        Container.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 120, 70));

        cn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        Container.add(cn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, 210, 30));

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel10.setText("CONTACT:");
        Container.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 120, 70));

        ut.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "USER" }));
        ut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));
        Container.add(ut, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, 210, 30));

        Header.setBackground(new java.awt.Color(51, 51, 0));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CREATE USER FORM");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 970, -1));

        Container.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 110));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cream and Brown Retro Cafe Logo.png"))); // NOI18N
        Container.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 500, 180));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cream and Brown Retro Cafe Logo.png"))); // NOI18N
        Container.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel16.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jLabel16.setText("USER STATUS:");
        Container.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 130, 70));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cream and Brown Retro Cafe Logo.png"))); // NOI18N
        Container.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, 470, 360));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cream and Brown Retro Cafe Logo.png"))); // NOI18N
        Container.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 470, 360));

        getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void uidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        usersForm usf = new usersForm();
        usf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
       if (username.getText().isEmpty() || email.getText().isEmpty() || cn.getText().isEmpty()
        || pw.getText().isEmpty() || cpas.getText().isEmpty() || ans.getText().isEmpty()) {

    JOptionPane.showMessageDialog(null, "All fields are required!");

} else if (!email.getText().endsWith("@gmail.com")) {
    JOptionPane.showMessageDialog(null, "Invalid email format.");
    email.setText("");

} else if (!isValidContactNumber(cn.getText())) {
    JOptionPane.showMessageDialog(null, "Contact number must contain only digits and be 11 digits long.");
    cn.setText("");

} else if (pw.getText().length() < 8) {
    JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.");
    pw.setText("");
    cpas.setText("");
} else if (duplicateCheck()) {
    JOptionPane.showMessageDialog(null, "Duplicate record exists!");

} else {
    dbConnector dbc = new dbConnector();
    String hashedPassword = "";
    String hashedAnswer = "";

    try {
        hashedPassword = passwordHasher.hashPassword(pw.getText());
        hashedAnswer = passwordHasher.hashPassword(ans.getText());
    } catch (NoSuchAlgorithmException ex) {
        JOptionPane.showMessageDialog(null, "Hashing error: " + ex.getMessage());
        return;
    }

    String sql = "INSERT INTO tbl_user (u_username, u_email, u_contact, u_usertype, u_password, u_status, u_question, u_answer, u_image) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement pst = dbc.connect.prepareStatement(sql);
        pst.setString(1, username.getText());
        pst.setString(2, email.getText());
        pst.setString(3, cn.getText());
        pst.setString(4, ut.getSelectedItem().toString());
        pst.setString(5, hashedPassword);
        pst.setString(6, us.getSelectedItem().toString());
        pst.setString(7, sq.getSelectedItem().toString());
        pst.setString(8, hashedAnswer);
        pst.setString(9, destination); // path to the saved image

        int inserted = pst.executeUpdate();

        if (inserted > 0) {
            // Save image to destination folder
            try {
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Image save error: " + ex.getMessage());
                return;
            }

            JOptionPane.showMessageDialog(null, "Registration Success!");
            usersForm usf = new usersForm();
            usf.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Connection Error!");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
    }
}


    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
       if (username.getText().isEmpty() || email.getText().isEmpty() || cn.getText().isEmpty()
        || pw.getText().isEmpty() || cpas.getText().isEmpty() || ans.getText().isEmpty()) {

    JOptionPane.showMessageDialog(null, "All fields are required!");

} else if (pw.getText().length() < 8) {
    JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.");
    pw.setText("");
    cpas.setText("");

} else if (!email.getText().endsWith("@gmail.com")) {
    JOptionPane.showMessageDialog(null, "Invalid email format.");
    email.setText("");

} else if (!isValidContactNumber(cn.getText())) {
    JOptionPane.showMessageDialog(null, "Contact number must contain only digits and be 11 digits long.");
    cn.setText("");

} else if (updateCheck()) {
    JOptionPane.showMessageDialog(null, "Duplicate record exists!");

} else {
    dbConnector dbc = new dbConnector();

    try {
        String hashedPassword = passwordHasher.hashPassword(pw.getText());
        String hashedAnswer = passwordHasher.hashPassword(ans.getText());
        String question = sq.getSelectedItem().toString();

        // Example path: you should already have selectedFile and destination defined
        Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);

        String sql = "UPDATE tbl_user SET u_username = ?, u_email = ?, u_contact = ?, u_usertype = ?, "
                   + "user_password = ?, u_status = ?, u_question = ?, u_answer = ?, u_image = ? "
                   + "WHERE u_id = ?";

        PreparedStatement pst = dbc.connect.prepareStatement(sql);
        pst.setString(1, username.getText());
        pst.setString(2, email.getText());
        pst.setString(3, cn.getText());
        pst.setString(4, ut.getSelectedItem().toString());
        pst.setString(5, hashedPassword);
        pst.setString(6, us.getSelectedItem().toString());
        pst.setString(7, question);
        pst.setString(8, hashedAnswer);
        pst.setString(9, destination);
        pst.setString(10, uid.getText());

        int updated = pst.executeUpdate();

        if (updated > 0) {
            JOptionPane.showMessageDialog(null, "User updated successfully!");
            usersForm usf = new usersForm();
            usf.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Update failed!");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }}}
// Utility method
private boolean isValidContactNumber(String contact) {
    return contact.matches("\\d{11}");



    }//GEN-LAST:event_updateActionPerformed

    private void cpasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpasActionPerformed
        if(cpas.isSelected()){
            pw.setEchoChar((char)0);         
        }
        else {
            pw.setEchoChar('*');
            
        }
    }//GEN-LAST:event_cpasActionPerformed

    private void ansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ansActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        remove.setEnabled(false);
       select.setEnabled(true);
       image.setIcon(null);
       destination="";
       path="";
    }//GEN-LAST:event_removeActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
      JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        selectedFile = fileChooser.getSelectedFile();
                        destination = "src/userimages/" + selectedFile.getName();
                        path  = selectedFile.getAbsolutePath();
                        
                        
                        if(FileExistenceChecker(path) == 1){
                          JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                            destination = "";
                            path="";
                        }else{
                            image.setIcon(ResizeImage(path, null, image));
                            select.setEnabled(false);
                            remove.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        System.out.println("File Error!");
                    }
                }
    }//GEN-LAST:event_selectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createUserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JPanel Header;
    public javax.swing.JButton add;
    public javax.swing.JTextField ans;
    private javax.swing.JButton cancel;
    private javax.swing.JButton clear;
    public javax.swing.JTextField cn;
    private javax.swing.JCheckBox cpas;
    private javax.swing.JButton delete;
    public javax.swing.JTextField email;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPasswordField pw;
    private javax.swing.JButton refresh;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    public javax.swing.JComboBox<String> sq;
    public javax.swing.JTextField uid;
    public javax.swing.JButton update;
    public javax.swing.JComboBox<String> us;
    public javax.swing.JTextField username;
    public javax.swing.JComboBox<String> ut;
    // End of variables declaration//GEN-END:variables
}
