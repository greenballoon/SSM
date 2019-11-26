package com.shop.web;

import com.shop.domain.Product;
import com.shop.domain.QueryVo;
import com.shop.exception.MyException;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/list",method =RequestMethod.GET) //method 允许get请求方式，默认为get请求
    public ModelAndView getList() throws MyException {
     /*   if (true)
        {
            throw new MyException("找不到商品hhh");
        }
        int i = 1/0;*/
        List<Product> list = productService.getProductList();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("productList");//逻辑视图(假的，需要配置才能找到) 物理视图(\WEB-INF\jsp\productList.jsp)

        //addObject(String key,Object Value) 用法和request域一样
        mv.addObject("productList",list);

        return mv;
    }
    //查看详情页
   /* @RequestMapping(value = "/itemEdit")
    public ModelAndView getProductById(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String id = request.getParameter("id");
        Product product = productService.getProductById(Integer.parseInt(id));

        ModelAndView mv = new ModelAndView();
        mv.addObject("item",product);
        mv.setViewName("productItem");
        return mv;
    }*/

    /*@RequestMapping(value = "/itemEdit",params = "id") //params 限定请求参数
    public ModelAndView getProductById(Integer id)
    {
        Product product = productService.getProductById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("item",product);
        mv.setViewName("productItem");
        return mv;
    }*/

    //@PathVariable可以用来映射URL中的占位符到目标方法的参数中
    @RequestMapping(value = "/itemEdit/{id}")
    public ModelAndView getProductById(@PathVariable("id") Integer qw , HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        Product product = productService.getProductById(qw);

        ModelAndView mv = new ModelAndView();

        mv.addObject("item",product);

        mv.setViewName("productItem");
        return mv;
    }

    //修改功能
    /*@RequestMapping(value = "/updateProduct")
    public ModelAndView updateProduct(Product product)
    {
//        product.setCreatetime(new Date());
        productService.update(product);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        return mv;
    }*/
    //修改功能(图片上传)
    @RequestMapping(value = "/updateProduct")
    public ModelAndView updateProduct(MultipartFile pictureFile,Product product) throws IOException {
        if(!pictureFile.isEmpty())
        {
            String filename = pictureFile.getOriginalFilename(); //获取图片的完整路径
            String NewFileName = UUID.randomUUID().toString()+filename.substring(filename.lastIndexOf(".")); //使用随机生成的字符串 + 原图片的扩展名 组成
        pictureFile.transferTo(new File("D:\\images\\"+NewFileName));  //将图片保存在服务器(硬盘)
            //保存在数据库
            product.setPic(NewFileName);
        }
        productService.update(product);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        return mv;
    }


    //多个对象查询(这里只看是否能接收到参数)
    @RequestMapping(value = "/query")
    public  ModelAndView query(QueryVo vo)
    {
        System.out.println(vo);
        return null;
    }

    //批量删除
    @RequestMapping(value = "/deleteAll")
    public ModelAndView deleteAll(QueryVo vo)
    {
        System.out.println(vo);
        return null;
    }

    //批量修改
    @RequestMapping(value = "/updateAll")
    public ModelAndView updateAll(QueryVo vo)
    {
        System.out.println(vo);
        return null;
    }

    /**
     * @RequestBody 帮我们把json数据转换成Java实体对象
     * (json数据格式和Java实体之间进行转换)
     * 注：ajax 中json的key必须要和java实体中字段一样
     *
     * @param product
     * @return
     */
    /*@RequestMapping(value = "/sendjson")
    public ModelAndView sendjson(@RequestBody Product product)
    {
        System.out.println(product);
        return null;

    }*/
    //把Java实体对象转换成json数据
    @RequestMapping(value = "/sendjson")
    @ResponseBody
    public Product sendjson(@RequestBody Product product)
    {
        System.out.println(product);
        return product;
    }

    //Controller返回值
    /**
     * 返回void
     * 保存数据：Model
     */
    @RequestMapping(value = "/test1")
    public void test1(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        model.addAttribute("name","cw");
        //转发
        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request,response);
        //重定向
        //        response.sendRedirect("list");
    }

    /**
     * 返回String
     * @param model
     * @return
     */
    //转发到productList.jsp
    @RequestMapping(value = "/test2")
    public String test2(Model model) {
        model.addAttribute("name","ac");
        return "productList";
    }

    //转发到productList.jsp
    @RequestMapping(value = "/test3")
    public String test3() {
        return "forward:/list";
    }
    //重定向(控制器里的方法)
    @RequestMapping(value = "/test4")
    public String test4() {
        return "redirect:/list";
    }

    /**
     * 拦截器测试
     */
    @RequestMapping("/hello")
    public String Hello() {
        System.out.println("Hello!");
        return "success";
    }



}
