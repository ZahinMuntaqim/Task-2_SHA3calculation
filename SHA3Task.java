import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SHA3Task {

    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\Admin\\Downloads\\Compressed\\task2_3"; // Change this to your directory path
        String email = "mdzahin290@gmail.com".toLowerCase();

        try {
            List<String> hashes = new ArrayList<>();

            // Calculate SHA3-256 hash for each file
            File folder = new File(directoryPath);
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    String fileHash = calculateSHA3_256(file);
                    hashes.add(fileHash);
                }
            }

            // Sort hashes
            Collections.sort(hashes);

            // Join sorted hashes without any separator
            StringBuilder concatenatedHashes = new StringBuilder();
            for (String hash : hashes) {
                concatenatedHashes.append(hash);
            }

            // Concatenate the result with your email in lowercase
            String finalString = concatenatedHashes.toString() + email;

            // Calculate SHA3-256 of the resulting string
            String finalHash = calculateSHA3_256(finalString.getBytes());

            // Print the result to submit
            System.out.println("!task2 " + email + " " + finalHash);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String calculateSHA3_256(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        }
        byte[] bytes = digest.digest();
        return bytesToHex(bytes);
    }

    private static String calculateSHA3_256(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        byte[] bytes = digest.digest(input);
        return bytesToHex(bytes);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
