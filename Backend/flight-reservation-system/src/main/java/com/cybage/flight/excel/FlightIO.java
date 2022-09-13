package com.cybage.flight.excel;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.cybage.flight.entities.Flight;

public class FlightIO {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Flight> listFlight;
     
    public FlightIO(List<Flight> listFlight) {
        this.listFlight = listFlight;
        workbook = new XSSFWorkbook() ;
    }

 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Flight");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Flight Number", style);      
        createCell(row, 1, "Flight Name", style);       
        createCell(row, 2, "Flight Type Name", style);   
        createCell(row, 3, "Total Seats", style);      
      
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Flight flight : listFlight) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, flight.getFlightNumber(), style);
            createCell(row, columnCount++, flight.getFlightName(), style);
            createCell(row, columnCount++, flight.getFlightType(), style);
            createCell(row, columnCount++, flight.getTotalSeats(), style);
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}



