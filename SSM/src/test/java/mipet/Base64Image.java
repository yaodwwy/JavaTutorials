package mipet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Image {
    public static void main(String[] args) {
        // 测试从Base64编码转换为图片文件
        String strImg = "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";
        GenerateImage(strImg, "D:\\wangyc.png");
        
        // 测试从图片文件转换为Base64编码
      //  System.out.println(GetImageStr("d:\\wangyc.jpg"));
    }

    public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}