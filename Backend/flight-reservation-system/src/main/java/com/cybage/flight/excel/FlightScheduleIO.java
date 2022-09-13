package com.cybage.flight.excel;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cybage.flight.entities.FlightSchedule;

//import  org.apache.poi.ss.usermodel.Font;


public class FlightScheduleIO {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<FlightSchedule> listFlightSchedule;
     
    public FlightScheduleIO(List<FlightSchedule> listFlightSchedule) {
        this.listFlightSchedule = listFlightSchedule;
        workbook = new XSSFWorkbook() ;
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Flight Schedule");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Flight Schedule ID", style);
        createCell(row, 1, "Flight Name", style);    
        createCell(row, 2, "Flight Number", style);    
        createCell(row, 3, "Source", style);      
        createCell(row, 4, "Destination", style);       
        createCell(row, 5, "Departure Date	", style);
        createCell(row, 6, "Arrival Date", style);  
        createCell(row, 7, "Departure Time", style);  
        createCell(row, 8, "Arrival Time", style);  
        createCell(row, 9, "Available Seats", style);
        createCell(row, 10, "Stops", style);
        createCell(row, 11, "Fare", style); 
        createCell(row, 12, "Is Refundable", style); 
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        }else if (value instanceof Time) {
            cell.setCellValue((Time) value);
        }else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }
        else {
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
                 
        for (FlightSchedule flightSchedule : listFlightSchedule) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, flightSchedule.getId(), style);
            createCell(row, columnCount++, flightSchedule.getFlight().getFlightName(), style);
            createCell(row, columnCount++, flightSchedule.getFlight().getFlightNumber(), style);
            createCell(row, columnCount++, flightSchedule.getSource(), style);
            createCell(row, columnCount++, flightSchedule.getDestination(), style);
            createCell(row, columnCount++, flightSchedule.getDepartureDate(), style);
            createCell(row, columnCount++, flightSchedule.getArrivalDate(), style);
            createCell(row, columnCount++, flightSchedule.getDepartureTime(), style);
            createCell(row, columnCount++, flightSchedule.getArrivalTime().getTime(), style);
            createCell(row, columnCount++, flightSchedule.getAvailableSeats(), style);
            createCell(row, columnCount++, flightSchedule.getStops(), style);
            createCell(row, columnCount++, flightSchedule.getFare(), style);
            createCell(row, columnCount++, flightSchedule.getIsRefundable(), style);
            
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




