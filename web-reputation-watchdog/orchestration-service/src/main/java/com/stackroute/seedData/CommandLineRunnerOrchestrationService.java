//package com.stackroute.seedData;
//
//
//import com.stackroute.domain.Review;
//import com.stackroute.dto.RatingServiceDTO;
//import com.stackroute.service.AddReviewServiceImpl;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Iterator;
//
//@Component
//public class CommandLineRunnerOrchestrationService implements CommandLineRunner {
//
//    private AddReviewServiceImpl addReviewService;
//
//    @Autowired
//    public CommandLineRunnerOrchestrationService(AddReviewServiceImpl addReviewService) {
//
//        this.addReviewService = addReviewService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // need to load Excel XLSX file to read
//        File file = new File("OrchestrationService.xlsx");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        // create an XSSF Workbook object for our XLSX Excel File
//        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
//        // get the first sheet
//        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
//        // iterate on every rows
//        Iterator<Row> rowIterator = xssfSheet.iterator();
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            // iterate on cells for the current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                System.out.println(cell.toString() + ";");
//            }
//            System.out.println();
//        }
//        xssfWorkbook.close();
//        fileInputStream.close();
//        int noOfRow = xssfSheet.getLastRowNum();
//        for (int i = 0; i <= noOfRow; i++) {
//            Review review = new Review();
//            review.setReviewTitle(xssfSheet.getRow(i).getCell(0).toString());
//            review.setReviewDescription(xssfSheet.getRow(i).getCell(1).toString());
//            review.setReviewedBy(xssfSheet.getRow(i).getCell(2).toString());
//            review.setReviewerScore(new Integer(xssfWorkbook.getSheetAt(0).getRow(i).getCell(4).getRawValue()));
//            review.setEntityTitle(xssfSheet.getRow(i).getCell(5).toString());
//            RatingServiceDTO ratingServiceDTO = new RatingServiceDTO();
//            ratingServiceDTO.setReviewTitle(xssfSheet.getRow(i).getCell(0).toString());
//            ratingServiceDTO.setReviewedBy(xssfSheet.getRow(i).getCell(2).toString());
//            ratingServiceDTO.setReviewerScore(new Integer(xssfWorkbook.getSheetAt(0).getRow(i).getCell(4).getRawValue()));
//            ;
//            ratingServiceDTO.setReviewId(new Long(xssfWorkbook.getSheetAt(0).getRow(i).getCell(4).getRawValue()));
//            ratingServiceDTO.setReviewDescription(xssfSheet.getRow(i).getCell(1).toString());
////      GraphQueryDTO graphQueryDTO=new GraphQueryDTO();
////      graphQueryDTO.setReviewTitle(xssfSheet.getRow(i).getCell(0).toString());
////      graphQueryDTO.setReviewedBy(xssfSheet.getRow(i).getCell(2).toString());
////      graphQueryDTO.setReviewDescription(xssfSheet.getRow(i).getCell(1).toString());
////      graphQueryDTO.setReviewId(new Long(xssfWorkbook.getSheetAt(0).getRow(i).getCell(4).getRawValue()));
////      DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
////    graphQueryDTO.setTimeStamp(xssfWorkbook.getSheetAt(0).getRow(i).getCell(5).getRawValue());
////    ratingServiceDTO.setTimeStamp(xssfWorkbook.getSheetAt(0).getRow(i).getCell(5).getRawValue());
//            addReviewService.postReview(review);
//
//        }
//    }
//}
