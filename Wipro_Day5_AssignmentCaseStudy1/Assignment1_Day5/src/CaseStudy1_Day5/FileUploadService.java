package CaseStudy1_Day5;

import java.util.concurrent.*;
import java.util.List;
import java.nio.file.Path;

public class FileUploadService {
    private final ExecutorService executor;
    private final int MAX_CONCURRENT_UPLOADS = 5;

    public FileUploadService() {
        // Create fixed thread pool with max concurrent uploads
        this.executor = Executors.newFixedThreadPool(MAX_CONCURRENT_UPLOADS);
    }

    public Future<UploadResult> uploadFile(Path filePath, String destination) {
        // Submit each upload as a callable task
        return executor.submit(() -> {
            // Simulate file upload processing
            long fileSize = simulateFileProcessing(filePath);
            return new UploadResult(filePath.getFileName().toString(), 
                                 destination, 
                                 fileSize, 
                                 true);
        });
    }

    public List<Future<UploadResult>> uploadMultipleFiles(List<Path> files, String destination) {
        // Submit all files for concurrent processing
        return files.stream()
                   .map(file -> uploadFile(file, destination))
                   .toList();
    }

    public void shutdown() {
        // Graceful shutdown
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private long simulateFileProcessing(Path filePath) throws InterruptedException {
        // Simulate variable processing time (100-2000ms)
        long processingTime = 100 + (long)(Math.random() * 1900);
        Thread.sleep(processingTime);
        return filePath.toFile().length(); // Return simulated file size
    }

    public static class UploadResult {
        private final String filename;
        private final String destination;
        private final long size;
        private final boolean success;

        public UploadResult(String filename, String destination, long size, boolean success) {
            this.filename = filename;
            this.destination = destination;
            this.size = size;
            this.success = success;
        }

        @Override
        public String toString() {
            return String.format("%s -> %s (%d bytes) %s",
                filename, destination, size, success ? "✓" : "✗");
        }
    }
}