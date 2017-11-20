package com.atgeretg.util.swing;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class SwingUtils {

	/**
	 * 等比缩放图片
	 * 
	 * @param path
	 *            图片路径
	 * @param width
	 *            压缩成多宽
	 * @param height
	 *            压缩成多高
	 * @return 有图片的ImageIcon对象
	 */
	public static ImageIcon scaledImage(String path, int width, int height) {
		ImageIcon imageWelcomeUse = new ImageIcon(path);
		Image image = imageWelcomeUse.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
		imageWelcomeUse.setImage(image);
		return imageWelcomeUse;
	}

	/**
	 * 获取选择文件的路径，用于选择文件，上传文件
	 * 
	 * @return 选择的文件绝对路径
	 */
	public static String getChooseFile() {
		JFileChooser jChooser = new JFileChooser();
		// 设置默认的打开目录,如果不设的话按照window的默认目录(我的文档)
		jChooser.setCurrentDirectory(null);
		// 设置打开文件类型,此处设置成只能选择文件夹，不能选择文件
		// jChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能打开文件夹
		// 打开一个对话框
		int index = jChooser.showDialog(null, "选择文件");
		if (index == JFileChooser.APPROVE_OPTION) {
			// 把获取到的文件的绝对路径显示在文本编辑框中
			return jChooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

	/**
	 * 获取选择的路径，用于选择文件夹，保存文件
	 * 
	 * @return 选择的文件夹绝对路径
	 */
	public static String getChoosePath() {
		JFileChooser jChooser = new JFileChooser();
		// 设置默认的打开目录,如果不设的话按照window的默认目录(我的文档)
		jChooser.setCurrentDirectory(null);
		// 设置打开文件类型,此处设置成只能选择文件夹，不能选择文件
		jChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 只能打开文件夹
		// 打开一个对话框
		int index = jChooser.showDialog(null, "选择目录");
		if (index == JFileChooser.APPROVE_OPTION) {
			// 把获取到的文件的绝对路径显示在文本编辑框中
			return jChooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

	/**
	 * 设置JTextField只能输入数字和点
	 * 
	 * @param textField
	 */
	public static void setInputNumOrPointTextField(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() { // 限制只能输入数字和点
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || keyChar == KeyEvent.VK_PERIOD) {

				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
	}

	/**
	 * 设置JTextField只能输入数字
	 * 
	 * @param textField
	 */
	public static void setInputNum4TextField(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() { // 限制只能输入数字
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {

				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
	}

}
