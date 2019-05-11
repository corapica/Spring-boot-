package tn.biat.service;

import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.biat.util.UploadUtil;

@Service
public class UploadService {
	
	private final UploadUtil uploadUtil;
	@Autowired
	private ClientService clientService;
	public UploadService(UploadUtil uploadUtil) {
		this.uploadUtil = uploadUtil;
	}

	public List<Map<String, String>> upload(MultipartFile file, String label, String adminManager) throws Exception {

		Path tempDir = Files.createTempDirectory("");

		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		
		file.transferTo(tempFile);

		Workbook workbook = WorkbookFactory.create(tempFile);

		Sheet sheet = workbook.getSheetAt(0);

		Supplier<Stream<Row>> rowStreamSupplier = uploadUtil.getRowStreamSupplier(sheet);
		
		Row headerRow = rowStreamSupplier.get().findFirst().get();
		
		List<String> headerCells = new  ArrayList<String>();
		headerCells.add("firstName");
		headerCells.add("lastName");
		headerCells.add("email");

		int colCount = headerCells.size();
		
		List<Map<String, String>> ListMapClients = rowStreamSupplier.get()
				.skip(0)
				.map(row -> {
			
					List<String> cellList = uploadUtil.getStream(row)
							.map(Cell::getStringCellValue)
							.collect(Collectors.toList());	
					
					return uploadUtil.cellIteratorSupplier(colCount)
							 .get()
							 .collect(toMap(headerCells::get, cellList::get));
		})
		.collect(Collectors.toList());
		for (Map<String, String> client : ListMapClients) {
			clientService.create(client.get("firstName"), client.get("lastName"), client.get("email"), label, adminManager);
		}
		return ListMapClients;
	}

}