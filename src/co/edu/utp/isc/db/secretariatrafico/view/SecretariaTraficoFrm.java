package co.edu.utp.isc.db.secretariatrafico.view;

import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban
 */
public class SecretariaTraficoFrm extends javax.swing.JFrame {

    /**
     * Creates new form SecretariaTrafico
     */
    public SecretariaTraficoFrm() {
        initComponents();
        setLocationRelativeTo(null);
        panelSeleccionado = null;
        agentesPnl.cargarListas();
        autosPnl.cargarListas();
        ciudadesPnl.cargarListas();
        concesionariosPnl.cargarListas();
        infraccionesPnl.cargarListas();
        marcasPnl.cargarListas();
        modelosPnl.cargarListas();
        multasPnl.cargarListas();
        personasPnl.cargarListas();
        tiposPersonasPnl.cargarListas();
        tiposVehiculosPnl.cargarListas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblEntidad = new javax.swing.JLabel();
        cmbEntidades = new javax.swing.JComboBox<>();
        pnlCrud = new javax.swing.JPanel();
        pnlEntidades = new javax.swing.JPanel();
        pnlVacio = new javax.swing.JPanel();
        ciudadesPnl = new co.edu.utp.isc.db.secretariatrafico.view.CiudadesPnl();
        multasPnl = new co.edu.utp.isc.db.secretariatrafico.view.MultasPnl();
        marcasPnl = new co.edu.utp.isc.db.secretariatrafico.view.MarcasPnl();
        tiposPersonasPnl = new co.edu.utp.isc.db.secretariatrafico.view.TiposPersonasPnl();
        tiposVehiculosPnl = new co.edu.utp.isc.db.secretariatrafico.view.TiposVehiculosPnl();
        modelosPnl = new co.edu.utp.isc.db.secretariatrafico.view.ModelosPnl();
        infraccionesPnl = new co.edu.utp.isc.db.secretariatrafico.view.InfraccionesPnl();
        agentesPnl = new co.edu.utp.isc.db.secretariatrafico.view.AgentesPnl();
        autosPnl = new co.edu.utp.isc.db.secretariatrafico.view.AutosPnl();
        concesionariosPnl = new co.edu.utp.isc.db.secretariatrafico.view.ConcesionariosPnl();
        personasPnl = new co.edu.utp.isc.db.secretariatrafico.view.PersonasPnl();
        vacioPnl = new javax.swing.JPanel();
        pnlTabla = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Secretaría de Tráfico");

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Secretaría de Tráfico");

        lblEntidad.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblEntidad.setText("Entidad:");

        cmbEntidades.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cmbEntidades.setMaximumRowCount(12);
        cmbEntidades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agentes", "Autos", "Ciudades", "Concesionarios", "Infracciones", "Marcas", "Modelos", "Multas", "Personas", "Tipos Personas", "Tipos Vehículos" }));
        cmbEntidades.setSelectedIndex(-1);
        cmbEntidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEntidadesActionPerformed(evt);
            }
        });

        pnlEntidades.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout pnlVacioLayout = new javax.swing.GroupLayout(pnlVacio);
        pnlVacio.setLayout(pnlVacioLayout);
        pnlVacioLayout.setHorizontalGroup(
            pnlVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        pnlVacioLayout.setVerticalGroup(
            pnlVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        pnlEntidades.add(pnlVacio, "Vacio");
        pnlEntidades.add(ciudadesPnl, "Ciudades");
        pnlEntidades.add(multasPnl, "Multas");
        pnlEntidades.add(marcasPnl, "Marcas");
        pnlEntidades.add(tiposPersonasPnl, "Tipos Personas");
        pnlEntidades.add(tiposVehiculosPnl, "Tipos Vehículos");
        pnlEntidades.add(modelosPnl, "Modelos");
        pnlEntidades.add(infraccionesPnl, "Infracciones");
        pnlEntidades.add(agentesPnl, "Agentes");
        pnlEntidades.add(autosPnl, "Autos");
        pnlEntidades.add(concesionariosPnl, "Concesionarios");
        pnlEntidades.add(personasPnl, "Personas");

        javax.swing.GroupLayout vacioPnlLayout = new javax.swing.GroupLayout(vacioPnl);
        vacioPnl.setLayout(vacioPnlLayout);
        vacioPnlLayout.setHorizontalGroup(
            vacioPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        vacioPnlLayout.setVerticalGroup(
            vacioPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        pnlEntidades.add(vacioPnl, "Vacio");

        pnlTabla.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        btnEditar.setText("Editar Registro");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar Registro");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTabla);

        javax.swing.GroupLayout pnlTablaLayout = new javax.swing.GroupLayout(pnlTabla);
        pnlTabla.setLayout(pnlTablaLayout);
        pnlTablaLayout.setHorizontalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTablaLayout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)))
        );
        pnlTablaLayout.setVerticalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar Registro");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar Registro");
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCrudLayout = new javax.swing.GroupLayout(pnlCrud);
        pnlCrud.setLayout(pnlCrudLayout);
        pnlCrudLayout.setHorizontalGroup(
            pnlCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEntidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCrudLayout.createSequentialGroup()
                .addComponent(pnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrudLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(73, 73, 73)
                .addComponent(btnActualizar)
                .addGap(21, 21, 21))
        );
        pnlCrudLayout.setVerticalGroup(
            pnlCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrudLayout.createSequentialGroup()
                .addComponent(pnlEntidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlCrud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblEntidad)
                .addGap(18, 18, 18)
                .addComponent(cmbEntidades, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEntidad)
                    .addComponent(cmbEntidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(pnlCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if ((tblTabla.getSelectedRow() != -1) && (panelSeleccionado != null)) {
            btnActualizar.setEnabled(true);
            btnGuardar.setEnabled(false);
            panelSeleccionado.seleccionar(tblTabla);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (panelSeleccionado != null) {
            panelSeleccionado.eliminar(tblTabla);
        }        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (panelSeleccionado != null) {
            panelSeleccionado.guardar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (panelSeleccionado != null) {
            panelSeleccionado.actualizar();
        }
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void cmbEntidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEntidadesActionPerformed
        String item = cmbEntidades.getSelectedItem().toString();
        if (item != null) {
            switch (item) {
                case "Agentes":
                    cambiarCard(item);
                    panelSeleccionado = agentesPnl;
                    break;
                case "Autos":
                    cambiarCard(item);
                    panelSeleccionado = autosPnl;
                    break;
                case "Ciudades":
                    cambiarCard(item);
                    panelSeleccionado = ciudadesPnl;
                    break;
                case "Concesionarios":
                    cambiarCard(item);
                    panelSeleccionado = concesionariosPnl;
                    break;
                case "Infracciones":
                    cambiarCard(item);
                    panelSeleccionado = infraccionesPnl;
                    break;
                case "Marcas":
                    cambiarCard(item);
                    panelSeleccionado = marcasPnl;
                    break;
                case "Modelos":
                    cambiarCard(item);
                    panelSeleccionado = modelosPnl;
                    break;
                case "Multas":
                    cambiarCard(item);
                    panelSeleccionado = multasPnl;
                    break;
                case "Personas":
                    cambiarCard(item);
                    panelSeleccionado = personasPnl;
                    break;
                case "Tipos Personas":
                    cambiarCard(item);
                    panelSeleccionado = tiposPersonasPnl;
                    break;
                case "Tipos Vehículos":
                    cambiarCard(item);
                    panelSeleccionado = tiposVehiculosPnl;
                    break;
                default:
                    break;
            }
        }
        panelSeleccionado.cargarListas();
        modelo = panelSeleccionado.getModelo();
        tblTabla.setModel(modelo);
        panelSeleccionado.cargarRegistros();
    }//GEN-LAST:event_cmbEntidadesActionPerformed

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
            java.util.logging.Logger.getLogger(SecretariaTraficoFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecretariaTraficoFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecretariaTraficoFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecretariaTraficoFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecretariaTraficoFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private co.edu.utp.isc.db.secretariatrafico.view.AgentesPnl agentesPnl;
    private co.edu.utp.isc.db.secretariatrafico.view.AutosPnl autosPnl;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private co.edu.utp.isc.db.secretariatrafico.view.CiudadesPnl ciudadesPnl;
    private javax.swing.JComboBox<String> cmbEntidades;
    private co.edu.utp.isc.db.secretariatrafico.view.ConcesionariosPnl concesionariosPnl;
    private co.edu.utp.isc.db.secretariatrafico.view.InfraccionesPnl infraccionesPnl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEntidad;
    private javax.swing.JLabel lblTitulo;
    private co.edu.utp.isc.db.secretariatrafico.view.MarcasPnl marcasPnl;
    private co.edu.utp.isc.db.secretariatrafico.view.ModelosPnl modelosPnl;
    private co.edu.utp.isc.db.secretariatrafico.view.MultasPnl multasPnl;
    private co.edu.utp.isc.db.secretariatrafico.view.PersonasPnl personasPnl;
    private javax.swing.JPanel pnlCrud;
    private javax.swing.JPanel pnlEntidades;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JPanel pnlVacio;
    private javax.swing.JTable tblTabla;
    private co.edu.utp.isc.db.secretariatrafico.view.TiposPersonasPnl tiposPersonasPnl;
    private co.edu.utp.isc.db.secretariatrafico.view.TiposVehiculosPnl tiposVehiculosPnl;
    private javax.swing.JPanel vacioPnl;
    // End of variables declaration//GEN-END:variables
    private Crud panelSeleccionado;
    private DefaultTableModel modelo;
    
    public void cambiarCard(String card) {
        ((CardLayout) pnlEntidades.getLayout()).show(pnlEntidades, card);
    }
    
}
