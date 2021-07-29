package fa.training.servlet;

import fa.training.entity.BaseEntity;
import fa.training.enumeration.ResultFilter;
import fa.training.meta.Meta;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class BaseServlet<T extends BaseEntity> extends HttpServlet {
    private String baseJspPath;
    private final static String JSP_ROOT = "/jsp/";

    protected abstract ResultFilter getDefaultResultFilter();

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
    protected ResultFilter[] getResultFilter(HttpServletRequest request) {
        String[] filter = request.getParameterValues("filter");
        if (filter == null) {
            return new ResultFilter[]{getDefaultResultFilter()};
        } else {
            ResultFilter[] resultFilter = new ResultFilter[filter.length];
            for (int i = 0; i < filter.length; i++) {
                resultFilter[i] = ResultFilter.getResultFilter(filter[i]);
            }
            return resultFilter;
        }
    }

    protected void setTitle(HttpServletRequest request, String title) {
        request.setAttribute("pageTitle", title);
    }

    protected void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        if (request.getParameter("raw") != null) { // does not include template
            request.getRequestDispatcher(JSP_ROOT + path).forward(request, response);
        } else if (request.getParameter("json") != null) {
            // return result as JSON
        } else { // include template
            request.setAttribute("page", path);
            request.getRequestDispatcher(JSP_ROOT + baseJspPath).forward(request, response);
        }
    }

    protected void forwardToServlet(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    @SuppressWarnings("unchecked")
    protected void doGetBase(HttpServletRequest request, HttpServletResponse response, Class metaClass) throws Exception {
        System.out.println("Templating");
        Class generic = (Class) metaClass.getDeclaredMethod("getEntityClass").invoke(null);
        Class genericDAO = (Class) metaClass.getDeclaredMethod("getDAOClass").invoke(null);

        Object genericDAOInstance = genericDAO.getDeclaredConstructor().newInstance();
        Object id = getId(request, ((Meta[]) (metaClass.getDeclaredMethod("values")).invoke(null))[0]);

        try {
            if (id == null) { //no id --> view list
                String[] keyword = request.getParameterValues("keyword");
                ResultFilter[] filter = getResultFilter(request);
                int index = getIndex(request);

                List<T> resultList;

                if (index != -1) {
                    Method getTotalPageMethod;
                    try {
                        getTotalPageMethod = genericDAO.getDeclaredMethod("getTotalPage");
                    } catch (NoSuchMethodException e) {
                        getTotalPageMethod = genericDAO.getSuperclass().getDeclaredMethod("getTotalPage");
                    }

                    Integer maxPage = (Integer) getTotalPageMethod.invoke(genericDAOInstance);
                    if (index > maxPage) { //out of range

                    } else {

                        if (keyword == null) { //no keyword -> full list
                            Method getListMethod;
                            try {
                                getListMethod = genericDAO.getDeclaredMethod("getList", int.class);
                            } catch (NoSuchMethodException e) {
                                getListMethod = genericDAO.getSuperclass().getDeclaredMethod("getList", int.class);
                            }
                            resultList = (List<T>) getListMethod.invoke(genericDAOInstance, index);
                        } else { //have keyword -> search
                            Method searchMethod;
                            try {
                                searchMethod = genericDAO.getDeclaredMethod("search", ResultFilter[].class, String[].class, int.class);
                            } catch (NoSuchMethodException e) {
                                searchMethod = genericDAO.getSuperclass().getDeclaredMethod("search", ResultFilter[].class, String[].class, int.class);
                            }
                            resultList = (List<T>) searchMethod.invoke(genericDAOInstance, filter, keyword, index);
                        }

                        request.setAttribute("resultList", resultList);
                        request.setAttribute("currentPage", index);
                        request.setAttribute("maxPage", maxPage);
                    }

                } else { //index bad format
                    request.setAttribute("requestError", true);
                }
                String path = String.format("%1$sMgr/list-%1$s.jsp",
                        generic.getSimpleName().substring(0, 1).toLowerCase() + generic.getSimpleName().substring(1));
                System.out.println("Forward to: " + path);
                forwardToJsp(request, response, path);

            } else if (id.equals(-1)) { //id bad format
                String path = String.format("%1$sMgr/list-%1$s.jsp",
                        generic.getSimpleName().substring(0, 1).toLowerCase() + generic.getSimpleName().substring(1));
                System.out.println("Forward to: " + path);
                forwardToJsp(request, response, path);
            } else { //have id --> view detail
                Method getMethod;
                try {
                    getMethod = genericDAO.getDeclaredMethod("get", Object.class);
                } catch (NoSuchMethodException e) {
                    getMethod = genericDAO.getSuperclass().getDeclaredMethod("get", Object.class);
                }

                Object result = getMethod.invoke(genericDAOInstance, id);

                request.setAttribute("detail", result);

                String path = String.format("%1$sMgr/view-%1$s.jsp",
                        generic.getSimpleName().substring(0, 1).toLowerCase() + generic.getSimpleName().substring(1));
                System.out.println("Forward to: " + path);
                forwardToJsp(request, response, path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
