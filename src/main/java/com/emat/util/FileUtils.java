package com.emat.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

import org.apache.commons.codec.binary.Base64;

/**
 * 文件相关的操作
 */

public class FileUtils {

    private FileUtils() {
    }

    /**
     * 将计量单位字节转换为相应单位
     * 
     * @param size
     * @return
     */
    public static String getFileSize(String fileSize) {
        String temp = "";
        DecimalFormat df = new DecimalFormat("0.00");
        double dbFileSize = Double.parseDouble(fileSize);
        if (dbFileSize >= 1024) {
            if (dbFileSize >= 1048576) {
                if (dbFileSize >= 1073741824) {
                    temp = df.format(dbFileSize / 1024 / 1024 / 1024) + " GB";
                } else {
                    temp = df.format(dbFileSize / 1024 / 1024) + " MB";
                }
            } else {
                temp = df.format(dbFileSize / 1024) + " KB";
            }
        } else {
            temp = df.format(dbFileSize / 1024) + " KB";
        }
        return temp;
    }

    public static void createFile(InputStream in, String filePath) {
        if (in == null) {
            throw new RuntimeException("create file error: inputstream is null");
        }
        int potPos = filePath.lastIndexOf('/') + 1;
        String folderPath = filePath.substring(0, potPos);
        createFolder(folderPath);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            byte[] by = new byte[1024];
            int c;
            while ((c = in.read(by)) != -1) {
                outputStream.write(by, 0, c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 是否是允许上传文件
     * 
     * @param ex
     * @return
     */
    public static boolean isAllowUp(String logoFileName) {
        logoFileName = logoFileName.toLowerCase();
        String allowTYpe = "gif,jpg,jpeg,bmp,swf,png,rar,doc,docx,xls,xlsx,pdf,zip,ico,txt,mp4";
        if (!logoFileName.trim().equals("") && logoFileName.length() > 0) {
            String ex = logoFileName.substring(logoFileName.lastIndexOf(".") + 1, logoFileName.length());
            return allowTYpe.toUpperCase().indexOf(ex.toUpperCase()) >= 0;
        } else {
            return false;
        }
    }

    /**
     * 是否是允许上传的图片
     * 
     * @param imgFileName
     * @return
     */
    public static boolean isAllowUpImg(String imgFileName) {
        imgFileName = imgFileName.toLowerCase();
        String allowTYpe = "gif,jpg,bmp,png,jpeg";
        if (!imgFileName.trim().equals("") && imgFileName.length() > 0) {
            String ex = imgFileName.substring(imgFileName.lastIndexOf(".") + 1, imgFileName.length());
            return allowTYpe.toUpperCase().indexOf(ex.toUpperCase()) >= 0;
        } else {
            return false;
        }
    }

    /**
     * 把内容写入文件
     * 
     * @param filePath
     * @param fileContent
     */
    public static void write(String filePath, String fileContent) {
        try {
            FileOutputStream fo = new FileOutputStream(filePath);
            OutputStreamWriter out = new OutputStreamWriter(fo, "UTF-8");

            out.write(fileContent);
            out.close();
        } catch (IOException ex) {
            System.err.println("Create File Error!");
            ex.printStackTrace();
        }
    }

    /**
     * 读取文件内容 默认是UTF-8编码
     * 
     * @param filePath
     * @return
     */
    public static String read(String filePath, String code) {
        if (code == null || code.equals("")) {
            code = "UTF-8";
        }
        String fileContent = "";
        File file = new File(filePath);
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), code);
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent = fileContent + line + "\n";
            }
            read.close();
            read = null;
            reader.close();
            read = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            fileContent = "";
        }
        return fileContent;
    }

    /**
     * 文件是否存在
     * 
     * @param filepath
     * @return
     */
    public static boolean exist(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }

    /**
     * 获取文件类型
     * 
     * @param file
     * @return type
     */
    public static String getFileName(String file) {
        if (file != null) {
            file = file.replace("\\", "/");
            if (file.contains("/")) {
                file = file.substring(file.lastIndexOf("/") + 1);
            }
            return file;
        }
        return "";
    }

    /**
     * 创建文件夹
     * 
     * @param filePath
     */
    public static void createFolder(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception ex) {
            System.err.println("Make Folder Error:" + ex.getMessage());
        }
    }

    /**
     * 重命名文件、文件夹
     * 
     * @param from
     * @param to
     */
    public static void renameFile(String from, String to) {
        try {
            File file = new File(from);
            if (file.exists()) {
                file.renameTo(new File(to));
            }
        } catch (Exception ex) {
            System.err.println("Rename File/Folder Error:" + ex.getMessage());
        }
    }

    /**
     * 得到文件的扩展名
     * 
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf('.'));
        }
        return "";
    }

    /**
     * 通过File对象创建文件
     * 
     * @param file
     * @param filePath
     */
    public static void createFile(File file, String filePath) {
        int potPos = filePath.lastIndexOf('/') + 1;
        String folderPath = filePath.substring(0, potPos);
        createFolder(folderPath);
        FileOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            fileInputStream = new FileInputStream(file);
            byte[] by = new byte[1024];
            int c;
            while ((c = fileInputStream.read(by)) != -1) {
                outputStream.write(by, 0, c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readFile(String resource) {
        InputStream stream = getResourceAsStream(resource);
        String content = readStreamToString(stream);
        return content;
    }

    public static InputStream getResourceAsStream(String resource) {
        String stripped = resource.startsWith("/") ? resource.substring(1) : resource;
        InputStream stream = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            stream = classLoader.getResourceAsStream(stripped);
        }
        return stream;
    }

    public static String readStreamToString(InputStream stream) {
        String fileContent = "";
        try {
            InputStreamReader read = new InputStreamReader(stream, "utf-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent = fileContent + line + "\n";
            }
            read.close();
            read = null;
            reader.close();
            read = null;
        } catch (Exception ex) {
            fileContent = "";
        }
        return fileContent;
    }

    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param imgStr
     *            base64编码字符串
     * @param imgFile
     *            图片路径-具体到文件
     * @return
     */
    public static boolean GenerateImage(String imgStr, String imgFile) {
        if (imgStr == null) // 图像数据为空
            return false;
        // 对字节数组字符串进行Base64解码并生成图片
        Base64 decoder = new Base64();
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成图片
            OutputStream out = new FileOutputStream(imgFile); // 新生成的图片
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            // 加密
            Base64 encoder = new Base64();
            return encoder.encodeToString(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
