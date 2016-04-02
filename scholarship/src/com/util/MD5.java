package com.util;



import java.util.Calendar;

/**
 * MD5的算法在RFC1321 中定义 在RFC 1321中，给出了Test suite用来检验你的实现是否正确： MD5 ("") =
 * d41d8cd98f00b204e9800998ecf8427e MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
 * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72 MD5 ("message digest") =
 * f96b697d7cb7938d525a2f31aaf161d0 MD5 ("abcdefghijklmnopqrstuvwxyz") =
 * c3fcd3d76192e4007dfb496cca67e13b
 * 
 * @creator:jinxinzhang
 * @createDate:2008-11-06
 * @revisor:
 * @reviseDate:
 * 
 * 传入参数：一个字节数组 传出参数：字节数组的 MD5 结果字符串
 */
public class MD5 {
	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 上传图片名称转换为MD5格式
	 * @param imgName 为图片完整文件名称(包含扩展名称)
	 * @return
	 */
	public static String getImgName(String imgName)
	{
		
		if(imgName.length()==0)
		{
			return imgName;
		}
		String tmpName = new Long(Calendar.getInstance().getTimeInMillis()).toString();//以当前时间精确到毫秒数做为唯一文件名
		//String tmpName=imgName.substring(imgName.lastIndexOf("\\")+1,imgName.lastIndexOf("."));
		String tmpType=imgName.substring(imgName.lastIndexOf("."));
		String resultName =MD5.getMD5(tmpName.getBytes());
		resultName+=tmpType;
		//System.out.println(resultName);
		return resultName;
	}
	
	
	public static String getMD5Password(String str){
		if(str.length()==0)
		{
			return str;
		}
		String resultString =MD5.getMD5(str.getBytes());
		return resultString;
	}
	
	
	
	
	public static void main(String args[]){
		String x="x";
		System.out.println(MD5.getMD5Password(x));
	}
}
