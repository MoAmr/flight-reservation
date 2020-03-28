package util;

import com.flight.reservation.entities.Reservation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PDFGenerator {

    public void generateItinerary(Reservation reservation, String filePath) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(generateTable(reservation));
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private PdfPTable generateTable(Reservation reservation) {

        PdfPTable table = new PdfPTable(2);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Flight Itinerary"));
        cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Flight Details"));
        cell.setColspan(2);
        table.addCell(cell);

        table.addCell("Departure City");
        table.addCell(reservation.getFlight().getDepartureCity());

        table.addCell("Arrival City");
        table.addCell(reservation.getFlight().getArrivalCity());

        table.addCell("Flight Number");
        table.addCell(reservation.getFlight().getFlightNumber());

        table.addCell("Departure Date");
        table.addCell(reservation.getFlight().getDateOfDeparture().toString());

        table.addCell("Departure Time");
        table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());

        return table;
    }
}
