//package com.multiThread.MultiThread.controller;
//
//import com.multiThread.MultiThread.service.FileService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/v1/upload-files")
//public class FileController {
//    private FileService fileService ;
//    // ******************* we could not use controller because are files were huge
//    @PostMapping("/csv")
//    @ResponseBody
//    public ResponseEntity<String> uploadFile(@RequestParam("customersFile") MultipartFile customersFile
//            ,@RequestParam("accountsFile") MultipartFile accountsFile){
//        fileService.readFiles(customersFile , accountsFile);
//        return new ResponseEntity<>("Item saved successfully ! " , HttpStatus.OK);
//    }
//}
