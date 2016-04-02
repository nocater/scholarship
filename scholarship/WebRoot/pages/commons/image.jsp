<%@ page import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" pageEncoding="utf-8"%>
<%@ page import="java.io.OutputStream" %>


<!-- 
 * Description: 生成验证码的jsp			
 * @author 张金昕                         
 * @Version 1.0                            
 * @Created at 2009-08-21                  
 * @Modified by         
 */
 -->
 
<%!
	Color getRandColor(int fc,int bc){
	Random random = new Random();
	if(fc>255) fc=255;
	if(bc>255) bc=255;
	int r=fc+random.nextInt(bc-fc);
	int g=fc+random.nextInt(bc-fc);
	int b=fc+random.nextInt(bc-fc);
	return new Color(r,g,b);
}
%>

<%
	try{
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		int width=60, height=20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		OutputStream os=response.getOutputStream();
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		g.setColor(getRandColor(160,200));
		for (int i=0;i<155;i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x,y,x+xl,y+yl);
		}
		String sRand="";
		StringBuffer buff = new StringBuffer();
		String codeKey = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		char[] arr = codeKey.toCharArray();
		//char[] key = new char[4];
		for (int j=0;j<4;j++){
			String rand="";
			int code = (int)(Math.random() * 62);
			char c = arr[code];
			rand += c;
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(rand,13*j+6,16);
			buff.append(rand);
		}
		sRand = buff.toString();
		
		session.setAttribute("rand",sRand.toUpperCase());
		
		
		g.dispose();
		
		ImageIO.write(image, "JPEG",os);
		os.flush();
		os.close();
		os=null;
		response.flushBuffer();
		out.clear();
		out = pageContext.pushBody();
	}
	catch(IllegalStateException e)
	{
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
%>

