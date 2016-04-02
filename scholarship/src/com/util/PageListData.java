package com.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 分页对象--请误修改
 * <p>
 * 该类实现了分页查询结果和分页信息的封装。
 * 
 * @author cuiyongliang
 * @version 1.0,2008-10-30
 * @since 1.0
 */
public class PageListData extends ActionSupport
{
    private List dataArray = null;

    private int count = -1;

    private int pageSize = 10;

    private int pageCount = 0;

    private int pageNeeded = 1;

    private int flag = 1;

    private int countFilter = 1;

    public String getFooter()
    {
        StringBuffer str = new StringBuffer("");
        int prev = pageNeeded - 1;
        int next = pageNeeded + 1;

        // 添加操作分页栏的js脚本
        /*str.append("<script> ");
        str.append("function topagesubmit(pagestr){");
        str.append("with (document.forms[0]){");
        str.append("pageNeeded.value=pagestr;");
        str.append("submit();");
        str.append("}");
        str.append("}");
        str.append("</script>");*/

        if (pageNeeded > 1)
        {
            str.append("<input type=submit onclick=' pageNeeded.value=" + 1 + ";' value=") ;
            str.append(getText("page.frist")).append("> ");
            str.append("&nbsp;<input type=submit onclick=' pageNeeded.value=" + prev + ";' value=" );
            str.append(getText("page.last")).append("> ");
        } else
        {
            str.append("<input type=submit disabled='true' value=" );
            str.append(getText("page.frist")).append("> ");
            		
            str.append("&nbsp;<input type=submit disabled='true' value=");
            str.append(getText("page.last")).append("> ");
        }

        if (pageNeeded < pageCount)
        {
            str.append("&nbsp;<input type=submit  onclick=' pageNeeded.value=" + next + ";' value=");
            str.append(getText("page.next")).append("> ");

        } else
        {
            str.append("&nbsp;<input type=submit disabled='true' value=");
            str.append(getText("page.next")).append("> ");
        }

        if (pageCount > 1 && pageNeeded != pageCount)
        {
            str.append("&nbsp;<input type=submit onclick=' pageNeeded.value=" + pageCount + ";' value=");
            str.append(getText("page.end")).append("> ");
        } else
        {
            str.append("&nbsp;<input type=submit disabled='true' value="); 
            str.append(getText("page.end")).append("> ");
        }
        
        str.append("&nbsp;").append(getText("page.total") + pageCount + getText("page.page") + getCount() + getText("page.recode"));
        
        
        str.append("&nbsp;").append(getText("page.showrows")).append("<SELECT size=1 name=pagesize onchange=' pageNeeded.value=1; pageSize.value=this.value;submit();'>");
        
//        if (pageSize == 3)
//            str.append("<OPTION value=3 selected>3</OPTION>");
//        else
//            str.append("<OPTION value=3>3</OPTION>");
        
        if (pageSize == 10)
            str.append("<OPTION value=10 selected>10</OPTION>");
        else
            str.append("<OPTION value=10>10</OPTION>");
        
        if (pageSize == 20)
            str.append("<OPTION value=20 selected>20</OPTION>");
        else
            str.append("<OPTION value=20>20</OPTION>");
        
        if (pageSize == 50)
            str.append("<OPTION value=50 selected>50</OPTION>");
        else
            str.append("<OPTION value=50>50</OPTION>");
        
        if (pageSize == 100)
            str.append("<OPTION value=100 selected>100</OPTION>");
        else
            str.append("<OPTION value=100>100</OPTION>");
        
        str.append("</SELECT>");
        str.append(getText("page.rownum"));

        str.append("&nbsp;").append(getText("page.trunpage")).append("<SELECT size=1 name=Pagelist onchange=' pageNeeded.value=this.value;submit();'>");
        for (int i = 1; i < pageCount + 1; i++)
        {
            if (i == pageNeeded)
            {
                str.append("<OPTION value=" + i + " selected>" + i + "</OPTION>");
            } else
            {
                str.append("<OPTION value=" + i + ">" + i + "</OPTION>");                
            }
        }
        str.append("</SELECT>").append(getText("page.page"));
        
        str.append("<INPUT type=hidden  value=" + pageNeeded + " name='pageNeeded' > ");
        str.append("<INPUT type=hidden  value=" + pageSize + " name='pageSize' > ");
      
        /*str.append("<INPUT type=hidden  name='pageNeeded'> ");
        str.append("<INPUT type=hidden  name='pageSize' > ");*/

        return str.toString();
    }

    public int getFlag()
    {
        return flag;
    }

    public void setFlag(int flag)
    {
        this.flag = flag;
    }

    public int getCountFilter()
    {
        return countFilter;
    }

    public void setCountFilter(int countFilter)
    {
        this.countFilter = countFilter;
    }
    
    public PageListData()
    {
        newDataArray();
    }

    public List<?> getDataArray()
    {
        return dataArray;
    }

    public void setDataArray(List<?> dataArray)
    {
        this.dataArray = dataArray;
    }

    public Object getData(int i)
    {
        return getDataArray().get(i);
    }

    public void newDataArray()
    {
        if (dataArray == null)
        {
            dataArray = new ArrayList();
        }
    }

    public void clearDataArray()
    {
        if (dataArray != null)
        {
            dataArray.clear();
        }
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
    	 if (pageSize != 0)
         {
             pageCount = count / pageSize;
             if (count % pageSize != 0)
             {
                 pageCount++;
             }
         }
        this.count = count;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageNeeded()
    {
        return pageNeeded;
    }

    public void setPageNeeded(int pages)
    {
        this.pageNeeded = pages;
    }    
}