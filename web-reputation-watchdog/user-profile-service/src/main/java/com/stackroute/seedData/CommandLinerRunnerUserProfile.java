//package com.stackroute.seedData;
//
//import com.stackroute.domain.Entity;
//import com.stackroute.domain.Review;
//import com.stackroute.domain.UserProfile;
//import com.stackroute.dto.UserProfileDto;
//import com.stackroute.repository.UserProfileRepository;
//import com.stackroute.service.UserProfileServiceImpl;
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
//public class CommandLinerRunnerUserProfile implements CommandLineRunner {
//
//  private UserProfileServiceImpl userProfileServiceImpl;
//@Autowired
//  public CommandLinerRunnerUserProfile(  UserProfileServiceImpl userProfileServiceImpl) {
//
//    this.userProfileServiceImpl = userProfileServiceImpl;
//  }
//
//  @Override
//  public void run(String... args) throws Exception {
//    // need to load Excel XLSX file to read
//    File file = new File("UserProfile_2.xlsx");
//    FileInputStream fileInputStream = new FileInputStream(file);
//    // create an XSSF Workbook object for our XLSX Excel File
//    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
//    // get the first sheet
//    XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
//    // iterate on every rows
//    Iterator<Row> rowIterator = xssfSheet.iterator();
//    while (rowIterator.hasNext()) {
//      Row row = rowIterator.next();
//      // iterate on cells for the current row
//      Iterator<Cell> cellIterator = row.cellIterator();
//      while (cellIterator.hasNext()) {
//        Cell cell = cellIterator.next();
//        System.out.println(cell.toString() + ";");
//      }
//      System.out.println();
//    }
//    xssfWorkbook.close();
//    fileInputStream.close();
//
//    int noOfRow = xssfSheet.getLastRowNum();
//    for (int i = 0; i < noOfRow; i++) {
//      UserProfile userProfile=new UserProfile();
//      userProfile.setEmailId(xssfSheet.getRow(i).getCell(0).toString());
//      userProfile.setFirstName(xssfSheet.getRow(i).getCell(1).toString());
//      userProfile.setLastName(xssfSheet.getRow(i).getCell(2).toString());
//      System.out.println(xssfSheet.getRow(i).getCell(3).toString());
//      userProfile.setMobileNumber(new Double(xssfWorkbook.getSheetAt(0).getRow(i).getCell(22).getRawValue()));
//      userProfile.setUserScore(new Double(xssfWorkbook.getSheetAt(0).getRow(i).getCell(3).getRawValue()));
//      Review myReviews = new Review();
//      myReviews.setReviewTitle(xssfSheet.getRow(i).getCell(5).toString());
//      myReviews.setReviewDescription(xssfSheet.getRow(i).getCell(6).toString());
//      myReviews.setReviewedBy(xssfSheet.getRow(i).getCell(8).toString());
//      List<Review> list3=new ArrayList<>();
//      list3.add(myReviews);
//      ArrayList list = new ArrayList<>(Collections.singleton(xssfSheet.getRow(i).getCell(12).toString()));
//      myReviews.setDownVoters(list);
//      ArrayList list1 = new ArrayList<>(Collections.singleton(xssfSheet.getRow(i).getCell(11).toString()));
//      myReviews.setUpVoters(list1);
//      myReviews.setGenuine(new Boolean(xssfSheet.getRow(i).getCell(10).toString()));
//      userProfile.setMyReviews(list3);
//      Entity entity = new Entity();
//      entity.setEntityName(xssfSheet.getRow(i).getCell(7).toString());
//      entity.setEntityCategory(xssfSheet.getRow(i).getCell(18).toString());
//      entity.setEntityDescription(xssfSheet.getRow(i).getCell(14).toString());
//      List<Entity> list4=new ArrayList<>();
//      list4.add(entity);
//      entity.setEntityImageUrl(xssfSheet.getRow(i).getCell(13).toString());
//      entity.setEntityLocation(xssfSheet.getRow(i).getCell(15).toString());
//      entity.setEntityPostedBy(xssfSheet.getRow(i).getCell(16).toString());
//      entity.setOverAllRating(new Double(xssfWorkbook.getSheetAt(0).getRow(i).getCell(21).getRawValue()));
//      entity.setEntitySubCategory(xssfSheet.getRow(i).getCell(19).toString());
//      entity.setLocation(xssfSheet.getRow(i).getCell(20).toString());
//      userProfile.setMyEntities(list4);
//      UserProfileDto userProfileDto = new UserProfileDto();
//      userProfileDto.setEmailId(xssfSheet.getRow(i).getCell(0).toString());
//      userProfileDto.setFirstName(xssfSheet.getRow(i).getCell(1).toString());
//      userProfileDto.setMobileNumber(new Double(xssfWorkbook.getSheetAt(0).getRow(i).getCell(22).getRawValue()));
//      userProfileDto.setPassword(xssfSheet.getRow(i).getCell(4).toString());
//      userProfileServiceImpl.saveUserProfile(userProfileDto);
//     userProfileServiceImpl.updateTheProfile(userProfile);
//    }
//
//  }
//}
//
