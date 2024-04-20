package umu.tds.AppMusic.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import javax.swing.filechooser.FileSystemView;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CreadorPDF {

	private Document document;
	private Usuario usuario;

	public CreadorPDF(Usuario usuario) {
		this.usuario = usuario;
	}

	public void crearPDF() throws FileNotFoundException, DocumentException {
		/*FileSystemView view = FileSystemView.getFileSystemView();
		File desktopDirectory = view.getHomeDirectory();
		String rutaEscritorio = desktopDirectory.getAbsolutePath();

		// Ruta completa del archivo PDF en el escritorio
		String rutaArchivo = Paths.get(rutaEscritorio, usuario.getNombre() + ".pdf").toString();*/
		
		
		String rutaArchivo = usuario.getNombre() + ".pdf";
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));

		document.open();
		// añadimos nombre de usuario
		document.add(new Paragraph(usuario.getNombre()));

		for (PlayList p : usuario.getPlaylists()) {
			document.add(new Paragraph("\n"));
			document.add(new Paragraph(" Playlist : " + p.getNombre() + ""));
			for (Cancion c : p.getCanciones()) {
				document.add(new Paragraph("	Titulo: " + c.getTitulo()));
				document.add(new Paragraph("	Interprete: " + c.getInterprete()));
				document.add(new Paragraph("	Estilo Musical: " + c.getEstilo()));

				document.add(new Paragraph("\n"));
			}
		}

		document.close();

	}

}
