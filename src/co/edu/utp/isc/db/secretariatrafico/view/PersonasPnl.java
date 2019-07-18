package co.edu.utp.isc.db.secretariatrafico.view;

import co.edu.utp.isc.db.secretariatrafico.controller.JpaController;
import co.edu.utp.isc.db.secretariatrafico.model.Ciudades;
import co.edu.utp.isc.db.secretariatrafico.model.Personas;
import co.edu.utp.isc.db.secretariatrafico.model.TiposPersonas;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban
 */
public class PersonasPnl extends javax.swing.JPanel implements Crud {

    /**
     * Creates new form PersonasPnl
     */
    public PersonasPnl() {
        initComponents();
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbTipoPersona = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNacimiento = new javax.swing.JTextField();
        chkAI = new javax.swing.JCheckBox();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbCiudad = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 210));

        jLabel10.setText("Nombre:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel11.setText("Apellidos:");

        jLabel1.setText("ID persona:");

        jLabel12.setText("Tipo persona:");

        chkAI.setPreferredSize(new java.awt.Dimension(24, 26));
        chkAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAIActionPerformed(evt);
            }
        });

        jLabel2.setText("AI");

        jLabel13.setText("Dirección::");

        jLabel14.setText("Teléfono:");

        jLabel15.setText("Ciudad:");

        jLabel9.setText("Fecha nacimiento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbTipoPersona, javax.swing.GroupLayout.Alignment.LEADING, 0, 343, Short.MAX_VALUE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId)
                    .addComponent(txtApellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNacimiento)
                    .addComponent(cmbCiudad, 0, 344, Short.MAX_VALUE)
                    .addComponent(txtDireccion)
                    .addComponent(txtTelefono))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(chkAI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbTipoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(1, 1, 1)))))
                .addContainerGap(15, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkAI;
    private javax.swing.JComboBox<String> cmbCiudad;
    private javax.swing.JComboBox<String> cmbTipoPersona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel modelo = null;
    private final SimpleDateFormat formatoFecha;
    private Personas persona = new Personas();
    private Ciudades c = new Ciudades();
    private TiposPersonas t = new TiposPersonas();
    private List<Personas> listaPersonas;
    private List<Ciudades> listaCiudades;
    private List<TiposPersonas> listaTiposPersonas;
    
    
    @Override
    public void cargarListas() {
        this.listaPersonas = JpaController.getInstance().getPersonasControlador().findPersonasEntities();
        this.listaCiudades = JpaController.getInstance().getCiudadesControlador().findCiudadesEntities();
        this.listaTiposPersonas = JpaController.getInstance().getTiposPersonasControlador().findTiposPersonasEntities();
    }

    @Override
    public DefaultTableModel getModelo() {
        if (modelo == null) {
            try {
                modelo = (new DefaultTableModel(
                        null, new String[]{
                            "ID Persona", "Nombre", "Apellidos",
                            "Fecha Nacimiento", "Dirección", "Teléfono",
                            "Tipo Persona", "Ciudad"}) {
                    Class[] types = new Class[]{
                        java.lang.String.class,
                        java.lang.String.class,
                        java.lang.String.class,
                        java.util.Date.class,
                        java.lang.String.class,
                        java.lang.String.class,
                        java.lang.String.class,
                        java.lang.String.class
                    };
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false, false, false
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
            for (Ciudades ciudad : listaCiudades) {
                cmbCiudad.addItem(ciudad.getNombreCiudad());
            }
            cmbCiudad.setSelectedIndex(-1);
            for (TiposPersonas tipoVehiculo : listaTiposPersonas) {
                cmbTipoPersona.addItem(tipoVehiculo.getNombreTipoPersona());
            }
            cmbTipoPersona.setSelectedIndex(-1);
            modelo.setRowCount(0);
            for (Personas p : listaPersonas) {
                modelo.addRow(new Object[]{
                    p.getIdPersona(),
                    p.getNombrePersona(),
                    p.getApellidosPersona(),
                    p.getFechaNacimientoPersona(),
                    p.getDireccionPersona(),
                    p.getTelefonoPersona(),
                    p.getIdTipoPersona().getNombreTipoPersona(),
                    p.getIdCiudad().getNombreCiudad()
                });
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void registrar() {
        String getFecha = txtNacimiento.getText().trim();
        Date fecha;
        try {
            fecha = formatoFecha.parse(getFecha);
            persona.setFechaNacimientoPersona(fecha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        persona.setNombrePersona(txtNombre.getText().trim());
        persona.setApellidosPersona(txtApellidos.getText().trim());
        persona.setDireccionPersona(txtDireccion.getText().trim());
        persona.setTelefonoPersona(txtTelefono.getText().trim());

        for (Ciudades ciudad : listaCiudades) {
            if (ciudad.getNombreCiudad().equals(cmbCiudad.getSelectedItem().toString())) {
                persona.setIdCiudad(ciudad);
            }
        }
        
        for (TiposPersonas tipoVehiculo : listaTiposPersonas) {
            if (tipoVehiculo.getNombreTipoPersona().equals(cmbTipoPersona.getSelectedItem().toString())) {
                persona.setIdTipoPersona(tipoVehiculo);
            }
        }
    }

    @Override
    public void guardar() {
        try {
            if (txtId.getText().isEmpty()) {
                persona.setIdPersona(null);
            } else {
                persona.setIdPersona(Integer.parseInt(txtId.getText().trim()));
            }
            
            registrar();
            
            JpaController.getInstance().getPersonasControlador().create(persona);
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
        txtApellidos.setText(tblTabla.getValueAt(tblTabla.getSelectedRow(), 2).toString());
        txtNacimiento.setText(formatoFecha.format((Date) tblTabla.getValueAt(tblTabla.getSelectedRow(), 3)));
        txtDireccion.setText(tblTabla.getValueAt(tblTabla.getSelectedRow(), 4).toString());
        txtTelefono.setText(tblTabla.getValueAt(tblTabla.getSelectedRow(),5).toString());
        cmbTipoPersona.setSelectedItem(tblTabla.getValueAt(tblTabla.getSelectedRow(), 6).toString());
        cmbCiudad.setSelectedItem(tblTabla.getValueAt(tblTabla.getSelectedRow(), 7).toString());
    }

    @Override
    public void actualizar() {
        try {
            persona.setIdPersona(Integer.parseInt(txtId.getText().trim()));
            
            registrar();

            JpaController.getInstance().getPersonasControlador().edit(persona);
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
        txtNacimiento.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        cmbTipoPersona.setSelectedIndex(-1);
        cmbCiudad.setSelectedIndex(-1);
    }

    @Override
    public void eliminar(JTable tblTabla) {
        int id = (int) tblTabla.getValueAt(tblTabla.getSelectedRow(), 0);
        try {
            JpaController.getInstance().getPersonasControlador().destroy(id);
            JOptionPane.showMessageDialog(null, "Registro eliminado");
            recargar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void recargar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
