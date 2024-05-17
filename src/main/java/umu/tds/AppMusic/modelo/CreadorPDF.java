package umu.tds.AppMusic.modelo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Clase para crear un archivo PDF con la información de un usuario.
 */
public class CreadorPDF {

    public CreadorPDF() {
    }

    /**
     * Crea un archivo PDF con la información del usuario.
     *
     * @param usuario el usuario cuya información se añadirá al PDF.
     * @throws FileNotFoundException si no se puede encontrar el archivo de salida.
     * @throws DocumentException     si ocurre un error al crear el documento.
     */
    public void crearPDF(Usuario usuario) throws FileNotFoundException, DocumentException {
        String rutaArchivo = usuario.getNombre() + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));

        document.open();
        // Añadimos el nombre de usuario
        document.add(new Paragraph(usuario.getNombre()));

        for (PlayList p : usuario.getPlaylists()) {
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(" Playlist : " + p.getNombre()));
            for (Cancion c : p.getCanciones()) {
                document.add(new Paragraph("  Titulo: " + c.getTitulo()));
                document.add(new Paragraph("  Interprete: " + c.getInterprete()));
                document.add(new Paragraph("  Estilo Musical: " + c.getEstilo()));
                document.add(new Paragraph("\n"));
            }
        }

        document.close();
    }
}
