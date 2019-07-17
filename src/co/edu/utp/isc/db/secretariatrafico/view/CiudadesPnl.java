package co.edu.utp.isc.db.secretariatrafico.view;

import co.edu.utp.isc.db.secretariatrafico.controller.JpaController;
import co.edu.utp.isc.db.secretariatrafico.model.Ciudades;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban
 */
public class CiudadesPnl extends javax.swing.JPanel implements Crud {

    /**
     * Creates new form CiudadesPnl
     */
    public CiudadesPnl() {
        initComponents();
        this.modelo = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chkAI = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ciudades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 210));

        chkAI.setPreferredSize(new java.awt.Dimension(24, 26));
        chkAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAIActionPerformed(evt);
            }
        });

        jLabel1.setText("ID ciudad:");

        jLabel2.setText("AI");

        jLabel3.setText("Nombre:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addGap(58, 58, 58)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chkAI, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkAIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAIActionPerformed
        if (chkAI.isSelected()) {
            txtId.setText("");
            txtId.setEnabled(false);
        } else {
            txtId.setEnabled(true);
        }
    }//GEN-LAST:event_chkAIActionPerformed

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkAI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
    private Viewer viewer;
    private DefaultTableModel modelo;
    private Ciudades ciudad = new Ciudades();
    private List<Ciudades> listaCiudades;
    
    @Override
    public void cargarListas() {
        this.listaCiudades = JpaController.getInstance().getCiudadesControlador().findCiudadesEntities();
    }

    @Override
    public DefaultTableModel getModelo() {
        if (modelo == null) {
            try {
                modelo = (new DefaultTableModel(
                        null, new String[]{
                            "ID Ciudad", "Nombre"}) {
                    Class[] types = new Class[]{
                        java.lang.String.class,
                        java.lang.String.class
                    };
                    boolean[] canEdit = new boolean[]{
                        false, false
                    };

                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int colIndex) {
                        return canEdit[colIndex];
                    }
                });
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return modelo;
    }

    @Override
    public void cargarRegistros() {
        try {
            modelo.setRowCount(0);
            for (Ciudades c : listaCiudades) {
                modelo.addRow(new Object[]{
                    c.getIdCiudad(),
                    c.getNombreCiudad()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void registrar() {
        ciudad.setNombreCiudad(txtNombre.getText().trim());
    }

    @Override
    public void guardar() {
        try {
            if (txtId.getText().isEmpty()) {
                ciudad.setIdCiudad(null);
            } else {
                ciudad.setIdCiudad(Integer.parseInt(txtId.getText().trim()));
            }
            
            registrar();
            
            JpaController.getInstance().getCiudadesControlador().create(ciudad);
            JOptionPane.showMessageDialog(null, "Registro guardado");
            limpiarCampos();
            recargar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    

    @Override
    public void seleccionar(JTable tblTabla) {
        chkAI.setEnabled(false);
        txtId.setText(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
        txtNombre.setText(tblTabla.getValueAt(tblTabla.getSelectedRow(), 1).toString());
    }

    @Override
    public void actualizar() {
        try {
            ciudad.setIdCiudad(Integer.parseInt(txtId.getText().trim()));
            
            registrar();

            JpaController.getInstance().getCiudadesControlador().edit(ciudad);
            JOptionPane.showMessageDialog(null, "Registro actualizado");
            limpiarCampos();
            recargar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void limpiarCampos() {
        chkAI.setEnabled(true);
        chkAI.setSelected(false);
        txtId.setEnabled(true);
        txtId.setText("");
        txtNombre.setText("");
    }

    @Override
    public void eliminar(JTable tblTabla) {
        int id = (int) tblTabla.getValueAt(tblTabla.getSelectedRow(), 0);
        try {
            JpaController.getInstance().getCiudadesControlador().destroy(id);
            JOptionPane.showMessageDialog(null, "Registro eliminado");
            recargar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void recargar() {
        cargarListas();
        cargarRegistros();
    }
}
