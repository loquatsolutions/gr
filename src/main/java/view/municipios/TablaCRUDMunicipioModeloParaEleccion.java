package view.municipios;


import view.recorridosyKm.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Municipio;






public class TablaCRUDMunicipioModeloParaEleccion extends AbstractTableModel {

    private List<Municipio> listaMunicipio;
    private boolean editable;

    
         
    
    protected String[] columnNames = new String[] {
          "Id","Municipio"
    };

    protected Class[] columnClasses = new Class[] {
           Integer.class, String.class};

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public int getRowCount() {
        return (listaMunicipio != null) ? listaMunicipio.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
     
   
  
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        Municipio municipio = listaMunicipio.get(rowIndex);
        switch (columnIndex){
         
            case 0:
                return municipio.getId();
            case 1:
                return municipio.getMunicipio();
           
        }
        
        return null;
    }

   
    public void actualizarListaMunicipios(List<Municipio> lista){
        this.listaMunicipio = lista;
    
    }

    public List<Municipio> getListaMunicipio() {
        return listaMunicipio;
    }
    
    
    
    @Override
    public boolean isCellEditable (int row, int column) {
       
        return false;
    }
    
    

}
