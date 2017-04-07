package view.fichaCliente;

import DatosPrecargados.SingletonClientes;
import command.clientes.CargarClientesCommand;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import process.cargador.CargadorListaCliente;
import process.parser.clientes.ClienteParserJson;

/**  
 * @category JTextField con menú desplegable de coincidencias 
 * @version 1.0
 * @param widthPopupPanel es el ancho del popup
 * @param heightPopupPanel es el alto del popup
 * @param autoComplete activamos o desactivamos el autocompletado
 *
 */


public class TextFieldBusqueda extends JTextField {

	private JPopupMenu popup;
	private DefaultTableModel tableModel;
	private JTable jTable;
	private JPanel panel;
	private int widthPopupPanel;
	private int heightPopupPanel;
	private List<String> dataList = new ArrayList<String>();
	private boolean autocomplete;
        private FichaCliente ficha;
        private HashMap hashContador;

	public TextFieldBusqueda(FichaCliente ficha, int widthPopupPanel , int heightPopupPanel , boolean autoComplete ){
		this.widthPopupPanel = widthPopupPanel;
		this.heightPopupPanel = heightPopupPanel;
		this.autocomplete = autoComplete;
                this.setFont(new java.awt.Font("Tahoma", 2, 16));
		initComponent();
                this.ficha = ficha;
	}

    public HashMap getHashContador() {
        return hashContador;
    }
	
        
        
     public void llenarLista() {
      
        dataList.removeAll(dataList);
            try {
              hashContador=new HashMap<>();
              List<Cliente> clientesBusqueda = SingletonClientes.getInstance().getLista();
                for (int it=0;it<clientesBusqueda.size();it++){
                    hashContador.put(clientesBusqueda.get(it).getId(), it);
                    String st =clientesBusqueda.get(it).getId().toString() + " (" + clientesBusqueda.get(it).getNombre() + ")";
                    dataList.add(st);
                    
                }   
            } catch (Exception ex) {
                Logger.getLogger(TextFieldBusqueda.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      }
     
     
       public void llenarListaClientesDeBaja() {
      
        dataList.removeAll(dataList);
            try {
              hashContador=new HashMap<>();
              List<Cliente> clientesBusqueda2 = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeListaClientesBaja();
                
                for (int it=0;it<clientesBusqueda2.size();it++){
                    hashContador.put(clientesBusqueda2.get(it).getId(), it);
                    String st =clientesBusqueda2.get(it).getId().toString() + " (" + clientesBusqueda2.get(it).getNombre() + ")";
                    dataList.add(st);
                    
                }   
            } catch (Exception ex) {
                Logger.getLogger(TextFieldBusqueda.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      }
     
	
	private void initComponent() {
		
		createDocumentListeners();
		createKeyListeners();
		
		popup = new JPopupMenu();
		popup.setVisible(false);
		
                panel = new JPanel(new BorderLayout());
	  	
                
            
		tableModel = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row , int column){
				return false;
			}
		};
		
		jTable = new JTable();
                jTable.setFont(new java.awt.Font("Tahoma", 2, 16));
		jTable.setFillsViewportHeight(true);
		jTable.setGridColor(Color.WHITE);
		
		jTable.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {

                                
				if ( e.getKeyChar() ==  KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB ) {
					setTextInTextField();
                                         try {
                                              ficha.cargarClienteDesdeBarraBusqueda();
                                         } catch (Exception ex) {
                                                Logger.getLogger(TextFieldBusqueda.class.getName()).log(Level.SEVERE, null, ex);
                                         }
                                        
				}
				
				if ( e.getKeyCode() == 8){
					requestFocus();
				}
			}
		});
		
		
		jTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent event)
			{
			  
				setTextInTextField();
                                 try {
                                   ficha.cargarClienteDesdeBarraBusqueda();
                                } catch (Exception ex) {
                                    Logger.getLogger(TextFieldBusqueda.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
			  
			}
		});
		
		commitData();
		
		jTable.setModel(tableModel);
		jTable.setTableHeader(null);
		
