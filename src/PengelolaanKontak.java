import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author USER
 */
public class PengelolaanKontak extends javax.swing.JFrame {
    private KontakCRUD kontakcrud;

    /**
     * Creates new form PengelolaanKontak
     */
    public PengelolaanKontak() {
        initComponents();
        
        kontakcrud = new KontakCRUD();
        
        //event untuk semua button
        btnTambah.addActionListener(e -> addKontak());
        btnEdit.addActionListener(e -> editKontak());
        btnHapus.addActionListener(e -> hapusKontak());
        btnCari.addActionListener(e -> cariKontak());
        btnEkspor.addActionListener(e -> eksporCSV());
        btnImpor.addActionListener(e -> imporCSV());
        btnKeluar.addActionListener(e -> System.exit(0));
        
        tabelKontak.setModel(new DefaultTableModel(
        new Object[][]{}, //data awal kosong
        new String[]{"ID", "Nama", "Nomor Telepon", "Kategori"} //untuk judul kolom
));
    }
    
    private void addKontak() {
        try {
            String nama = txtNama.getText();
            String nomor = txtNomor.getText();
            String kategori = boxKategori.getSelectedItem().toString();

            if (nama.isEmpty() || nomor.isEmpty() || kategori.equals("Pilih Kategori")) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }

            if (!nomor.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Nomor telepon harus berupa angka!");
                return;
            }

            kontakcrud.addKontak(nama, nomor, kategori);
            refreshTabel();
            hapusInput();
            JOptionPane.showMessageDialog(this, "Kontak berhasil ditambahkan.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void editKontak() {
        int selectedRow = tabelKontak.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kontak yang akan diubah!");
            return;
        }

        try {
            int id = Integer.parseInt(tabelKontak.getValueAt(selectedRow, 0).toString());
            String nama = txtNama.getText();
            String nomor = txtNomor.getText();
            String kategori = boxKategori.getSelectedItem().toString();

            if (nama.isEmpty() || nomor.isEmpty() || kategori.equals("Pilih Kategori")) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }

            if (!nomor.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Nomor telepon harus berupa angka!");
                return;
            }

            kontakcrud.editKontak(id, nama, nomor, kategori);
            refreshTabel();
            hapusInput();
            JOptionPane.showMessageDialog(this, "Kontak berhasil diperbarui.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void hapusKontak() {
        int selectedRow = tabelKontak.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kontak yang akan dihapus!");
            return;
        }

        try {
            int id = Integer.parseInt(tabelKontak.getValueAt(selectedRow, 0).toString());
            kontakcrud.hapusKontak(id);
            refreshTabel();
            JOptionPane.showMessageDialog(this, "Kontak berhasil dihapus.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void cariKontak() {
        String keyword = txtCari.getText();
        try {
            List<String[]> hasilCari = kontakcrud.cariKontak(keyword);
            updateTabel(hasilCari);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void eksporCSV() {
        try {
            String filePath = "C:/Users/USER/Documents/NetBeansProjects/AplikasiPengelolaanKontak/kontak.csv";
            kontakcrud.eksporData(filePath);
            JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke " + filePath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void imporCSV() {
        try {
            String filePath = "C:/Users/USER/Documents/NetBeansProjects/AplikasiPengelolaanKontak/kontak.csv";
            kontakcrud.imporData(filePath);
            refreshTabel();
            JOptionPane.showMessageDialog(this, "Data berhasil diimpor dari " + filePath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void refreshTabel() {
        try {
            List<String[]> semuaKontak = kontakcrud.getAllKontak();
            updateTabel(semuaKontak);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void updateTabel(List<String[]> data) {
        DefaultTableModel model = (DefaultTableModel) tabelKontak.getModel();
        model.setRowCount(0); // Hapus semua baris
        for (String[] row : data) {
            model.addRow(row); // Tambahkan baris baru
        }
    }
    
    private void hapusInput() {
        txtNama.setText("");
        txtNomor.setText("");
        boxKategori.setSelectedIndex(0);
    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNomor = new javax.swing.JTextField();
        boxKategori = new javax.swing.JComboBox<>();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        btnEkspor = new javax.swing.JButton();
        btnImpor = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKontak = new javax.swing.JTable();

        jScrollPane2.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Aplikasi Pengelolaan Kontak");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nama");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nomor Telepon");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Kategori");

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        txtNomor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNomor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomorActionPerformed(evt);
            }
        });

        boxKategori.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        boxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Teman", "Keluarga", "Teman Kerja" }));

        btnTambah.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        btnKeluar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnKeluar.setText("Keluar");
        btnKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        btnEkspor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnEkspor.setText("Ekspor Data");

        btnImpor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnImpor.setText("Impor Data");

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        btnCari.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCari.setText("Cari");

        tabelKontak.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tabelKontak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelKontak);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnImpor, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEkspor)
                                .addGap(32, 32, 32)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCari))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus)
                    .addComponent(btnKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCari, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImpor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEkspor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomorActionPerformed

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
            java.util.logging.Logger.getLogger(PengelolaanKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PengelolaanKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PengelolaanKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PengelolaanKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PengelolaanKontak().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxKategori;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEkspor;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnImpor;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JTable tabelKontak;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNomor;
    // End of variables declaration//GEN-END:variables
}
