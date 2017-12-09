/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.jsp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome2";
	}

	@RequestMapping("/t")
	@ResponseBody
	public ResponseEntity<byte[]> t() throws IOException {

		File file = new File("F:" + File.separator + "1.png");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "1.png");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
	
	@RequestMapping("/a")
	@ResponseBody
	public ResponseEntity<byte[]> a() throws Exception {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("1");// 分页
		Sheet sheet2 = wb.createSheet("2");// 分页2
		
		Row row = sheet.createRow(0);// 第0+1行
		Cell cell = row.createCell(0);// 第row行第0+1列
		cell.setCellValue("abc");
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "1workbook.xls");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		wb.write(outByteStream);
		return new ResponseEntity<byte[]>(outByteStream.toByteArray(), headers, HttpStatus.CREATED);
	}

}
