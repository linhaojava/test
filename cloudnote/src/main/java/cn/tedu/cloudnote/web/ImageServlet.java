package cn.tedu.cloudnote.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5952700857874526001L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] png = createPng();
		response.setContentType("image/png");
		response.setContentLength(png.length);
		response.getOutputStream().write(png);
		response.getOutputStream().close();
	}

	public byte[] createPng() throws IOException {
		// 创建图片对象
		BufferedImage img = new BufferedImage(200, 100, BufferedImage.TYPE_3BYTE_BGR);
		img.setRGB(100, 50, 0xffff);
		// 将图片编码为byte
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);

		// 得到所有bytes
		byte[] ary = out.toByteArray();
		out.close();
		return ary;
	}
}
