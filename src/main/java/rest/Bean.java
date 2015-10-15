package rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import model.Field;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class Bean implements Serializable {

    private List<Field> fields;

    public Bean() {
        fields = new ArrayList<Field>();
    }

    public List<Field> getFields() {
        return fields;
    }

}