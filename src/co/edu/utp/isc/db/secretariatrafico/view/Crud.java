package co.edu.utp.isc.db.secretariatrafico.view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban
 */
public interface Crud {
    
    void cargarListas();
    
    DefaultTableModel getModelo();
        
    void cargarRegistros();
    
    void registrar();
    
    void guardar();
    
    void seleccionar(JTable tblTabla);
    
    void actualizar();
    
    void limpiarCampos();
    
    void eliminar(JTable tblTabla);
    
    void recargar();
    
}
