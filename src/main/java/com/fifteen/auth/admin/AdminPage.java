package com.fifteen.auth.admin;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import com.fifteen.database.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

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
  private JTextField editIsAdmin;
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
        int isAdmin = Integer.parseInt(users.getValueAt(row, 2).toString());
        editEmail.setText(email);
        editUsername.setText(username);
        editIsAdmin.setText(String.valueOf(isAdmin));
      }
    });

    applyChangesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          //prepare statement
          PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("UPDATE userdb" + " SET USERNAME = ?, EMAIL = ?" + "WHERE IS_ADMIN = ?");
          //set parameters
          preparedStatement.setString(1, editUsername.getText());
          preparedStatement.setString(2, editEmail.getText());
          preparedStatement.setInt(3, Integer.parseInt(editIsAdmin.getText()));
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

  {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    adminPanel = new JPanel();
    adminPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
    final Spacer spacer1 = new Spacer();
    adminPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayoutManager(12, 1, new Insets(0, 0, 0, 0), -1, -1));
    adminPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    panel1.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
    Database = new JLabel();
    Database.setText("Database");
    panel1.add(Database, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JPanel panel2 = new JPanel();
    panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    panel1.add(panel2, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    final Spacer spacer2 = new Spacer();
    panel1.add(spacer2, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer3 = new Spacer();
    panel1.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    dbTable = new JScrollPane();
    panel1.add(dbTable, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    users = new JTable();
    users.setAutoResizeMode(4);
    users.setEnabled(true);
    users.putClientProperty("Table.isFileList", Boolean.FALSE);
    dbTable.setViewportView(users);
    final Spacer spacer4 = new Spacer();
    panel1.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer5 = new Spacer();
    panel1.add(spacer5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final JPanel panel3 = new JPanel();
    panel3.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
    panel1.add(panel3, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    editEmail = new JTextField();
    panel3.add(editEmail, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    final Spacer spacer6 = new Spacer();
    panel3.add(spacer6, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    editUsername = new JTextField();
    panel3.add(editUsername, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    final JLabel label1 = new JLabel();
    label1.setText("Change EMAIL");
    panel3.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label2 = new JLabel();
    label2.setText("Change USERNAME");
    panel3.add(label2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer7 = new Spacer();
    panel3.add(spacer7, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer8 = new Spacer();
    panel3.add(spacer8, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer9 = new Spacer();
    panel3.add(spacer9, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    editIsAdmin = new JTextField();
    panel3.add(editIsAdmin, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    final Spacer spacer10 = new Spacer();
    panel3.add(spacer10, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    final JLabel label3 = new JLabel();
    label3.setText("Change IS ADMIN?");
    panel3.add(label3, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    applyChangesButton = new JButton();
    applyChangesButton.setText("Apply changes");
    panel1.add(applyChangesButton, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JPanel panel4 = new JPanel();
    panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
    panel1.add(panel4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    editButton = new JButton();
    editButton.setText("Edit");
    panel4.add(editButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    deleteButton = new JButton();
    deleteButton.setText("Delete");
    panel4.add(deleteButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JPanel panel5 = new JPanel();
    panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    panel1.add(panel5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    final JPanel panel6 = new JPanel();
    panel6.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
    panel1.add(panel6, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    refreshButton = new JButton();
    refreshButton.setText("Refresh");
    panel6.add(refreshButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer11 = new Spacer();
    panel6.add(spacer11, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    final Spacer spacer12 = new Spacer();
    panel6.add(spacer12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return adminPanel;
  }
}