package CaseStudy1_Day5;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Future;

import CaseStudy1_Day5.FileUploadService.UploadResult;


public class FileUploadMain {

	public static void main(String[] args) {
		FileUploadService uploadService = new FileUploadService();

        // Sample files to upload
        List<Path> files = List.of(
            Paths.get("report.pdf"),
            Paths.get("image.jpg"),
            Paths.get("data.csv"),
            Paths.get("presentation.pptx"),
            Paths.get("archive.zip")
        );

        System.out.println("Starting concurrent uploads...");
        List<Future<UploadResult>> futures = uploadService.uploadMultipleFiles(files, "cloud-storage");

        // Monitor upload progress
        futures.forEach(future -> {
            try {
                // Blocking call to get result (in real app, use callbacks or CompletableFuture)
            	UploadResult result = future.get();
                System.out.println("Upload completed: " + result);
            } catch (Exception e) {
                System.err.println("Upload failed: " + e.getMessage());
            }
        });

        uploadService.shutdown();
        System.out.println("All uploads processed");
	}

}
