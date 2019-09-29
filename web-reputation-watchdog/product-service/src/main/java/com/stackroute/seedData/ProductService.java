//package com.stackroute.seedData;
//
//import com.stackroute.domain.Entity;
//import com.stackroute.domain.Review;
//import com.stackroute.dto.ReviewDTO;
//import com.stackroute.service.EntityServiceImpl;
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
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//@Component
//public class ProductService implements CommandLineRunner {
//    private EntityServiceImpl entityService;
//
//    @Autowired
//    public ProductService(EntityServiceImpl entityService) {
//        this.entityService = entityService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // need to load Excel XLSX file to read data
//        File file = new File("Product-Service.xlsx");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        // create an XSSF Workbook object for our XLSX Excel File
//        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
//        // get the first sheet
//        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
//        int noOfRow = xssfSheet.getLastRowNum();
//
////        // iterate on every rows
//        Iterator<Row> rowIterator = xssfSheet.iterator();
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            // iterate on cells for the current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//
//            }
//
//        }
//        xssfWorkbook.close();
//        fileInputStream.close();
//        int noOfrow = xssfSheet.getLastRowNum();
//        for (int i = 1; i <= noOfrow; i++) {
//            Entity entity = new Entity();
//            entity.setEntityName(xssfSheet.getRow(i).getCell(15).toString());
//            entity.setEntityImageUrl(xssfSheet.getRow(i).getCell(0).toString());
//            entity.setEntityDescription(xssfSheet.getRow(i).getCell(1).toString());
//            entity.setEntityLocation(xssfSheet.getRow(i).getCell(2).toString());
//            entity.setEntityPostedBy(xssfSheet.getRow(i).getCell(3).toString());
//            entity.setEntityCategory(xssfSheet.getRow(i).getCell(5).toString());
//            entity.setEntitySubCategory(xssfSheet.getRow(i).getCell(6).toString());
//            entity.setOverAllRating((xssfSheet.getRow(i).getCell(7).toString()));
//            Review review = new Review();
//            List<Review> list3 = new ArrayList<>();
//            list3.add(review);
//            review.setReviewTitle(xssfSheet.getRow(i).getCell(8).toString());
//            review.setReviewDescription(xssfSheet.getRow(i).getCell(9).toString());
//            review.setReviewedBy(xssfSheet.getRow(i).getCell(10).toString());
//            ArrayList list = new ArrayList<>(Collections.singleton(xssfSheet.getRow(i).getCell(12).toString()));
//            review.setDownVoters(list);
//            ArrayList list1 = new ArrayList<>(Collections.singleton(xssfSheet.getRow(i).getCell(13).toString()));
//            review.setUpVoters(list1);
//            review.setGenuine(new Boolean(xssfSheet.getRow(i).getCell(14).toString()));
//            entity.setReviews(list3);
//            ReviewDTO reviewDTO = new ReviewDTO();
//            reviewDTO.setEntityName(xssfSheet.getRow(i).getCell(15).toString());
//            reviewDTO.setReviewTitle(xssfSheet.getRow(i).getCell(8).toString());
//            reviewDTO.setReviewDescription(xssfSheet.getRow(i).getCell(9).toString());
//            reviewDTO.setReviewedBy(xssfSheet.getRow(i).getCell(11).toString());
//            ArrayList list4 = new ArrayList<>(Collections.singleton(xssfSheet.getRow(i).getCell(12).toString()));
//            review.setDownVoters(list4);
//            ArrayList list5 = new ArrayList<>(Collections.singleton(xssfSheet.getRow(i).getCell(13).toString()));
//            review.setUpVoters(list5);
//            entityService.saveEntity(entity);
//        }
//    }
//}
//
//
