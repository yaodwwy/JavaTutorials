/*
package utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

*/
/**
 * Created by Adam on 2017/3/15.
 *//*

public class OssUtils {

    private static String endpoint = ResourcesUtils.getPropertyValue("oss.properties", "endpoint");
    private static String accessKeyId = ResourcesUtils.getPropertyValue("oss.properties", "accessKeyId");
    private static String accessKeySecret = ResourcesUtils.getPropertyValue("oss.properties", "accessKeySecret");
    private static String bucketName = ResourcesUtils.getPropertyValue("oss.properties", "bucketName");

    private static OSSClient client = null;
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());

    */
/**
     * 使用inputStream
     *//*

    public static String putObject(byte[] bytes, String path, String name, String suffix) {
        String key = path + "/" + name + suffix;
        ClientConfiguration conf = new ClientConfiguration();
        conf.setIdleConnectionTime(1000);
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);

        //Claim a upload id firstly
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
        InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
        String uploadId = result.getUploadId();
        //分片上传
        final long partSize = 5 * 1024 * 1024L;   // 5MB
        String tmpDir = System.getProperty("java.io.tmpdir");
        ByteUtils.byte2File(bytes, tmpDir, name + suffix);
        final File sampleFile = new File(tmpDir + "/" + name + suffix);
        sampleFile.deleteOnExit();
        long fileLength = sampleFile.length();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }
        if (partCount > 10000) {
            throw new RuntimeException("Total parts count should not exceed 10000");
        }
        //开始上传
        for (int i = 0; i < partCount; i++) {
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            executorService.execute(new PartUploader(sampleFile, startPos, curPartSize, i + 1, uploadId, key));
        }
        //等待所有上传完成
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //验证所有块上传完成
        if (partETags.size() != partCount) {
            throw new IllegalStateException("Upload multiparts fail due to some parts are not finished yet");
        }

        completeMultipartUpload(uploadId, key);

        OSSObject object = client.getObject(new GetObjectRequest(bucketName, key));
        return object.getKey();
    }

    public static void putObject(InputStream inputStream, String path, String name, String suffix){
        String key = path + "/" + name + suffix;
        ClientConfiguration conf = new ClientConfiguration();
        conf.setIdleConnectionTime(1000);
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        PutObjectResult putObjectResult = client.putObject(bucketName, key, inputStream);
        String eTag = putObjectResult.getETag();
    }

    private static void inputstreamtofile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void completeMultipartUpload(String uploadId, String key) {
        // Make part numbers in ascending order
        Collections.sort(partETags, new Comparator<PartETag>() {

            @Override
            public int compare(PartETag p1, PartETag p2) {
                return p1.getPartNumber() - p2.getPartNumber();
            }
        });

        System.out.println("Completing to upload multiparts\n");
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
        client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    private static class PartUploader implements Runnable {

        private File localFile;
        private long startPos;

        private long partSize;
        private int partNumber;
        private String uploadId;
        private String key;

        public PartUploader(File localFile, long startPos, long partSize, int partNumber, String uploadId, String key) {
            this.localFile = localFile;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
            this.key = key;
        }

        @Override
        @SuppressWarnings("all")
        public void run() {
            InputStream instream = null;
            try {
                instream = new FileInputStream(this.localFile);
                instream.skip(this.startPos);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(key);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);

                UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);
                System.out.println("Part#" + this.partNumber + " done\n");
                synchronized (partETags) {
                    partETags.add(uploadPartResult.getPartETag());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}*/
