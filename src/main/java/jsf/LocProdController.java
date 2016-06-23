package jsf;

import jpa.LocProd;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import sessionbean.LocProdFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("locProdController")
@SessionScoped
public class LocProdController implements Serializable {

    private LocProd current;
    private DataModel items = null;
    @EJB
    private sessionbean.LocProdFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public LocProdController() {
    }

    public LocProd getSelected() {
        if (current == null) {
            current = new LocProd();
            current.setLocProdPK(new jpa.LocProdPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private LocProdFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (LocProd) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new LocProd();
        current.setLocProdPK(new jpa.LocProdPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getLocProdPK().setIdLoc(current.getLocal().getLocalPK().getIdLoc());
            current.getLocProdPK().setIdCom(current.getLocal().getLocalPK().getIdCom());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LocProdCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (LocProd) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getLocProdPK().setIdLoc(current.getLocal().getLocalPK().getIdLoc());
            current.getLocProdPK().setIdCom(current.getLocal().getLocalPK().getIdCom());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LocProdUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (LocProd) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LocProdDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public LocProd getLocProd(jpa.LocProdPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = LocProd.class)
    public static class LocProdControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LocProdController controller = (LocProdController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "locProdController");
            return controller.getLocProd(getKey(value));
        }

        jpa.LocProdPK getKey(String value) {
            jpa.LocProdPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.LocProdPK();
            key.setIdCom(Integer.parseInt(values[0]));
            key.setIdLoc(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.LocProdPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdCom());
            sb.append(SEPARATOR);
            sb.append(value.getIdLoc());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof LocProd) {
                LocProd o = (LocProd) object;
                return getStringKey(o.getLocProdPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + LocProd.class.getName());
            }
        }

    }

}
