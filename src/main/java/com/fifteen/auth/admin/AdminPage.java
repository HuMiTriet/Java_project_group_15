package com.fifteen.auth.admin;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

import com.fifteen.database.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.commons.dbutils.DbUtils;

/**
 * Admin GUI where the admin has access to all the users in the database and
 * thereby has the power to edit/delete users
 * and change their passwords in case the user forgets their password
 *
 * @author Ante Maric 1273904
 */

public class AdminPage extends JFrame {
  private JPanel adminPanel;
  private JButton editButton;
  private JButton deleteButton;
  private JTable users;
  private JLabel Database;
  private JScrollPane dbTable;
  private JButton refreshButton;
  private JButton applyChangesButton;
  private JTextField editEmail;
  private JTextField editUsername;
  private JFrame frame;

  public AdminPage() {

    DefaultTableModel model = new DefaultTableModel() {
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    users.setAutoCreateRowSorter(true);
    users.setFillsViewportHeight(true);
    users.setPreferredScrollableViewportSize(new Dimension(550, 200));
    users.setModel(model);

    frame = new JFrame("Admin frame");
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(600, 300));
    frame.setResizable(false);

    // adding the panel
    frame.add(adminPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    model.addColumn("EMAIL");
    model.addColumn("USERNAME");
    model.addColumn("IS ADMIN?");

    DefaultTableModel tblModel = (DefaultTableModel) users.getModel();

    try {
      // create connection to database
      DBMethod.createConnection();
      Statement st = DBConnection.getConnection().createStatement();
      // sql query
      // executeQuery("select * from userdb");
      ResultSet rs = st.executeQuery("SELECT * FROM userdb");

      while (rs.next()) {
        // data will be added until finish
        String email = rs.getString("email");
        String username = rs.getString("username");
        String is_admin = rs.getString("is_admin");

        // string array to store data into jtable
        String tbData[] = new String[]{email, username, is_admin};
        // System.out.println(Arrays.toString(tbData));

        // add string array data into jtable
        tblModel.addRow(tbData);
      }

      DBMethod.closeConnection();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }


    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          DBMethod.createConnection();
          // work with the row selected in the db
          int row = users.getSelectedRow();
          String emailRow = users.getValueAt(row, 0).toString();
          PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("DELETE FROM userdb WHERE EMAIL = ?");
          preparedStatement.setString(1, emailRow);
          preparedStatement.executeUpdate();
          System.out.println("USER SUCCESSFULLY DELETED");
          preparedStatement.close();
          DBMethod.closeConnection();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    });

    editButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // work with the row selected in the db
        int row = users.getSelectedRow();
        String email = users.getValueAt(row, 0).toString();
        String username = users.getValueAt(row, 1).toString();
        editEmail.setText(email);
        editUsername.setText(username);
      }
    });

    applyChangesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          //prepare statement
         PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("UPDATE userdb" + " SET USERNAME = ?" + " WHERE EMAIL = ?");
         //set parameters
          preparedStatement.setString(1, editEmail.getText());
          preparedStatement.setString(2, editUsername.getText());
          //execute sql
         preparedStatement.executeUpdate();
         System.out.println("USER SUCCESSFULLY UPDATED");
         preparedStatement.close();
         DBMethod.closeConnection();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }

      }
    });


/*    refreshButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("SELECT * FROM userdb");
          preparedStatement.executeQuery();
          users.setModel(());
          preparedStatement.close();
          DBMethod.closeConnection();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    });*/
  }
}