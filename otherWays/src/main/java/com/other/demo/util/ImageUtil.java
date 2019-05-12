package com.other.demo.util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * 头像的上传和修改
 */
@Component
public class ImageUtil {
    private static final Logger logger = Logger.getLogger(ImageUtil.class);

    private static  final  String IMAGE_URL = "G:\\bishe\\image\\";
    private static final  String suffix =".jpg";
    public boolean uploadImage(String base64Data,String username) throws Exception {
        System.out.println("==上传图片==");
        System.out.println("==接收到的数据=="+base64Data);


        String data = "";//实体部分数据
        if(base64Data==null||"".equals(base64Data)){
            throw new Exception("base64数据为空");
        }else {
            String [] d = base64Data.split("base64,");//将字符串分成数组
            if(d != null && d.length == 2){
                data = d[1];
            }else {
                throw new Exception("base64数据不合法");
            }
        }
       //图片后缀，用以识别哪种格式数据
      /*  //data:image/jpeg;base64,base64编码的jpeg图片数据
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
            suffix = ".jpg";
        }else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        }else if("data:image/gif;".equalsIgnoreCase(dataPrix)){
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        }else if("data:image/png;".equalsIgnoreCase(dataPrix)){
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else {
            throw new Exception("base64数据不合法");
        }*/
        String tempFileName=username+suffix;
        String imgFilePath = IMAGE_URL + tempFileName;//新生成的图片
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(data);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("上传图片失败");
        }
        return false;
    }

    public void getImage(String usernmae, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        File file = new File(IMAGE_URL + usernmae);
        String image = IMAGE_URL + usernmae+suffix;
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(image);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
