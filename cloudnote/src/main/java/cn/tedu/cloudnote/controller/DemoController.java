package cn.tedu.cloudnote.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/demo")
public class DemoController {
	/**
	 * 图片下载
	 * 
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(produces = "image/png", value = "/png.do")
	@ResponseBody
	public byte[] png(HttpServletResponse res) throws Exception {
		byte[] png = createPng();
		res.setContentLength(png.length);
		res.setContentType("image/png");
		return png;
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

	/**
	 * Excel下载
	 * 
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", value = "/excel.do")
	@ResponseBody
	public byte[] excel(HttpServletResponse res) throws IOException {
		byte[] bytes = createExcel();
		res.addHeader("Content-Disposition", "attachment;filename=\"hello.xls\"");
		return bytes;
	}

	private byte[] createExcel() throws IOException {
		// 利用API创建EXCEL对象
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表
		HSSFSheet sheet = workbook.createSheet();
		// 在表中创建行和列
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		// 在0格子中写入helloWorld
		cell.setCellValue("Hello World");
		// 将EXCEL对象编码BYTE【】
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		// 返回BYTE【】
		byte[] excel = out.toByteArray();
		out.close();
		return excel;
	}

	@RequestMapping("/upload.do")
	@ResponseBody
	public String upload(MultipartFile userfile1) throws IOException {
		// springmvc会自动的解析文件上载流，将上载信息保存到MultipartFile中,注入控制器
		// 前提：导入Apache文件上载包和配置上载解析器
		System.out.println(userfile1);
		// 吸纳时原始文件名
		String name = userfile1.getOriginalFilename();
		System.out.println(name);
		// 获取文件的数据
		byte[] data = userfile1.getBytes();
		InputStream data1 = userfile1.getInputStream();
		// 保存文件
		FileOutputStream out1 = new FileOutputStream("D:\\" + userfile1.getOriginalFilename());
		out1.write(data, 0, data.length);
		FileOutputStream out2 = new FileOutputStream("D:\\" + userfile1.getOriginalFilename() + "1");
		int b;
		while ((b = data1.read()) != -1) {
			out2.write(b);
		}
		out1.close();
		out2.close();
		// 获取文件的类型
		String type = userfile1.getContentType();
		System.out.println(type);
		// 获取表单输入input控件的名字
		String inputname = userfile1.getName();
		System.out.println(inputname);
		// 获取文件大小
		long size = userfile1.getSize();
		System.out.println(size);
		return "OK";
	}
}