		JScrollPane scroll = new JScrollPane(jTable);
		panel.setPreferredSize( new Dimension( widthPopupPanel  , heightPopupPanel ) );
		panel.add(scroll, BorderLayout.CENTER);
		popup.add(panel);
	}

	private void commitData(){
		
		String[] columns = new String[] {"X"};
		String[][] data = new String[dataList.size()][1];
		
		for (int i = 0 ; i < dataList.size() ; i++){
			String [] dato = new String[]{  dataList.get(i)    };
			data[i][0] = dato[0];
		}
		
		tableModel.setDataVector(data, columns);
	}
	
	
	
	
	private void setTextInTextField(){
		String texto = jTable.getModel().getValueAt( jTable.getSelectedRow() , 0 ).toString();
		setText(texto);
		popup.setVisible(false);
		requestFocus();
	}
	
	
	
	private void createDocumentListeners() {

		this.getDocument().addDocumentListener(new DocumentListener() {
                        
			@Override
			public void changedUpdate(DocumentEvent e) {
                          	if ( containsAnyWord(getText()) && autocomplete ){
                                     
					showPopup(e);
				}else{
					popup.setVisible(false);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
                         	if ( containsAnyWord(getText()) && autocomplete ){
                                     
					showPopup(e);
				}else{
					popup.setVisible(false);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
                          	if ( containsAnyWord(getText()) && autocomplete ){
					showPopup(e);
				}else{
					popup.setVisible(false);
				}
			}
                       
		});

	}

	
	private void createKeyListeners() {
		
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				
				int code = e.getKeyCode();

				switch (code) {
					case KeyEvent.VK_DOWN: {
					        	
                                                showPopup(e);
						if ( popup.isVisible() && jTable.getRowCount() > 1){
							jTable.setRowSelectionInterval(1,1);
							jTable.requestFocus();
						}
						else{
							jTable.requestFocus();
						}
						
					break;
					}
	
					case KeyEvent.VK_ENTER:{
						
						if ( popup.isVisible()){
							setText( jTable.getModel().getValueAt( jTable.getSelectedRow()  , 0 ).toString() );
							popup.setVisible(false);
						}
						
					break;
					}
				}
			
			}
		});

	}

        private void showPopup(KeyEvent e) {
             			if (!popup.isVisible()) {
					popup.show( this , 0, 15 );
					popup.setVisible(true);
				}
	
				getFilteredList(this.getText());
				this.grabFocus();
				
				if (jTable.getRowCount() > 0){
					jTable.setRowSelectionInterval( 0 , 0);
				}
	
		
		
	}
        
	
	private void showPopup(DocumentEvent e) {
              if (e.getDocument().getLength() > 0 ) {
				if (!popup.isVisible()) {
					popup.show( this , 0, 15 );
					popup.setVisible(true);
				}
	
				getFilteredList(this.getText());
				this.grabFocus();
				
				if (jTable.getRowCount() > 0){
					jTable.setRowSelectionInterval( 0 , 0);
				}
	
		} else {
			popup.setVisible(false);
		}
	}

	
	private boolean containsAnyWord(String wordEntered){
		for (int i = 0 ; i < dataList.size() ; i++){
			if( dataList.get(i).toLowerCase().startsWith(wordEntered.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	private int getCoincidencesListSize(String wordEntered){
		
		int newLong = 0;
		for (int i = 0 ; i < dataList.size() ; i++){
			if( dataList.get(i).toLowerCase().startsWith(wordEntered.toLowerCase())){
				newLong++;
			}
		}
		return newLong;
	}
	
	
	private void getFilteredList(String wordEntered){
		
		int newListLong = getCoincidencesListSize(wordEntered);
		int tama�o= dataList.size();
		String[] columns = new String[] {"X"};
		String[][] data = new String[newListLong][1];
               
		int index = 0;
		
		for (int i = 0 ; i < dataList.size() ; i++){
                	if( dataList.get(i).toLowerCase().startsWith(wordEntered.toLowerCase()) || 
				dataList.get(i).toLowerCase().startsWith(wordEntered.toLowerCase()) ){
					String [] dato = new String[]{ dataList.get(i)};
					data[index][0] = dato[0];
					index++;
                                      
			}
             
		}
		tableModel.getDataVector().clear();
            
		
		tableModel.setDataVector(data, columns);
            
	}
	
	public List<String> getDataList() {
		return dataList;
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}
	
	public int getWidthPopupPanel() {
		return widthPopupPanel;
	}

	public void setWidthPopupPanel(int widthPopupPanel) {
		this.widthPopupPanel = widthPopupPanel;
		panel.setPreferredSize(new Dimension(widthPopupPanel , getWidthPopupPanel() ));
		panel.revalidate();
		panel.repaint();
	}

	public int getHeightPopupPanel() {
		return heightPopupPanel;
	}

	public void setHeightPopupPanel(int heightPopupPanel) {
		this.heightPopupPanel = heightPopupPanel;
		panel.setPreferredSize(new Dimension( getWidthPopupPanel() ,heightPopupPanel));
		panel.revalidate();
		panel.repaint();
	}
	
	public boolean isAutocomplete() {
		return autocomplete;
	}
	public void setAutocomplete(boolean autocomplete) {
		this.autocomplete = autocomplete;
	}
}