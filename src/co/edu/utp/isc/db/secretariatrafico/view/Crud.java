package co.edu.utp.isc.db.secretariatrafico.view;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban
 */
public interface Crud {
    
    DefaultTableModel getModelo();
    void cargarRegistros();
    void guardar();
    
//    void crearTabla();
    
}
