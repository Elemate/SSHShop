/**
 * 
 */
package com.wq.user.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author youto8023
 * 生成验证码
 */
@SuppressWarnings("serial")
public class CopyOfCheckImgAction extends ActionSupport {
	
	@Override
	public String execute() throws IOException{
		//步骤一，绘制一张图片，相当于模板
		int width = 130;
		int height = 40;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		
		//步骤二，给图片绘制背景颜色 -----通过绘图对象
		Graphics graphics = bufferedImage.getGraphics();	//得到画图对象 ---画笔
		//给画笔设置颜色, 然后填充图片背景
		graphics.setColor(getRandomColor(100, 250));
		//四个参数分别是：前两个起始点坐标，后面是宽和高
		graphics.fillRect(0, 0, width, height);				
		
		//步骤三，绘制边框
		graphics.setColor(Color.WHITE);		//边框颜色
		graphics.drawRect(0, 0, width-1, height-1);	//画一个矩形
		
		//步骤四，四个随机数字
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setFont(new Font("宋体", Font.BOLD, 20));
		
		String words = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Random random = new Random();	//生成随机字符
		StringBuffer sb = new StringBuffer();
		//定义x坐标
		int x = 10;
		for (int i = 0; i < 4; i++) {
			//随即颜色
			graphics2d.setColor(new Color(20+random.nextInt(110), 20+random.nextInt(110), 20+random.nextInt(110)));
			//旋转30度
			int jiaodu = random.nextInt(60) - 30;
			//换算弧度
			double theta = jiaodu*Math.PI/180;
			
			//生成一个随机数字
			int index = random.nextInt(words.length());
			//获得字符code
			char code = words.charAt(index);
			sb.append(code);
			//将code输出到图片上
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(code), x, 30);
			graphics2d.rotate(-theta, x, 20);
			x += 30;
		}
		
		//将生成的字母放入session中
		ServletActionContext.getRequest().getSession().setAttribute("checkCode", sb.toString());
		
		//步骤五， 绘制干扰线
		graphics.setColor(getRandomColor(160, 200));
		int x1,x2,y1,y2;
		for (int i = 0; i < 30; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(10);
			y1 = random.nextInt(height);
			y2 = random.nextInt(10);
			graphics.drawLine(x1, y1, x1+x2, y1+y2);
		}
		//将上面图片输入到浏览器IO
		graphics.dispose(); 	//释放资源
		ImageIO.write(bufferedImage, "JPG", ServletActionContext.getResponse().getOutputStream());
		return NONE;
	}

	/**
	 * @param fc 范围参数1
	 * @param bc 范围参数2
	 * @return
	 */
	private Color getRandomColor(int fc, int bc) {
		// TODO Auto-generated method stub
		if (fc>255) {
			fc = 255;
		}
		if (bc>255) {
			bc = 255;
		}
		int r = fc + new Random().nextInt(bc-fc);
		int g = fc + new Random().nextInt(bc-fc);
		int b = fc + new Random().nextInt(bc-fc);
		return new Color(r, g, b);
	}
}
