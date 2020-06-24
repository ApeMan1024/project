package com.apeman1024.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

import com.apeman1024.Ichange.IHomeChange;
import com.apeman1024.change.HomeChange;
import com.apeman1024.entity.User;



@WebServlet("/perfect")
public class Perfect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Perfect() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> uRl = getURl(request);
		User user=new User();
		user.setUsername(uRl.get("username"));
		user.setPhone(uRl.get("phone"));
		user.setAddress(uRl.get("address"));
		user.setCard(uRl.get("card"));
		user.setEmail(uRl.get("email"));
		user.setHead(uRl.get("head"));
		user.setPass2(uRl.get("pass2"));
		IHomeChange home = new HomeChange();
		User perfect = home.perfect(user);
		PrintWriter writer = response.getWriter();
		if(perfect==null)writer.print(false);
		else {
			JSONObject jsonObject = new JSONObject(perfect);
			writer.print(jsonObject.toString());
		}
	}

	
	public Map<String, String> getURl(HttpServletRequest request) {
		Map<String, String>map=new HashMap<String, String>();
		map.put("head", "");
		//获取图片文件保存的地址
//		String path=Index.class.getClassLoader().getResource("/").getPath().replace("classes","images");
		String path=Index.class.getClassLoader().getResource("../../images/head").getPath();
		//判断地址下的目录是否存在，不存在，则创建
		File foder=new File(path);
		if(!foder.exists()) {
			foder.mkdirs();
		}
		DiskFileItemFactory factory=new DiskFileItemFactory();//创建工厂
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List<FileItem> list = upload.parseRequest(request);
			FileItem item1=null;
			for (FileItem item :list) {
				if(!item.isFormField()) {
					item1=item;
				}
				else {
					map.put(item.getFieldName(), item.getString("utf-8"));
				}
			}
			if(item1!=null) {
				String name = item1.getName();
				name=name.substring(name.lastIndexOf("\\")+1);
				String suffixName = FilenameUtils.getExtension(name);
				InputStream is=item1.getInputStream();
				String pathname=path+"/"+map.get("username")+"."+suffixName;
				File file= new File(pathname);
				FileOutputStream fos=new FileOutputStream(file);
				byte[] buffer=new byte[1024];
				int len=0;
				while((len=(is.read(buffer)))>-1) {
					fos.write(buffer,0,len);
				}
				fos.close();
				is.close();
				map.put("head",request.getContextPath()+"/images/head/"+map.get("username")+"."+suffixName);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
