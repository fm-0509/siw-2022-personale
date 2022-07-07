package it.uniroma3.siw;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
public class JSONResponse <T> {

    private static final Integer STATUS_OK = 200;
    private static final Integer STATUS_ERROR = 500;


    private Integer status;
    private String html;
    private List<T> data;

    private JSONResponse(Integer status, String message, List<T> data)
    {
        this.setStatus(status);
        this.setHtml(message);
        this.setData(data);
    }

    public static <T> JSONResponse CreateOKResponse(String html, List<T> data)
    {
        return new JSONResponse(STATUS_OK, html, data);
    }
   public static <T> JSONResponse CreateOKResponse(List<T> data)
   {
       return new JSONResponse(STATUS_OK, "",data);
   }
    public static JSONResponse CreateOKResponse(String html)
    {
        return new JSONResponse(STATUS_OK, html, Collections.EMPTY_LIST);
    }



    public static <T> JSONResponse CreateErrorResponse(String html, List<T> data)
    {
        return new JSONResponse(STATUS_ERROR, html, data);
    }
    public static <T> JSONResponse CreateErrorResponse(List<T> data)
    {
        return new JSONResponse(STATUS_ERROR, "", data);
    }
    public static JSONResponse CreateErrorResponse(String html)
    {
        return new JSONResponse(STATUS_ERROR, html, Collections.EMPTY_LIST);
    }





    private void setStatus(Integer status)
    {
        this.status = status;
    }
    private void setHtml(String html)
    {
        this.html = html;
    }
    private void setData(List<T> data)
    {
        this.data = data;
    }
}
