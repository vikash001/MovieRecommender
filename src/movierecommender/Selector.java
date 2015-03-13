/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecommender;

import com.opencsv.CSVReader;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Striker
 */
public class Selector extends javax.swing.JFrame {

    /**
     * Creates new form Selector
     */
    JPanel aPanel, sPanel;
    JScrollPane aSP, sSP;
    
    ArrayList<String> names;
    ArrayList<Integer> available;
    ArrayList<Integer> selected;
    boolean isSelected[];
    int nTopics;
    JLabel label[];
    
    public Selector() {
        initComponents();
        
        CSVReader reader;
        String [] nextLine;
        nTopics = 0;
        names = new ArrayList<String>();
        
        try {
//            reader = new CSVReader(new FileReader("\\data\\Topics.csv"));
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL url = classLoader.getResource("data/Topics.csv");
            File file = new File(url.toURI());
            reader = new CSVReader(new FileReader(file));
            while ((nextLine = reader.readNext()) != null) {
                names.add(nextLine[0]);
                nTopics++;
            }
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(Selector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        aPanel = new JPanel(new GridLayout(nTopics,1));
        sPanel = new JPanel(new GridLayout(nTopics,1));
        aPanel.setOpaque(false);
        sPanel.setOpaque(false);
        aSP = new JScrollPane(aPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sSP = new JScrollPane(sPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        aSP.getVerticalScrollBar().setUnitIncrement((int)(50.0/2.2));
        sSP.getVerticalScrollBar().setUnitIncrement(50);
        aSP.getViewport().setOpaque(false);
        sSP.getViewport().setOpaque(false);
        jPanel1.add(aSP);
        jPanel2.add(sSP);
        
        
        label = new JLabel[nTopics];
        isSelected = new boolean[nTopics];
        available = new ArrayList<Integer>();
        selected = new ArrayList<Integer>();
        
        for (int i = 0; i < nTopics; i++) {
            label[i] = new JLabel((String)names.get(i));
            label[i].setName(String.valueOf(i));
            label[i].setBackground(new java.awt.Color(255, 255, 255));
            label[i].setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
            label[i].setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
            label[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            label[i].setOpaque(false);
            label[i].setBorder(new EmptyBorder(0,20,0,0));
            label[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    onClickEvent(Integer.parseInt(evt.getComponent().getName()));
                }
            });
            
            isSelected[i] = false;
        }


        
        for (int i = 0; i < nTopics; i++) {
            available.add(i);
            aPanel.add(label[i]);
        }
        aPanel.revalidate();
        aPanel.repaint();
//        updateAvailable("");
        
        setLocation(350, 150);
        getContentPane().setBackground(new Color(255,255,204));
    }

    void updateAvailable(String inp) {
        for (Integer i : available) {
            aPanel.remove(label[(int) i]);
        }
        available.clear();
        
        for (int i = 0; i < nTopics; i++) {
            if (!isSelected[i] && (inp.length() == 0 || ((String)names.get(i)).matches("(?i:.*" + inp + ".*)"))) {
                available.add(i);
            }
        }
        
        for (Integer i : available) {
            aPanel.add(label[(int) i]);
        }
        aPanel.revalidate();
        aPanel.repaint();
    }
    
    void addMovie(int id) {
        if (id >= 0 && id < nTopics && !isSelected[id]) {
            isSelected[id] = true;
            selected.add(id);
            
            sPanel.add(label[id]);
            sPanel.revalidate();
            sPanel.repaint();
            
            aPanel.remove(label[id]);
            aPanel.revalidate();
            aPanel.repaint();
        }
    }
    
    void removeMovie(int id) {
        if (isSelected[id]) {
            isSelected[id] = false;
            selected.remove(new Integer(id));
            
            sPanel.remove(label[id]);
            sPanel.revalidate();
            sPanel.repaint();
            
            String q = query.getText();
            if (q.equals("") || ((String)names.get(id)).contains(q)) {
                updateAvailable(q);
            }
        }
    }
    
    void onClickEvent(int id) {
        if (isSelected[id]) {
            removeMovie(id);
        }
        else {
            addMovie(id);
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        query = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));
        setForeground(new java.awt.Color(255, 255, 204));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 10, 30));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMaximumSize(new java.awt.Dimension(301, 287));
        jPanel1.setMinimumSize(new java.awt.Dimension(301, 287));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(301, 287));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel3.setBackground(new java.awt.Color(255, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("List of Available Topics");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 153, 0)));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setMaximumSize(new java.awt.Dimension(301, 287));
        jPanel2.setMinimumSize(new java.awt.Dimension(301, 287));
        jPanel2.setPreferredSize(new java.awt.Dimension(301, 287));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel4.setBackground(new java.awt.Color(255, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Your selected Topics");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 255)));
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setOpaque(true);
        jPanel2.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel2);

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setInheritsPopupMenu(true);

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Show Me!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        query.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                queryKeyReleased(evt);
            }
        });

        jLabel2.setText("Search:");

        jLabel1.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select your Favourite topics:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(query, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(query, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 377, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void queryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_queryKeyReleased
        // TODO add your handling code here:
        updateAvailable(query.getText());
    }//GEN-LAST:event_queryKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int response;
        response = JOptionPane.showConfirmDialog(this, "You sure?", "Movie Reccomendor", JOptionPane.YES_NO_OPTION);
        if (response != 0) return;
        
        query.setText("");
        
        for (Integer i : selected) {
            isSelected[(int)i] = false;
            sPanel.remove(label[(int) i]);
        }
        for (Integer i : available) {
            aPanel.remove(label[(int) i]);
        }
        selected.clear();
        available.clear();
        
        for (int i = 0; i < nTopics; i++) {
            available.add(i);
            aPanel.add(label[i]);
        }
        
        aPanel.revalidate();
        aPanel.repaint();
        sPanel.revalidate();
        sPanel.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        if (selected.size() == 0) {
            JOptionPane.showMessageDialog(this, "Please select atlesast one topic.", "Movie Reccomendor", JOptionPane.OK_OPTION);
            return;
        }
        this.setVisible(false);
        (new Result()).setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Selector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Selector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Selector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Selector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Selector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField query;
    // End of variables declaration//GEN-END:variables
}
