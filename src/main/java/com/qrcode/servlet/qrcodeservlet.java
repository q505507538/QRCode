package com.qrcode.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String content = request.getParameter("content");
		String size = request.getParameter("size");
		if (!"".equals(content) && content != null) {
			System.out.println("qrcode->" + content);
			if (!"".equals(size) && size != null) {
				System.out.println("size->" + size);
				try {
					OutputStream os = response.getOutputStream();
					QRCodeWriter writer = new QRCodeWriter() ;
					BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, Integer.parseInt(size), Integer.parseInt(size));
					MatrixToImageWriter.writeToStream(matrix, "png", os);
					os.flush();
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					OutputStream os = response.getOutputStream();
					QRCodeWriter writer = new QRCodeWriter() ;
					BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200);
					MatrixToImageWriter.writeToStream(matrix, "png", os);
					os.flush();
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
