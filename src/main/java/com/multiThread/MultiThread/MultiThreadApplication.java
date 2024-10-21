package com.multiThread.MultiThread;

import com.multiThread.MultiThread.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@AllArgsConstructor
public class MultiThreadApplication {
//	private final FileService fileService ;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MultiThreadApplication.class, args);

		FileService fileService = context.getBean(FileService.class);

		//fileService.readFiles();

	}

}
