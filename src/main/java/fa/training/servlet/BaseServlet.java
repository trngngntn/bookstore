package fa.training.servlet;

import com.google.gson.*;
import fa.training.dao.BaseDAO;
import fa.training.entity.BaseEntity;
import fa.training.meta.Meta;
import fa.training.utils.ResultFilter;
import fa.training.utils.db.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseServlet<T extends BaseEntity<T>> extends HttpServlet {
    private String baseJspPath;
    private final static String JSP_ROOT = "/jsp/";
    protected String addFormTitle = "Add";
    protected String editFormTitle = "Detail";

    public abstract Class<? extends Meta> getMeta();

    protected abstract ResultFilter[] getResultFilter();

    protected void setBaseJspPath(String path) {
        baseJspPath = path;
    }

    /**
     * Extract ID parameter from HTTP request
     *
     * @param request HTTP request to extract ID
     * @return
     */
    protected Object getId(HttpServletRequest request, Meta meta) {
        String idS = request.getPathInfo();
        if (idS == null) {
            return null;
        } else {
            idS = idS.substring(1);
        }
        if (meta.getType() == String.class) {
            return idS;
        } else {
            int id;
            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                return -1;
            }
            return id;
        }
    }

    /**
     * Extract index parameter from HTTP request
     *
     * @param request HTTP request to extract index
     * @return
     */
    protected int getIndex(HttpServletRequest request) {
        String indexS = request.getParameter("index");
        int index = 1;
        if (indexS == null) return index;
        try {
            index = Integer.parseInt(indexS);
        } catch (NumberFormatException e) {
            return -1;
        }
        return index;
    }

    /**
     * Extract filter parameter from HTTP request
     *
     * @param request HTTP request to extract filter
     * @return
     */
    protected ResultFilter[] getRequestFilter(HttpServletRequest request) throws Exception {
        String[] filter = request.getParameterValues("filter");
        //optional
        if (filter == null) {
            return null;
        }
        //main
        else {
            ResultFilter[] resultFilters = new ResultFilter[filter.length];
            for (int i = 0; i < filter.length; i++) {
                resultFilters[i] = ResultFilter.getResultFilter(filter[i]);
                System.out.println("LABEL: " + resultFilters[i].getLabel());
            }
            return resultFilters;
        }
    }

    protected void setTitle(HttpServletRequest request, String title) {
        request.setAttribute("pageTitle", title);
    }

    protected void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
        if (request.getParameter("raw") != null) { // does not include template
            request.getRequestDispatcher(JSP_ROOT + path).forward(request, response);
        } else if (request.getParameter("json") != null) { // return result as JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Gson gson = new Gson();
            BaseEntity detail = (BaseEntity) request.getAttribute("detail");
            List<BaseEntity> list = (List<BaseEntity>) request.getAttribute("resultList");
            if (detail != null) {
                response.getWriter().print(gson.toJson(detail));
            } else if (list != null) {
                response.getWriter().print(gson.toJson(list));
            }
        } else { // include template
            request.setAttribute("page", path);
            request.getRequestDispatcher(JSP_ROOT + baseJspPath).forward(request, response);
        }
    }

    protected void doAfterForwardToList(HttpServletRequest request) throws Exception {
    }

    protected void doAfterForwardToView(HttpServletRequest request) throws Exception {
    }

    protected void forwardToServlet(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //extract generic class name from meta
            String genericClassName = ((Class<T>) getMeta().getDeclaredMethod("getEntityClass").invoke(null)).getSimpleName();
            final String classNameUncapitalize = genericClassName.substring(0, 1).toLowerCase() + genericClassName.substring(1);

            //create an instance of DAO class
            BaseDAO<T> genericDAOInstance = (BaseDAO<T>) ((Class<?>) getMeta().getDeclaredMethod("getDAOClass")
                    .invoke(null)).getDeclaredConstructor().newInstance();

            //extract id
            Meta keyMeta = ((Meta[]) (getMeta().getDeclaredMethod("values")).invoke(null))[0];
            Object id = getId(request, keyMeta);

            //set attributes for form displaying
            request.setAttribute("formPage", String.format("../%1$sMgr/form-%1$s.jsp", classNameUncapitalize));
            request.setAttribute("actionPath", request.getContextPath() + "/" + classNameUncapitalize);

            if (id == null) { //no id --> view list
                String[] keyword = request.getParameterValues("keyword");
                int index = getIndex(request);

                //get filter for result
                ResultFilter[] requestFilters = getRequestFilter(request);

                List<T> resultList = null;

                if (index != -1) {
                    int maxPage;
                    StringBuilder prm = new StringBuilder();
                    //no keyword -> full list
                    if (keyword == null) {
                        maxPage = genericDAOInstance.getTotalPage();
                        if (index > maxPage) { //out of range

                        } else {
                            resultList = genericDAOInstance.getList(index);
                        }
                    }
                    //have keyword -> search
                    else {
                        if (requestFilters[0] == getResultFilter()[0]) {
                            request.setAttribute("keyword", keyword[0]);
                        }

                        for (int i = 0; i < requestFilters.length; i++) {
                            prm.append(String.format("filter=%s&keyword=%s&", requestFilters[i].getLabel(), keyword[i]));
                        }

                        maxPage = genericDAOInstance.getTotalSearchPage(requestFilters, keyword);
                        if (index > maxPage) { //out of range

                        } else {
                            if (request.getParameter("all") != null) {
                                resultList = genericDAOInstance.searchAll(requestFilters, keyword);
                            } else {
                                resultList = genericDAOInstance.search(requestFilters, keyword, index);
                            }
                        }
                        request.setAttribute("resultFilters", new ArrayList<ResultFilter>(Arrays.asList(requestFilters)));
                        request.setAttribute("keywords", keyword);
                    }

                    //prm.append(String.format("index=%s", index));
                    request.setAttribute("prm", prm.toString());
                    request.setAttribute("filters", getResultFilter());

                    request.setAttribute("resultList", resultList);
                    request.setAttribute("currentPage", index);
                    request.setAttribute("maxPage", maxPage);

                }
                //index bad format
                else {
                    request.setAttribute("requestError", true);
                }
                request.setAttribute("barTitle", addFormTitle);
                String path = String.format("%1$sMgr/list-%1$s.jsp", classNameUncapitalize);
                System.out.println("Forward to: " + path);

                doAfterForwardToList(request);

                forwardToJsp(request, response, path);

            } else if (id.equals(-1)) { //id bad format
                String path = String.format("%1$sMgr/list-%1$s.jsp", classNameUncapitalize);
                System.out.println("Forward to: " + path);
                forwardToJsp(request, response, path);
            } else { //have id --> view detail
                T result = genericDAOInstance.get(id);

                request.setAttribute("detail", result);
                request.setAttribute("barTitle", editFormTitle);
                request.setAttribute("editing", true);

                doAfterForwardToView(request);

                forwardToJsp(request, response, "common/common-form.jsp");
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
            response.getWriter().print("bad_format");
        } catch (DBException e) {
            e.printStackTrace();
            response.getWriter().print("database_error");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("internal_error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class<T> genericClass = (Class<T>) getMeta().getDeclaredMethod("getEntityClass").invoke(null);

            //parse JSON from request body
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            T obj = (T) gson.fromJson(request.getReader(), genericClass);
            if(!obj.check()){
                response.getWriter().print("invalid");
                return;
            }
            obj.normalize();
            //add to database
            BaseDAO<T> genericDAOInstance = (BaseDAO<T>) ((Class<?>) getMeta().getDeclaredMethod("getDAOClass")
                    .invoke(null)).getDeclaredConstructor().newInstance();
            genericDAOInstance.add(obj);
            response.getWriter().print("success");
        } catch (JsonParseException e) {
            e.printStackTrace();
            response.getWriter().print("bad_format");
        } catch (DBException e) {
            e.printStackTrace();
            response.getWriter().print("database_error");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("internal_error");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class<T> genericClass = (Class<T>) getMeta().getDeclaredMethod("getEntityClass").invoke(null);

            //extract meta for key field
            Meta keyMeta = ((Meta[]) (getMeta().getDeclaredMethod("values")).invoke(null))[0];

            //parse JSON from request body
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Object id = getId(request, keyMeta);
            T obj = (T) gson.fromJson(request.getReader(), genericClass);
            obj.set(keyMeta, id);
            if(!obj.check()){
                response.getWriter().print("invalid");
                return;
            }
            obj.normalize();
            //update to database
            BaseDAO<T> genericDAOInstance = (BaseDAO<T>) ((Class<?>) getMeta().getDeclaredMethod("getDAOClass")
                    .invoke(null)).getDeclaredConstructor().newInstance();
            genericDAOInstance.update(obj);
            response.getWriter().print("success");
        } catch (JsonParseException e) {
            e.printStackTrace();
            response.getWriter().print("bad_format");
        } catch (DBException e) {
            e.printStackTrace();
            response.getWriter().print("database_error");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("internal_error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Meta keyMeta = ((Meta[]) (getMeta().getDeclaredMethod("values")).invoke(null))[0];
            Object id = getId(request, keyMeta);
            BaseDAO<T> genericDAOInstance = (BaseDAO<T>) ((Class<?>) getMeta().getDeclaredMethod("getDAOClass")
                    .invoke(null)).getDeclaredConstructor().newInstance();
            genericDAOInstance.delete(id);
            response.getWriter().print("success");
        } catch (DBException e) {
            e.printStackTrace();
            response.getWriter().print("database_error");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("internal_error");
        }
    }
}
