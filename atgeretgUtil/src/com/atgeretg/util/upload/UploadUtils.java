package com.atgeretg.util.upload;


public class UploadUtils {
//	public static final String PRODUCT_PHOTO_MIN = "upload/photo/product/min/";
//	public static final String PRODUCT_PHOTO_MAX = "upload/photo/product/max/";
//	public static final String POSITION_PHOTO_MIN = "upload/photo/position/min/";
//	public static final String POSITION_PHOTO_MAX = "upload/photo/position/max/";
//	public static final String USER_PHOTO_MIN = "upload/photo/user/min/";
//	public static final String USER_PHOTO_max = "upload/photo/user/max/";
//
//	// private static String basePath
//	// =ServletActionContext.getServletContext().getRealPath("/");
//	// private static String productMaxPhotoPath =
//	// ServletActionContext.getServletContext().getRealPath(PRODUCT_PHOTO_MAX);
//	// private static String productMinPhotoPath =
//	// ServletActionContext.getServletContext().getRealPath(PRODUCT_PHOTO_MIN);
//	// private static String positionPhotoMinPhotoPath =
//	// ServletActionContext.getServletContext().getRealPath(POSITION_PHOTO_MIN);
//	// private static String positionPhotoMaxPhotoPath =
//	// ServletActionContext.getServletContext().getRealPath(POSITION_PHOTO_MAX);
//	// private static String userPhotoMaxPhotoPath =
//	// ServletActionContext.getServletContext().getRealPath(USER_PHOTO_MIN);
//	private static final int USER = 3;
//	private static final int POSITION = 2;
//	private static final int PRODUCT = 1;
//
//	/**
//	 * 得到文件保存的相对位置
//	 * 
//	 * @param uploadFile
//	 * @param nameSuffix
//	 *            文件后缀名
//	 * @return
//	 */
//	public static String saveUploadFile(File uploadFile, String nameSuffix) {
//		/*
//		 * 得到upload文件夹的绝对路径 ServletActionContext.getServletContext()
//		 */
//		// StringBuffer sbb = new StringBuffer();
//		// sbb.append(File.separator).append("upload").append(File.separator).append("photo").append(File.separator);
//		String basePath = ServletActionContext.getServletContext().getRealPath("/upload/photo/");
//		// 把日期类型格式化为"/yyyy/MM/dd/"这种形式的字符串
//		// 如果文件夹不存在，就创建文件夹
//		File dir = new File(basePath);
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//		// UUID.randomUUID().toString()能够保证名字的唯一性
//		String name = UUID.randomUUID().toString() + "." + nameSuffix;
//		StringBuffer sb = new StringBuffer();
//		sb.append(basePath).append("/").append(name);
//		String path = sb.toString();
//		File dest = new File(path);
//		// 把文件移动到dest处
//		uploadFile.renameTo(dest);
//		return "upload/photo/" + name;
//	}
//
//	/**
//	 * 图片产品保存
//	 * 
//	 * @param uploadFile
//	 * @param fileFileName
//	 *            文件名
//	 * @return
//	 */
//	public static void saveUploadProductPhoto(File uploadFile, String fileName) {
//		save(PRODUCT, uploadFile, fileName);
//	}
//
//	/**
//	 * 图片位置保存
//	 * 
//	 * @param uploadFile
//	 * @param fileFileName
//	 *            文件名
//	 * @return
//	 */
//	public static void saveUploadPositionPhoto(File uploadFile, String fileName) {
//		save(POSITION, uploadFile, fileName);
//	}
//
//	/**
//	 * 图片用户保存
//	 * 
//	 * @param uploadFile
//	 * @param fileFileName
//	 *            文件名
//	 * @return
//	 */
//	public static void saveUploadUserPhoto(File uploadFile, String fileName) {
//		save(USER, uploadFile, fileName);
//	}
//
//	static private void save(int type, File uploadFile, String fileName) {
//		/*
//		 * 不可能没有的
//		 */
//		// File dirMax = new File(productMaxPhotoPath);
//		// File dirMin = new File(productMinPhotoPath);
//		// if(!dirMax.exists()){
//		// dirMax.mkdirs();
//		// }
//		// if(!dirMin.exists()){
//		// dirMin.mkdirs();
//		// }
//
//		String pathMin = null;
//		String pathMax = null;
//		switch (type) {
//		case USER:
//			pathMin = ServletActionContext.getServletContext().getRealPath(USER_PHOTO_MIN) + "/" + fileName;
//			break;
//		case POSITION:
//			pathMax = ServletActionContext.getServletContext().getRealPath(POSITION_PHOTO_MAX) + "/" + fileName;
//			pathMin = ServletActionContext.getServletContext().getRealPath(POSITION_PHOTO_MIN) + "/" + fileName;
//			break;
//		case PRODUCT:
//			pathMax = ServletActionContext.getServletContext().getRealPath(PRODUCT_PHOTO_MAX) + "/" + fileName;
//			pathMin = ServletActionContext.getServletContext().getRealPath(PRODUCT_PHOTO_MIN) + "/" + fileName;
//
//			break;
//
//		default:
//			break;
//		}
//		ImageUtil.defMinZipImage(uploadFile, pathMin);
//		if (pathMax != null)
//			ImageUtil.defMaxZipImage(uploadFile, pathMax);
//	}
//
//	/**
//	 * 生成一个唯一的后缀名为jpg的图片名
//	 * 
//	 * @param fileFileName
//	 * @return
//	 */
//	public static String createUniqueJpgName() {
//		return UUID.randomUUID().toString() + "." + "jpg";
//		// String[] split = fileFileName.split("\\.");
//		// String nameSuffix = split[split.length - 1];
//		// System.out.println("basePath = " + basePath);
//		// System.out.println("productMaxPhotoPath = " + productMaxPhotoPath);
//		// System.out.println("productMaxPhotoPath = " + productMaxPhotoPath);
//	}
//
//	/**
//	 * 从路径中获取文件名字
//	 * 
//	 * @param path
//	 * @return
//	 */
//	public static String getOldName(String path) {
//		if (path == null) {
//			return null;
//		}
//		String name = path.substring(path.lastIndexOf("/") + 1).trim();
//		if (name == null || "".equals(name)) {
//			return createUniqueJpgName();
//		}
//		return name;
//	}
//
}
