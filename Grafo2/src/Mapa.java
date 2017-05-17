import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class Mapa {
	private ArrayList <Coordinate> coordenadas = new ArrayList <Coordinate>();
	private LinkedList<MapMarker> marcadores = new LinkedList<MapMarker>();
	private JTextField IngresarLongitud;
	private JTextField IngresarLatitud;
	private JTextField distancia;
	private JTextField cantPeajes;
	private JFrame frmRoswellMaps;
	private Grafo1 grafo;
	private static int ids= 0;
	private int id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mapa window = new Mapa();
					window.frmRoswellMaps.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mapa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		grafo = new Grafo1(0);
		JComboBox<String> seleccionDestino = new JComboBox<String>();
		JComboBox<String> SeleccionInicio = new JComboBox<String>();
		JComboBox<String> AristaBx = new JComboBox<String>();
		JComboBox<String> aristaBx2 = new JComboBox<String>();
		JCheckBox checkPeaje = new JCheckBox("Peaje");
		
		frmRoswellMaps = new JFrame();
		frmRoswellMaps.setType(Type.UTILITY);
		frmRoswellMaps.setTitle("Roswell Maps");
		frmRoswellMaps.setBounds(0, 0, 1290, 1046);
		frmRoswellMaps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRoswellMaps.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 13, 994, 989);
		frmRoswellMaps.getContentPane().add(panel);
		panel.setLayout(null);

		JMapViewer mapa = new JMapViewer();
		mapa.setBounds(0, 0, 994, 989);
		mapa.getMapPosition(-34.5233260,-58.7008550);
		panel.add(mapa);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(1028, 139, 231, 12);
		frmRoswellMaps.getContentPane().add(separator);
		
		JPanel AgregarVertice = new JPanel();
		AgregarVertice.setBounds(1006, 13, 265, 122);
		frmRoswellMaps.getContentPane().add(AgregarVertice);
		AgregarVertice.setLayout(null);
		
		JLabel lblAgregarCiudad = new JLabel("Latitud");
		lblAgregarCiudad.setBounds(25, 37, 125, 36);
		AgregarVertice.add(lblAgregarCiudad);
		lblAgregarCiudad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnAgregarVertice = new JButton("Agregar Ciudad");
		btnAgregarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Coordinate coordenada = tomarCoordenadas();
				coordenadas.add(coordenada);
				
				grafo.agregarVertice(coordenada);
				
				String nombreCiudad = crearNombre();
				
				agregarMarcador(coordenada, nombreCiudad);
				
				añadirComboBox(nombreCiudad);
			}

			private void añadirComboBox( String identificador) {
				seleccionDestino.addItem(identificador);
				SeleccionInicio.addItem(identificador);
				AristaBx.addItem(identificador);
				aristaBx2.addItem(identificador);
			}

			private void agregarMarcador(Coordinate coordenada, String identificador) {
				marcadores.add(new MapMarkerDot(identificador,
						new Coordinate(coordenada.getLat(),coordenada.getLon())));
				
				marcadores.getLast().getStyle().setBackColor(Color.RED);
				marcadores.getLast().getStyle().setColor(Color.GREEN);
				
				mapa.addMapMarker(marcadores.getLast());
			}

			private String crearNombre() {
				id = ++ids;
				String identificador = ""+id ;
				return identificador;
			}

			private Coordinate tomarCoordenadas() {
				double latitud = Double.parseDouble(IngresarLatitud.getText());
				double longitud = Double.parseDouble(IngresarLongitud.getText());
				Coordinate coordenada = new Coordinate(latitud,longitud);
				
				return coordenada;
			}
		});
		btnAgregarVertice.setBounds(100, 82, 153, 28);
		AgregarVertice.add(btnAgregarVertice);
		btnAgregarVertice.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(25, 0, 125, 36);
		AgregarVertice.add(lblLongitud);
		lblLongitud.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		IngresarLongitud = new JTextField();
		IngresarLongitud.setBounds(100, 8, 145, 24);
		AgregarVertice.add(IngresarLongitud);
		IngresarLongitud.setColumns(10);
		
		IngresarLatitud = new JTextField();
		IngresarLatitud.setBounds(100, 45, 145, 24);
		AgregarVertice.add(IngresarLatitud);
		IngresarLatitud.setColumns(10);
		
		JPanel AgregarArista = new JPanel();
		AgregarArista.setLayout(null);
		AgregarArista.setBounds(1006, 147, 265, 180);
		frmRoswellMaps.getContentPane().add(AgregarArista);
		
		JLabel lblCiudad1 = new JLabel("Ciudad");
		lblCiudad1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCiudad1.setBounds(25, 37, 125, 36);
		AgregarArista.add(lblCiudad1);
		
		JButton btnAgregarArista = new JButton("Agregar ruta");
		btnAgregarArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int inicio = Integer.parseInt((String) AristaBx.getSelectedItem())-1;
				int destino = Integer.parseInt((String) aristaBx2.getSelectedItem())-1;
				int peso = Integer.parseInt(distancia.getText());
				boolean tienePeaje = checkPeaje.isSelected();
				
				grafo.agregarArista(inicio, destino, peso,tienePeaje);
			
				agregarLineaArista(inicio, destino);
			}

			private void agregarLineaArista(int inicio, int destino) {
				Coordinate a = coordenadas.get(inicio);
				Coordinate b = coordenadas.get(destino);
				
				ArrayList <Coordinate>coordenadas = new ArrayList();
				coordenadas.add(a);
				coordenadas.add(b);
				coordenadas.add(a);
				
				MapPolygon poligono = new MapPolygonImpl (coordenadas);
				poligono.getStyle().setColor(Color.CYAN);
				mapa.addMapPolygon(poligono);
			}
		});
		btnAgregarArista.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAgregarArista.setBounds(100, 121, 145, 28);
		AgregarArista.add(btnAgregarArista);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCiudad.setBounds(25, 0, 125, 36);
		AgregarArista.add(lblCiudad);
		
		JLabel lblDistancia = new JLabel("Distancia");
		lblDistancia.setBounds(25, 76, 125, 36);
		AgregarArista.add(lblDistancia);
		lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		distancia = new JTextField();
		distancia.setBounds(100, 84, 145, 24);
		AgregarArista.add(distancia);
		distancia.setColumns(10);
		
		
		checkPeaje.setBounds(25, 123, 69, 25);
		AgregarArista.add(checkPeaje);
		checkPeaje.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(14, 168, 231, 12);
		AgregarArista.add(separator_1);
		
		
		AristaBx.setBounds(100, 9, 145, 22);
		AgregarArista.add(AristaBx);
		
		
		aristaBx2.setBounds(100, 46, 145, 22);
		AgregarArista.add(aristaBx2);
		
		JPanel Dijkstra = new JPanel();
		Dijkstra.setLayout(null);
		Dijkstra.setBounds(1006, 328, 265, 180);
		frmRoswellMaps.getContentPane().add(Dijkstra);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDestino.setBounds(25, 37, 125, 36);
		Dijkstra.add(lblDestino);
		
		JButton btnCalcularRuta = new JButton("Calcular");
		btnCalcularRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int inicio = Integer.parseInt((String) SeleccionInicio.getSelectedItem())-1;
				int destino = Integer.parseInt((String) seleccionDestino.getSelectedItem())-1;
				int peajes = Integer.parseInt(cantPeajes.getText());
				
				Dijkstra solucion = new Dijkstra(grafo, inicio, destino,peajes);
				solucion.resolver();
				String resultado = solucion.mostrarResultado();
				JOptionPane ventanaResultado = new JOptionPane();
				JOptionPane.showMessageDialog(null, resultado, "Solucion",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnCalcularRuta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCalcularRuta.setBounds(100, 121, 145, 28);
		Dijkstra.add(btnCalcularRuta);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInicio.setBounds(25, 0, 125, 36);
		Dijkstra.add(lblInicio);
		
		JLabel lblCantidadDePeajes = new JLabel("Cantidad de\n peajes");
		lblCantidadDePeajes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCantidadDePeajes.setBounds(25, 76, 152, 36);
		Dijkstra.add(lblCantidadDePeajes);
		
		cantPeajes = new JTextField();
		cantPeajes.setColumns(10);
		cantPeajes.setBounds(198, 84, 47, 24);
		Dijkstra.add(cantPeajes);
		
		
		seleccionDestino.setBounds(100, 46, 145, 22);
		Dijkstra.add(seleccionDestino);
		
		
		SeleccionInicio.setBounds(100, 9, 145, 22);
		Dijkstra.add(SeleccionInicio);
		
		JPanel guardarCargar = new JPanel();
		guardarCargar.setLayout(null);
		guardarCargar.setBounds(1006, 822, 265, 180);
		frmRoswellMaps.getContentPane().add(guardarCargar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnGuardar.setBounds(149, 139, 101, 28);
		guardarCargar.add(btnGuardar);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCargar.setBounds(29, 139, 91, 28);
		guardarCargar.add(btnCargar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNuevo.setBounds(84, 98, 91, 28);
		guardarCargar.add(btnNuevo);
//		
		MapMarker marker = new MapMarkerDot("aqui", new Coordinate(-34.5233260,-58.7008550));
		marker.getStyle().setBackColor(Color.RED);
		marker.getStyle().setColor(Color.RED);
		mapa.addMapMarker(marker);
		
		

		
		

	}
	
//	public void guardar() {
//		try {
//			FileOutputStream fos = new FileOutputStream("Mapa.txt");
//			ObjectOutputStream out = new ObjectOutputStream(fos);
//			out.writeObject();
//			out.writeObject(p2);
//			out.writeObject(p3);
//			out.close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//	}
	
//	public void cargar() {
//		try {
//			FileInputStream ﬁs = new FileInputStream("personas.txt");
//			ObjectInputStream in = new ObjectInputStream(ﬁs);
////			p3 = (Persona) in.readObject();
//			in.close();
//		} catch (Exception ex) {
//		}
//
//	}
}
