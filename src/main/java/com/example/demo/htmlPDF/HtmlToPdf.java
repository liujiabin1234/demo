package com.example.demo.htmlPDF;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 利用HTML代码片段生成PDF
 */
public class HtmlToPdf {
	private final static String DEST = "C:\\80afa41a3bfc474cbf1ac8b5bc1d.pdf";	//生成pdf的路径
	private final static String SRC = "D:\\microger\\MicroFinance\\upload_files\\Contract\\3b33a68bbf52417aac48687937352464.html";	//html文件路径
	public static final String FONT = "/font/NotoSansCJKsc-Regular.otf";	//本地字体路径

	public static void main(String[] args) {
		try {
			HtmlToPdf html = new HtmlToPdf();
			html.tomPdf(SRC, DEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * HTML转换成pdf
	 * @param html html文件路径
	 * @param DEST 生成pdf的路径
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void tomPdf(String html, String DEST) throws Exception {
		ConverterProperties props = new ConverterProperties();
		DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, false, false);
		defaultFontProvider.addFont(FONT);
		props.setFontProvider(defaultFontProvider);
		PdfWriter writer = new PdfWriter(DEST);
		PdfDocument pdf = new PdfDocument(writer);
		pdf.setDefaultPageSize(new PageSize(595.0F, 842.0F));
		Document document = HtmlConverter.convertToDocument(new FileInputStream(html), pdf, props);
		document.close();
		pdf.close();

	}

		// 将所有内容在一个页面显示
//		EndPosition endPosition = new EndPosition();
//		LineSeparator separator = new LineSeparator(endPosition);
//		document.add(separator);
//		document.getRenderer().close();
//		PdfPage page = pdf.getPage(1);
//		float y = endPosition.getY() - 36;
//		page.setMediaBox(new Rectangle(0, y, 595, 14400 - y));




	static class EndPosition implements ILineDrawer {

		/** A Y-position. */
		protected float y;

		/**
		 * Gets the Y-position.
		 *
		 * @return the Y-position
		 */
		public float getY() {
			return y;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#draw(com.itextpdf.kernel.pdf.
		 * canvas.PdfCanvas, com.itextpdf.kernel.geom.Rectangle)
		 */
		@Override
		public void draw(PdfCanvas pdfCanvas, Rectangle rect) {
			this.y = rect.getY();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#getColor()
		 */
		@Override
		public Color getColor() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#getLineWidth()
		 */
		@Override
		public float getLineWidth() {
			return 0;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#setColor(com.itextpdf.kernel.
		 * color.Color)
		 */
		@Override
		public void setColor(Color color) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#setLineWidth(float)
		 */
		@Override
		public void setLineWidth(float lineWidth) {
		}

	}
}
