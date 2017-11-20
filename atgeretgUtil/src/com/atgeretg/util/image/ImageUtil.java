package com.atgeretg.util.image;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	private static Image img;  
    private static int width;  
    private static int height;
    private static String path;
    
    /**
     * 默认宽：150 高：150 压缩图片，不可放大
     * @param imageFile
     * @param filePath
     */
    public static void defMinZipImage(File imageFile, String saveFilePath){
    	zipImage(imageFile, 150, 150, saveFilePath);
    }
    
    /**
     * 默认宽：500 高：500 压缩图片，不可放大
     * @param imageFile 图片文件
     * @param filePath 保存的图片的路径
     */
    public static void defMaxZipImage(File imageFile, String saveFilePath){
    	zipImage(imageFile, 500, 500, saveFilePath);
    }
    
    /**
     * 按指定宽高压缩图片，不可放大
     * @param imageFile
     * 			图片文件
     * @param destWidth
     * 			目标宽
     * @param destHight
     * 			目标高
     * @param saveFilePath
     * 			保存的图片的路径
     */
    public static void zipImage(File imageFile,int destWidth, int destHight, String saveFilePath){
		try {
			path = saveFilePath;
			img = ImageIO.read(imageFile);
			height = img.getHeight(null);  // 得到源图长  
			width = img.getWidth(null);    // 得到源图宽  
			if(destHight > height && destWidth > width){
				/*
				 * 不可能将图片放大
				 * */
				writeFile(imageFile);
			}else{
				resizeFix(destWidth, destHight);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}      // 构造Image对象  
    }
    
    
    /**
	 * 图片转成byte字节
	 * @param path
	 * @return
	 */
	public static byte[] image2Byte(String path){
		File image = new File(path);
		byte[] bytes = null;
		if (!image.exists()) {
			System.out.println(path + "文件不存在");
			return bytes;
		}
		InputStream in = null;
		try {
			in = new FileInputStream(image);
			int length = (int) image.length();
			if (length > Integer.MAX_VALUE) {// 当文件的长度超过了int的最大值
				System.out.println(path + "文件太大了");
				return bytes;
			}
			bytes = new byte[length];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && ((numRead = in.read(bytes, offset, bytes.length - offset)) >= 0)) {
				offset += numRead;
			}
			if (offset < bytes.length) {
				System.out.println(path +  "读取时出错，大小不一致");
				return null;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bytes;
	}
    
    
    
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    private static void resizeFix(int w, int h) throws IOException {  
    	int newHeight,newWeith;
        if (width / height > w / h) {
            newHeight = resizeByWidth(w);  
            newWeith = w;
        } else {  
            newWeith = resizeByHeight(h);  
            newHeight = h;
        }  
        resize(newWeith,newHeight);
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 ,返回的是高（h）
     * @param w int 新宽度 
     */  
    private static int resizeByWidth(int w){  
        return (int) (height * w / width);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 ,返回的是宽（w）
     * @param h int 新高度 
     */  
    private static int resizeByHeight(int h){  
        return (int) (width * h / height);  
    }  
    
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    private static void resize(int w, int h) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(path);  
        ImageIO.write(image,  "jpeg" , destFile);
        image.flush();
        
    }  
    
    
 
    
    /**
	 * 保存文件
	 * @param fileName
	 * @param is
	 * @return 文件全路径
	 */
	public static void writeFile(File file) {
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			is = new FileInputStream(file);
			fos = new FileOutputStream(path);
			byte[] readBytes = new byte[1024];// 缓冲大小
			int readed = 0;
			while ((readed = is.read(readBytes)) > 0) {
				fos.write(readBytes, 0, readed);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	
}